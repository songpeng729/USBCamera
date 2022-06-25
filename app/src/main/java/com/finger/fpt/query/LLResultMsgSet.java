package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 串查比对结果集合
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LLResultMsgSet {

    /**
     * 比对结果_名次
     */
    @XmlElement(name = "bdjg_mc")
    private String resultRanking = "";
    /**
     * 比对结果_得分
     */
    @XmlElement(name = "bdjg_df")
    private String resultScore = "";
    /**
     * 比对结果_原始系统_案事件编号
     */
    @XmlElement(name = "bdjg_ysxt_asjbh")
    private String resultOriginalSystemCaseId = "";
    /**
     * 比对结果_案事件编号
     */
    @XmlElement(name = "bdjg_asjbh")
    private String resultCaseId = "";
    /**
     * 比对结果_现场勘验编号
     */
    @XmlElement(name = "bdjg_xckybh")
    private String resultLatentSurveyId = "";
    /**
     * 比对结果_现场物证编号
     */
    @XmlElement(name = "bdjg_xcwzbh")
    private String resultLatentPhysicalId = "";
    /**
     * 比对结果_原始系统_现场指掌纹编号
     */
    @XmlElement(name = "bdjg_ysxt_xczzhwbh")
    private String fingerOrPalmId = "";
    /**
     * 比对结果_现场指掌纹卡编号
     */
    @XmlElement(name = "bdjg_xczzhwkbh")
    private String resultLatentCardId = "";

    public String getResultRanking() {
        return resultRanking;
    }

    public void setResultRanking(String resultRanking) {
        this.resultRanking = resultRanking;
    }

    public String getResultScore() {
        return resultScore;
    }

    public void setResultScore(String resultScore) {
        this.resultScore = resultScore;
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

    public String getResultLatentPhysicalId() {
        return resultLatentPhysicalId;
    }

    public void setResultLatentPhysicalId(String resultLatentPhysicalId) {
        this.resultLatentPhysicalId = resultLatentPhysicalId;
    }

    public String getFingerOrPalmId() {
        return fingerOrPalmId;
    }

    public void setFingerOrPalmId(String fingerOrPalmId) {
        this.fingerOrPalmId = fingerOrPalmId;
    }

    public String getResultLatentCardId() {
        return resultLatentCardId;
    }

    public void setResultLatentCardId(String resultLatentCardId) {
        this.resultLatentCardId = resultLatentCardId;
    }
}
