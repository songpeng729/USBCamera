package centipede.livescan;

public class MosaicNative{
	/*
   public static native void start();
   public static native int doMosaic(byte[] bytes);
   public static native void stop();
   */

   public static native void FastInit(int venderId, int productId, int fileDescriptor, int busNum, int devAddr, String usbfs);
   public static native boolean FastInitIsFinger();
   public static native void FastReadSendorImg(int seq);
   public static native int FastMosaicNew(int seq,byte[] buffer);
   public static native void FastEnd();

  /**
   * 读取图像操作
   * 1. ReadInit 初始化操作,delay延迟时间,可以传入800
   * 2. ReadImg 读取图像,传入的数组大小应该为640*640的大小,此方法不断的读取图像数据进行显示
   * 3. ReadEnd 读取终止
   */
   public static native int ReadInit(int venderId, int productId, int fileDescriptor, int busNum, int devAddr, String usbfs);
   public static native void ReadImg(byte[] buffer);
   public static native void ReadEnd();

   //以下四个函数是采集图像和拼接的函数，其中putImgInBuffer() 和 getImgfromBuffer 调用的顺序是一先一后，如果是多线程则要做同步
//采集图像函数
   /*
public static native int sensorReadimg();
//将采集到的图像放在特定的Buffer
public static native int putImgInBuffer();
//从特定的Buffer取出图像
public static native int getImgfromBuffer();
//对从特定的Buffer取出的图像进行拼接
public static native int doMosaicNew(byte[] buffer);
*/

   //3 拼接接口是否提供判断图像为指纹的函数
   public static native int IsSupportIdentifyFinger();
   //4 拼接接口是否提供判断图像质量的函数
   public static native int IsSupportImageQuality();
   //5 拼接接口是否提供判断指纹质量的函数
   public static native int IsSupportFingerQuality();
   //6 接口是否提供拼接指纹的图像增强功能
   public static native int IsSupportImageEnhance();
   //7 判断是否支持滚动采集函数
   public static native int IsSupportRollCap();
   //8 选择拼接方式的函数
   public static native int SetRollMode(int nRollMode);
   //12 判断图像质量
   public static native int ImageQuality(byte[] pDataBuf, int nWidth, int nHeight);
   //13 判断指纹质量
   public static native int FingerQuality(byte[] pDataBuf, int nWidth, int nHeight);
   //14 对图像进行增强
   public static native int ImageEnhance(byte[] pSrcImg, int nWidth, int nHeight, byte[] pTargetImg);
   //15 判断图像是否为指纹
   public static native int IsFinger(byte[] pDataBuf, int nWidth, int nHeight);
   //16 取得拼接接口错误信息 pszErrorInfo 256 个字节
   public static native int GetErrorInfo(int nErrorNo, byte[] pszErrorInfo);
   //17 取得接口的版本
   public static native int GetVersion();
   //18 获得拼接接口的说明 pszDesc 1024 个字节
   public static native int GetDesc(byte[] pszDesc);
   static {
//      System.loadLibrary("usbsensor");
    System.loadLibrary("jpeg-turbo1500");
    System.loadLibrary("usb100");
    System.loadLibrary("uvc");
    System.loadLibrary("livescan");
   }
}
