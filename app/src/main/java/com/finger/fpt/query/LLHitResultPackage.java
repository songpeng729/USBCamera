package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 指掌纹串查比中信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LLHitResultPackage {

/**
 * 任务编号
 */
    @XmlElement(name = "rwbh")
    private String taskId = "";
    /**
     * 指纹比对系统描述
     */
    @XmlElement(name = "zwbdxtlxms")
    private String comparisonSystemTypeDescript = "";
    /**
     * 原始系统_案事件编号
     */
    @XmlElement(name = "ysxt_asjbh")
    private String originalSystemCaseId = "";
    /**
     * 案事件编号
     */
    @XmlElement(name = "asjbh")
    private String caseId = "";
    /**
     * 现场勘验编号
     */
    @XmlElement(name = "xckybh")
    private String latentSurveyId = "";
    /**
     * 原始系统_现场指掌纹编号
     */
    @XmlElement(name = "ysxt_xczzhwbh")
    private String originalSystemLatentFingerId = "";
    /**
     * 现场物证编号
     */
    @XmlElement(name = "xcwzbh")
    private String latentPhysicalId = "";
    /**
     * 现场指掌纹卡编号
     */
    @XmlElement(name = "xczzhwkbh")
    private String cardId = "";
    /**
     * 比中结果_原始系统_案事件编号
     */
    @XmlElement(name = "bzjg_ysxt_asjbh")
    private String resultOriginalSystemCaseId = "";
    /**
     * 比中结果_案事件编号
     */
    @XmlElement(name = "bzjg_asjbh")
    private String resultCaseId = "";
    /**
     * 比中结果_现场勘验编号
     */
    @XmlElement(name = "bzjg_xckybh")
    private String resultLatentSurveyId = "";
    /**
     * 比中结果_原始系统_现场指掌纹编号
     */
    @XmlElement(name = "bzjg_ysxt_xczzhwbh")
    private String resultOriginalSystemLatentPersonId = "";
    /**
     * 比中结果_现场物证编号
     */
    @XmlElement(name = "bzjg_xcwzbh")
    private String resultLatentPhysicalId = "";
    /**
     * 比中结果_现场指掌纹卡编号
     */
    @XmlElement(name = "bzjg_xczzhwkbh")
    private String resultCardId = "";
    /**
     * 比中单位_公安机关机构代码
     */
    @XmlElement(name = "bzdw_gajgjgdm")
    private String hitUnitCode = "";
    /**
     * 比中单位_公安机关名称
     */
    @XmlElement(name = "bzdw_gajgmc")
    private String hitUnitName = "";
    /**
     * 比中人_姓名
     */
    @XmlElement(name = "bzr_xm")
    private String hitPersonName = "";
    /**
     * 比中人_公民身份号码
     */
    @XmlElement(name = "bzr_gmsfhm")
    private String hitPersonIdCard = "";
    /**
     * 比中人_联系电话
     */
    @XmlElement(name = "bzr_lxdh")
    private String hitPersonTel = "";
    /**
     * 比中时间
     */
    @XmlElement(name = "bzsj")
    private String hitDateTime = "";
    /**
     * 复核单位_公安机关机构代码
     */
    @XmlElement(name = "fhdw_gajgjgdm")
    private String checkUnitCode = "";
    /**
     * 复核单位_公安机关名称
     */
    @XmlElement(name = "fhdw_gajgmc")
    private String checkUnitName = "";
    /**
     * 复核人_姓名
     */
    @XmlElement(name = "fhr_xm")
    private String checkPersonName = "";
    /**
     * 复核人_公民身份号码
     */
    @XmlElement(name = "fhr_gmsfhm")
    private String checkPersonIdCard = "";
    /**
     * 复核人_联系电话
     */
    @XmlElement(name = "fhr_lxdh")
    private String checkPersonTel = "";
    /**
     * 复核时间
     */
    @XmlElement(name = "fhsj")
    private String checkDateTime = "";
    /**
     * 备注
     */
    @XmlElement(name = "bz")
    private String memo = "";

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getComparisonSystemTypeDescript() {
        return comparisonSystemTypeDescript;
    }

    public void setComparisonSystemTypeDescript(String comparisonSystemTypeDescript) {
        this.comparisonSystemTypeDescript = comparisonSystemTypeDescript;
    }

    public String getOriginalSystemCaseId() {
        return originalSystemCaseId;
    }

    public void setOriginalSystemCaseId(String originalSystemCaseId) {
        this.originalSystemCaseId = originalSystemCaseId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getLatentSurveyId() {
        return latentSurveyId;
    }

    public void setLatentSurveyId(String latentSurveyId) {
        this.latentSurveyId = latentSurveyId;
    }

    public String getOriginalSystemLatentFingerId() {
        return originalSystemLatentFingerId;
    }

    public void setOriginalSystemLatentFingerId(String originalSystemLatentFingerId) {
        this.originalSystemLatentFingerId = originalSystemLatentFingerId;
    }

    public String getLatentPhysicalId() {
        return latentPhysicalId;
    }

    public void setLatentPhysicalId(String latentPhysicalId) {
        this.latentPhysicalId = latentPhysicalId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getResultOriginalSystemCaseId() {
        return resultOriginalSystemCaseId;
    }

    public void setResultOriginalSystemCaseId(String resultOriginalSystemCaseId) {
        this.resultOriginalSystemCaseId = resultOriginalSystemCaseId;
    }

    public String getResultCaseId() {
        return resultCaseId;
    }

    public void setResultCaseId(String resultCaseId) {
        this.resultCaseId = resultCaseId;
    }

    public String getResultLatentSurveyId() {
        return resultLatentSurveyId;
    }

    public void setResultLatentSurveyId(String resultLatentSurveyId) {
        this.resultLatentSurveyId = resultLatentSurveyId;
    }

    public String getResultOriginalSystemLatentPersonId() {
        return resultOriginalSystemLatentPersonId;
    }

    public void setResultOriginalSystemLatentPersonId(String resultOriginalSystemLatentPersonId) {
        this.resultOriginalSystemLatentPersonId = resultOriginalSystemLatentPersonId;
    }

    public String getResultLatentPhysicalId() {
        return resultLatentPhysicalId;
    }

    public void setResultLatentPhysicalId(String resultLatentPhysicalId) {
        this.resultLatentPhysicalId = resultLatentPhysicalId;
    }

    public String getResultCardId() {
        return resultCardId;
    }

    public void setResultCardId(String resultCardId) {
        this.resultCardId = resultCardId;
    }

    public String getHitUnitCode() {
        return hitUnitCode;
    }

    public void setHitUnitCode(String hitUnitCode) {
        this.hitUnitCode = hitUnitCode;
    }

    public String getHitUnitName() {
        return hitUnitName;
    }

    public void setHitUnitName(String hitUnitName) {
        this.hitUnitName = hitUnitName;
    }

    public String getHitPersonName() {
        return hitPersonName;
    }

    public void setHitPersonName(String hitPersonName) {
        this.hitPersonName = hitPersonName;
    }

    public String getHitPersonIdCard() {
        return hitPersonIdCard;
    }

    public void setHitPersonIdCard(String hitPersonIdCard) {
        this.hitPersonIdCard = hitPersonIdCard;
    }

    public String getHitPersonTel() {
        return hitPersonTel;
    }

    public void setHitPersonTel(String hitPersonTel) {
        this.hitPersonTel = hitPersonTel;
    }

    public String getHitDateTime() {
        return hitDateTime;
    }

    public void setHitDateTime(String hitDateTime) {
        this.hitDateTime = hitDateTime;
    }

    public String getCheckUnitCode() {
        return checkUnitCode;
    }

    public void setCheckUnitCode(String checkUnitCode) {
        this.checkUnitCode = checkUnitCode;
    }

    public String getCheckUnitName() {
        return checkUnitName;
    }

    public void setCheckUnitName(String checkUnitName) {
        this.checkUnitName = checkUnitName;
    }

    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }

    public String getCheckPersonIdCard() {
        return checkPersonIdCard;
    }

    public void setCheckPersonIdCard(String checkPersonIdCard) {
        this.checkPersonIdCard = checkPersonIdCard;
    }

    public String getCheckPersonTel() {
        return checkPersonTel;
    }

    public void setCheckPersonTel(String checkPersonTel) {
        this.checkPersonTel = checkPersonTel;
    }

    public String getCheckDateTime() {
        return checkDateTime;
    }

    public void setCheckDateTime(String checkDateTime) {
        this.checkDateTime = checkDateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
