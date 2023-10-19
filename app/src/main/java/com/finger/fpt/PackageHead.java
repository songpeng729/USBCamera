package com.finger.fpt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"version","createTime","originSystem","sendUnitCode","sendUnitName","sendUnitSystemType","sendPersonName","sendPersonIdCard","sendPersonTel"})
public class PackageHead {

    /**
     * 版本号
     */
    @XmlElement(name = "version")
    private String version = "FPT0500";
    /**
     * 日期时间型,格式是”YYYYMMDDHH24MISS”
     */
    @XmlElement(name = "createTime")
    private String createTime;
    /**
     * 来源系统
     */
    @XmlElement(name = "originSystem")
    private String originSystem = "FingerAPP";
    /**
     * 发送单位代码
     */
    @XmlElement(name = "fsdw_gajgjgdm")
    private String sendUnitCode = "";
    /**
     * 发送单位名称
     */
    @XmlElement(name = "fsdw_gajgmc")
    private String sendUnitName = "";
    /**
     * 发送单位系统类型
     */
    @XmlElement(name = "fsdw_xtlx")
    private String sendUnitSystemType;
    /**
     * 发送人姓名
     */
    @XmlElement(name = "fsr_xm")
    private String sendPersonName = "";
    /**
     * 发送人身份证号码
     */
    @XmlElement(name = "fsr_gmsfhm")
    private String sendPersonIdCard = "";
    /**
     * 发送人联系电话
     */
    @XmlElement(name = "fsr_lxdh")
    private String sendPersonTel = "";

    public PackageHead(){}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOriginSystem() {
        return originSystem;
    }

    public void setOriginSystem(String originSystem) {
        this.originSystem = originSystem;
    }

    public String getSendUnitCode() {
        return sendUnitCode;
    }

    public void setSendUnitCode(String sendUnitCode) {
        this.sendUnitCode = sendUnitCode;
    }

    public String getSendUnitName() {
        return sendUnitName;
    }

    public void setSendUnitName(String sendUnitName) {
        this.sendUnitName = sendUnitName;
    }

    public String getSendUnitSystemType() {
        return sendUnitSystemType;
    }

    public void setSendUnitSystemType(String sendUnitSystemType) {
        this.sendUnitSystemType = sendUnitSystemType;
    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public String getSendPersonIdCard() {
        return sendPersonIdCard;
    }

    public void setSendPersonIdCard(String sendPersonIdCard) {
        this.sendPersonIdCard = sendPersonIdCard;
    }

    public String getSendPersonTel() {
        return sendPersonTel;
    }

    public void setSendPersonTel(String sendPersonTel) {
        this.sendPersonTel = sendPersonTel;
    }
}
