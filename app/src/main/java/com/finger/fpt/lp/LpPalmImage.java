package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场掌纹图像信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpPalmImage {
	
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
	 * 现场掌纹_现场指掌纹遗留部位
	 */
	@XmlElement(name = "xczhw_xczzhwylbw")
	private String remainPlace = "";

	/**
	 * 现场掌纹_尸体指掌纹_判断标识
	 */
	@XmlElement(name = "xczhw_stzzhw_pdbz")
	private String isCorpse = "";

	/**
	 * 现场掌纹_乳突线颜色代码
	 */
	@XmlElement(name = "xczhw_rtxysdm")
	private String ridgeColor = "";

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
	 * 现场掌纹_图像水平方向长度
	 */
	@XmlElement(name = "xczhw_txspfxcd")
	private String imgHorizontalLength = "";

	/**
	 * 现场掌纹_图像垂直方向长度
	 */
	@XmlElement(name = "xczhw_txczfxcd")
	private String imgVerticalLength = "";

	/**
	 * 现场掌纹_图像分辨率
	 */
	@XmlElement(name = "xczhw_txfbl")
	private String dpi = "";

	/**
	 * 现场掌纹_图像压缩方法描述
	 */
	@XmlElement(name = "xczhw_txysffms")
	private String imgCompressMethod = "";

	/**
	 * 现场掌纹_图像数据
	 */
	@XmlElement(name = "xczhw_txsj")
	private String imgData = "";

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
	 * 现场掌纹_现场指掌纹遗留部位
	 */
	public String getRemainPlace() {
		return remainPlace;
	}

	/**
	 * 现场掌纹_现场指掌纹遗留部位
	 */
	public void setRemainPlace(String remainPlace) {
		this.remainPlace = remainPlace;
	}

	/**
	 * 现场掌纹_尸体指掌纹_判断标识
	 */
	public String getIsCorpse() {
		return isCorpse;
	}

	/**
	 * 现场掌纹_尸体指掌纹_判断标识
	 */
	public void setIsCorpse(String isCorpse) {
		this.isCorpse = isCorpse;
	}

	/**
	 * 现场掌纹_乳突线颜色代码
	 */
	public String getRidgeColor() {
		return ridgeColor;
	}

	/**
	 * 现场掌纹_乳突线颜色代码
	 */
	public void setRidgeColor(String ridgeColor) {
		this.ridgeColor = ridgeColor;
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
	 * 现场掌纹_图像水平方向长度
	 */
	public String getImgHorizontalLength() {
		return imgHorizontalLength;
	}

	/**
	 * 现场掌纹_图像水平方向长度
	 */
	public void setImgHorizontalLength(String imgHorizontalLength) {
		this.imgHorizontalLength = imgHorizontalLength;
	}

	/**
	 * 现场掌纹_图像垂直方向长度
	 */
	public String getImgVerticalLength() {
		return imgVerticalLength;
	}

	/**
	 * 现场掌纹_图像垂直方向长度
	 */
	public void setImgVerticalLength(String imgVerticalLength) {
		this.imgVerticalLength = imgVerticalLength;
	}

	/**
	 * 现场掌纹_图像分辨率
	 */
	public String getDpi() {
		return dpi;
	}

	/**
	 * 现场掌纹_图像分辨率
	 */
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	/**
	 * 现场掌纹_图像压缩方法描述
	 */
	public String getImgCompressMethod() {
		return imgCompressMethod;
	}

	/**
	 * 现场掌纹_图像压缩方法描述
	 */
	public void setImgCompressMethod(String imgCompressMethod) {
		this.imgCompressMethod = imgCompressMethod;
	}

	/**
	 * 现场掌纹_图像数据
	 */
	public String getImgData() {
		return imgData;
	}

	/**
	 * 现场掌纹_图像数据
	 */
	public void setImgData(String imgData) {
		this.imgData = imgData;
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
