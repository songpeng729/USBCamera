package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 掌纹折返点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpPalmCoreLikePattern {

    @XmlElement(name = "zhwzfd_tzxzb")
    private String palmRetracingPointFeatureXCoordinate = ""; //掌纹折返点_特征X坐标
    @XmlElement(name = "zhwzfd_tzyzb")
    private String palmRetracingPointFeatureYCoordinate = ""; //掌纹折返点_特征Y坐标
    @XmlElement(name = "zhwzfd_tzzbfw")
    private String palmRetracingPointFeatureCoordinateRange = ""; //掌纹折返点_特征坐标范围
    @XmlElement(name = "zhwzfd_tzfx")
    private String palmRetracingPointFeatureDirection = ""; //掌纹折返点_特征方向
    @XmlElement(name = "zhwzfd_tzfxfw")
    private String palmRetracingPointFeatureDirectionRange = ""; //掌纹折返点_特征方向范围
    @XmlElement(name = "zhwzfd_tzzl")
    private String palmRetracingPointFeatureQuality = ""; //掌纹折返点_特征质量

    public String getPalmRetracingPointFeatureXCoordinate() {
        return palmRetracingPointFeatureXCoordinate;
    }

    public void setPalmRetracingPointFeatureXCoordinate(String palmRetracingPointFeatureXCoordinate) {
        this.palmRetracingPointFeatureXCoordinate = palmRetracingPointFeatureXCoordinate;
    }

    public String getPalmRetracingPointFeatureYCoordinate() {
        return palmRetracingPointFeatureYCoordinate;
    }

    public void setPalmRetracingPointFeatureYCoordinate(String palmRetracingPointFeatureYCoordinate) {
        this.palmRetracingPointFeatureYCoordinate = palmRetracingPointFeatureYCoordinate;
    }

    public String getPalmRetracingPointFeatureCoordinateRange() {
        return palmRetracingPointFeatureCoordinateRange;
    }

    public void setPalmRetracingPointFeatureCoordinateRange(String palmRetracingPointFeatureCoordinateRange) {
        this.palmRetracingPointFeatureCoordinateRange = palmRetracingPointFeatureCoordinateRange;
    }

    public String getPalmRetracingPointFeatureDirection() {
        return palmRetracingPointFeatureDirection;
    }

    public void setPalmRetracingPointFeatureDirection(String palmRetracingPointFeatureDirection) {
        this.palmRetracingPointFeatureDirection = palmRetracingPointFeatureDirection;
    }

    public String getPalmRetracingPointFeatureDirectionRange() {
        return palmRetracingPointFeatureDirectionRange;
    }

    public void setPalmRetracingPointFeatureDirectionRange(String palmRetracingPointFeatureDirectionRange) {
        this.palmRetracingPointFeatureDirectionRange = palmRetracingPointFeatureDirectionRange;
    }

    public String getPalmRetracingPointFeatureQuality() {
        return palmRetracingPointFeatureQuality;
    }

    public void setPalmRetracingPointFeatureQuality(String palmRetracingPointFeatureQuality) {
        this.palmRetracingPointFeatureQuality = palmRetracingPointFeatureQuality;
    }
}
