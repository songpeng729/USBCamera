package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 掌纹特征点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpPalmMinutia {
    /**
     * 掌纹特征点_特征X坐标
     */
    @XmlElement(name = "zhwtzd_tzxzb")
    private String fingerFeaturePointXCoordinate = "";
    /**
     * 掌纹特征点_特征Y坐标
     */
    @XmlElement(name = "zhwtzd_tzyzb")
    private String fingerFeaturePointYCoordinate = "";
    /**
     * 掌纹特征点_特征方向
     */
    @XmlElement(name = "zhwtzd_tzfx")
    private String fingerFeaturePointDirection = "";
    /**
     * 掌纹特征点_特征质量
     */
    @XmlElement(name = "zhwtzd_tzzl")
    private String fingerFeaturePointQuality = "";

    public String getFingerFeaturePointXCoordinate() {
        return fingerFeaturePointXCoordinate;
    }

    public void setFingerFeaturePointXCoordinate(String fingerFeaturePointXCoordinate) {
        this.fingerFeaturePointXCoordinate = fingerFeaturePointXCoordinate;
    }

    public String getFingerFeaturePointYCoordinate() {
        return fingerFeaturePointYCoordinate;
    }

    public void setFingerFeaturePointYCoordinate(String fingerFeaturePointYCoordinate) {
        this.fingerFeaturePointYCoordinate = fingerFeaturePointYCoordinate;
    }

    public String getFingerFeaturePointDirection() {
        return fingerFeaturePointDirection;
    }

    public void setFingerFeaturePointDirection(String fingerFeaturePointDirection) {
        this.fingerFeaturePointDirection = fingerFeaturePointDirection;
    }

    public String getFingerFeaturePointQuality() {
        return fingerFeaturePointQuality;
    }

    public void setFingerFeaturePointQuality(String fingerFeaturePointQuality) {
        this.fingerFeaturePointQuality = fingerFeaturePointQuality;
    }
}
