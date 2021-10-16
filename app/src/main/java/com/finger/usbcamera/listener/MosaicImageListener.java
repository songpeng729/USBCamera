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

    /**
     * 当状态更改时调用
     * @param status  MOSAIC_STATUS_XXX
     * @param message
     */
    public void onMosaicStatusChanged(int status, String message);
}
