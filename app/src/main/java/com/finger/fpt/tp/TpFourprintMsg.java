package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 捺印指掌纹四连指
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpFourprintMsg {
    /**
     * 四连指_指纹指位代码
     */
    @XmlElement(name="slz_zwzwdm")
    private String fgp = "";
    /**
     * 四连指_指掌纹缺失情况代码
     */
    @XmlElement(name="slz_zzhwqsqkdm")
    private String defect = "";
    /**
     * 四连指_自定义信息
     */
    @XmlElement(name="slz_zdyxx")
    private String customInfo = "";
    /**
     * 四连指_图像水平方向长度
     */
    @XmlElement(name="slz_txspfxcd")
    private String imgHorizontalLength = "";
    /**
     * 四连指_图像垂直方向长度
     */
    @XmlElement(name="slz_txczfxcd")
    private String imgVerticalLength = "";
    /**
     * 四连指_图像分辨率
     */
    @XmlElement(name="slz_txfbl")
    private String dpi = "";
    /**
     * 四连指_图像压缩方法描述
     */
    @XmlElement(name="slz_txysffms")
    private String imgCompressMethod = "";
    /**
     * 四联指_图像质量
     */
    @XmlElement(name="slz_txzl")
    private String imgQua = "";
    /**
     * 四连指_图像数据
     */
    @XmlElement(name="slz_txsj")
    private String imgData = "";

    public String getFgp() {
        return fgp;
    }

    public void setFgp(String fgp) {
        this.fgp = fgp;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getCustomInfo() {
        return customInfo;
    }

    public void setCustomInfo(String customInfo) {
        this.customInfo = customInfo;
    }

    public String getImgHorizontalLength() {
        return imgHorizontalLength;
    }

    public void setImgHorizontalLength(String imgHorizontalLength) {
        this.imgHorizontalLength = imgHorizontalLength;
    }

    public String getImgVerticalLength() {
        return imgVerticalLength;
    }

    public void setImgVerticalLength(String imgVerticalLength) {
        this.imgVerticalLength = imgVerticalLength;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getImgCompressMethod() {
        return imgCompressMethod;
    }

    public void setImgCompressMethod(String imgCompressMethod) {
        this.imgCompressMethod = imgCompressMethod;
    }

    public String getImgQua() {
        return imgQua;
    }

    public void setImgQua(String imgQua) {
        this.imgQua = imgQua;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }
}
