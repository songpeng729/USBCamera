package com.finger.fpt.tp;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 捺印指掌纹信息
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class FingerprintPackage {

    /**
     * 捺印指掌纹基本信息
     */
    @XmlElement(name = "descriptiveMsg")
    private DescriptiveMsg descriptiveMsg;
    /**
     * 捺印信息节点
     */
    @XmlElement(name = "collectInfoMsg")
    private CollectInfoMsg collectInfoMsg;
    /**
     * 捺印指纹信息
     */
    @XmlElementWrapper(name="fingers")
    @XmlElement(name = "fingerMsg")
    private List<TPFinger> fingers;
    /**
     * 捺印掌纹信息
     */
    @XmlElementWrapper(name="palms")
    @XmlElement(name="palmMsg")
    private List<TpPalm> palms;
    /**
     * 捺印指掌纹四连指
     * @return
     */
    @XmlElementWrapper(name="fourprints")
    @XmlElement(name="fourprintMsg")
    private List<TpFourprintMsg> fourprints;

    /**
     * 捺印指掌纹指节纹
     * @return
     */
    @XmlElementWrapper(name="knuckleprints")
    @XmlElement(name="knuckleprintMsg")
    private List<TpKnuckleprintMsg> knuckleprints;

    /**
     * 捺印全掌
     */
    @XmlElementWrapper(name="fullpalms")
    @XmlElement(name="fullpalmMsg")
    private List<TpFullpalmMsg> fullpalms;

    /**
     * 捺印指掌纹人像信息
     */
    @XmlElementWrapper(name="faceImages")
    @XmlElement(name="faceImage")
    private List<TpFaceImage> faceImages;


    public DescriptiveMsg getDescriptiveMsg() {
        return descriptiveMsg;
    }

    public void setDescriptiveMsg(DescriptiveMsg descriptiveMsg) {
        this.descriptiveMsg = descriptiveMsg;
    }

    public CollectInfoMsg getCollectInfoMsg() {
        return collectInfoMsg;
    }

    public void setCollectInfoMsg(CollectInfoMsg collectInfoMsg) {
        this.collectInfoMsg = collectInfoMsg;
    }

    public List<TPFinger> getFingers() {
        return fingers;
    }

    public void setFingers(List<TPFinger> fingers) {
        this.fingers = fingers;
    }

    public List<TpPalm> getPalms() {
        return palms;
    }

    public void setPalms(List<TpPalm> palms) {
        this.palms = palms;
    }

    public List<TpFourprintMsg> getFourprints() {
        return fourprints;
    }

    public void setFourprints(List<TpFourprintMsg> fourprints) {
        this.fourprints = fourprints;
    }

    public List<TpKnuckleprintMsg> getKnuckleprints() {
        return knuckleprints;
    }

    public void setKnuckleprints(List<TpKnuckleprintMsg> knuckleprints) {
        this.knuckleprints = knuckleprints;
    }

    public List<TpFullpalmMsg> getFullpalms() {
        return fullpalms;
    }

    public void setFullpalms(List<TpFullpalmMsg> fullpalms) {
        this.fullpalms = fullpalms;
    }

    public List<TpFaceImage> getFaceImages() {
        return faceImages;
    }

    public void setFaceImages(List<TpFaceImage> faceImages) {
        this.faceImages = faceImages;
    }
}
