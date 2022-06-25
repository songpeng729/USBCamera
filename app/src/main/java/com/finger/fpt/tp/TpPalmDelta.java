package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 掌纹三角点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpPalmDelta {
    /**
     * 掌纹三角点_特征X坐标
     */
    @XmlElement(name = "zhwsjd_tzxzb")
    private String palmTrianglePointFeatureXCoordinate = "";
    /**
     * 掌纹三角点_特征Y坐标
     */
    @XmlElement(name = "zhwsjd_tzyzb")
    private String palmTrianglePointFeatureYCoordinate = "";
    /**
     * 掌纹三角点_特征坐标范围
     */
    @XmlElement(name = "zhwsjd_tzzbfw")
    private String palmTrianglePointFeatureCoodinateRange = "";
    /**
     * 掌纹三角点方向信息节点
     */
    @XmlElement(name = "deltaDirection")
    private List<TpPalmDeltaDirection> deltaDirections;
    /**
     * 掌纹三角点_掌纹三角位置类型代码
     */
    @XmlElement(name = "zhwsjd_zhwsjwzlxdm")
    private String deltaPostionClassCode = "";
    /**
     * 掌纹三角点_特征质量
     */
    @XmlElement(name = "zhwsjd_tzzl")
    private String deltaFeatureQuality = "";

    public String getPalmTrianglePointFeatureXCoordinate() {
        return palmTrianglePointFeatureXCoordinate;
    }

    public void setPalmTrianglePointFeatureXCoordinate(String palmTrianglePointFeatureXCoordinate) {
        this.palmTrianglePointFeatureXCoordinate = palmTrianglePointFeatureXCoordinate;
    }

    public String getPalmTrianglePointFeatureYCoordinate() {
        return palmTrianglePointFeatureYCoordinate;
    }

    public void setPalmTrianglePointFeatureYCoordinate(String palmTrianglePointFeatureYCoordinate) {
        this.palmTrianglePointFeatureYCoordinate = palmTrianglePointFeatureYCoordinate;
    }

    public String getPalmTrianglePointFeatureCoodinateRange() {
        return palmTrianglePointFeatureCoodinateRange;
    }

    public void setPalmTrianglePointFeatureCoodinateRange(String palmTrianglePointFeatureCoodinateRange) {
        this.palmTrianglePointFeatureCoodinateRange = palmTrianglePointFeatureCoodinateRange;
    }

    public List<TpPalmDeltaDirection> getDeltaDirections() {
        return deltaDirections;
    }

    public void setDeltaDirections(List<TpPalmDeltaDirection> deltaDirections) {
        this.deltaDirections = deltaDirections;
    }

    public String getDeltaPostionClassCode() {
        return deltaPostionClassCode;
    }

    public void setDeltaPostionClassCode(String deltaPostionClassCode) {
        this.deltaPostionClassCode = deltaPostionClassCode;
    }

    public String getDeltaFeatureQuality() {
        return deltaFeatureQuality;
    }

    public void setDeltaFeatureQuality(String deltaFeatureQuality) {
        this.deltaFeatureQuality = deltaFeatureQuality;
    }
}
