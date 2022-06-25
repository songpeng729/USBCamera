package com.finger.fpt.lp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 现场指纹特征
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpFingerFeature {
	
	/**
	 * 原始系统_现场指掌纹编号
	 */
	@XmlElement(name = "ysxt_xczzhwbh")
	private String fingerId = "";
	
	/**
	 * 现场物证编号
	 */
	@XmlElement(name = "xcwzbh")
	private String physicalEvidenceNo = "";
	
	/**
	 * 现场指纹_特征组合标识符
	 */
	@XmlElement(name = "xczw_tzzhbsf")
	private String mntCombination = "";
	
	/**
	 * 现场指纹_特征组合描述信息
	 */
	@XmlElement(name = "xczw_tzzhmsxx")
	private String mntCombinationMessage = "";
	
	/**
	 * 特征提取方式代码
	 */
	@XmlElement(name = "xczw_zzhwtztqfsdm")
	private String extractMethod = "";
	
	/**
	 * 现场指纹_指位分析_简要情况
	 */
	@XmlElement(name = "xczw_zhiwfx_jyqk")
	private String fgp = "";
	
	/**
	 * 现场指纹_纹型分析_简要情况
	 */
	@XmlElement(name = "xczw_wxfx_jyqk")
	private String pattern = "";
	
	/**
	 * 现场指纹_指纹方向_特征方向
	 */
	@XmlElement(name = "xczw_zwfx_tzfx")
	private String featureDir = "";
	
	/**
	 * 现场指纹_指纹方向_特征方向范围
	 */
	@XmlElement(name = "xczw_zwfx_tzfxfw")
	private String featureDirRange = "";

	/**
	 * 现场指纹_指纹中心点_特征X坐标
	 */
	@XmlElement(name = "xczw_zwzxd_tzxzb")
	private String centerPointX = "";

	/**
	 * 现场指纹_指纹中心点_特征Y坐标
	 */
	@XmlElement(name = "xczw_zwzxd_tzyzb")
	private String centerPointY = "";

	/**
	 * 现场指纹_指纹中心点_特征坐标范围
	 */
	@XmlElement(name = "xczw_zwzxd_tzzbfw")
	private String centerPointRange = "";

	/**
	 * 现场指纹_指纹中心点_特征方向
	 */
	@XmlElement(name = "xczw_zwzxd_tzfx")
	private String centerPointDir = "";

	/**
	 * 现场指纹_指纹中心点_特征方向范围
	 */
	@XmlElement(name = "xczw_zwzxd_tzfxfw")
	private String centerPointDirRange = "";

	/**
	 * 现场指纹_指纹中心点_特征可靠度
	 */
	@XmlElement(name = "xczw_zwzxd_tzkkd")
	private String centerPointRel = "";

	/**
	 * 现场指纹_指纹副中心_特征X坐标
	 */
	@XmlElement(name = "xczw_zwfzx_tzxzb")
	private String subCenterPointX = "";

	/**
	 * 现场指纹_指纹副中心_特征Y坐标
	 */
	@XmlElement(name = "xczw_zwfzx_tzyzb")
	private String subCenterPointY = "";

	/**
	 * 现场指纹_指纹副中心_特征坐标范围
	 */
	@XmlElement(name = "xczw_zwfzx_tzzbfw")
	private String subCenterPointRange = "";

	/**
	 * 现场指纹_指纹副中心_特征方向
	 */
	@XmlElement(name = "xczw_zwfzx_tzfx")
	private String subCenterPointDir = "";

	/**
	 * 现场指纹_指纹副中心_特征方向范围
	 */
	@XmlElement(name = "xczw_zwfzx_tzfxfw")
	private String subCenterPointDirRange = "";

	/**
	 * 现场指纹_指纹副中心_特征可靠度
	 */
	@XmlElement(name = "xczw_zwfzx_tzkkd")
	private String subCenterPointRel = "";

	/**
	 * 现场指纹_指纹左三角_特征X坐标
	 */
	@XmlElement(name = "xczw_zwzsj_tzxzb")
	private String leftTriangleX = "";

	/**
	 * 现场指纹_指纹左三角_特征Y坐标
	 */
	@XmlElement(name = "xczw_zwzsj_tzyzb")
	private String leftTriangleY = "";

	/**
	 * 现场指纹_指纹左三角_特征坐标范围
	 */
	@XmlElement(name = "xczw_zwzsj_tzzbfw")
	private String leftTriangleRange = "";

	/**
	 * 现场指纹_指纹左三角_特征方向
	 */
	@XmlElement(name = "xczw_zwzsj_tzfx")
	private String leftTriangleDir = "";

	/**
	 * 现场指纹_指纹左三角_特征方向范围
	 */
	@XmlElement(name = "xczw_zwzsj_tzfxfw")
	private String leftTriangleDirRange = "";

	/**
	 * 现场指纹_指纹左三角_特征可靠度
	 */
	@XmlElement(name = "xczw_zwzsj_tzkkd")
	private String leftTriangleRel = "";

	/**
	 * 现场指纹_指纹右三角_特征X坐标
	 */
	@XmlElement(name = "xczw_zwysj_tzxzb")
	private String rightTriangleX = "";

	/**
	 * 现场指纹_指纹右三角_特征Y坐标
	 */
	@XmlElement(name = "xczw_zwysj_tzyzb")
	private String rightTriangleY = "";

	/**
	 * 现场指纹_指纹右三角_特征坐标范围
	 */
	@XmlElement(name = "xczw_zwysj_tzzbfw")
	private String rightTriangleRange = "";

	/**
	 * 现场指纹_指纹右三角_特征方向
	 */
	@XmlElement(name = "xczw_zwysj_tzfx")
	private String rightTriangleDir = "";

	/**
	 * 现场指纹_指纹右三角_特征方向范围
	 */
	@XmlElement(name = "xczw_zwysj_tzfxfw")
	private String rightTriangleDirRange = "";

	/**
	 * 现场指纹_指纹右三角_特征可靠度
	 */
	@XmlElement(name = "xczw_zwysj_tzkkd")
	private String rightTriangleRel = "";

	/**
	 * 指纹特征点信息
	 */
	@XmlElementWrapper(name = "minutiaSet")
	@XmlElement(name = "minutia")
	private List<LpFingerFeatureMinutia> lpFingerFeatureMinutiaList;

	/**
	 * 现场指纹_自定义信息
	 */
	@XmlElement(name = "xczw_zdyxx")
	private String customInfo = "";
	
	
	
	
	/**
	 * 原始系统_现场指掌纹编号
	 */
	public String getFingerId() {
		return fingerId;
	}

	/**
	 * 原始系统_现场指掌纹编号
	 */
	public void setFingerId(String fingerId) {
		this.fingerId = fingerId;
	}

	/**
	 * 现场物证编号
	 */
	public String getPhysicalEvidenceNo() {
		return physicalEvidenceNo;
	}

	/**
	 * 现场物证编号
	 */
	public void setPhysicalEvidenceNo(String physicalEvidenceNo) {
		this.physicalEvidenceNo = physicalEvidenceNo;
	}

	/**
	 * 现场指纹_特征组合标识符
	 */
	public String getMntCombination() {
		return mntCombination;
	}

	/**
	 * 现场指纹_特征组合标识符
	 */
	public void setMntCombination(String mntCombination) {
		this.mntCombination = mntCombination;
	}

	/**
	 * 现场指纹_特征组合描述信息
	 */
	public String getMntCombinationMessage() {
		return mntCombinationMessage;
	}

	/**
	 * 现场指纹_特征组合描述信息
	 */
	public void setMntCombinationMessage(String mntCombinationMessage) {
		this.mntCombinationMessage = mntCombinationMessage;
	}

	/**
	 * 特征提取方式代码
	 */
	public String getExtractMethod() {
		return extractMethod;
	}

	/**
	 * 特征提取方式代码
	 */
	public void setExtractMethod(String extractMethod) {
		this.extractMethod = extractMethod;
	}

	/**
	 * 现场指纹_指位分析_简要情况
	 */
	public String getFgp() {
		return fgp;
	}

	/**
	 * 现场指纹_指位分析_简要情况
	 */
	public void setFgp(String fgp) {
		this.fgp = fgp;
	}

	/**
	 * 现场指纹_纹型分析_简要情况
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * 现场指纹_纹型分析_简要情况
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * 现场指纹_指纹方向_特征方向
	 */
	public String getFeatureDir() {
		return featureDir;
	}

	/**
	 * 现场指纹_指纹方向_特征方向
	 */
	public void setFeatureDir(String featureDir) {
		this.featureDir = featureDir;
	}

	/**
	 * 现场指纹_指纹方向_特征方向范围
	 */
	public String getFeatureDirRange() {
		return featureDirRange;
	}

	/**
	 * 现场指纹_指纹方向_特征方向范围
	 */
	public void setFeatureDirRange(String featureDirRange) {
		this.featureDirRange = featureDirRange;
	}

	/**
	 * 现场指纹_指纹中心点_特征X坐标
	 */
	public String getCenterPointX() {
		return centerPointX;
	}

	/**
	 * 现场指纹_指纹中心点_特征X坐标
	 */
	public void setCenterPointX(String centerPointX) {
		this.centerPointX = centerPointX;
	}

	/**
	 * 现场指纹_指纹中心点_特征Y坐标
	 */
	public String getCenterPointY() {
		return centerPointY;
	}

	/**
	 * 现场指纹_指纹中心点_特征Y坐标
	 */
	public void setCenterPointY(String centerPointY) {
		this.centerPointY = centerPointY;
	}

	/**
	 * 现场指纹_指纹中心点_特征坐标范围
	 */
	public String getCenterPointRange() {
		return centerPointRange;
	}

	/**
	 * 现场指纹_指纹中心点_特征坐标范围
	 */
	public void setCenterPointRange(String centerPointRange) {
		this.centerPointRange = centerPointRange;
	}

	/**
	 * 现场指纹_指纹中心点_特征方向
	 */
	public String getCenterPointDir() {
		return centerPointDir;
	}

	/**
	 * 现场指纹_指纹中心点_特征方向
	 */
	public void setCenterPointDir(String centerPointDir) {
		this.centerPointDir = centerPointDir;
	}

	/**
	 * 现场指纹_指纹中心点_特征方向范围
	 */
	public String getCenterPointDirRange() {
		return centerPointDirRange;
	}

	/**
	 * 现场指纹_指纹中心点_特征方向范围
	 */
	public void setCenterPointDirRange(String centerPointDirRange) {
		this.centerPointDirRange = centerPointDirRange;
	}

	/**
	 * 现场指纹_指纹中心点_特征可靠度
	 */
	public String getCenterPointRel() {
		return centerPointRel;
	}

	/**
	 * 现场指纹_指纹中心点_特征可靠度
	 */
	public void setCenterPointRel(String centerPointRel) {
		this.centerPointRel = centerPointRel;
	}

	/**
	 * 现场指纹_指纹副中心_特征X坐标
	 */
	public String getSubCenterPointX() {
		return subCenterPointX;
	}

	/**
	 * 现场指纹_指纹副中心_特征X坐标
	 */
	public void setSubCenterPointX(String subCenterPointX) {
		this.subCenterPointX = subCenterPointX;
	}

	/**
	 * 现场指纹_指纹副中心_特征Y坐标
	 */
	public String getSubCenterPointY() {
		return subCenterPointY;
	}

	/**
	 * 现场指纹_指纹副中心_特征Y坐标
	 */
	public void setSubCenterPointY(String subCenterPointY) {
		this.subCenterPointY = subCenterPointY;
	}

	/**
	 * 现场指纹_指纹副中心_特征坐标范围
	 */
	public String getSubCenterPointRange() {
		return subCenterPointRange;
	}

	/**
	 * 现场指纹_指纹副中心_特征坐标范围
	 */
	public void setSubCenterPointRange(String subCenterPointRange) {
		this.subCenterPointRange = subCenterPointRange;
	}

	/**
	 * 现场指纹_指纹副中心_特征方向
	 */
	public String getSubCenterPointDir() {
		return subCenterPointDir;
	}

	/**
	 * 现场指纹_指纹副中心_特征方向
	 */
	public void setSubCenterPointDir(String subCenterPointDir) {
		this.subCenterPointDir = subCenterPointDir;
	}

	/**
	 * 现场指纹_指纹副中心_特征方向范围
	 */
	public String getSubCenterPointDirRange() {
		return subCenterPointDirRange;
	}

	/**
	 * 现场指纹_指纹副中心_特征方向范围
	 */
	public void setSubCenterPointDirRange(String subCenterPointDirRange) {
		this.subCenterPointDirRange = subCenterPointDirRange;
	}

	/**
	 * 现场指纹_指纹副中心_特征可靠度
	 */
	public String getSubCenterPointRel() {
		return subCenterPointRel;
	}

	/**
	 * 现场指纹_指纹副中心_特征可靠度
	 */
	public void setSubCenterPointRel(String subCenterPointRel) {
		this.subCenterPointRel = subCenterPointRel;
	}

	/**
	 * 现场指纹_指纹左三角_特征X坐标
	 */
	public String getLeftTriangleX() {
		return leftTriangleX;
	}

	/**
	 * 现场指纹_指纹左三角_特征X坐标
	 */
	public void setLeftTriangleX(String leftTriangleX) {
		this.leftTriangleX = leftTriangleX;
	}

	/**
	 * 现场指纹_指纹左三角_特征Y坐标
	 */
	public String getLeftTriangleY() {
		return leftTriangleY;
	}

	/**
	 * 现场指纹_指纹左三角_特征Y坐标
	 */
	public void setLeftTriangleY(String leftTriangleY) {
		this.leftTriangleY = leftTriangleY;
	}

	/**
	 * 现场指纹_指纹左三角_特征坐标范围
	 */
	public String getLeftTriangleRange() {
		return leftTriangleRange;
	}

	/**
	 * 现场指纹_指纹左三角_特征坐标范围
	 */
	public void setLeftTriangleRange(String leftTriangleRange) {
		this.leftTriangleRange = leftTriangleRange;
	}

	/**
	 * 现场指纹_指纹左三角_特征方向
	 */
	public String getLeftTriangleDir() {
		return leftTriangleDir;
	}

	/**
	 * 现场指纹_指纹左三角_特征方向
	 */
	public void setLeftTriangleDir(String leftTriangleDir) {
		this.leftTriangleDir = leftTriangleDir;
	}

	/**
	 * 现场指纹_指纹左三角_特征方向范围
	 */
	public String getLeftTriangleDirRange() {
		return leftTriangleDirRange;
	}

	/**
	 * 现场指纹_指纹左三角_特征方向范围
	 */
	public void setLeftTriangleDirRange(String leftTriangleDirRange) {
		this.leftTriangleDirRange = leftTriangleDirRange;
	}

	/**
	 * 现场指纹_指纹左三角_特征可靠度
	 */
	public String getLeftTriangleRel() {
		return leftTriangleRel;
	}

	/**
	 * 现场指纹_指纹左三角_特征可靠度
	 */
	public void setLeftTriangleRel(String leftTriangleRel) {
		this.leftTriangleRel = leftTriangleRel;
	}

	/**
	 * 现场指纹_指纹右三角_特征X坐标
	 */
	public String getRightTriangleX() {
		return rightTriangleX;
	}

	/**
	 * 现场指纹_指纹右三角_特征X坐标
	 */
	public void setRightTriangleX(String rightTriangleX) {
		this.rightTriangleX = rightTriangleX;
	}

	/**
	 * 现场指纹_指纹右三角_特征Y坐标
	 */
	public String getRightTriangleY() {
		return rightTriangleY;
	}

	/**
	 * 现场指纹_指纹右三角_特征Y坐标
	 */
	public void setRightTriangleY(String rightTriangleY) {
		this.rightTriangleY = rightTriangleY;
	}

	/**
	 * 现场指纹_指纹右三角_特征坐标范围
	 */
	public String getRightTriangleRange() {
		return rightTriangleRange;
	}

	/**
	 * 现场指纹_指纹右三角_特征坐标范围
	 */
	public void setRightTriangleRange(String rightTriangleRange) {
		this.rightTriangleRange = rightTriangleRange;
	}

	/**
	 * 现场指纹_指纹右三角_特征方向
	 */
	public String getRightTriangleDir() {
		return rightTriangleDir;
	}

	/**
	 * 现场指纹_指纹右三角_特征方向
	 */
	public void setRightTriangleDir(String rightTriangleDir) {
		this.rightTriangleDir = rightTriangleDir;
	}

	/**
	 * 现场指纹_指纹右三角_特征方向范围
	 */
	public String getRightTriangleDirRange() {
		return rightTriangleDirRange;
	}

	/**
	 * 现场指纹_指纹右三角_特征方向范围
	 */
	public void setRightTriangleDirRange(String rightTriangleDirRange) {
		this.rightTriangleDirRange = rightTriangleDirRange;
	}

	/**
	 * 现场指纹_指纹右三角_特征可靠度
	 */
	public String getRightTriangleRel() {
		return rightTriangleRel;
	}

	/**
	 * 现场指纹_指纹右三角_特征可靠度
	 */
	public void setRightTriangleRel(String rightTriangleRel) {
		this.rightTriangleRel = rightTriangleRel;
	}

	/**
	 * 指纹特征点信息
	 */
	public List<LpFingerFeatureMinutia> getLpFingerFeatureMinutiaList() {
		return lpFingerFeatureMinutiaList;
	}

	/**
	 * 指纹特征点信息
	 */
	public void setLpFingerFeatureMinutiaList(List<LpFingerFeatureMinutia> lpFingerFeatureMinutiaList) {
		this.lpFingerFeatureMinutiaList = lpFingerFeatureMinutiaList;
	}

	/**
	 * 现场指纹_自定义信息
	 */
	public String getCustomInfo() {
		return customInfo;
	}

	/**
	 * 现场指纹_自定义信息
	 */
	public void setCustomInfo(String customInfo) {
		this.customInfo = customInfo;
	}

}
