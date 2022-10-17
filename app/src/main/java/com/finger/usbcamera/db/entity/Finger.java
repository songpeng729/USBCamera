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
    @Id
    @Property(nameInDb = "id")
    private String id;
    /**
     * 采集人员主键
     */
    @Property(nameInDb = "PERSON_ID")
    private String personId;
    /**
     * 指位(1-10)
     */
    @Property(nameInDb = "FGP")
    private int fgp;

    /**
     * 是否平面
     */
    @Property(nameInDb = "IS_FLAT")
    private int isFlat;
    /**
     * wsq压缩图
     */
    @Property(nameInDb = "IMG_DATA")
    private byte[] imgData;
    /**
     * 特征
     */
    @Property(nameInDb = "MNT_DATA")
    private byte[] mntData;
    /**
     * 采集日期
     */
    @Property(nameInDb = "CREATE_DATE")
    private Date createDate;
    /**
     * 采集人
     */
    @Property(nameInDb = "GATHER_USERID")
    private String gatherUserId;
    @Generated(hash = 1526037110)
    public Finger(String id, String personId, int fgp, int isFlat, byte[] imgData,
            byte[] mntData, Date createDate, String gatherUserId) {
        this.id = id;
        this.personId = personId;
        this.fgp = fgp;
        this.isFlat = isFlat;
        this.imgData = imgData;
        this.mntData = mntData;
        this.createDate = createDate;
        this.gatherUserId = gatherUserId;
    }
    @Generated(hash = 814071080)
    public Finger() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPersonId() {
        return this.personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public int getFgp() {
        return this.fgp;
    }
    public void setFgp(int fgp) {
        this.fgp = fgp;
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
    public String getGatherUserId() {
        return this.gatherUserId;
    }
    public void setGatherUserId(String gatherUserId) {
        this.gatherUserId = gatherUserId;
    }
    public int getIsFlat() {
        return this.isFlat;
    }
    public void setIsFlat(int isFlat) {
        this.isFlat = isFlat;
    }
}
