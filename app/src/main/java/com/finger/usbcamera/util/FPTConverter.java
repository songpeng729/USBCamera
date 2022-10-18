package com.finger.usbcamera.util;

import com.finger.fpt.tp.DescriptiveMsg;
import com.finger.fpt.tp.FingerprintPackage;
import com.finger.fpt.tp.TPFinger;
import com.finger.usbcamera.db.entity.Finger;
import com.finger.usbcamera.db.entity.Person;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * FPT5 转换类
 */
public class FPTConverter {
    public static FingerprintPackage convert2FingerprintPackage(Person person, List<Finger> fingerList){
        FingerprintPackage fingerprintPackage = new FingerprintPackage();
        fingerprintPackage.setDescriptiveMsg(convert2DescriptiveMsg(person));
        fingerprintPackage.setFingers(convert2TPFingerList(fingerList));

        return fingerprintPackage;
    }
    private static List<TPFinger> convert2TPFingerList(List<Finger> fingerList){
        List<TPFinger> tpFingerList = new ArrayList<>();
        for (Finger finger : fingerList) {
            TPFinger tpFinger = new TPFinger();
            boolean isFlat = finger.getIsFlat() == 1;
            tpFinger.setFgp(isFlat ? finger.getFgp()+10+ "" : finger.getFgp()+"");
            tpFinger.setFingerImageData(Base64.getEncoder().encodeToString(finger.getImgData()));
            tpFinger.setFingerImageHorizontalDirectionLength("640");
            tpFinger.setFingerImageVerticalDirectionLength("640");
            tpFinger.setFingerImageRatio("500");
            tpFingerList.add(tpFinger);
        }

        return tpFingerList;
    }
    private static DescriptiveMsg convert2DescriptiveMsg(Person person){
        DescriptiveMsg msg = new DescriptiveMsg();
        msg.setName(person.getName());
        //TODO 字典转换
        msg.setGender(person.getGender());
        msg.setBirthday(person.getBirthday());
        msg.setHukouAddress(person.getAddress());
        msg.setEthnic(person.getEthnic());
        return msg;
    }
}
