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
    @Property(nameInDb = "sex")
    private String sex;
    @Property(nameInDb = "nation")
    private String nation;
    @Property(nameInDb = "nationality")
    private String nationality;
    @Property(nameInDb = "birthday")
    private String birthday;
    @Property(nameInDb = "address")
    private String address;
    @Property(nameInDb = "gather_date")
    private Date gatherDate;


    @Generated(hash = 1404527497)
    public Person(String id, String personId, String name, String idCardNo,
            String sex, String nation, String nationality, String birthday,
            String address, Date gatherDate) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.idCardNo = idCardNo;
        this.sex = sex;
        this.nation = nation;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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
