package com.finger.fpt5;

import com.finger.fpt.FPT5Object;
import com.finger.fpt.PackageHead;
import com.finger.usbcamera.util.XmlUtil;

import org.junit.Test;

public class Fpt5FileTest {
    @Test
    public void test_fpt5File(){
        FPT5Object object = new FPT5Object();
        object.setPackageHead(new PackageHead());
        String xml = XmlUtil.convertToXml(object);
        System.out.println(xml);
    }
}
