package com.finger.usbcamera.internal;

/**
 * 授权信息
 */
public class LicenseInfo {
    private int vendorId = 0;
    private int productId = 0;
    private long timeInMillis = 0;
    public LicenseInfo(){
    }
    public LicenseInfo(int vendorId, int productId, long timeInMillis){
        this.vendorId = vendorId;
        this.productId = productId;
        this.timeInMillis = timeInMillis;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }
}
