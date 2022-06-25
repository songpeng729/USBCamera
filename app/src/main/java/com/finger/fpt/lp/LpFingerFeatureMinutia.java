package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场指纹细节特征点信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpFingerFeatureMinutia {
	
	/**
	 * 现场指纹_指纹特征点_特征X坐标
	 */
	@XmlElement(name = "xczw_zwtzd_tzxzb")
	private String x = "";

	/**
	 * 现场指纹_指纹特征点_特征Y坐标
	 */
	@XmlElement(name = "xczw_zwtzd_tzyzb")
	private String y = "";

	/**
	 * 现场指纹_指纹特征点_特征方向
	 */
	@XmlElement(name = "xczw_zwtzd_tzfx")
	private String dir = "";

	/**
	 * 现场指纹_指纹特征点_特征质量
	 */
	@XmlElement(name = "xczw_zwtzd_tzzl")
	private String qua = "";

	
	
	
	/**
	 * 现场指纹_指纹特征点_特征X坐标
	 */
	public String getX() {
		return x;
	}

	/**
	 * 现场指纹_指纹特征点_特征X坐标
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * 现场指纹_指纹特征点_特征Y坐标
	 */
	public String getY() {
		return y;
	}

	/**
	 * 现场指纹_指纹特征点_特征Y坐标
	 */
	public void setY(String y) {
		this.y = y;
	}

	/**
	 * 现场指纹_指纹特征点_特征方向
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * 现场指纹_指纹特征点_特征方向
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * 现场指纹_指纹特征点_特征质量
	 */
	public String getQua() {
		return qua;
	}

	/**
	 * 现场指纹_指纹特征点_特征质量
	 */
	public void setQua(String qua) {
		this.qua = qua;
	}
}
