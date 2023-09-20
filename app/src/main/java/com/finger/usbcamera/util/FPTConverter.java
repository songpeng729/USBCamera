package com.finger.usbcamera.util;

import com.finger.fpt.tp.CollectInfoMsg;
import com.finger.fpt.tp.DescriptiveMsg;
import com.finger.fpt.tp.FingerprintPackage;
import com.finger.fpt.tp.TPFinger;
import com.finger.fpt.tp.TpFaceImage;
import com.finger.usbcamera.db.entity.Face;
import com.finger.usbcamera.db.entity.Finger;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.entity.User;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * FPT5 转换类
 */
public class FPTConverter {
    private static final String SYS_TYPE_CODE = "0000";//TODO 系统类型代码

    private static final String FACE_CENTER_CODE = "1";
    private static final String FACE_LEFT_CODE = "2";
    private static final String FACE_RIGHT_CODE = "4";
    private static final String FACE_OTHER = "9";

    public static FingerprintPackage convert2FingerprintPackage(Person person, List<Finger> fingerList, Face face, User user){
        FingerprintPackage fingerprintPackage = new FingerprintPackage();
        fingerprintPackage.setDescriptiveMsg(convert2DescriptiveMsg(person));
        fingerprintPackage.setCollectInfoMsg(convert2CollectInfoMsg(person,user));
        fingerprintPackage.setFingers(convert2TPFingerList(fingerList));
        if(face != null){
            fingerprintPackage.setFaceImages(convert2TpFaceImage(face));
        }

        return fingerprintPackage;
    }
    private static List<TPFinger> convert2TPFingerList(List<Finger> fingerList){
        List<TPFinger> tpFingerList = new ArrayList<>();
        for (Finger finger : fingerList) {
            TPFinger tpFinger = new TPFinger();
            tpFinger.setFgp(finger.getIsFlat() ? finger.getFgp()+10+ "" : finger.getFgp()+"");
            //去掉头部64位信息
            byte[] imgData = finger.getImgData();
            byte[] wsqData = new byte[imgData.length - 64];
            System.arraycopy(imgData, 64, wsqData, 0, wsqData.length);

            tpFinger.setFingerImageData(wsqData);
            tpFinger.setFingerImageHorizontalDirectionLength("640");
            tpFinger.setFingerImageVerticalDirectionLength("640");
            tpFinger.setFingerImageRatio("500");
            tpFinger.setFingerFeatureExtractionMethodCode("0");
            tpFinger.setFingerImageCompressMethodDescript("1400");

            tpFingerList.add(tpFinger);
        }

        return tpFingerList;
    }
    private static DescriptiveMsg convert2DescriptiveMsg(Person person){
        DescriptiveMsg msg = new DescriptiveMsg();
        msg.setCasePersonid(person.getPersonId());
        msg.setFingerPalmCardId(person.getPersonId());
        msg.setName(person.getName());
        //字典转换
        msg.setGender(FPTCode.getGenderCode(person.getGender()));
        msg.setEthnic(FPTCode.getEthnicCode(person.getEthnic()));
        msg.setBirthday(person.getBirthday());
        msg.setHukouAddress(person.getAddress());
        return msg;
    }
    private static CollectInfoMsg convert2CollectInfoMsg(Person person, User user){
        CollectInfoMsg msg = new CollectInfoMsg();
        msg.setChopDateTime(DateUtils.date2String(person.getGatherDate()));
        msg.setChopPersonIdCard(user.getIdCardNo());
        msg.setChopPersonName(user.getName());
        msg.setChopPersonTel(user.getPhone());
        msg.setChopUnitCode(user.getUnitCode());
        msg.setChopUnitCode(user.getUnitName());
        msg.setFingerprintComparisonSysTypeDescript(SYS_TYPE_CODE);
        return msg;
    }
    private static List<TpFaceImage> convert2TpFaceImage(Face face){
        List<TpFaceImage> faceImageList = new ArrayList<>();
        byte[] centerImage = face.getCenterImage();
        byte[] leftImage = face.getLeftImage();
        byte[] rightImage = face.getRightImage();
        if(centerImage != null){
            TpFaceImage faceImage = new TpFaceImage();
            faceImage.setPersonPictureTypeCode("JPEG");
            faceImage.setPersonPictureImageData(Base64.getEncoder().encodeToString(centerImage));
            faceImage.setPersonPictureFileLayout(FACE_CENTER_CODE);
            faceImageList.add(faceImage);
        }
        if(leftImage != null){
            TpFaceImage faceImage = new TpFaceImage();
            faceImage.setPersonPictureTypeCode("JPEG");
            faceImage.setPersonPictureImageData(Base64.getEncoder().encodeToString(leftImage));
            faceImage.setPersonPictureFileLayout(FACE_LEFT_CODE);
            faceImageList.add(faceImage);
        }
        if(rightImage != null){
            TpFaceImage faceImage = new TpFaceImage();
            faceImage.setPersonPictureTypeCode("JPEG");
            faceImage.setPersonPictureImageData(Base64.getEncoder().encodeToString(rightImage));
            faceImage.setPersonPictureFileLayout(FACE_RIGHT_CODE);
            faceImageList.add(faceImage);
        }

        return faceImageList;
    }
}
