package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 捺印全掌
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpFullpalmMsg {

    /**
     * 全掌_掌纹掌位代码
     */
    @XmlElement(name = "qz_zhwzhwdm")
    private String fullPalmPostionCode = "";
    /**
     * 全掌_指掌纹缺失情况代码
     */
    @XmlElement(name = "qz_zzhwqsqkdm")
    private String fullPalmLackPalmCauseCode = "";
    /**
     * 全掌_自定义信息
     */
    @XmlElement(name = "qz_zdyxx")
    private String fullPalmCustomInfo = "";
    /**
     * 全掌_图像水平方向长度
     */
    @XmlElement(name = "qz_txspfxcd")
    private String fullPalmImageHorizontalDirectionLength = "";
    /**
     * 全掌_图像垂直方向长度
     */
    @XmlElement(name = "qz_txczfxcd")
    private String fullPalmImageVerticalDirectionLength = "";
    /**
     * 全掌_图像分辨率
     */
    @XmlElement(name = "qz_txfbl")
    private String fullPalmImageRatio = "";
    /**
     * 全掌_图像压缩方法描述
     */
    @XmlElement(name = "qz_txysffms")
    private String fullPalmImageCompressMethodDescript = "";
    /**
     * 全掌_图像质量
     */
    @XmlElement(name = "qz_txzl")
    private String fullPalmImageQuality = "";
    /**
     * 全掌_图像数据
     */
    @XmlElement(name = "qz_txsj")
    private String fullPalmImageData = "";

    public String getFullPalmPostionCode() {
        return fullPalmPostionCode;
    }

    public void setFullPalmPostionCode(String fullPalmPostionCode) {
        this.fullPalmPostionCode = fullPalmPostionCode;
    }

    public String getFullPalmLackPalmCauseCode() {
        return fullPalmLackPalmCauseCode;
    }

    public void setFullPalmLackPalmCauseCode(String fullPalmLackPalmCauseCode) {
        this.fullPalmLackPalmCauseCode = fullPalmLackPalmCauseCode;
    }

    public String getFullPalmCustomInfo() {
        return fullPalmCustomInfo;
    }

    public void setFullPalmCustomInfo(String fullPalmCustomInfo) {
        this.fullPalmCustomInfo = fullPalmCustomInfo;
    }

    public String getFullPalmImageHorizontalDirectionLength() {
        return fullPalmImageHorizontalDirectionLength;
    }

    public void setFullPalmImageHorizontalDirectionLength(String fullPalmImageHorizontalDirectionLength) {
        this.fullPalmImageHorizontalDirectionLength = fullPalmImageHorizontalDirectionLength;
    }

    public String getFullPalmImageVerticalDirectionLength() {
        return fullPalmImageVerticalDirectionLength;
    }

    public void setFullPalmImageVerticalDirectionLength(String fullPalmImageVerticalDirectionLength) {
        this.fullPalmImageVerticalDirectionLength = fullPalmImageVerticalDirectionLength;
    }

    public String getFullPalmImageRatio() {
        return fullPalmImageRatio;
    }

    public void setFullPalmImageRatio(String fullPalmImageRatio) {
        this.fullPalmImageRatio = fullPalmImageRatio;
    }

    public String getFullPalmImageCompressMethodDescript() {
        return fullPalmImageCompressMethodDescript;
    }

    public void setFullPalmImageCompressMethodDescript(String fullPalmImageCompressMethodDescript) {
        this.fullPalmImageCompressMethodDescript = fullPalmImageCompressMethodDescript;
    }

    public String getFullPalmImageQuality() {
        return fullPalmImageQuality;
    }

    public void setFullPalmImageQuality(String fullPalmImageQuality) {
        this.fullPalmImageQuality = fullPalmImageQuality;
    }

    public String getFullPalmImageData() {
        return fullPalmImageData;
    }

    public void setFullPalmImageData(String fullPalmImageData) {
        this.fullPalmImageData = fullPalmImageData;
    }
}
