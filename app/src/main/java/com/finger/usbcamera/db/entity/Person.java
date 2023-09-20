package com.finger.usbcamera.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

/**
 * 人员信息主表
 * 导出fpt要求现住址和户籍地住址,没有警综人员编号，案事件人员编号等字段
 */
@Entity(nameInDb = "PERSON")
public class Person {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    /**
     * 捺印卡号
     */
    @Property(nameInDb = "personid")
    private String personId;
    /**
     * 姓名
     */
    @Property(nameInDb = "name")
    private String name;
    /**
     * 身份证号
     */
    @Property(nameInDb = "idcardno")
    private String idCardNo;

    /**
     * 身份证照片
     */
    @Property(nameInDb = "idcard_photo")
    private byte[] idCardPhoto;

    /**
     * 性别
     */
    @Property(nameInDb = "gender")
    private String gender;
    /**
     * 民族
     */
    @Property(nameInDb = "ethnic")
    private String ethnic;
    /**
     * 国籍
     */
    @Property(nameInDb = "nationality")
    private String nationality;
    /**
     * 出生日期
     */
    @Property(nameInDb = "birthday")
    private String birthday;
    /**
     * 身份证地址
     */
    @Property(nameInDb = "address")
    private String address;
    /**
     * 采集时间
     */
    @Property(nameInDb = "gather_date")
    private Date gatherDate;

    /**
     * 备注
     */
    @Property(nameInDb = "remark")
    private String remark;

    /**
     * 采集人
     */
    @Property(nameInDb = "gather_user_id")
    private Long gatherUserId;

    /**
     * 身份证采集模式，1：身份证拍照或扫描 0：手动录入
     */
    @Property(nameInDb = "idcard_status")
    private int idCardStatus;

    /**
     * 人像采集状态 1:已采集
     */
    @Property(nameInDb = "face_status")
    private int faceStatus = 0;

    /**
     * 指纹采集状态 1:已采集
     */
    @Property(nameInDb = "finger_status")
    private int fingerStatus = 0;

    /**
     * 上报状态 1:已上报，2：上报失败
     */
    @Property(nameInDb = "upload_status")
    private int uploadStatus= 0;

    @Generated(hash = 1853538121)
    public Person(Long id, String personId, String name, String idCardNo,
            byte[] idCardPhoto, String gender, String ethnic, String nationality,
            String birthday, String address, Date gatherDate, String remark,
            Long gatherUserId, int idCardStatus, int faceStatus, int fingerStatus,
            int uploadStatus) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.idCardNo = idCardNo;
        this.idCardPhoto = idCardPhoto;
        this.gender = gender;
        this.ethnic = ethnic;
        this.nationality = nationality;
        this.birthday = birthday;
        this.address = address;
        this.gatherDate = gatherDate;
        this.remark = remark;
        this.gatherUserId = gatherUserId;
        this.idCardStatus = idCardStatus;
        this.faceStatus = faceStatus;
        this.fingerStatus = fingerStatus;
        this.uploadStatus = uploadStatus;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return this.idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public byte[] getIdCardPhoto() {
        return this.idCardPhoto;
    }

    public void setIdCardPhoto(byte[] idCardPhoto) {
        this.idCardPhoto = idCardPhoto;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnic() {
        return this.ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getGatherDate() {
        return this.gatherDate;
    }

    public void setGatherDate(Date gatherDate) {
        this.gatherDate = gatherDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGatherUserId() {
        return this.gatherUserId;
    }

    public void setGatherUserId(Long gatherUserId) {
        this.gatherUserId = gatherUserId;
    }

    public int getIdCardStatus() {
        return this.idCardStatus;
    }

    public void setIdCardStatus(int idCardStatus) {
        this.idCardStatus = idCardStatus;
    }

    public int getFaceStatus() {
        return this.faceStatus;
    }

    public void setFaceStatus(int faceStatus) {
        this.faceStatus = faceStatus;
    }

    public int getFingerStatus() {
        return this.fingerStatus;
    }

    public void setFingerStatus(int fingerStatus) {
        this.fingerStatus = fingerStatus;
    }

    public int getUploadStatus() {
        return this.uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
