package com.finger.usbcamera.util;

import com.finger.fpt.tp.DescriptiveMsg;
import com.finger.fpt.tp.FingerprintPackage;
import com.finger.usbcamera.db.entity.Person;

/**
 * FPT5 转换类
 */
public class FPTConverter {
    public static FingerprintPackage convert2FingerprintPackage(Person person){
        FingerprintPackage fingerprintPackage = new FingerprintPackage();
        fingerprintPackage.setDescriptiveMsg(convert2DescriptiveMsg(person));

        return fingerprintPackage;
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
