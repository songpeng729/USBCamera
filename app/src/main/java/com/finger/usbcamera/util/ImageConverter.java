package com.finger.usbcamera.util;

import android.util.Log;

import gbfp.jni.GBFPNative;

/**
 * Created by songpeng on 2018/4/1.
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

    public static boolean checkImageQuality(boolean isFlat, byte[] imageData){
        if (GBFPNative.FPBegin() != 1){
            Log.e(LOG_TAG, "GBFPNative.FPBegin() 异常");
            return false;
        }

        if(isFlat){
            int quality = GBFPNative.FPGetFptQuality(imageData, 640, 640);
            if(quality >= 60){
                return true;
            }
        }else{
            //TODO
//            int quality = MosaicNative.FingerQuality(imgDataBuffer, width, height);
//            if(quality < 0){
//                //滚动采集出现异常，条件多样，这里直接使用handler处理
//                Message msg = fingerSurfaceViewHandler.obtainMessage(MOSAIC_STATUS_FAIL);
//                msg.arg1 = quality;
//                fingerSurfaceViewHandler.sendMessage(msg);
//            }
            return true;
        }
        return false;
    }
}
