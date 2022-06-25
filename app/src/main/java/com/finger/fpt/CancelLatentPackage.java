package com.finger.fpt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 撤销现场指纹信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CancelLatentPackage {

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
    private String latentCardId = "";
    /**
     * 办理单位_公安机关机构代码
     */
    @XmlElement(name = "bldw_gajgjgdm")
    private String handleUnitCode = "";
    /**
     * 办理单位_公安机关名称
     */
    @XmlElement(name = "bldw_gajgmc")
    private String handleUnitName = "";
    /**
     * 办理人_姓名
     */
    @XmlElement(name = "blr_xm")
    private String handlePersonName = "";
    /**
     * 办理人_公民身份号码
     */
    @XmlElement(name = "blr_gmsfhm")
    private String handlePersonIdCard = "";
    /**
     * 办理人_联系电话
     */
    @XmlElement(name = "blr_lxdh")
    private String handlePersonTel = "";
    /**
     * 比中时间
     */
    @XmlElement(name = "bzsj")
    private String hitDateTime = "";

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

    public String getLatentCardId() {
        return latentCardId;
    }

    public void setLatentCardId(String latentCardId) {
        this.latentCardId = latentCardId;
    }

    public String getHandleUnitCode() {
        return handleUnitCode;
    }

    public void setHandleUnitCode(String handleUnitCode) {
        this.handleUnitCode = handleUnitCode;
    }

    public String getHandleUnitName() {
        return handleUnitName;
    }

    public void setHandleUnitName(String handleUnitName) {
        this.handleUnitName = handleUnitName;
    }

    public String getHandlePersonName() {
        return handlePersonName;
    }

    public void setHandlePersonName(String handlePersonName) {
        this.handlePersonName = handlePersonName;
    }

    public String getHandlePersonIdCard() {
        return handlePersonIdCard;
    }

    public void setHandlePersonIdCard(String handlePersonIdCard) {
        this.handlePersonIdCard = handlePersonIdCard;
    }

    public String getHandlePersonTel() {
        return handlePersonTel;
    }

    public void setHandlePersonTel(String handlePersonTel) {
        this.handlePersonTel = handlePersonTel;
    }

    public String getHitDateTime() {
        return hitDateTime;
    }

    public void setHitDateTime(String hitDateTime) {
        this.hitDateTime = hitDateTime;
    }
}
