package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场指掌纹查询比对请求信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LatentTaskPackage {
    /**
     * 任务编号
     */
    @XmlElement(name = "rwbh")
    private String taskId = "";
    /**
     * 指掌纹比对任务类型代码
     */
    @XmlElement(name = "zzhwbdrwlxdm")
    private String taskTypeCode = "";
    /**
     * 指纹比对系统描述
     */
    @XmlElement(name = "zwbdxtlxms")
    private String comparisonSystemDescript = "";
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
     * 提交时间
     */
    @XmlElement(name = "tjsj")
    private String submitDateTime = "";

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public String getComparisonSystemDescript() {
        return comparisonSystemDescript;
    }

    public void setComparisonSystemDescript(String comparisonSystemDescript) {
        this.comparisonSystemDescript = comparisonSystemDescript;
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

    public String getLatentCardId() {
        return latentCardId;
    }

    public void setLatentCardId(String latentCardId) {
        this.latentCardId = latentCardId;
    }

    public String getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(String submitDateTime) {
        this.submitDateTime = submitDateTime;
    }
}
