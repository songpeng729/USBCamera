package com.finger.fpt;

import com.finger.fpt.tp.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="package")
@XmlType(propOrder = {"packageHead","fingerprintPackageList"})
public class FPT5Object {

    @XmlElement(name = "packageHead")
    private PackageHead packageHead;

    /**
     * 捺印指掌纹信息
     */
    @XmlElement(name="fingerprintPackage")
    private List<FingerprintPackage> fingerprintPackageList;
//    /**
//     * 自定义信息
//     */
//    @XmlElement(name = "customDataPackage")
//    private List<CustomDataPackage> customDataPackages;

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
}
