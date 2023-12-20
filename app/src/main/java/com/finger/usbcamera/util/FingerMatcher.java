package com.finger.usbcamera.util;

import gbfp.jni.GBFPNative;

/**
 * 指纹比对
 */
public class FingerMatcher {

    /**
     * 指纹特征比对
     * @param srcFeature
     * @param destFeature
     * @return
     */
    public static boolean featureMatchGAFIS(byte[] srcFeature, byte[] destFeature ){
        if(GBFPNative.FPBegin() != 1){
            return false;
        }
        float[] similarity = new float[1];
        GBFPNative.FPFeatureMatchGAFIS(srcFeature, destFeature, similarity);
        return similarity[0] > 0.6;
    }
}
