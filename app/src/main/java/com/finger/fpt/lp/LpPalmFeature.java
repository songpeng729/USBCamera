package com.finger.fpt.lp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 现场掌纹特征
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpPalmFeature {
	
	/**
	 * 现场掌纹_现场指掌纹编号
	 */
	@XmlElement(name = "xczhw_xczzhwbh")
	private String palmId = "";

	/**
	 * 现场掌纹_现场物证编号
	 */
	@XmlElement(name = "xczhw_xcwzbh")
	private String physicalEvidenceNo = "";

	/**
	 * 现场掌纹_特征组合标识符
	 */
	@XmlElement(name = "xczhw_tzzhbsf")
	private String mntCombination = "";

	/**
	 * 现场掌纹_特征组合描述信息
	 */
	@XmlElement(name = "xczhw_tzzhmsxx")
	private String mntCombinationMessage = "";

	/**
	 * 现场掌纹_指掌纹特征提取方式代码
	 */
	@XmlElement(name = "xczhw_zzhwtztqfsdm")
	private String extractMethod = "";

	/**
	 * 现场掌纹_指掌纹比对状态代码
	 */
	@XmlElement(name = "xczhw_zzhwbdztdm")
	private String matchStatus = "";

	/**
	 * 现场掌纹_掌位分析_简要情况
	 */
	@XmlElement(name = "xczhw_zhwfx_jyqk")
	private String fgp = "";

	/**
	 * 现场掌纹_掌纹折返点信息
	 */
	@XmlElementWrapper(name = "coreLikePatternSet")
	@XmlElement(name = "coreLikePattern")
	private List<LpPalmCoreLikePattern> lpPalmCoreLikePatternList;

	/**
	 * 现场掌纹_掌纹三角点信息
	 */
	@XmlElementWrapper(name = "deltaSet")
	@XmlElement(name = "delta")
	private List<LpPalmDelta> lpPalmDeltaList;

	/**
	 * 现场掌纹_掌纹特征点信息
	 */
	@XmlElementWrapper(name = "minutiaSet")
	@XmlElement(name = "minutia")
	private List<LpPalmFeatureMinutia> lpPalmFeatureMinutiaList;

	/**
	 * 现场掌纹_自定义信息
	 */
	@XmlElement(name = "xczhw_zdyxx")
	private String customInfo = "";
	
	

	/**
	 * 现场掌纹_现场指掌纹编号
	 */
	public String getPalmId() {
		return palmId;
	}

	/**
	 * 现场掌纹_现场指掌纹编号
	 */
	public void setPalmId(String palmId) {
		this.palmId = palmId;
	}

	/**
	 * 现场掌纹_现场物证编号
	 */
	public String getPhysicalEvidenceNo() {
		return physicalEvidenceNo;
	}

	/**
	 * 现场掌纹_现场物证编号
	 */
	public void setPhysicalEvidenceNo(String physicalEvidenceNo) {
		this.physicalEvidenceNo = physicalEvidenceNo;
	}

	/**
	 * 现场掌纹_特征组合标识符
	 */
	public String getMntCombination() {
		return mntCombination;
	}

	/**
	 * 现场掌纹_特征组合标识符
	 */
	public void setMntCombination(String mntCombination) {
		this.mntCombination = mntCombination;
	}

	/**
	 * 现场掌纹_特征组合描述信息
	 */
	public String getMntCombinationMessage() {
		return mntCombinationMessage;
	}

	/**
	 * 现场掌纹_特征组合描述信息
	 */
	public void setMntCombinationMessage(String mntCombinationMessage) {
		this.mntCombinationMessage = mntCombinationMessage;
	}

	/**
	 * 现场掌纹_指掌纹特征提取方式代码
	 */
	public String getExtractMethod() {
		return extractMethod;
	}

	/**
	 * 现场掌纹_指掌纹特征提取方式代码
	 */
	public void setExtractMethod(String extractMethod) {
		this.extractMethod = extractMethod;
	}

	/**
	 * 现场掌纹_指掌纹比对状态代码
	 */
	public String getMatchStatus() {
		return matchStatus;
	}

	/**
	 * 现场掌纹_指掌纹比对状态代码
	 */
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	/**
	 * 现场掌纹_掌位分析_简要情况
	 */
	public String getFgp() {
		return fgp;
	}

	/**
	 * 现场掌纹_掌位分析_简要情况
	 */
	public void setFgp(String fgp) {
		this.fgp = fgp;
	}

	/**
	 * 现场掌纹_掌纹折返点信息
	 */
	public List<LpPalmCoreLikePattern> getLpPalmCoreLikePatternList() {
		return lpPalmCoreLikePatternList;
	}

	/**
	 * 现场掌纹_掌纹折返点信息
	 */
	public void setLpPalmCoreLikePatternList(List<LpPalmCoreLikePattern> lpPalmCoreLikePatternList) {
		this.lpPalmCoreLikePatternList = lpPalmCoreLikePatternList;
	}

	/**
	 * 现场掌纹_掌纹三角点信息
	 */
	public List<LpPalmDelta> getLpPalmDeltaList() {
		return lpPalmDeltaList;
	}

	/**
	 * 现场掌纹_掌纹三角点信息
	 */
	public void setLpPalmDeltaList(List<LpPalmDelta> lpPalmDeltaList) {
		this.lpPalmDeltaList = lpPalmDeltaList;
	}

	/**
	 * 现场掌纹_掌纹特征点信息
	 */
	public List<LpPalmFeatureMinutia> getLpPalmFeatureMinutiaList() {
		return lpPalmFeatureMinutiaList;
	}

	/**
	 * 现场掌纹_掌纹特征点信息
	 */
	public void setLpPalmFeatureMinutiaList(List<LpPalmFeatureMinutia> lpPalmFeatureMinutiaList) {
		this.lpPalmFeatureMinutiaList = lpPalmFeatureMinutiaList;
	}

	/**
	 * 现场掌纹_自定义信息
	 */
	public String getCustomInfo() {
		return customInfo;
	}

	/**
	 * 现场掌纹_自定义信息
	 */
	public void setCustomInfo(String customInfo) {
		this.customInfo = customInfo;
	}
}
