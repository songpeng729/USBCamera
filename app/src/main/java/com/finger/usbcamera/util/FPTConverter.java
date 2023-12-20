package com.finger.usbcamera.util;

import com.finger.fpt.FPT5Constants;
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
    private static final String SYS_TYPE_CODE = "1900";//TODO 系统类型代码

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
            int fgp = finger.getIsFlat() ? finger.getFgp() + 10 : finger.getFgp();
            tpFinger.setFgp(fgp >= 10 ? ""+fgp : "0"+finger.getFgp());
            tpFinger.setFingerFeatureExtractionMethodCode("A");
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
        msg.setOriginalSystemCasePersonId(person.getPersonId());
        msg.setFingerPalmCardId(person.getPersonId());
        msg.setName(person.getName());
        msg.setCredentialsCode(FPTCode.DEFAULT_CERTIFICATE_TYPE);
        msg.setCredentialsNo(person.getIdCardNo());
        //字典转换
        msg.setGender(FPTCode.getGenderCode(person.getGender()));
        msg.setEthnic(FPTCode.getEthnicCode(person.getEthnic()));
        msg.setNationality(FPTCode.NATIONALITY_CHINA);
        msg.setBirthday(person.getBirthday());
        msg.setHukouAddress(person.getAddress());
        return msg;
    }
    private static CollectInfoMsg convert2CollectInfoMsg(Person person, User user){
        CollectInfoMsg msg = new CollectInfoMsg();
        msg.setChopDateTime(DateUtils.date2String(person.getGatherDate(),"yyyyMMddHHmmss"));
        msg.setChopPersonIdCard(user.getIdCardNo());
        msg.setChopPersonName(user.getName());
        msg.setChopPersonTel(user.getPhone());
        msg.setChopUnitCode(user.getUnitCode());
        msg.setChopUnitName(user.getUnitName());
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
            faceImage.setPersonPictureTypeCode(FACE_CENTER_CODE);
            faceImage.setPersonPictureFileLayout("JPEG");
            faceImage.setPersonPictureImageData(BitmapUtil.bitmap2Bytes(BitmapUtil.bytes2Bitmap(centerImage)));
            faceImageList.add(faceImage);
        }
        if(leftImage != null){
            TpFaceImage faceImage = new TpFaceImage();
            faceImage.setPersonPictureTypeCode(FACE_LEFT_CODE);
            faceImage.setPersonPictureFileLayout("JPEG");
            faceImage.setPersonPictureImageData(BitmapUtil.bitmap2Bytes(BitmapUtil.bytes2Bitmap(leftImage)));
            faceImageList.add(faceImage);
        }
        if(rightImage != null){
            TpFaceImage faceImage = new TpFaceImage();
            faceImage.setPersonPictureTypeCode(FACE_RIGHT_CODE);
            faceImage.setPersonPictureFileLayout("JPEG");
            faceImage.setPersonPictureImageData(BitmapUtil.bitmap2Bytes(BitmapUtil.bytes2Bitmap(rightImage)));
            faceImageList.add(faceImage);
        }

        return faceImageList;
    }
}
