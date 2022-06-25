package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 指掌纹查重比对结果信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TTResultPackage {
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
     * 原始系统_案事件相关人员编号
     */
    @XmlElement(name = "ysxt_asjxgrybh")
    private String originalSystemPersonId = "";
    /**
     * 警综人员编号
     */
    @XmlElement(name = "jzrybh")
    private String jingZongPersonId = "";
    /**
     * 案事件相关人员编号
     */
    @XmlElement(name = "asjxgrybh")
    private String xzPersonId = "";
    /**
     * 指掌纹卡编号
     */
    @XmlElement(name = "zzhwkbh")
    private String cardId = "";
    /**
     * 是否指纹_判断标识
     */
    @XmlElement(name = "sfzw_pdbz")
    private String whetherFingerJudgmentMark = "";
    /**
     * 比对结果集合
     */
    @XmlElementWrapper(name="resultMsgSet")
    @XmlElement(name = "resultMsg")
    private List<TTResultMsgSet> ttResultMsgSets;

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

    public String getOriginalSystemPersonId() {
        return originalSystemPersonId;
    }

    public void setOriginalSystemPersonId(String originalSystemPersonId) {
        this.originalSystemPersonId = originalSystemPersonId;
    }

    public String getJingZongPersonId() {
        return jingZongPersonId;
    }

    public void setJingZongPersonId(String jingZongPersonId) {
        this.jingZongPersonId = jingZongPersonId;
    }

    public String getXzPersonId() {
        return xzPersonId;
    }

    public void setXzPersonId(String xzPersonId) {
        this.xzPersonId = xzPersonId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getWhetherFingerJudgmentMark() {
        return whetherFingerJudgmentMark;
    }

    public void setWhetherFingerJudgmentMark(String whetherFingerJudgmentMark) {
        this.whetherFingerJudgmentMark = whetherFingerJudgmentMark;
    }

    public List<TTResultMsgSet> getTtResultMsgSets() {
        return ttResultMsgSets;
    }

    public void setTtResultMsgSets(List<TTResultMsgSet> ttResultMsgSets) {
        this.ttResultMsgSets = ttResultMsgSets;
    }
}
