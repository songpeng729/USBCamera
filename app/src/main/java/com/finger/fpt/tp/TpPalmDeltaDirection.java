package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TpPalmDeltaDirection {

    @XmlElement(name = "zhwsjd_tzfx")
    private String palmTrianglePointFeatureDirection = ""; //掌纹三角点_特征方向
    @XmlElement(name = "zhwsjd_tzfxfw")
    private String palmTrianglePointFeatureDirectionRange = ""; //掌纹三角点_特征方向范围

    public String getPalmTrianglePointFeatureDirection() {
        return palmTrianglePointFeatureDirection;
    }

    public void setPalmTrianglePointFeatureDirection(String palmTrianglePointFeatureDirection) {
        this.palmTrianglePointFeatureDirection = palmTrianglePointFeatureDirection;
    }

    public String getPalmTrianglePointFeatureDirectionRange() {
        return palmTrianglePointFeatureDirectionRange;
    }

    public void setPalmTrianglePointFeatureDirectionRange(String palmTrianglePointFeatureDirectionRange) {
        this.palmTrianglePointFeatureDirectionRange = palmTrianglePointFeatureDirectionRange;
    }

}
