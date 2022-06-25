package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 捺印指掌纹查询比对请求信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintTaskPackage {

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
     * 原始系统_案事件相关人员编号
     */
    @XmlElement(name = "ysxt_asjxgrybh")
    private String originalSystemPersonId = "";
    /**
     *  警综人员编号
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

    public String getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(String submitDateTime) {
        this.submitDateTime = submitDateTime;
    }
}
