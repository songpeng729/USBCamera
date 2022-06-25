package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 正查比对结果集合
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LTResultMsgSet {

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
     * 比对结果_原始系统_案事件相关人员编号
     */
    @XmlElement(name = "bdjg_ysxt_asjxgrybh")
    private String resultOriginalSystemPersonId = "";
    /**
     * 比对结果_警综人员编号
     */
    @XmlElement(name = "bdjg_jzrybh")
    private String resultJingZongPersonId = "";
    /**
     * 比对结果_案事件相关人员编号
     */
    @XmlElement(name = "bdjg_asjxgrybh")
    private String resultXzPersonId = "";
    /**
     * 比对结果_指掌纹卡编号
     */
    @XmlElement(name = "bdjg_zzhwkbh")
    private String resultCardId = "";
    /**
     * 比对结果_指掌位代码
     */
    @XmlElement(name = "bdjg_zzhwdm")
    private String resultFingerPalmPostionCode = "";

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

    public String getResultOriginalSystemPersonId() {
        return resultOriginalSystemPersonId;
    }

    public void setResultOriginalSystemPersonId(String resultOriginalSystemPersonId) {
        this.resultOriginalSystemPersonId = resultOriginalSystemPersonId;
    }

    public String getResultJingZongPersonId() {
        return resultJingZongPersonId;
    }

    public void setResultJingZongPersonId(String resultJingZongPersonId) {
        this.resultJingZongPersonId = resultJingZongPersonId;
    }

    public String getResultXzPersonId() {
        return resultXzPersonId;
    }

    public void setResultXzPersonId(String resultXzPersonId) {
        this.resultXzPersonId = resultXzPersonId;
    }

    public String getResultCardId() {
        return resultCardId;
    }

    public void setResultCardId(String resultCardId) {
        this.resultCardId = resultCardId;
    }

    public String getResultFingerPalmPostionCode() {
        return resultFingerPalmPostionCode;
    }

    public void setResultFingerPalmPostionCode(String resultFingerPalmPostionCode) {
        this.resultFingerPalmPostionCode = resultFingerPalmPostionCode;
    }

}
