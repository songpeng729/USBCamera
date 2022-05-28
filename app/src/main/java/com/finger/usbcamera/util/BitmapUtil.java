package com.finger.usbcamera.util;

public class BitmapUtil {

    public static int[] convert2Pixels(byte[] pixBuff) {
        int w = 640;
        int h = 640;
        int len = h * w;
        int[] buf_pic = new int[len];

        int r;
        int g;
        int b;
        int alpha = 0xff000000;

        int index = 0;
        for(index = 0;index < len;index++){
            r = pixBuff[index]&0xFF;
            g = pixBuff[index]&0xFF;
            b = pixBuff[index]&0xFF;

            buf_pic[index] = alpha | (b << 16) | (g << 8) | r;
        }
        return buf_pic;
    }
}
