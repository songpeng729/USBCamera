package com.finger.fpt.lp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场指掌纹信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LatentPackage {
	
	/**
	 * 案件信息
	 */
	@XmlElement(name = "caseMsg")
	private LpMsg lpMsg;
	
	/**
	 * 提取信息
	 */
	@XmlElement(name = "collectInfoMsg")
	private LpExtractInfo lpExtractInfo;
	
	/**
	 * 指纹信息
	 */
	@XmlElement(name = "fingers")
	private List<LpFinger> lpFingerList;
	
	/**
	 * 掌纹信息
	 */
	@XmlElement(name = "palms")
	private List<LpPalm> lpPalmList;
	
	
	

	/**
	 * 案件信息
	 */
	public LpMsg getLpMsg() {
		return lpMsg;
	}

	/**
	 * 案件信息
	 */
	public void setLpMsg(LpMsg lpMsg) {
		this.lpMsg = lpMsg;
	}

	/**
	 * 提取信息
	 */
	public LpExtractInfo getLpExtractInfo() {
		return lpExtractInfo;
	}

	/**
	 * 提取信息
	 */
	public void setLpExtractInfo(LpExtractInfo lpExtractInfo) {
		this.lpExtractInfo = lpExtractInfo;
	}

	/**
	 * 指纹信息
	 */
	public List<LpFinger> getLpFingerList() {
		return lpFingerList;
	}

	/**
	 * 指纹信息
	 */
	public void setLpFingerList(List<LpFinger> lpFingerList) {
		this.lpFingerList = lpFingerList;
	}

	/**
	 * 掌纹信息
	 */
	public List<LpPalm> getLpPalmList() {
		return lpPalmList;
	}

	/**
	 * 掌纹信息
	 */
	public void setLpPalmList(List<LpPalm> lpPalmList) {
		this.lpPalmList = lpPalmList;
	}
}
