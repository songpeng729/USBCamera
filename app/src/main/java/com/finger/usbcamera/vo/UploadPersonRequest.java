package com.finger.usbcamera.vo;

import com.finger.usbcamera.db.entity.Face;
import com.finger.usbcamera.db.entity.Finger;
import com.finger.usbcamera.db.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员信息
 */
public class UploadPersonRequest{
    private Person person;
    private Face face;
    private List<Finger> fingerList = new ArrayList<Finger>();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public List<Finger> getFingerList() {
        return fingerList;
    }

    public void setFingerList(List<Finger> fingerList) {
        this.fingerList = fingerList;
    }
}
