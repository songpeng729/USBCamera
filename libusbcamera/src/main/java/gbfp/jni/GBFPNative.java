package gbfp.jni;

public class GBFPNative{

  /*输出：code 为5个字节长度
  返回：1正常
  功能：版本信息获取 
  */
  public static native int FPGetVersion(byte[] code);

  /*返回：GBFP特征大小
   功能： 得到GBFP特征大小
  */
  public static native int FPGetFeaSize();

  /*返回：1正常
   功能： 初始化操作，必须首先调用这个函数
  */
  public static native int FPBegin();


  /*  输入：pFingerImgBuf 图像数据
         iImageHeight 图像高
         iImageWidth 图像宽
    输出：
         pFeatureData 特征数组，分配空间大小建议2000字节以上
         pFeaLen 特征实际大小，数组长度为1即可
    返回：1正常，<0 出错
    功能：UCAS算法指纹图像特征提取，特征格式GBFP，对应旧接口FPFeatureExtract
    */
  public static native int FPFeatureExtractGBFPUCAS(byte[] pFingerImgBuf, int iImageHeight, int iImageWidth, byte[] pFeatureData, int[] pFeaLen);

  /*  输入：pFingerImgBuf 图像数据
         iImageHeight 图像高
         iImageWidth 图像宽
    输出：
         pFeatureData 特征数组，大小建议分配空间2000字节以上
         pFeaLen 特征实际大小，数组长度为1即可
    返回：1正常，<0 出错
    功能：GFS算法指纹图像特征提取，特征格式GBFP，对应旧接口FPFeatureExtractGFS
    */
  public static native int FPFeatureExtractGBFPGFS(byte[] pFingerImgBuf, int iImageHeight, int iImageWidth, byte[] pFeatureData, int[] pFeaLen);


  /*  输入：cFingerPos 指位 1-20的整数
              cIsPlain 0,不是平面指位, 1, 平面指纹
              isNewTT 是否提取TT新特征，0提取704个字节带文件头的gafis老特征，1提取4024字节的TT新特征
              isExtractBinImg 是否提取纹线特征，0不提取纹线特征，1提取纹线特征
              pFingerImgBuf 图像数据
               iImageHeight 图像高
               iImageWidth 图像宽
      输出：
               pBinImg 纹线数据，如果isExtractBinImg为1，则分配空间大小不少于64+iImageWidth*iImageHeight/10，否则这个参数在算法内部会被忽略，可以给一个任意长度的数组
               pFeatureData 指纹细节点特征，，如果isNewTT为1，分配空间不少于4024字节，否则分配空间不少于704字节
               pBinImgLen 纹线特征pBinImg的实际长度，数组长度为1即可，如果isExtractBinImg为0，则这个参数在算法内部会被忽略
               pFeaLen 指纹细节点特征pFeatureData的实际长度，数组长度为1即可

      返回：1正常，<0 出错
      功能：GAFIS指纹图像特征提取,特征格式GAFIS系统兼容的格式，相当于旧接口FPFeatureExtractGAFISOrg和FPFeatureExtractGAFISNewTT的合并版
      */
  public static native int FPFeatureExtractGAFIS(byte cFingerPos, byte cIsPlain, byte isNewTT, byte isExtractBinImg, byte[] pFingerImgBuf, int iImageHeight,
                                                 int iImageWidth, byte[] pBinImg,byte[] pFeatureData, int[] pBinImgLen, int[] pFeaLen);


  /*  输入：pCprdata 纹线数据
      输出：
            pBimg 纹线细化图像，分配空间大小不少于图像长乘以宽
            pMapImg 分割图像，分配空间大小不少于图像长乘以宽
            pImgwidth 解压出来图像宽，数组长度为1即可
            pImgheight 解压出来图像高，数组长度为1即可
            pImgdpi 图像dpi，数组长度为1即可

      返回：1正常，<0 出错
      功能：纹线数据解压，这个接口用来做算法测试
      */
  public static native int FPBinImgDecode(byte[] pCprdata, byte[] pBimg, byte[] pMapImg, int[] pImgwidth, int[] pImgheight, int[] pImgdpi);


  public static native int FPGetGBFPMntNum(int len, byte[] pFea);
  public static native int FPGetGAFISMntNum(int len, byte[] pFea);

  /*  输入：
               width 图像宽
               height 图像高
      返回：>0 长,宽为width, height的图像数据构造成bmp图像后的大小，<0 出错
      功能：计算长,宽为width, height的图像数据构造成8位bmp图像后的大小
      */
  public static native int GetBmpLen(int width,int height);

  /*  输入：pImgBuf 图像数据
               width 图像宽
               height 图像高

      输出：
               pBmpData, 构造后的bmp图像,如果不知道数组该取多大，可以先调用GetBmpLen得到应该取的数组长度
      返回：>0构造后的bmp图像大小，-1 pBmpData数组空间不够
      功能：根据灰度数据和长,宽构造8位bmp图像，根据返回的图像大小len只要将pBmpData前len个字节写入.bmp文件就能看到图像了

      */
  public static native int CreatBmp(byte[] pImgBuf,int width,int height,byte[] pBmpData);

  /*  输入：pBmpData bmp数据
      输出：
       pWidth 图像宽
           pHeight 图像高
      返回：1正常，-1输入不是8位也不是24位bmp图
      功能：得到bmp位图的宽和高

      */
  public static native int GetBmpWH(byte[] pBmpData,int[] pWidth,int[] pHeight);

  /*  输入：pBmpData bmp数据
      输出：
       pWidth 图像宽
           pHeight 图像高
           pImgBuf, 解析后的raw图
      返回：1正常，-1输入不是8位也不是24位bmp图，-2数组pImgBuf空间不够
      功能：解析8位或者24位bmp位图，得到图像宽高和raw图，如果是24位bmp则去掉颜色转化为灰度raw图输出到pImgBuf

      */
  public static native int ReadBmp(byte[] pBmpData,int[] pWidth,int[] pHeight,byte[] pImgBuf);

  /* 输入：	iFeatureLength1 特征1数据长度
          pFeatureData1 特征1数据
          iFeatureLength2 特征2数据长度
          pFeatureData2 特征2数据
     输出：pfSimilarity 两个特征相似度值，范围(0-1)
     返回：1正常，<0出错
      功能：对2个GBFP格式指纹特征数据进行比对，对应旧接口FPFeatureMatch
    */
  public static native int FPFeatureMatchGBFP(int iFeatureLength1, byte[] pFeatureData1, int iFeatureLength2, byte[] pFeatureData2, float[] pfSimilarity);


  /* 输入：
          pFeatureData1 GAFIS特征1
          pFeatureData2 GAFIS特征2
     输出：pfSimilarity 两个特征相似度值，范围(0-1)
     返回：1正常，<0出错
      功能：对2个GAFIS格式指纹特征数据进行比对
    */
  public static native int FPFeatureMatchGAFIS(byte[]  pFeatureData1, byte[] pFeatureData2, float[]  pfSimilarity);

  /* 输入：
            pFeatureData1 GAFIS格式特征1数据
            pFeatureData2 GAFIS格式征2数据
  输出：
            pfSimilarity 相似度值范围(0-1)
        返回：>0正常，<0 出错
        功能：对2个GAFIS新特征进行其中LP特征的比对，用来验证新特征是不是有效，这个接口用来做算法测试
      */
  public static native int FPMatchTTLP(byte[] pFeatureData1, byte[] pFeatureData2, float[] pfSimilarity);


  /* 输入：	pFingerImgBuf 图像数据
                  width 图像宽
                  height 图像高
                  cutWidth切割后图像宽，如果切割成身份证指纹则为256
                  cutHeight切割后图像高，如果切割成身份证指纹则为360
     输出：
                  pOutImg 切割后的指纹图像，空间大小为cutWidth×cutHeight
     返回：1成功，<0,表示出错
     功能：将输入图像pFingerImgBuf切割为cutWidth×cutHeight大小的图像，存储在pOutImg
      */
  public static native int  FPCutIDImg(byte[] pFingerImgBuf, int width, int height, int cutWidth, int cutHeight, byte[] pOutImg);

  /* 输入：	pFingerImgBuf 图像数据
              width 图像宽
              height 图像高
              cutWidth切割后图像宽，如果切割成身份证指纹则为256
              cutHeight切割后图像高，如果切割成身份证指纹则为360
     输出：
              pOutImg 输出图，大小为width×height
          返回：1成功，<0,表示出错
          功能：做切割等各种测试用的，这个接口用来做算法测试
      */
  public static native int  FPTestCutIDImg(byte[] pFingerImgBuf, int width, int height, int cutWidth, int cutHeight, byte[] pOutImg);

  /* 输入：	pFingerImgBuf 图像数据
          width 图像宽
          height 图像高
          pFeatureData 身份证特征数据
输出：
          pfSimilarity 存储匹配分数，数组长度为1，分数范围 0-1
      返回：如果匹配分数大于0.25则返回1，表示是同一指纹,否则返回0表示是不同指纹,返回-8表示质量差，如果返回其他小于0的值,表示出错
      功能：对图像和身份证特征数据进行比对，返回匹配结果，算法内部设定的阈值为0.25，这个接口也可以根据需要不考虑返回值，根据pfSimilarity的值自己设定阈值来判断是否同一指纹
    */
  public static native int  FPImageMatchID(byte[] pFingerImgBuf, int width, int height, byte[] pFeatureData, float[] pfSimilarity);

  /*输入：
          pFingerImgBuf 图像数据
          iImageHeight 图像高
          iImageWidth 图像宽
   返回：指纹图像质量分数 0-100
   功能：计算指纹图像质量分数
    */
  public static native int FPGetFptQuality(byte[] pFingerImgBuf, int iImageHeight, int iImageWidth);


  /*输入：
          pFingerImgData 图像数据
          nRow 图像高
          nCol 图像宽
          pos 指位 1-20
          bIsPlain 是否平面指纹， 1--是，0--否
          nResolution dpi，置为500即可
          nCompressRatio 压缩倍数
  输出：
          pCompressedImgBuf 压缩数据，大小至少为nRow * nCol * 1.2/nCompressRatio 个字节
          pnBufLength 压缩数据长度,长度为1
   返回：1正常，<0 错误
   功能：对图像进行wsq压缩
    */
  public static native int FPCompress(byte[] pFingerImgData, int nRow,int nCol, byte pos, byte bIsPlain, int nResolution,int nCompressRatio,byte[] pCompressedImgBuf,int[] pnBufLength);

  /*输入：
          pCompressedImg 压缩数据
          nCompressedImgLength 压缩数据长度
  输出：
          pFingerImg 图像数据
          pnRow 图像高，数组长度为1
          pnCol 图像宽，数组长度为1
   返回：1正常，<0 错误
   功能：对wsq数据进行解压缩
    */
  public static native int FPDecompress(byte[] pCompressedImg,int nCompressedImgLength, byte[] pFingerImg,int[] pnRow, int[] pnCol);


  /*输入：
          pImg 图像
          pos 第几个指纹 取值范围 0-19 对应指位 1-20
          bIsPlain 是否平面指纹 1是，0否
          isFirstUse, 是否第一次添加，1是，0否，如果是第一次添加，程序会将pTenCardFea的数据都清0再添加
          height 图像高
          width 图像宽
  输出：
          pTenCardFea 十指信息数据，建议长度至少为860000个字节
   返回：>0 十指信息数据的长度，<0 错误
   功能：将pImg存入十指信息卡结构中
    */
  public static native int FPAddTenFptCard(byte[] pImg,byte pos, byte bIsPlain, byte isFirstUse, int height,int width,byte[] pTenCardFea);

  /*输入：
          pTenCardFea 十指信息数据
          pos 第几个指纹 取值范围 0-19 对应指位 1-20
  输出：
          pImg 图像
          pHeight 图像高
          pWidth 图像宽
   返回：1正常，<0 错误
   功能：从十指信息数据中取出对应指位的图像
    */
  public static native int FPGetTenFptCardImg(byte[] pTenCardFea,int pos,byte[] pImg,int[] pHeight,int[] pWidth);

  /*输入：
          pTenCardFea 十指信息数据
          pos 第几个指纹 取值范围 0-19 对应指位 1-20
  输出：
          pFea 特征数据，建议大小为1800个字节以上
          pLen 特征数据长度
   返回：1正常，<0 错误
   功能：从十指信息数据中取出对应指位的特征
    */
  public static native int FPGetTenFptCardFea(byte[] pTenCardFea,int pos,byte[] pFea,int[] pLen);

  /*输入：
          pTenCard1 十指信息数据
          pTenCard2 十指信息数据

   返回：>=0 十指信息比对分数(0-1)，<0 错误
   功能：比对十指信息
    */
  public static native float FPMatchTenFptCard(byte[] pTenCard1, byte[] pTenCard2);

  /*返回：1正常
   功能： 结束操作，这个函数是最后结束的时候调用的
  */
  public static native int FPEnd();


  static{
    System.loadLibrary("GBFP");
  }
}
