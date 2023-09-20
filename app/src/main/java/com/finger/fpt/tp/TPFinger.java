package com.finger.fpt.tp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * 捺印指纹信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TPFinger {
    /**
     * 指纹指位代码
     */
    @XmlElement(name = "zwzwdm")
    private String fgp = "";
    /**
     * 指掌纹特征提取方式代码
     */
    @XmlElement(name = "zzhwtztqfsdm")
    private String fingerFeatureExtractionMethodCode = "";
    @XmlElement(name = "zzhwqsqkdm")
    private String adactylismCauseCode = ""; //指掌纹缺失情况代码
    @XmlElement(name = "zwwxzfl_zwwxdm")
    private String fingerPatternMasterCode = ""; //指纹纹型主分类代码
    @XmlElement(name = "zwwxffl_zwwxdm")
    private String fingerPatternSlaveCode = "";  //指纹纹型副分类代码
    @XmlElement(name = "zwfx_tzfx")
    private String fingerFeatureDirection = ""; //指纹方向-特征方向
    @XmlElement(name = "zwfx_tzfxfw")
    private String fingerFeatureDirectionRange = ""; //指纹方向-特征方向-范围
    @XmlElement(name = "zwzxd_tzxzb")
    private String fingerCenterPointFeatureXCoordinate = ""; //指纹中心点特征X坐标
    @XmlElement(name = "zwzxd_tzyzb")
    private String fingerCenterPointFeatureYCoordinate = ""; //指纹中心点特征Y坐标
    @XmlElement(name = "zwzxd_tzzbfw")
    private String fingerCenterPointFeatureCoordinateRange = ""; //指纹中心点特征坐标范围
    @XmlElement(name = "zwzxd_tzfx")
    private String fingerCenterPointFeatureDirection = ""; //指纹中心点特征特征方向
    @XmlElement(name = "zwzxd_tzfxfw")
    private String fingerCenterPointFeatureDirectionRange = ""; //指纹中心点特征特征方向范围
    @XmlElement(name = "zwzxd_tzkkd")
    private String fingerCenterPointFeatureReliabilityLevel = ""; //指纹中心点可靠度
    @XmlElement(name = "zwfzx_tzxzb")
    private String fingerSlaveCenterFeatureXCoordinate = ""; //指纹副中心特征X坐标
    @XmlElement(name = "zwfzx_tzyzb")
    private String fingerSlaveCenterFeatureYCoordinate = ""; //指纹副中心特征Y坐标
    @XmlElement(name = "zwfzx_tzzbfw")
    private String fingerSlaveCenterFeatureCoordinateRange = ""; //指纹副中心特征坐标范围
    @XmlElement(name = "zwfzx_tzfx")
    private String fingerSlaveCenterFeatureDirection = ""; //指纹副中心特征方向
    @XmlElement(name = "zwfzx_tzfxfw")
    private String fingerSlaveCenterFeatureDirectionRange = ""; //指纹副中心特征方向范围
    @XmlElement(name = "zwfzx_tzkkd")
    private String fingerSlaveCenterFeatureReliabilityLevel = ""; //指纹副中心特征可靠度
    @XmlElement(name = "zwzsj_tzxzb")
    private String fingerLeftTriangleFeatureXCoordinate = ""; //指纹左三角特征X坐标
    @XmlElement(name = "zwzsj_tzyzb")
    private String fingerLeftTriangleFeatureYCoordinate = ""; //指纹左三角特征Y坐标
    @XmlElement(name = "zwzsj_tzzbfw")
    private String fingerLeftTriangleFeatureCoordinateRange = ""; //指纹左三角特征坐标范围
    @XmlElement(name = "zwzsj_tzfx")
    private String fingerLeftTriangleFeatureDirection = ""; //指纹左三角特征方向
    @XmlElement(name = "zwzsj_tzfxfw")
    private String fingerLeftTriangleFeatureDirectionRange = ""; //指纹左三角特征方向范围
    @XmlElement(name = "zwzsj_tzkkd")
    private String fingerLeftTriangleFeatureReliabilityLevel = ""; //指纹左三角特征方向范围
    @XmlElement(name = "zwysj_tzxzb")
    private String fingerRightTriangleFeatureXCoordinate = ""; //指纹右三角特征X坐标
    @XmlElement(name = "zwysj_tzyzb")
    private String fingerRightTriangleFeatureYCoordinate = ""; //指纹右三角特征Y坐标
    @XmlElement(name = "zwysj_tzzbfw")
    private String fingerRightTriangleFeatureCoordinateRange = ""; //指纹右三角特征坐标范围
    @XmlElement(name = "zwysj_tzfx")
    private String fingerRightTriangleFeatureDirection = ""; //指纹右三角特征方向
    @XmlElement(name = "zwysj_tzfxfw")
    private String fingerRightTriangleFeatureDirectionRange = ""; //指纹右三角特征方向范围
    @XmlElement(name = "zwysj_tzkkd")
    private String fingerRightTriangleFeatureReliabilityLevel = ""; //指纹右三角特征方向范围
    /**
     * 指纹特征点信息
     */
    @XmlElementWrapper(name="minutiaSet")
    @XmlElement(name="minutia")
    private List<TpFingerFeatureData> tpFingerFeatureDataList = new ArrayList<>();
    /**
     * 指纹_自定义信息
     */
    @XmlElement(name = "zw_zdyxx")
    private String fingerCustomInfo = "";
    /**
     * 指纹_图像水平方向长度
     */
    @XmlElement(name = "zw_txspfxcd")
    private String fingerImageHorizontalDirectionLength = "";
    /**
     * 指纹_图像垂直方向长度
     */
    @XmlElement(name = "zw_txczfxcd")
    private String fingerImageVerticalDirectionLength = "";
    /**
     * 指纹_图像分辨率
     */
    @XmlElement(name = "zw_txfbl")
    private String fingerImageRatio = "";
    /**
     * 0000表示无压缩，其他值前2个字节数字代表系统产品生产的单位代码，
     * 后2个字节数字代表系统产品的版本，从00开始递增，对于WSQ压缩方法，
     * 前2个数字固定为14，后两个数字代表系统产品生产的单位代码
     */
    @XmlElement(name = "zw_txysffms")
    private String fingerImageCompressMethodDescript = "";
    /**
     * 指纹_图像质量
     */
    @XmlElement(name = "zw_txzl")
    private String fingerImageQuality = "";
    /**
     * 指纹_图像数据
     */
    @XmlElement(name = "zw_txsj")
    private byte[] fingerImageData;

    public String getFgp() {
        return fgp;
    }

    public void setFgp(String fgp) {
        this.fgp = fgp;
    }

    public String getFingerFeatureExtractionMethodCode() {
        return fingerFeatureExtractionMethodCode;
    }

    public void setFingerFeatureExtractionMethodCode(String fingerFeatureExtractionMethodCode) {
        this.fingerFeatureExtractionMethodCode = fingerFeatureExtractionMethodCode;
    }

    public String getAdactylismCauseCode() {
        return adactylismCauseCode;
    }

    public void setAdactylismCauseCode(String adactylismCauseCode) {
        this.adactylismCauseCode = adactylismCauseCode;
    }

    public String getFingerPatternMasterCode() {
        return fingerPatternMasterCode;
    }

    public void setFingerPatternMasterCode(String fingerPatternMasterCode) {
        this.fingerPatternMasterCode = fingerPatternMasterCode;
    }

    public String getFingerPatternSlaveCode() {
        return fingerPatternSlaveCode;
    }

    public void setFingerPatternSlaveCode(String fingerPatternSlaveCode) {
        this.fingerPatternSlaveCode = fingerPatternSlaveCode;
    }

    public String getFingerFeatureDirection() {
        return fingerFeatureDirection;
    }

    public void setFingerFeatureDirection(String fingerFeatureDirection) {
        this.fingerFeatureDirection = fingerFeatureDirection;
    }

    public String getFingerFeatureDirectionRange() {
        return fingerFeatureDirectionRange;
    }

    public void setFingerFeatureDirectionRange(String fingerFeatureDirectionRange) {
        this.fingerFeatureDirectionRange = fingerFeatureDirectionRange;
    }

    public String getFingerCenterPointFeatureXCoordinate() {
        return fingerCenterPointFeatureXCoordinate;
    }

    public void setFingerCenterPointFeatureXCoordinate(String fingerCenterPointFeatureXCoordinate) {
        this.fingerCenterPointFeatureXCoordinate = fingerCenterPointFeatureXCoordinate;
    }

    public String getFingerCenterPointFeatureYCoordinate() {
        return fingerCenterPointFeatureYCoordinate;
    }

    public void setFingerCenterPointFeatureYCoordinate(String fingerCenterPointFeatureYCoordinate) {
        this.fingerCenterPointFeatureYCoordinate = fingerCenterPointFeatureYCoordinate;
    }

    public String getFingerCenterPointFeatureCoordinateRange() {
        return fingerCenterPointFeatureCoordinateRange;
    }

    public void setFingerCenterPointFeatureCoordinateRange(String fingerCenterPointFeatureCoordinateRange) {
        this.fingerCenterPointFeatureCoordinateRange = fingerCenterPointFeatureCoordinateRange;
    }

    public String getFingerCenterPointFeatureDirection() {
        return fingerCenterPointFeatureDirection;
    }

    public void setFingerCenterPointFeatureDirection(String fingerCenterPointFeatureDirection) {
        this.fingerCenterPointFeatureDirection = fingerCenterPointFeatureDirection;
    }

    public String getFingerCenterPointFeatureDirectionRange() {
        return fingerCenterPointFeatureDirectionRange;
    }

    public void setFingerCenterPointFeatureDirectionRange(String fingerCenterPointFeatureDirectionRange) {
        this.fingerCenterPointFeatureDirectionRange = fingerCenterPointFeatureDirectionRange;
    }

    public String getFingerCenterPointFeatureReliabilityLevel() {
        return fingerCenterPointFeatureReliabilityLevel;
    }

    public void setFingerCenterPointFeatureReliabilityLevel(String fingerCenterPointFeatureReliabilityLevel) {
        this.fingerCenterPointFeatureReliabilityLevel = fingerCenterPointFeatureReliabilityLevel;
    }

    public String getFingerSlaveCenterFeatureXCoordinate() {
        return fingerSlaveCenterFeatureXCoordinate;
    }

    public void setFingerSlaveCenterFeatureXCoordinate(String fingerSlaveCenterFeatureXCoordinate) {
        this.fingerSlaveCenterFeatureXCoordinate = fingerSlaveCenterFeatureXCoordinate;
    }

    public String getFingerSlaveCenterFeatureYCoordinate() {
        return fingerSlaveCenterFeatureYCoordinate;
    }

    public void setFingerSlaveCenterFeatureYCoordinate(String fingerSlaveCenterFeatureYCoordinate) {
        this.fingerSlaveCenterFeatureYCoordinate = fingerSlaveCenterFeatureYCoordinate;
    }

    public String getFingerSlaveCenterFeatureCoordinateRange() {
        return fingerSlaveCenterFeatureCoordinateRange;
    }

    public void setFingerSlaveCenterFeatureCoordinateRange(String fingerSlaveCenterFeatureCoordinateRange) {
        this.fingerSlaveCenterFeatureCoordinateRange = fingerSlaveCenterFeatureCoordinateRange;
    }

    public String getFingerSlaveCenterFeatureDirection() {
        return fingerSlaveCenterFeatureDirection;
    }

    public void setFingerSlaveCenterFeatureDirection(String fingerSlaveCenterFeatureDirection) {
        this.fingerSlaveCenterFeatureDirection = fingerSlaveCenterFeatureDirection;
    }

    public String getFingerSlaveCenterFeatureDirectionRange() {
        return fingerSlaveCenterFeatureDirectionRange;
    }

    public void setFingerSlaveCenterFeatureDirectionRange(String fingerSlaveCenterFeatureDirectionRange) {
        this.fingerSlaveCenterFeatureDirectionRange = fingerSlaveCenterFeatureDirectionRange;
    }

    public String getFingerSlaveCenterFeatureReliabilityLevel() {
        return fingerSlaveCenterFeatureReliabilityLevel;
    }

    public void setFingerSlaveCenterFeatureReliabilityLevel(String fingerSlaveCenterFeatureReliabilityLevel) {
        this.fingerSlaveCenterFeatureReliabilityLevel = fingerSlaveCenterFeatureReliabilityLevel;
    }

    public String getFingerLeftTriangleFeatureXCoordinate() {
        return fingerLeftTriangleFeatureXCoordinate;
    }

    public void setFingerLeftTriangleFeatureXCoordinate(String fingerLeftTriangleFeatureXCoordinate) {
        this.fingerLeftTriangleFeatureXCoordinate = fingerLeftTriangleFeatureXCoordinate;
    }

    public String getFingerLeftTriangleFeatureYCoordinate() {
        return fingerLeftTriangleFeatureYCoordinate;
    }

    public void setFingerLeftTriangleFeatureYCoordinate(String fingerLeftTriangleFeatureYCoordinate) {
        this.fingerLeftTriangleFeatureYCoordinate = fingerLeftTriangleFeatureYCoordinate;
    }

    public String getFingerLeftTriangleFeatureCoordinateRange() {
        return fingerLeftTriangleFeatureCoordinateRange;
    }

    public void setFingerLeftTriangleFeatureCoordinateRange(String fingerLeftTriangleFeatureCoordinateRange) {
        this.fingerLeftTriangleFeatureCoordinateRange = fingerLeftTriangleFeatureCoordinateRange;
    }

    public String getFingerLeftTriangleFeatureDirection() {
        return fingerLeftTriangleFeatureDirection;
    }

    public void setFingerLeftTriangleFeatureDirection(String fingerLeftTriangleFeatureDirection) {
        this.fingerLeftTriangleFeatureDirection = fingerLeftTriangleFeatureDirection;
    }

    public String getFingerLeftTriangleFeatureDirectionRange() {
        return fingerLeftTriangleFeatureDirectionRange;
    }

    public void setFingerLeftTriangleFeatureDirectionRange(String fingerLeftTriangleFeatureDirectionRange) {
        this.fingerLeftTriangleFeatureDirectionRange = fingerLeftTriangleFeatureDirectionRange;
    }

    public String getFingerLeftTriangleFeatureReliabilityLevel() {
        return fingerLeftTriangleFeatureReliabilityLevel;
    }

    public void setFingerLeftTriangleFeatureReliabilityLevel(String fingerLeftTriangleFeatureReliabilityLevel) {
        this.fingerLeftTriangleFeatureReliabilityLevel = fingerLeftTriangleFeatureReliabilityLevel;
    }

    public String getFingerRightTriangleFeatureXCoordinate() {
        return fingerRightTriangleFeatureXCoordinate;
    }

    public void setFingerRightTriangleFeatureXCoordinate(String fingerRightTriangleFeatureXCoordinate) {
        this.fingerRightTriangleFeatureXCoordinate = fingerRightTriangleFeatureXCoordinate;
    }

    public String getFingerRightTriangleFeatureYCoordinate() {
        return fingerRightTriangleFeatureYCoordinate;
    }

    public void setFingerRightTriangleFeatureYCoordinate(String fingerRightTriangleFeatureYCoordinate) {
        this.fingerRightTriangleFeatureYCoordinate = fingerRightTriangleFeatureYCoordinate;
    }

    public String getFingerRightTriangleFeatureCoordinateRange() {
        return fingerRightTriangleFeatureCoordinateRange;
    }

    public void setFingerRightTriangleFeatureCoordinateRange(String fingerRightTriangleFeatureCoordinateRange) {
        this.fingerRightTriangleFeatureCoordinateRange = fingerRightTriangleFeatureCoordinateRange;
    }

    public String getFingerRightTriangleFeatureDirection() {
        return fingerRightTriangleFeatureDirection;
    }

    public void setFingerRightTriangleFeatureDirection(String fingerRightTriangleFeatureDirection) {
        this.fingerRightTriangleFeatureDirection = fingerRightTriangleFeatureDirection;
    }

    public String getFingerRightTriangleFeatureDirectionRange() {
        return fingerRightTriangleFeatureDirectionRange;
    }

    public void setFingerRightTriangleFeatureDirectionRange(String fingerRightTriangleFeatureDirectionRange) {
        this.fingerRightTriangleFeatureDirectionRange = fingerRightTriangleFeatureDirectionRange;
    }

    public String getFingerRightTriangleFeatureReliabilityLevel() {
        return fingerRightTriangleFeatureReliabilityLevel;
    }

    public void setFingerRightTriangleFeatureReliabilityLevel(String fingerRightTriangleFeatureReliabilityLevel) {
        this.fingerRightTriangleFeatureReliabilityLevel = fingerRightTriangleFeatureReliabilityLevel;
    }

    public List<TpFingerFeatureData> getTpFingerFeatureDataList() {
        return tpFingerFeatureDataList;
    }

    public void setTpFingerFeatureDataList(List<TpFingerFeatureData> tpFingerFeatureDataList) {
        this.tpFingerFeatureDataList = tpFingerFeatureDataList;
    }

    public String getFingerCustomInfo() {
        return fingerCustomInfo;
    }

    public void setFingerCustomInfo(String fingerCustomInfo) {
        this.fingerCustomInfo = fingerCustomInfo;
    }

    public String getFingerImageHorizontalDirectionLength() {
        return fingerImageHorizontalDirectionLength;
    }

    public void setFingerImageHorizontalDirectionLength(String fingerImageHorizontalDirectionLength) {
        this.fingerImageHorizontalDirectionLength = fingerImageHorizontalDirectionLength;
    }

    public String getFingerImageVerticalDirectionLength() {
        return fingerImageVerticalDirectionLength;
    }

    public void setFingerImageVerticalDirectionLength(String fingerImageVerticalDirectionLength) {
        this.fingerImageVerticalDirectionLength = fingerImageVerticalDirectionLength;
    }

    public String getFingerImageRatio() {
        return fingerImageRatio;
    }

    public void setFingerImageRatio(String fingerImageRatio) {
        this.fingerImageRatio = fingerImageRatio;
    }

    public String getFingerImageCompressMethodDescript() {
        return fingerImageCompressMethodDescript;
    }

    public void setFingerImageCompressMethodDescript(String fingerImageCompressMethodDescript) {
        this.fingerImageCompressMethodDescript = fingerImageCompressMethodDescript;
    }

    public String getFingerImageQuality() {
        return fingerImageQuality;
    }

    public void setFingerImageQuality(String fingerImageQuality) {
        this.fingerImageQuality = fingerImageQuality;
    }

    public byte[] getFingerImageData() {
        return fingerImageData;
    }

    public void setFingerImageData(byte[] fingerImageData) {
        this.fingerImageData = fingerImageData;
    }
}
