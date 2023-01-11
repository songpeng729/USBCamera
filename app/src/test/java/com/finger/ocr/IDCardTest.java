package com.finger.ocr;

import com.baidu.ocr.IDCardUtil;
import com.baidu.auth.AuthService;

import org.json.JSONObject;
import org.junit.Test;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * https://console.bce.baidu.com/tools/?_=1668473684721#/api?product=AI&project=%E6%96%87%E5%AD%97%E8%AF%86%E5%88%AB&parent=%E5%8D%A1%E8%AF%81OCR&api=rest%2F2.0%2Focr%2Fv1%2Fidcard&method=post
 */
public class IDCardTest {
    @Test
    public void test_getAuth(){
        String accessToken = AuthService.getAuth();
        System.out.println(accessToken);
    }
    @Test
    public void test_idcard() throws Exception {
        IDCardUtil.postidcard("/Users/songpeng/Downloads/songpeng-doc/idcard.jpeg");
    }
    @Test
    public void test_resultJson() throws Exception {
        byte[] b = Files.readAllBytes(Paths.get("/Users/songpeng/Downloads/rest-2.0-ocr-v1-idcard.txt"));
        String str = new String(b);
        JSONObject jsonObject = new JSONObject(str);
        String photo = jsonObject.getString("photo");
        byte[] photoData = Base64.getDecoder().decode(photo);
        new FileOutputStream("/Users/songpeng/Downloads/photo.jpg").write(photoData);
    }

}
