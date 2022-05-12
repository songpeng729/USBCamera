package com.finger.usbcamera.listener;

/**
 * Created by songpeng on 2018/3/11.
 * 拼接图像监听接口
 */
public interface MosaicImageListener {
    public final int MOSAIC_STATUS_MESSAGE	= 1;//有消息信息
    public final int MOSAIC_STATUS_SUCCESS	= 2;//拼接完成
    public final int MOSAIC_STATUS_FAIL	= 3;//拼接失败
    public final int MOSAIC_STATUS_START = 4;//开始拼接
    public final int MOSAIC_STATUS_END = 5;//拼接结束

   /* public final int MOSAIC_ERROR_CODE_rollback = -101;
    public final int MOSAIC_ERROR_CODE_fuzzy = -102;
    public final int MOSAIC_ERROR_CODE_too_small = -103;
    public final int MOSAIC_ERROR_CODE_too_dry = -104;
    public final int MOSAIC_ERROR_CODE_too_wet = -105;
    public final int MOSAIC_ERROR_CODE_finger_fuzzy = -106;
    public final int MOSAIC_ERROR_CODE_core_left = -107;
    public final int MOSAIC_ERROR_CODE_core_right = -108;
    public final int MOSAIC_ERROR_CODE_core_top = -109;
    public final int MOSAIC_ERROR_CODE_core_down = -110;
    public final int MOSAIC_ERROR_CODE_no_core = -111;
    public final int MOSAIC_ERROR_CODE_gravity_left = -112;
    public final int MOSAIC_ERROR_CODE_gravity_right = -113;
    public final int MOSAIC_ERROR_CODE_gravity_top = -114;
    public final int MOSAIC_ERROR_CODE_gravity_down = -115;
    public final int MOSAIC_ERROR_CODE_not_support_plain = -116;*/

    /**
     * 当状态更改时调用
     * @param status  MOSAIC_STATUS_XXX
     * @param message
     */
    public void onMosaicStatusChanged(int status, String message);
}
