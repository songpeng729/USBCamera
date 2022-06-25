package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 捺印掌纹信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TpPalm {
    /**
     * 掌纹掌位代码
     */
    @XmlElement(name = "zhwzhwdm")
    private String palmPostionCode = "";
    /**
     * 掌纹_指掌纹缺失情况代码
     */
    @XmlElement(name = "zhw_zzhwqsqkdm")
    private String lackPalmCauseCode = "";
    /**
     * 掌纹_指掌纹特征提取方式代码
     */
    @XmlElement(name = "zhw_zzhwtztqfsdm")
    private String palmFeatureExtractionMethodCode = "";
    /**
     * 掌纹折返点
     */
    @XmlElementWrapper(name="coreLikePatternSet")
    @XmlElement(name="coreLikePattern")
    private List<TpPalmCoreLikePattern> tpPalmCoreLikePatternList;
    /**
     * 掌纹三角点
     */
    @XmlElementWrapper(name="deltaSet")
    @XmlElement(name="delta")
    private List<TpPalmDelta> tpPalmDeltaList;
    /**
     * 掌纹特征点
     */
    @XmlElementWrapper(name="minutiaSet")
    @XmlElement(name="minutia")
    private List<TpPalmMinutia> tpPalmMinutiaList;
    /**
     * 掌纹_自定义信息
     */
    @XmlElement(name = "zhw_zdyxx")
    private String palmCustomInfo = "";
    /**
     * 掌纹_图像水平方向长度
     */
    @XmlElement(name = "zhw_txspfxcd")
    private String palmImageHorizontalDirectionLength = "";
    /**
     * 掌纹_图像垂直方向长度
     */
    @XmlElement(name = "zhw_txczfxcd")
    private String palmImageVerticalDirectionLength = "";
    /**
     *  掌纹_图像分辨率
     */
    @XmlElement(name = "zhw_txfbl")
    private String palmImageRatio = "";

    /**
     * 掌纹_图像压缩方法描述
     * 0000表示无压缩，其他值前2个字节数字代表系统产品生产的单位代码，
     * 后2个字节数字代表系统产品的版本，从00开始递增，对于WSQ压缩方法，
     * 前2个数字固定为14，后两个数字代表系统产品生产的单位代码
     */
    @XmlElement(name = "zhw_txysffms")
    private String palmImageCompressMethodDescript = "";
    /**
     * 掌纹_图像质量
     */
    @XmlElement(name = "zhw_txzl")
    private String palmImageQuality = "";
    /**
     * 掌纹_图像数据
     */
    @XmlElement(name = "zhw_txsj")
    private String palmImageData = "";

    public String getPalmPostionCode() {
        return palmPostionCode;
    }

    public void setPalmPostionCode(String palmPostionCode) {
        this.palmPostionCode = palmPostionCode;
    }

    public String getLackPalmCauseCode() {
        return lackPalmCauseCode;
    }

    public void setLackPalmCauseCode(String lackPalmCauseCode) {
        this.lackPalmCauseCode = lackPalmCauseCode;
    }

    public String getPalmFeatureExtractionMethodCode() {
        return palmFeatureExtractionMethodCode;
    }

    public void setPalmFeatureExtractionMethodCode(String palmFeatureExtractionMethodCode) {
        this.palmFeatureExtractionMethodCode = palmFeatureExtractionMethodCode;
    }

    public List<TpPalmCoreLikePattern> getTpPalmCoreLikePatternList() {
        return tpPalmCoreLikePatternList;
    }

    public void setTpPalmCoreLikePatternList(List<TpPalmCoreLikePattern> tpPalmCoreLikePatternList) {
        this.tpPalmCoreLikePatternList = tpPalmCoreLikePatternList;
    }

    public List<TpPalmDelta> getTpPalmDeltaList() {
        return tpPalmDeltaList;
    }

    public void setTpPalmDeltaList(List<TpPalmDelta> tpPalmDeltaList) {
        this.tpPalmDeltaList = tpPalmDeltaList;
    }

    public List<TpPalmMinutia> getTpPalmMinutiaList() {
        return tpPalmMinutiaList;
    }

    public void setTpPalmMinutiaList(List<TpPalmMinutia> tpPalmMinutiaList) {
        this.tpPalmMinutiaList = tpPalmMinutiaList;
    }

    public String getPalmCustomInfo() {
        return palmCustomInfo;
    }

    public void setPalmCustomInfo(String palmCustomInfo) {
        this.palmCustomInfo = palmCustomInfo;
    }

    public String getPalmImageHorizontalDirectionLength() {
        return palmImageHorizontalDirectionLength;
    }

    public void setPalmImageHorizontalDirectionLength(String palmImageHorizontalDirectionLength) {
        this.palmImageHorizontalDirectionLength = palmImageHorizontalDirectionLength;
    }

    public String getPalmImageVerticalDirectionLength() {
        return palmImageVerticalDirectionLength;
    }

    public void setPalmImageVerticalDirectionLength(String palmImageVerticalDirectionLength) {
        this.palmImageVerticalDirectionLength = palmImageVerticalDirectionLength;
    }

    public String getPalmImageRatio() {
        return palmImageRatio;
    }

    public void setPalmImageRatio(String palmImageRatio) {
        this.palmImageRatio = palmImageRatio;
    }

    public String getPalmImageCompressMethodDescript() {
        return palmImageCompressMethodDescript;
    }

    public void setPalmImageCompressMethodDescript(String palmImageCompressMethodDescript) {
        this.palmImageCompressMethodDescript = palmImageCompressMethodDescript;
    }

    public String getPalmImageQuality() {
        return palmImageQuality;
    }

    public void setPalmImageQuality(String palmImageQuality) {
        this.palmImageQuality = palmImageQuality;
    }

    public String getPalmImageData() {
        return palmImageData;
    }

    public void setPalmImageData(String palmImageData) {
        this.palmImageData = palmImageData;
    }
}
