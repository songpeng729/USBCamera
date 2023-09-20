package com.finger.usbcamera.util;

import com.finger.usbcamera.USBCameraAPP;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebHttpClientUtil {
    static final OkHttpClient httpClient = new OkHttpClient().newBuilder().build();

    public static String doPost(String url, String context) throws IOException {
        /*
        application/x-www-form-urlencoded：默认， key1=value1&key2=value2
multipart/form-data：一般用于表单需要文件上传
application/json：提交json数据
text/plain：将文件设置为纯文本的形式
application/octet-stream:提交二进制数据，如果用于文件上传，只能上传一个文件
         */
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, context);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = httpClient.newCall(request).execute();
        if(response.body() != null){
            return response.body().string();
        }

        return "";
    }
}
