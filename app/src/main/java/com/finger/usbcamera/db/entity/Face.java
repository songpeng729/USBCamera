package com.finger.usbcamera.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 人像
 */
@Entity(nameInDb = "FACE")
public class Face {
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    /**
     * 采集人员主键
     */
    @Property(nameInDb = "person_id")
    private Long personId;
    /**
     * 正脸
     */
    @Property(nameInDb = "center_image")
    private byte[] centerImage;
    @Property(nameInDb = "left_image")
    private byte[] leftImage;
    @Property(nameInDb = "right_image")
    private byte[] rightImage;
    /**
     * 创建时间
     */
    @Property(nameInDb = "create_date")
    private Date createDate;
    @Generated(hash = 1954142677)
    public Face(Long id, Long personId, byte[] centerImage, byte[] leftImage,
            byte[] rightImage, Date createDate) {
        this.id = id;
        this.personId = personId;
        this.centerImage = centerImage;
        this.leftImage = leftImage;
        this.rightImage = rightImage;
        this.createDate = createDate;
    }
    @Generated(hash = 601504354)
    public Face() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPersonId() {
        return this.personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public byte[] getCenterImage() {
        return this.centerImage;
    }
    public void setCenterImage(byte[] centerImage) {
        this.centerImage = centerImage;
    }
    public byte[] getLeftImage() {
        return this.leftImage;
    }
    public void setLeftImage(byte[] leftImage) {
        this.leftImage = leftImage;
    }
    public byte[] getRightImage() {
        return this.rightImage;
    }
    public void setRightImage(byte[] rightImage) {
        this.rightImage = rightImage;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
