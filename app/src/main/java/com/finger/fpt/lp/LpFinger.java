package com.finger.fpt.lp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 现场指纹
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LpFinger {
	
	/**
	 * 现场指纹图像信息
	 */
	@XmlElement(name = "fingerImageMsg")
	private LpFingerImage lpFingerImage;
	
	/**
	 * 现场指纹特征信息
	 */
	@XmlElement(name = "fingerFeatureMsg")
	private LpFingerFeature lpFingerFeature;
	
	
	/**
	 * 现场指纹图像信息
	 */
	public LpFingerImage getLpFingerImage() {
		return lpFingerImage;
	}

	/**
	 * 现场指纹图像信息
	 */
	public void setLpFingerImage(LpFingerImage lpFingerImage) {
		this.lpFingerImage = lpFingerImage;
	}

	/**
	 * 现场指纹特征信息
	 */
	public LpFingerFeature getLpFingerFeature() {
		return lpFingerFeature;
	}

	/**
	 * 现场指纹特征信息
	 */
	public void setLpFingerFeature(LpFingerFeature lpFingerFeature) {
		this.lpFingerFeature = lpFingerFeature;
	}
}
