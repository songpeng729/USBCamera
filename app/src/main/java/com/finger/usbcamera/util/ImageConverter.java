package com.finger.usbcamera.util;

import android.util.Log;

import centipede.livescan.MosaicNative;
import gbfp.jni.GBFPNative;

/**
 * 图像转换器，压缩和解压缩
 */
public class ImageConverter {
    private static final String LOG_TAG = "ImageConverter";

    public static byte[] compress(int fgp, boolean isFlat, byte[] imageData){
        return compress(fgp, isFlat, imageData, 640, 640);
    }
    /**
     * 压缩图像
     * @param fgp
     * @param isFlat
     * @param imageData
     * @param width
     * @param height
     * @return
     */
    public static byte[] compress(int fgp, boolean isFlat, byte[] imageData, int width, int height){
        if (GBFPNative.FPBegin() != 1){
            Log.e(LOG_TAG, "GBFPNative.FPBegin() 异常");
            return null;
        }
        byte[] tempData = new byte[width*height*12/100];
        int[] length = new int[1];
        int ret = GBFPNative.FPCompress(imageData, height, width, (byte)fgp, (byte)(isFlat?1:0), 500, 10, tempData, length);
        if(ret > 0){
            byte[] cprData = new byte[length[0]];
            System.arraycopy(tempData, 0, cprData, 0, length[0]);
            return cprData;
        }
        return null;
    }

    public static byte[] decompress(byte[] cprData){
        return decompress(cprData, 640, 640);
    }
    public static byte[] decompress(byte[] cprData, int height, int width){
        if (GBFPNative.FPBegin() != 1){
            Log.e(LOG_TAG, "GBFPNative.FPBegin() 异常");
            return null;
        }
        byte[] imageData = new byte[height*width];
        int ret = GBFPNative.FPDecompress(cprData, cprData.length, imageData, new int[]{height},new int[]{width});
        if(ret > 0){
            return imageData;
        }
        return null;
    }

    /**
     * 检查图像质量
     * @param isFlat
     * @param imageData
     * @return
     */
    public static int checkImageQuality(boolean isFlat, byte[] imageData){
        if (GBFPNative.FPBegin() != 1){
            Log.e(LOG_TAG, "GBFPNative.FPBegin() 异常");
            return 0;
        }

        if(isFlat){
            int quality = GBFPNative.FPGetFptQuality(imageData, 640, 640);
            Log.d(LOG_TAG, "GBFPNative.FPGetFptQuality() "+ quality);
            return quality;
        }else{
            int quality = MosaicNative.ImageQuality(imageData, 640, 640);
            Log.d(LOG_TAG, "MosaicNative.ImageQuality() "+ quality);
            return quality;
        }
    }
}
