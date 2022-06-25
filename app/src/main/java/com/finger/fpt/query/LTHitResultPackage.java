package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 指掌纹正查和倒查比中信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LTHitResultPackage {

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
     * 现场指纹_案事件编号
     */
    @XmlElement(name = "xczw_asjbh")
    private String latentFingerCaseId = "";
    /**
     * 现场指纹_原始系统_案事件编号
     */
    @XmlElement(name = "xczw_ysxt_asjbh")
    private String latentFingerOriginalSystemCaseId = "";
    /**
     * 现场指纹_现场勘验编号
     */
    @XmlElement(name = "xczw_xckybh")
    private String latentFingerLatentSurveyId = "";
    /**
     * 现场指纹_原始系统_现场指掌纹编号
     */
    @XmlElement(name = "xczw_ysxt_xczzhwbh")
    private String latentFingerOriginalSystemFingerId = "";
    /**
     * 现场指纹_现场物证编号
     */
    @XmlElement(name = "xczw_xcwzbh")
    private String latentFingerLatentPhysicalId = "";
    /**
     * 现场指纹_现场指掌纹卡编号
     */
    @XmlElement(name = "xczw_xczzhwkbh")
    private String latentFingerCardId = "";
    /**
     * 捺印指纹_原始系统_案事件相关人员编号
     */
    @XmlElement(name = "nyzw_ysxt_asjxgrybh")
    private String fingerPrintOriginalSystemPersonId = "";
    /**
     * 捺印指纹_警综人员编号
     */
    @XmlElement(name = "nyzw_jzrybh")
    private String fingerPrintJingZongPersonId = "";
    /**
     * 捺印指纹_案事件相关人员编号
     */
    @XmlElement(name = "nyzw_asjxgrybh")
    private String fingerPrintXzPersonId = "";
    /**
     * 捺印指纹_指掌纹卡编号
     */
    @XmlElement(name = "nyzw_zzhwkbh")
    private String fingerPrintCardId = "";
    /**
     * 捺印指纹_指掌位代码
     */
    @XmlElement(name = "nyzw_zzhwdm")
    private String fingerPrintPostionCode = "";
    /**
     * 捺印指纹_指掌纹比对方法代码
     */
    @XmlElement(name = "nyzw_zzhwbdffdm")
    private String fingerPrintComparisonMethodCode = "";
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

    public String getLatentFingerCaseId() {
        return latentFingerCaseId;
    }

    public void setLatentFingerCaseId(String latentFingerCaseId) {
        this.latentFingerCaseId = latentFingerCaseId;
    }

    public String getLatentFingerOriginalSystemCaseId() {
        return latentFingerOriginalSystemCaseId;
    }

    public void setLatentFingerOriginalSystemCaseId(String latentFingerOriginalSystemCaseId) {
        this.latentFingerOriginalSystemCaseId = latentFingerOriginalSystemCaseId;
    }

    public String getLatentFingerLatentSurveyId() {
        return latentFingerLatentSurveyId;
    }

    public void setLatentFingerLatentSurveyId(String latentFingerLatentSurveyId) {
        this.latentFingerLatentSurveyId = latentFingerLatentSurveyId;
    }

    public String getLatentFingerOriginalSystemFingerId() {
        return latentFingerOriginalSystemFingerId;
    }

    public void setLatentFingerOriginalSystemFingerId(String latentFingerOriginalSystemFingerId) {
        this.latentFingerOriginalSystemFingerId = latentFingerOriginalSystemFingerId;
    }

    public String getLatentFingerLatentPhysicalId() {
        return latentFingerLatentPhysicalId;
    }

    public void setLatentFingerLatentPhysicalId(String latentFingerLatentPhysicalId) {
        this.latentFingerLatentPhysicalId = latentFingerLatentPhysicalId;
    }

    public String getLatentFingerCardId() {
        return latentFingerCardId;
    }

    public void setLatentFingerCardId(String latentFingerCardId) {
        this.latentFingerCardId = latentFingerCardId;
    }

    public String getFingerPrintOriginalSystemPersonId() {
        return fingerPrintOriginalSystemPersonId;
    }

    public void setFingerPrintOriginalSystemPersonId(String fingerPrintOriginalSystemPersonId) {
        this.fingerPrintOriginalSystemPersonId = fingerPrintOriginalSystemPersonId;
    }

    public String getFingerPrintJingZongPersonId() {
        return fingerPrintJingZongPersonId;
    }

    public void setFingerPrintJingZongPersonId(String fingerPrintJingZongPersonId) {
        this.fingerPrintJingZongPersonId = fingerPrintJingZongPersonId;
    }

    public String getFingerPrintXzPersonId() {
        return fingerPrintXzPersonId;
    }

    public void setFingerPrintXzPersonId(String fingerPrintXzPersonId) {
        this.fingerPrintXzPersonId = fingerPrintXzPersonId;
    }

    public String getFingerPrintCardId() {
        return fingerPrintCardId;
    }

    public void setFingerPrintCardId(String fingerPrintCardId) {
        this.fingerPrintCardId = fingerPrintCardId;
    }

    public String getFingerPrintPostionCode() {
        return fingerPrintPostionCode;
    }

    public void setFingerPrintPostionCode(String fingerPrintPostionCode) {
        this.fingerPrintPostionCode = fingerPrintPostionCode;
    }

    public String getFingerPrintComparisonMethodCode() {
        return fingerPrintComparisonMethodCode;
    }

    public void setFingerPrintComparisonMethodCode(String fingerPrintComparisonMethodCode) {
        this.fingerPrintComparisonMethodCode = fingerPrintComparisonMethodCode;
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
