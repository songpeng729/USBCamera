package com.finger.usbcamera.util;

import android.util.Log;

import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.vo.FingerData;

import gbfp.jni.GBFPNative;

/**
 * 特征提取
 */
public class FeatureExtractor {
    static final String LOG_TAG = "FeatureExtractor";

    @Nullable
    public static byte[] extractFeature(int fgp, boolean isFlat, byte[] imageData){
       return extractFeature(fgp, isFlat, imageData, 640, 640);
    }
    /**
     * 特征提取
     * @param imageData UCAS指纹图像
     * @param width
     * @param height
     * @return 特征格式GBFP
     */
    @Nullable
    public static byte[] extractFeature(int fgp, boolean isFlat, byte[] imageData, int width, int height){
        if (GBFPNative.FPBegin() != 1){
            Log.e(LOG_TAG, "GBFPNative.FPBegin() 异常");
            return null;
        }
       /*  输入：              cFingerPos 指位 1-20的整数
                        cIsPlain 0,不是平面指位, 1, 平面指纹
                        isNewTT 是否提取TT新特征，0否，1是
                        isExtractBinImg 是否提取纹线特征，0否，1是
		                pFingerImgBuf 图像数据
			            iImageHeight 图像高
			            iImageWidth 图像宽
	    输出：
                         pBinImg 纹线特征，如果isExtractBinImg为1，则分配空间大小不少于64+iImageWidth*iImageHeight，如果isExtractBinImg为0，则这个参数在算法内部会被忽略，可以给一个任意长度的数组
			             pFeatureData 特征数组，如果isNewTT为1，分配空间不少于4024字节，否则分配空间不少于704字节
                         pBinImgLen 纹线特征pBinImg的大小，数组长度为1即可，如果isExtractBinImg为0，则这个参数在算法内部会被忽略
                         pFeaLen 特征pFeatureData的大小，数组长度为1即可
	    返回：>0正常，<0 出错
	    功能：GAFIS特征提取，特征格式为GAFIS系统兼容的格式
	    */
        byte[] featureData = new byte[704];
        int ret = GBFPNative.FPFeatureExtractGAFIS((byte)fgp, (byte)(isFlat?1:0),(byte)0,(byte)0, imageData, height, width, new byte[1], featureData, new int[1], new int[1]);
        if (ret > 0) {
            return featureData;
        } else {
            return null;
        }
    }

    /**
     * 特征提取.GFS特征704字节用于上报和发送查询
     * @param fingerData
     * @return 特征格式GBFP
     */
    @Nullable
    public static byte[] extractFeatureByFingerData(FingerData fingerData){
        return extractFeature(fingerData.getFgp(), fingerData.isFlat(), fingerData.getImage());
    }

}
