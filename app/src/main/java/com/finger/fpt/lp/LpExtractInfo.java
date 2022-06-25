package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 提取信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpExtractInfo {
	
	/**
	 * 指纹比对系统描述
	 */
	@XmlElement(name = "zwbdxtlxms")
	private String systemType = "";
	
	/**
	 * 提取单位_公安机关机构代码
	 */
	@XmlElement(name = "tqdw_gajgjgdm")
	private String extractUnitCode = "";
	
	/**
	 * 提取单位_公安机关名称
	 */
	@XmlElement(name = "tqdw_gajgmc")
	private String extractUnitName = "";
	
	/**
	 * 提取人员_姓名
	 */
	@XmlElement(name = "tqry_xm")
	private String extractor = "";
	
	/**
	 * 提取人员_公民身份号码
	 */
	@XmlElement(name = "tqry_gmsfhm")
	private String extractorIdCard = "";
	
	/**
	 * 提取人员_联系电话
	 */
	@XmlElement(name = "tqry_lxdh")
	private String extractorPhone = "";
	
	/**
	 * 提取时间（yyyyMMddHHmmss）
	 */
	@XmlElement(name = "tqsj")
	private String extractDate = "";
	
	
	
	
	

	/**
	 * 指纹比对系统描述
	 */
	public String getSystemType() {
		return systemType;
	}

	/**
	 * 指纹比对系统描述
	 */
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	/**
	 * 提取单位_公安机关机构代码
	 */
	public String getExtractUnitCode() {
		return extractUnitCode;
	}

	/**
	 * 提取单位_公安机关机构代码
	 */
	public void setExtractUnitCode(String extractUnitCode) {
		this.extractUnitCode = extractUnitCode;
	}

	/**
	 * 提取单位_公安机关名称
	 */
	public String getExtractUnitName() {
		return extractUnitName;
	}

	/**
	 * 提取单位_公安机关名称
	 */
	public void setExtractUnitName(String extractUnitName) {
		this.extractUnitName = extractUnitName;
	}

	/**
	 * 提取人员_姓名
	 */
	public String getExtractor() {
		return extractor;
	}

	/**
	 * 提取人员_姓名
	 */
	public void setExtractor(String extractor) {
		this.extractor = extractor;
	}

	/**
	 * 提取人员_公民身份号码
	 */
	public String getExtractorIdCard() {
		return extractorIdCard;
	}

	/**
	 * 提取人员_公民身份号码
	 */
	public void setExtractorIdCard(String extractorIdCard) {
		this.extractorIdCard = extractorIdCard;
	}

	/**
	 * 提取人员_联系电话
	 */
	public String getExtractorPhone() {
		return extractorPhone;
	}

	/**
	 * 提取人员_联系电话
	 */
	public void setExtractorPhone(String extractorPhone) {
		this.extractorPhone = extractorPhone;
	}

	/**
	 * 提取时间（yyyyMMddHHmmss）
	 */
	public String getExtractDate() {
		return extractDate;
	}

	/**
	 * 提取时间（yyyyMMddHHmmss）
	 */
	public void setExtractDate(String extractDate) {
		this.extractDate = extractDate;
	}
}
