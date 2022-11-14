package com.finger.usbcamera.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 采集人员指纹表
 */
@Entity(nameInDb = "FINGER")
public class Finger {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    /**
     * 采集人员主键
     */
    @Property(nameInDb = "person_id")
    private Long personId;
    /**
     * 指位(1-10)
     */
    @Property(nameInDb = "fgp")
    private int fgp;

    /**
     * 是否平面
     */
    @Property(nameInDb = "is_flat")
    private boolean isFlat;
    /**
     * wsq压缩图
     */
    @Property(nameInDb = "img_data")
    private byte[] imgData;
    /**
     * 特征
     */
    @Property(nameInDb = "mnt_data")
    private byte[] mntData;
    /**
     * 创建时间
     */
    @Property(nameInDb = "create_date")
    private Date createDate;
    @Generated(hash = 770619718)
    public Finger(Long id, Long personId, int fgp, boolean isFlat, byte[] imgData,
            byte[] mntData, Date createDate) {
        this.id = id;
        this.personId = personId;
        this.fgp = fgp;
        this.isFlat = isFlat;
        this.imgData = imgData;
        this.mntData = mntData;
        this.createDate = createDate;
    }
    @Generated(hash = 814071080)
    public Finger() {
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
    public int getFgp() {
        return this.fgp;
    }
    public void setFgp(int fgp) {
        this.fgp = fgp;
    }
    public boolean getIsFlat() {
        return this.isFlat;
    }
    public void setIsFlat(boolean isFlat) {
        this.isFlat = isFlat;
    }
    public byte[] getImgData() {
        return this.imgData;
    }
    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }
    public byte[] getMntData() {
        return this.mntData;
    }
    public void setMntData(byte[] mntData) {
        this.mntData = mntData;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
