package com.finger.fpt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 自定义信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomDataPackage {

    @XmlElement(name = "zdyxx")
    private String zdyxx;

    public String getZdyxx() {
        return zdyxx;
    }

    public void setZdyxx(String zdyxx) {
        this.zdyxx = zdyxx;
    }
}
