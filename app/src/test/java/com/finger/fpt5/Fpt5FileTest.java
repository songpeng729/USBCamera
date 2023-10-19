package com.finger.fpt5;

import com.finger.fpt.FPT5Object;
import com.finger.fpt.PackageHead;
import com.finger.fpt.tp.FingerprintPackage;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.db.entity.Face;
import com.finger.usbcamera.db.entity.Finger;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.entity.User;
import com.finger.usbcamera.db.greendao.FaceDao;
import com.finger.usbcamera.db.greendao.FingerDao;
import com.finger.usbcamera.util.DateUtils;
import com.finger.usbcamera.util.FPTConverter;
import com.finger.usbcamera.util.XmlUtil;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fpt5FileTest {
    @Test
    public void test_fpt5File(){
        FPT5Object fpt5Object = new FPT5Object();
        PackageHead packageHead = new PackageHead();
        packageHead.setCreateTime(DateUtils.date2DateTimeString(new Date()));

        User gatherUser = new User();

        packageHead.setSendPersonIdCard(gatherUser.getIdCardNo());
        packageHead.setSendPersonName(gatherUser.getName());
        packageHead.setSendPersonTel(gatherUser.getPhone());
        packageHead.setSendUnitCode(gatherUser.getUnitCode());
        packageHead.setSendUnitName(gatherUser.getUnitName());
        packageHead.setSendUnitSystemType("1900");
        packageHead.setSendUnitSystemType("FingerApp");

        fpt5Object.setPackageHead(packageHead);
        List<FingerprintPackage> fingerprintPackageList = new ArrayList<>();
        Person person = new Person();
        person.setGatherDate(new Date());

        FingerprintPackage fingerprintPackage = FPTConverter.convert2FingerprintPackage(person, new ArrayList<Finger>(), null, gatherUser);

        fingerprintPackageList.add(fingerprintPackage);
        fpt5Object.setFingerprintPackageList(fingerprintPackageList);
        String xml = XmlUtil.convertToXml(fpt5Object);
        System.out.println(xml);
    }
}
