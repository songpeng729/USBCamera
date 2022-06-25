package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 捺印信息节点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectInfoMsg {

    /**
     * 指纹比对系统类型描述
     */
    @XmlElement(name = "zwbdxtlxms")
    private String fingerprintComparisonSysTypeDescript = "";
    /**
     * 捺印单位公安机关机构代码
     */
    @XmlElement(name = "nydw_gajgjgdm")
    private String chopUnitCode = "";
    /**
     * 捺印单位公安机关机构名称
     */
    @XmlElement(name = "nydw_gajgmc")
    private String chopUnitName = "";
    /**
     * 捺印人姓名
     */
    @XmlElement(name = "nyry_xm")
    private String chopPersonName = "";
    /**
     * 捺印人身份证号码
     */
    @XmlElement(name = "nyry_gmsfhm")
    private String chopPersonIdCard = "";
    /**
     * 捺印人联系电话
     */
    @XmlElement(name = "nyry_lxdh")
    private String chopPersonTel = "";
    /**
     * 捺印时间
     */
    @XmlElement(name = "nysj")
    private String chopDateTime = "";

    public String getFingerprintComparisonSysTypeDescript() {
        return fingerprintComparisonSysTypeDescript;
    }

    public void setFingerprintComparisonSysTypeDescript(String fingerprintComparisonSysTypeDescript) {
        this.fingerprintComparisonSysTypeDescript = fingerprintComparisonSysTypeDescript;
    }

    public String getChopUnitCode() {
        return chopUnitCode;
    }

    public void setChopUnitCode(String chopUnitCode) {
        this.chopUnitCode = chopUnitCode;
    }

    public String getChopUnitName() {
        return chopUnitName;
    }

    public void setChopUnitName(String chopUnitName) {
        this.chopUnitName = chopUnitName;
    }

    public String getChopPersonName() {
        return chopPersonName;
    }

    public void setChopPersonName(String chopPersonName) {
        this.chopPersonName = chopPersonName;
    }

    public String getChopPersonIdCard() {
        return chopPersonIdCard;
    }

    public void setChopPersonIdCard(String chopPersonIdCard) {
        this.chopPersonIdCard = chopPersonIdCard;
    }

    public String getChopPersonTel() {
        return chopPersonTel;
    }

    public void setChopPersonTel(String chopPersonTel) {
        this.chopPersonTel = chopPersonTel;
    }

    public String getChopDateTime() {
        return chopDateTime;
    }

    public void setChopDateTime(String chopDateTime) {
        this.chopDateTime = chopDateTime;
    }
}
