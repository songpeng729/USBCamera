package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场掌纹三角点
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpPalmDeltaDirection {

	/**
	 * 现场掌纹_掌纹三角点_特征方向
	 */
	@XmlElement(name = "xczhw_zhwsjd_tzfx")
	private String dir = "";

	/**
	 * 现场掌纹_掌纹三角点_特征方向范围
	 */
	@XmlElement(name = "xczhw_zhwsjd_tzfxfw")
	private String dirRange = "";
	
	

	/**
	 * 现场掌纹_掌纹三角点_特征方向
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征方向
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征方向范围
	 */
	public String getDirRange() {
		return dirRange;
	}

	/**
	 * 现场掌纹_掌纹三角点_特征方向范围
	 */
	public void setDirRange(String dirRange) {
		this.dirRange = dirRange;
	}
	
}
