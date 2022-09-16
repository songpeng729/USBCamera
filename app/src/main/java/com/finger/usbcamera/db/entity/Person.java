package com.finger.usbcamera.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

@Entity(nameInDb = "PERSON")
public class Person {
    @Id
    @Property(nameInDb = "id")
    private String id;
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
     * 性别
     */
    @Property(nameInDb = "gender")
    private String gender;
    /**
     * 民族
     */
    @Property(nameInDb = "ethnic")
    private String ethnic;
    @Property(nameInDb = "nationality")
    private String nationality;
    @Property(nameInDb = "birthday")
    private String birthday;
    @Property(nameInDb = "address")
    private String address;
    @Property(nameInDb = "gather_date")
    private Date gatherDate;

    @Generated(hash = 1670348414)
    public Person(String id, String personId, String name, String idCardNo,
            String gender, String ethnic, String nationality, String birthday,
            String address, Date gatherDate) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.idCardNo = idCardNo;
        this.gender = gender;
        this.ethnic = ethnic;
        this.nationality = nationality;
        this.birthday = birthday;
        this.address = address;
        this.gatherDate = gatherDate;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getGatherDate() {
        return gatherDate;
    }

    public void setGatherDate(Date gatherDate) {
        this.gatherDate = gatherDate;
    }
}
