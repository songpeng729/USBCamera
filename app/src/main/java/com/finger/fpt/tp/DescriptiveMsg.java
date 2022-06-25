package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * 捺印指掌纹基本信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DescriptiveMsg {

    /**
     * 原始系统案事件相关人员编号
     */
    @XmlElement(name = "ysxt_asjxgrybh")
    private String  originalSystemCasePersonId = "";
    /**
     * 警综人员编号
     */
    @XmlElement(name = "jzrybh")
    private String  jingZongPersonId = "";
    /**
     * 案事件人员编号
     */
    @XmlElement(name = "asjxgrybh")
    private String  casePersonid = "";
    /**
     * 指掌纹卡编号
     */
    @XmlElement(name = "zzhwkbh")
    private String  fingerPalmCardId = "";
    /**
     * 采集信息原因代码
     */
    @XmlElementWrapper(name="collectingReasonSet")
    @XmlElement(name="cjxxyydm")
    private List<String> collectionReason = new ArrayList<>();
    /**
     * 姓名
     */
    @XmlElement(name = "xm")
    private String  name = "";
    /**
     * 别名/绰号
     */
    @XmlElement(name = "bmch")
    private String  alias = "";
    /**
     * 性别代码
     */
    @XmlElement(name = "xbdm")
    private String  sex = "";
    /**
     * 出生日期
     */
    @XmlElement(name = "csrq")
    private String  birthday = "";
    /**
     * 国籍代码
     */
    @XmlElement(name = "gjdm")
    private String  nationality = "";
    /**
     * 民族代码
     */
    @XmlElement(name = "mzdm")
    private String  nation = "";
    /**
     * 常用证件代码
     */
    @XmlElement(name = "cyzjdm")
    private String  credentialsCode = "";
    /**
     * 证件号码
     */
    @XmlElement(name = "zjhm")
    private String  credentialsNo = "";
    /**
     * 户籍地址_行政区划代码
     */
    @XmlElement(name = "hjdz_xzqhdm")
    private String  hukouAdministrativeDivisionCode = "";
    /**
     * 户籍地址_地址名称
     */
    @XmlElement(name = "hjdz_dzmc")
    private String  hukouAddress = "";
    /**
     * 现住址_行政区划代码
     */
    @XmlElement(name = "xzz_xzqhdm")
    private String  houseAdministrativeDivisionCode = "";
    /**
     * 现住址_地址名称
     */
    @XmlElement(name = "xzz_dzmc")
    private String  houseAddress = "";
    /**
     * 备注
     */
    @XmlElement(name = "bz")
    private String  memo = "";

    public String getOriginalSystemCasePersonId() {
        return originalSystemCasePersonId;
    }

    public void setOriginalSystemCasePersonId(String originalSystemCasePersonId) {
        this.originalSystemCasePersonId = originalSystemCasePersonId;
    }

    public String getJingZongPersonId() {
        return jingZongPersonId;
    }

    public void setJingZongPersonId(String jingZongPersonId) {
        this.jingZongPersonId = jingZongPersonId;
    }

    public String getCasePersonid() {
        return casePersonid;
    }

    public void setCasePersonid(String casePersonid) {
        this.casePersonid = casePersonid;
    }

    public String getFingerPalmCardId() {
        return fingerPalmCardId;
    }

    public void setFingerPalmCardId(String fingerPalmCardId) {
        this.fingerPalmCardId = fingerPalmCardId;
    }

    public List<String> getCollectionReason() {
        return collectionReason;
    }

    public void setCollectionReason(List<String> collectionReason) {
        this.collectionReason = collectionReason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCredentialsCode() {
        return credentialsCode;
    }

    public void setCredentialsCode(String credentialsCode) {
        this.credentialsCode = credentialsCode;
    }

    public String getCredentialsNo() {
        return credentialsNo;
    }

    public void setCredentialsNo(String credentialsNo) {
        this.credentialsNo = credentialsNo;
    }

    public String getHukouAdministrativeDivisionCode() {
        return hukouAdministrativeDivisionCode;
    }

    public void setHukouAdministrativeDivisionCode(String hukouAdministrativeDivisionCode) {
        this.hukouAdministrativeDivisionCode = hukouAdministrativeDivisionCode;
    }

    public String getHukouAddress() {
        return hukouAddress;
    }

    public void setHukouAddress(String hukouAddress) {
        this.hukouAddress = hukouAddress;
    }

    public String getHouseAdministrativeDivisionCode() {
        return houseAdministrativeDivisionCode;
    }

    public void setHouseAdministrativeDivisionCode(String houseAdministrativeDivisionCode) {
        this.houseAdministrativeDivisionCode = houseAdministrativeDivisionCode;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
