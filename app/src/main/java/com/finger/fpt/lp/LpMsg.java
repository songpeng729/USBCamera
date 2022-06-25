package com.finger.fpt.lp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 案件信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpMsg {
	
	/**
	 * 原始系统_案事件编号
	 */
	@XmlElement(name = "ysxt_asjbh")
	private String caseId = "";
	
	/**
	 * 案事件编号
	 */
	@XmlElement(name = "asjbh")
	private String ciCaseId = "";
	
	/**
	 * 现场勘验编号
	 */
	@XmlElement(name = "xckybh")
	private String xkId = "";
	
	/**
	 * 现场指掌纹卡编号
	 */
	@XmlElement(name = "xczzhwkbh")
	private String cardId = "";
	
	/**
	 * 案件类别代码集合
	 */
	@XmlElementWrapper(name = "caseClassSet")
	@XmlElement(name = "ajlbdm")
	private List<String> caseClassCodeList;
	
	/**
	 * 损失价值（人民币元）
	 */
	@XmlElement(name = "ssjzrmby")
	private String amount = "";
	
	/**
	 * 案事件发生地点_行政区划代码
	 */
	@XmlElement(name = "asjfsdd_xzqhdm")
	private String occurPlaceCode = "";
	
	/**
	 * 案事件发生地点_地址名称
	 */
	@XmlElement(name = "asjfsdd_dzmc")
	private String caseOccurPlaceDetail = "";
	
	/**
	 * 简要案情
	 */
	@XmlElement(name = "jyaq")
	private String caseBriefDetail = "";
	
	/**
	 * 是否命案_判断标识
	 */
	@XmlElement(name = "sfma_pdbz")
	private String isMurder = "";

	
	
	
	
	/**
	 * 原始系统_案事件编号
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 原始系统_案事件编号
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/**
	 * 案事件编号
	 */
	public String getCiCaseId() {
		return ciCaseId;
	}

	/**
	 * 案事件编号
	 */
	public void setCiCaseId(String ciCaseId) {
		this.ciCaseId = ciCaseId;
	}

	/**
	 * 现场勘验编号
	 */
	public String getXkId() {
		return xkId;
	}

	/**
	 * 现场勘验编号
	 */
	public void setXkId(String xkId) {
		this.xkId = xkId;
	}

	/**
	 * 现场指掌纹卡编号
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * 现场指掌纹卡编号
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * 案件类别代码集合
	 */
	public List<String> getCaseClassCodeList() {
		return caseClassCodeList;
	}

	/**
	 * 案件类别代码集合
	 */
	public void setCaseClassCodeList(List<String> caseClassCodeList) {
		this.caseClassCodeList = caseClassCodeList;
	}

	/**
	 * 损失价值（人民币元）
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * 损失价值（人民币元）
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * 案事件发生地点_行政区划代码
	 */
	public String getOccurPlaceCode() {
		return occurPlaceCode;
	}

	/**
	 * 案事件发生地点_行政区划代码
	 */
	public void setOccurPlaceCode(String occurPlaceCode) {
		this.occurPlaceCode = occurPlaceCode;
	}

	/**
	 * 案事件发生地点_地址名称
	 */
	public String getCaseOccurPlaceDetail() {
		return caseOccurPlaceDetail;
	}

	/**
	 * 案事件发生地点_地址名称
	 */
	public void setCaseOccurPlaceDetail(String caseOccurPlaceDetail) {
		this.caseOccurPlaceDetail = caseOccurPlaceDetail;
	}

	/**
	 * 简要案情
	 */
	public String getCaseBriefDetail() {
		return caseBriefDetail;
	}

	/**
	 * 简要案情
	 */
	public void setCaseBriefDetail(String caseBriefDetail) {
		this.caseBriefDetail = caseBriefDetail;
	}

	/**
	 * 是否命案_判断标识
	 */
	public String getIsMurder() {
		return isMurder;
	}

	/**
	 * 是否命案_判断标识
	 */
	public void setIsMurder(String isMurder) {
		this.isMurder = isMurder;
	}
	
	
}
