package com.finger.fpt.query;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 指掌纹查重比中信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TTHitResultPackage {

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
     * 指掌纹查重比中类型代码
     */
    @XmlElement(name = "zzhwccbzlxdm")
    private String ttHitTypeCode = "";
    /**
     * 原始系统_案事件相关人员编号
     */
    @XmlElement(name = "ysxt_asjxgrybh")
    private String originalPersonId = "";
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
     * 比中结果_原始系统_案事件相关人员编号
     */
    @XmlElement(name = "bzjg_ysxt_asjxgrybh")
    private String resultOriginalSystemPersonId = "";
    /**
     * 比中结果_警综人员编号
     */
    @XmlElement(name = "bzjg_jzrybh")
    private String resultjingZongPersonId = "";
    /**
     * 比中结果_案事件相关人员编号
     */
    @XmlElement(name = "bzjg_asjxgrybh")
    private String resultXzPersonId = "";
    /**
     * 比中结果_指掌纹卡编号
     */
    @XmlElement(name = "bzjg_zzhwkbh")
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

    public String getTtHitTypeCode() {
        return ttHitTypeCode;
    }

    public void setTtHitTypeCode(String ttHitTypeCode) {
        this.ttHitTypeCode = ttHitTypeCode;
    }

    public String getOriginalPersonId() {
        return originalPersonId;
    }

    public void setOriginalPersonId(String originalPersonId) {
        this.originalPersonId = originalPersonId;
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

    public String getResultOriginalSystemPersonId() {
        return resultOriginalSystemPersonId;
    }

    public void setResultOriginalSystemPersonId(String resultOriginalSystemPersonId) {
        this.resultOriginalSystemPersonId = resultOriginalSystemPersonId;
    }

    public String getResultjingZongPersonId() {
        return resultjingZongPersonId;
    }

    public void setResultjingZongPersonId(String resultjingZongPersonId) {
        this.resultjingZongPersonId = resultjingZongPersonId;
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
