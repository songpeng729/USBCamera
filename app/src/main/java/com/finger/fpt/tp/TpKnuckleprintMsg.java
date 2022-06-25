package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 捺印指掌纹指节纹
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpKnuckleprintMsg {

    /**
     * 指节纹_指纹指位代码
     */
    @XmlElement(name = "zjw_zwzwdm")
    private String knucklePrintPostionCode = "";
    /**
     * 指节纹_指掌纹缺失情况代码
     */
    @XmlElement(name = "zjw_zzhwqsqkdm")
    private String knucklePrintLackFingerCauseCode = "";
    /**
     * 指节纹_自定义信息
     */
    @XmlElement(name = "zjw_zdyxx")
    private String knucklePrintCustomInfo = "";
    /**
     * 指节纹_图像水平方向长度
     */
    @XmlElement(name = "zjw_txspfxcd")
    private String knucklePrintImageHorizontalDirectionLength = "";
    /**
     * 指节纹_图像垂直方向长度
     */
    @XmlElement(name = "zjw_txczfxcd")
    private String knucklePrintImageVerticalDirectionLength = "";
    /**
     * 指节纹_图像分辨率
     */
    @XmlElement(name = "zjw_txfbl")
    private String knucklePrintImageRatio = "";
    /**
     * 指节纹_图像压缩方法描述
     */
    @XmlElement(name = "zjw_txysffms")
    private String knucklePrintImageCompressMethodDescript = "";
    /**
     * 指节纹_图像质量
     */
    @XmlElement(name = "zjw_txzl")
    private String knucklePrintImageQuality = "";
    /**
     * 指节纹_图像数据
     */
    @XmlElement(name = "zjw_txsj")
    private String knucklePrintImageData = "";

    public String getKnucklePrintPostionCode() {
        return knucklePrintPostionCode;
    }

    public void setKnucklePrintPostionCode(String knucklePrintPostionCode) {
        this.knucklePrintPostionCode = knucklePrintPostionCode;
    }

    public String getKnucklePrintLackFingerCauseCode() {
        return knucklePrintLackFingerCauseCode;
    }

    public void setKnucklePrintLackFingerCauseCode(String knucklePrintLackFingerCauseCode) {
        this.knucklePrintLackFingerCauseCode = knucklePrintLackFingerCauseCode;
    }

    public String getKnucklePrintCustomInfo() {
        return knucklePrintCustomInfo;
    }

    public void setKnucklePrintCustomInfo(String knucklePrintCustomInfo) {
        this.knucklePrintCustomInfo = knucklePrintCustomInfo;
    }

    public String getKnucklePrintImageHorizontalDirectionLength() {
        return knucklePrintImageHorizontalDirectionLength;
    }

    public void setKnucklePrintImageHorizontalDirectionLength(String knucklePrintImageHorizontalDirectionLength) {
        this.knucklePrintImageHorizontalDirectionLength = knucklePrintImageHorizontalDirectionLength;
    }

    public String getKnucklePrintImageVerticalDirectionLength() {
        return knucklePrintImageVerticalDirectionLength;
    }

    public void setKnucklePrintImageVerticalDirectionLength(String knucklePrintImageVerticalDirectionLength) {
        this.knucklePrintImageVerticalDirectionLength = knucklePrintImageVerticalDirectionLength;
    }

    public String getKnucklePrintImageRatio() {
        return knucklePrintImageRatio;
    }

    public void setKnucklePrintImageRatio(String knucklePrintImageRatio) {
        this.knucklePrintImageRatio = knucklePrintImageRatio;
    }

    public String getKnucklePrintImageCompressMethodDescript() {
        return knucklePrintImageCompressMethodDescript;
    }

    public void setKnucklePrintImageCompressMethodDescript(String knucklePrintImageCompressMethodDescript) {
        this.knucklePrintImageCompressMethodDescript = knucklePrintImageCompressMethodDescript;
    }

    public String getKnucklePrintImageQuality() {
        return knucklePrintImageQuality;
    }

    public void setKnucklePrintImageQuality(String knucklePrintImageQuality) {
        this.knucklePrintImageQuality = knucklePrintImageQuality;
    }

    public String getKnucklePrintImageData() {
        return knucklePrintImageData;
    }

    public void setKnucklePrintImageData(String knucklePrintImageData) {
        this.knucklePrintImageData = knucklePrintImageData;
    }
}
