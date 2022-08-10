package com.finger.usbcamera;

import android.util.JsonReader;

import com.finger.usbcamera.db.entity.Person;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_json(){
        String jsonStr = "{\"words_result\":{\"姓名\":{\"location\":{\"top\":127,\"left\":265,\"width\":87,\"height\":34},\"words\":\"宋朋\"},\"民族\":{\"location\":{\"top\":191,\"left\":425,\"width\":25,\"height\":31},\"words\":\"汉\"},\"住址\":{\"location\":{\"top\":312,\"left\":249,\"width\":337,\"height\":76},\"words\":\"河南省西华县城关镇北关外六组\"},\"公民身份号码\":{\"location\":{\"top\":473,\"left\":379,\"width\":460,\"height\":46},\"words\":\"412722198907290810\"},\"出生\":{\"location\":{\"top\":250,\"left\":260,\"width\":261,\"height\":30},\"words\":\"19890729\"},\"性别\":{\"location\":{\"top\":190,\"left\":257,\"width\":30,\"height\":32},\"words\":\"男\"}}," +
                "\"words_result_num\":6,\"idcard_number_type\":1,\"image_status\":\"normal\",\"log_id\":1551052650214776068}";

        try {
            JSONObject object = new JSONObject(jsonStr);
            JSONObject wordsResult= object.getJSONObject("words_result");
            String name = wordsResult.getJSONObject("姓名").getString("words");
            String nation = wordsResult.getJSONObject("民族").getString("words");
            String address = wordsResult.getJSONObject("住址").getString("words");
            String idcardno = wordsResult.getJSONObject("公民身份号码").getString("words");
            String birthday = wordsResult.getJSONObject("出生").getString("words");
            String sex = wordsResult.getJSONObject("性别").getString("words");
            Person person = new Person();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONTokener jsonTokener = new JSONTokener(jsonStr);
//        jsonTokener.nextValue();
    }
}