package com.finger.usbcamera.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户表
 */
@Entity(nameInDb = "USER")
public class User {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "login_name")
    private String loginName;

    @Property(nameInDb = "password")
    private String password;
    /**
     * 捺印单位公安机关机构代码
     */
    @Property(nameInDb = "unit_code")
    private String unitCode;
    /**
     * 捺印单位公安机关机构名称
     */
    @Property(nameInDb = "unit_name")
    private String unitName;
    /**
     * 捺印人姓名
     */
    @Property(nameInDb = "name")
    private String name;
    /**
     * 捺印人身份证号码
     */
    @Property(nameInDb = "idcardno")
    private String idCardNo;
    /**
     * 捺印人联系电话
     */
    @Property(nameInDb = "phone")
    private String phone;
    @Generated(hash = 1425018413)
    public User(Long id, String loginName, String password, String unitCode,
            String unitName, String name, String idCardNo, String phone) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.name = name;
        this.idCardNo = idCardNo;
        this.phone = phone;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUnitCode() {
        return this.unitCode;
    }
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
    public String getUnitName() {
        return this.unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
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
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
