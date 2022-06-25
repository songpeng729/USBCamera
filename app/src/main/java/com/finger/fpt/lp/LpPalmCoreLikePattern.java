package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场掌纹折返点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpPalmCoreLikePattern {
	
	/**
	 * 现场掌纹_掌纹折返点_特征X坐标
	 */
	@XmlElement(name = "xczhw_zhwzfd_tzxzb")
	private String x = "";

	/**
	 * 现场掌纹_掌纹折返点_特征Y坐标
	 */
	@XmlElement(name = "xczhw_zhwzfd_tzyzb")
	private String y = "";

	/**
	 * 现场掌纹_掌纹折返点_特征坐标范围
	 */
	@XmlElement(name = "xczhw_zhwzfd_tzzbfw")
	private String range = "";

	/**
	 * 现场掌纹_掌纹折返点_特征方向
	 */
	@XmlElement(name = "xczhw_zhwzfd_tzfx")
	private String dir = "";

	/**
	 * 现场掌纹_掌纹折返点_特征方向范围
	 */
	@XmlElement(name = "xczhw_zhwzfd_tzfxfw")
	private String dirRange = "";

	/**
	 * 现场掌纹_掌纹折返点_特征质量
	 */
	@XmlElement(name = "xczhw_zhwzfd_tzzl")
	private String qua = "";

	
	
	/**
	 * 现场掌纹_掌纹折返点_特征X坐标
	 */
	public String getX() {
		return x;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征X坐标
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征Y坐标
	 */
	public String getY() {
		return y;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征Y坐标
	 */
	public void setY(String y) {
		this.y = y;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征坐标范围
	 */
	public String getRange() {
		return range;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征坐标范围
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征方向
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征方向
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征方向范围
	 */
	public String getDirRange() {
		return dirRange;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征方向范围
	 */
	public void setDirRange(String dirRange) {
		this.dirRange = dirRange;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征质量
	 */
	public String getQua() {
		return qua;
	}

	/**
	 * 现场掌纹_掌纹折返点_特征质量
	 */
	public void setQua(String qua) {
		this.qua = qua;
	}
}
