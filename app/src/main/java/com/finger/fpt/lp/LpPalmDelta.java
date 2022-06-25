package com.finger.fpt.lp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场掌纹三角点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpPalmDelta {

	/**
	 * 现场掌纹_掌纹三角点_特征X坐标
	 */
	@XmlElement(name = "xczhw_zhwsjd_tzxzb")
	private String x = "";

	/**
	 * 现场掌纹_掌纹三角点_特征Y坐标
	 */
	@XmlElement(name = "xczhw_zhwsjd_tzyzb")
	private String y = "";

	/**
	 * 现场掌纹_掌纹三角点_特征坐标范围
	 */
	@XmlElement(name = "xczhw_zhwsjd_tzzbfw")
	private String range = "";

	/**
	 * 现场掌纹三角点信息
	 */
	@XmlElement(name = "deltaDirection")
	private List<LpPalmDeltaDirection> lpPalmDeltaDirectionList;

	/**
	 * 现场掌纹_掌纹三角点_掌纹三角位置类型代码
	 */
	@XmlElement(name = "xczhw_zhwsjd_zhwsjwzlxdm")
	private String positionType = "";

	/**
	 * 现场掌纹_掌纹三角点_特征质量
	 */
	@XmlElement(name = "xczhw_zhwsjd_tzzl")
	private String qua = "";

	
	
	/**
	 * 现场掌纹_掌纹三角点_特征X坐标
	 */
	public String getX() {
		return x;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征X坐标
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征Y坐标
	 */
	public String getY() {
		return y;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征Y坐标
	 */
	public void setY(String y) {
		this.y = y;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征坐标范围
	 */
	public String getRange() {
		return range;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征坐标范围
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/**
	 * 现场掌纹_掌纹三角点_掌纹三角位置类型代码
	 */
	public String getPositionType() {
		return positionType;
	}

	/**
	 * 现场掌纹_掌纹三角点_掌纹三角位置类型代码
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征质量
	 */
	public String getQua() {
		return qua;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征质量
	 */
	public void setQua(String qua) {
		this.qua = qua;
	}

	/**
	 * 现场掌纹三角点信息
	 */
	public List<LpPalmDeltaDirection> getLpPalmDeltaDirectionList() {
		return lpPalmDeltaDirectionList;
	}

	/**
	 * 现场掌纹三角点信息
	 */
	public void setLpPalmDeltaDirectionList(List<LpPalmDeltaDirection> lpPalmDeltaDirectionList) {
		this.lpPalmDeltaDirectionList = lpPalmDeltaDirectionList;
	}
}
