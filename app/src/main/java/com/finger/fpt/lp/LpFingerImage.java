package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场指纹图像信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpFingerImage {
	
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
	 * 现场指纹_现场指掌纹遗留部位
	 */
	@XmlElement(name = "xczw_xczzhwylbw")
	private String remainPlace = "";
	
	/**
	 * 现场指纹_尸体指掌纹_判断标识
	 */
	@XmlElement(name = "xczw_stzzhw_pdbz")
	private String isCorpse = "";
	
	/**
	 * 现场指纹_乳突线颜色代码
	 */
	@XmlElement(name = "xczw_rtxysdm")
	private String ridgeColor = "";
	
	/**
	 * 现场指纹_连指开始_现场物证编号
	 */
	@XmlElement(name = "xczw_lzks_xcwzbh")
	private String mittensBegNo = "";
	
	/**
	 * 现场指纹_连指结束_现场物证编号
	 */
	@XmlElement(name = "xczw_lzjs_xcwzbh")
	private String mittensEndNo = "";
	
	/**
	 * 现场指纹_指掌纹比对状态代码
	 */
	@XmlElement(name = "xczw_zzhwbdztdm")
	private String matchStatus = "";
	
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
	 * 现场指纹_图像水平方向长度
	 */
	@XmlElement(name = "xczw_txspfxcd")
	private String imgHorizontalLength = "";
	
	/**
	 * 现场指纹_图像垂直方向长度
	 */
	@XmlElement(name = "xczw_txczfxcd")
	private String imgVerticalLength = "";
	
	/**
	 * 现场指纹_图像分辨率
	 */
	@XmlElement(name = "xczw_txfbl")
	private String dpi = "";
	
	/**
	 * 现场指纹_图像压缩方法描述
	 */
	@XmlElement(name = "xczw_txysffms")
	private String imgCompressMethod = "";
	
	/**
	 * 现场指纹_图像数据
	 */
	@XmlElement(name = "xczw_txsj")
	private String imgData = "";

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
	 * 现场指纹_现场指掌纹遗留部位
	 */
	public String getRemainPlace() {
		return remainPlace;
	}

	/**
	 * 现场指纹_现场指掌纹遗留部位
	 */
	public void setRemainPlace(String remainPlace) {
		this.remainPlace = remainPlace;
	}

	/**
	 * 现场指纹_尸体指掌纹_判断标识
	 */
	public String getIsCorpse() {
		return isCorpse;
	}

	/**
	 * 现场指纹_尸体指掌纹_判断标识
	 */
	public void setIsCorpse(String isCorpse) {
		this.isCorpse = isCorpse;
	}

	/**
	 * 现场指纹_乳突线颜色代码
	 */
	public String getRidgeColor() {
		return ridgeColor;
	}

	/**
	 * 现场指纹_乳突线颜色代码
	 */
	public void setRidgeColor(String ridgeColor) {
		this.ridgeColor = ridgeColor;
	}

	/**
	 * 现场指纹_连指开始_现场物证编号
	 */
	public String getMittensBegNo() {
		return mittensBegNo;
	}

	/**
	 * 现场指纹_连指开始_现场物证编号
	 */
	public void setMittensBegNo(String mittensBegNo) {
		this.mittensBegNo = mittensBegNo;
	}

	/**
	 * 现场指纹_连指结束_现场物证编号
	 */
	public String getMittensEndNo() {
		return mittensEndNo;
	}

	/**
	 * 现场指纹_连指结束_现场物证编号
	 */
	public void setMittensEndNo(String mittensEndNo) {
		this.mittensEndNo = mittensEndNo;
	}

	/**
	 * 现场指纹_指掌纹比对状态代码
	 */
	public String getMatchStatus() {
		return matchStatus;
	}

	/**
	 * 现场指纹_指掌纹比对状态代码
	 */
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
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
	 * 现场指纹_图像水平方向长度
	 */
	public String getImgHorizontalLength() {
		return imgHorizontalLength;
	}

	/**
	 * 现场指纹_图像水平方向长度
	 */
	public void setImgHorizontalLength(String imgHorizontalLength) {
		this.imgHorizontalLength = imgHorizontalLength;
	}

	/**
	 * 现场指纹_图像垂直方向长度
	 */
	public String getImgVerticalLength() {
		return imgVerticalLength;
	}

	/**
	 * 现场指纹_图像垂直方向长度
	 */
	public void setImgVerticalLength(String imgVerticalLength) {
		this.imgVerticalLength = imgVerticalLength;
	}

	/**
	 * 现场指纹_图像分辨率
	 */
	public String getDpi() {
		return dpi;
	}

	/**
	 * 现场指纹_图像分辨率
	 */
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	/**
	 * 现场指纹_图像压缩方法描述
	 */
	public String getImgCompressMethod() {
		return imgCompressMethod;
	}

	/**
	 * 现场指纹_图像压缩方法描述
	 */
	public void setImgCompressMethod(String imgCompressMethod) {
		this.imgCompressMethod = imgCompressMethod;
	}

	/**
	 * 现场指纹_图像数据
	 */
	public String getImgData() {
		return imgData;
	}

	/**
	 * 现场指纹_图像数据
	 */
	public void setImgData(String imgData) {
		this.imgData = imgData;
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
