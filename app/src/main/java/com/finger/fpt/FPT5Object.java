package com.finger.fpt;

import com.finger.fpt.lp.*;
import com.finger.fpt.query.*;
import com.finger.fpt.tp.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="package")
public class FPT5Object {

    @XmlElement(name = "packageHead")
    private PackageHead packageHead;

    /**
     * 捺印指掌纹信息
     */
    @XmlElement(name="fingerprintPackage")
    private List<FingerprintPackage> fingerprintPackageList;
    
    /**
     * 现场指掌纹信息
     */
    @XmlElement(name = "latentPackage")
	private List<LatentPackage> latentPackageList;

    /**
     * 现场指掌纹查询比对请求信息
     */
    @XmlElement(name = "latentTaskPackage")
    private List<LatentTaskPackage> latentTaskPackageList;

    /**
     * 捺印指掌纹查询比对请求信息
     */
    @XmlElement(name = "printTaskPackage")
    private List<PrintTaskPackage> printTaskPackageList;

    /**
     * 指掌纹正查比对结果信息
     */
    @XmlElement(name = "LTResultPackage")
    private List<LTResultPackage> ltResultPackageList;

    /**
     * 指掌纹倒查比对结果信息
     */
    @XmlElement(name = "TLResultPackage")
    private List<TLResultPackage> tlResultPackageList;

    /**
     * 指掌纹查重比对结果信息
     */
    @XmlElement(name = "TTResultPackage")
    private List<TTResultPackage> ttResultPackageList;

    /**
     * 指掌纹串查比对结果信息
     */
    @XmlElement(name = "LLResultPackage")
    private List<LLResultPackage> llResultPackageList;

    /**
     * 指掌纹正查和倒查比中信息
     */
    @XmlElement(name = "LTHitResultPackage")
    private List<LTHitResultPackage> ltHitResultPackageList;

    /**
     * 指掌纹查重比中信息
     */
    @XmlElement(name = "TTHitResultPackage")
    private List<TTHitResultPackage> ttHitResultPackageList;
    /**
     * 指掌纹串查比中信息
     */
    @XmlElement(name = "LLHitResultPackage")
    private List<LLHitResultPackage> llHitResultPackages;

    /**
     * 撤销现场指掌纹信息
     */
    @XmlElement(name = "cancelLatentPackage")
    private List<CancelLatentPackage> cancelLatentPackageList;

    /**
     * 自定义信息
     */
    @XmlElement(name = "customDataPackage")
    private List<CustomDataPackage> customDataPackages;

    /**
     * 文件名，不属于xml结构，仅用于导出时生成文件
     */
    @XmlTransient
    private String fileName;

    public PackageHead getPackageHead() {
        return packageHead;
    }

    public void setPackageHead(PackageHead packageHead) {
        this.packageHead = packageHead;
    }

    public List<FingerprintPackage> getFingerprintPackageList() {
        return fingerprintPackageList;
    }

    public void setFingerprintPackageList(List<FingerprintPackage> fingerprintPackageList) {
        this.fingerprintPackageList = fingerprintPackageList;
    }

	public List<LatentPackage> getLatentPackageList() {
		return latentPackageList;
	}

	public void setLatentPackageList(List<LatentPackage> latentPackageList) {
		this.latentPackageList = latentPackageList;
	}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<CustomDataPackage> getCustomDataPackages() {
        return customDataPackages;
    }

    public void setCustomDataPackages(List<CustomDataPackage> customDataPackages) {
        this.customDataPackages = customDataPackages;
    }

    public List<LatentTaskPackage> getLatentTaskPackageList() {
        return latentTaskPackageList;
    }

    public void setLatentTaskPackageList(List<LatentTaskPackage> latentTaskPackageList) {
        this.latentTaskPackageList = latentTaskPackageList;
    }

    public List<PrintTaskPackage> getPrintTaskPackageList() {
        return printTaskPackageList;
    }

    public void setPrintTaskPackageList(List<PrintTaskPackage> printTaskPackageList) {
        this.printTaskPackageList = printTaskPackageList;
    }

    public List<LTResultPackage> getLtResultPackageList() {
        return ltResultPackageList;
    }

    public void setLtResultPackageList(List<LTResultPackage> ltResultPackageList) {
        this.ltResultPackageList = ltResultPackageList;
    }

    public List<TLResultPackage> getTlResultPackageList() {
        return tlResultPackageList;
    }

    public void setTlResultPackageList(List<TLResultPackage> tlResultPackageList) {
        this.tlResultPackageList = tlResultPackageList;
    }

    public List<TTResultPackage> getTtResultPackageList() {
        return ttResultPackageList;
    }

    public void setTtResultPackageList(List<TTResultPackage> ttResultPackageList) {
        this.ttResultPackageList = ttResultPackageList;
    }

    public List<LLResultPackage> getLlResultPackageList() {
        return llResultPackageList;
    }

    public void setLlResultPackageList(List<LLResultPackage> llResultPackageList) {
        this.llResultPackageList = llResultPackageList;
    }

    public List<LTHitResultPackage> getLtHitResultPackageList() {
        return ltHitResultPackageList;
    }

    public void setLtHitResultPackageList(List<LTHitResultPackage> ltHitResultPackageList) {
        this.ltHitResultPackageList = ltHitResultPackageList;
    }

    public List<TTHitResultPackage> getTtHitResultPackageList() {
        return ttHitResultPackageList;
    }

    public void setTtHitResultPackageList(List<TTHitResultPackage> ttHitResultPackageList) {
        this.ttHitResultPackageList = ttHitResultPackageList;
    }

    public List<LLHitResultPackage> getLlHitResultPackages() {
        return llHitResultPackages;
    }

    public void setLlHitResultPackages(List<LLHitResultPackage> llHitResultPackages) {
        this.llHitResultPackages = llHitResultPackages;
    }

    public List<CancelLatentPackage> getCancelLatentPackageList() {
        return cancelLatentPackageList;
    }

    public void setCancelLatentPackageList(List<CancelLatentPackage> cancelLatentPackageList) {
        this.cancelLatentPackageList = cancelLatentPackageList;
    }
}
