package com.finger.usbcamera.vo;

/**
 * Created by songpeng on 2018/3/11.
 */
public class FingerData {
    public static final int FINGER_STATUS_NORMAL = 0;//指纹正常
    public static final int FINGER_STATUS_NONE = 1;//指纹缺指
    public static final int FINGER_STATUS_OTHER = 2;//其他原因

    private boolean isFlat;//是否平面
    private int fgp; //指位
    private int status; //状态
    private String condition;
    private byte[] image;//图像数据
    private byte[] feature;//特征数据

    public FingerData(int fgp, boolean isFlat){
        this.fgp = fgp;
        this.isFlat = isFlat;
        this.status = FINGER_STATUS_NORMAL;
    }

    public int getFgp() {
        return fgp;
    }

    public void setFgp(int fgp) {
        this.fgp = fgp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isFlat() {
        return isFlat;
    }

    public void setFlat(boolean flat) {
        isFlat = flat;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getFeature() {
        return feature;
    }

    public void setFeature(byte[] feature) {
        this.feature = feature;
    }
}
