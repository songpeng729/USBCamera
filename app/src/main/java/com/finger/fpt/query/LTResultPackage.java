package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 指掌纹正查比对结果信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LTResultPackage {

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
     * 比对单位_公安机关机构代码
     */
    @XmlElement(name = "bddw_gajgjgdm")
    private String comparisonUnitCode = "";
    /**
     * 比对单位_公安机关名称
     */
    @XmlElement(name = "bddw_gajgmc")
    private String comparisonUnitName = "";
    /**
     * 比对完成时间
     */
    @XmlElement(name = "bdwcsj")
    private String comparisonCompleteDateTime = "";
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
     * 比对结果集合
     */
    @XmlElementWrapper(name="resultMsgSet")
    @XmlElement(name = "resultMsg")
    private List<LTResultMsgSet> ltResultMsgSets;

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

    public String getComparisonUnitCode() {
        return comparisonUnitCode;
    }

    public void setComparisonUnitCode(String comparisonUnitCode) {
        this.comparisonUnitCode = comparisonUnitCode;
    }

    public String getComparisonUnitName() {
        return comparisonUnitName;
    }

    public void setComparisonUnitName(String comparisonUnitName) {
        this.comparisonUnitName = comparisonUnitName;
    }

    public String getComparisonCompleteDateTime() {
        return comparisonCompleteDateTime;
    }

    public void setComparisonCompleteDateTime(String comparisonCompleteDateTime) {
        this.comparisonCompleteDateTime = comparisonCompleteDateTime;
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

    public List<LTResultMsgSet> getLtResultMsgSets() {
        return ltResultMsgSets;
    }

    public void setLtResultMsgSets(List<LTResultMsgSet> ltResultMsgSets) {
        this.ltResultMsgSets = ltResultMsgSets;
    }
}
