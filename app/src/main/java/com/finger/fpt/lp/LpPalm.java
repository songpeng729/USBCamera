package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场掌纹
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpPalm {
	
	/**
	 * 现场掌纹图像信息
	 */
	@XmlElement(name = "palmImageMsg")
	private LpPalmImage lpPalmImage;

	/**
	 * 现场掌纹特征信息
	 */
	@XmlElement(name = "palmFeatureMsg")
	private LpPalmFeature lpPalmFeature;
	
	

	/**
	 * 现场掌纹图像信息
	 */
	public LpPalmImage getLpPalmImage() {
		return lpPalmImage;
	}

	/**
	 * 现场掌纹图像信息
	 */
	public void setLpPalmImage(LpPalmImage lpPalmImage) {
		this.lpPalmImage = lpPalmImage;
	}

	/**
	 * 现场掌纹特征信息
	 */
	public LpPalmFeature getLpPalmFeature() {
		return lpPalmFeature;
	}

	/**
	 * 现场掌纹特征信息
	 */
	public void setLpPalmFeature(LpPalmFeature lpPalmFeature) {
		this.lpPalmFeature = lpPalmFeature;
	}
}
