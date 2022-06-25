package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 捺印指掌纹人像信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpFaceImage {
    /**
     * 人像照片类型代码
     */
    @XmlElement(name = "rxzplxdm")
    private String personPictureTypeCode = "";
    /**
     * 人像_电子文件格式
     */
    @XmlElement(name = "rx_dzwjgs")
    private String personPictureFileLayout = "";
    /**
     * 人像_图像数据
     */
    @XmlElement(name = "rx_txsj")
    private String personPictureImageData = "";

    public String getPersonPictureTypeCode() {
        return personPictureTypeCode;
    }

    public void setPersonPictureTypeCode(String personPictureTypeCode) {
        this.personPictureTypeCode = personPictureTypeCode;
    }

    public String getPersonPictureFileLayout() {
        return personPictureFileLayout;
    }

    public void setPersonPictureFileLayout(String personPictureFileLayout) {
        this.personPictureFileLayout = personPictureFileLayout;
    }

    public String getPersonPictureImageData() {
        return personPictureImageData;
    }

    public void setPersonPictureImageData(String personPictureImageData) {
        this.personPictureImageData = personPictureImageData;
    }
}
