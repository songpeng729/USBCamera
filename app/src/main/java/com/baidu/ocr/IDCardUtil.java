package com.baidu.ocr;

import com.baidu.auth.AuthService;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.Word;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 身份证OCR识别
 * 重要提示代码中所需工具类
 * FileUtil,Base64Util,HttpUtil,GsonUtils请从
 * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
 * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
 * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
 * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
 * 下载
 */
public class IDCardUtil {
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    // 请求url
    private static final String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";

    public static IDCardResult2 postIdcard(String filePath) throws Exception{
        return postIdcard(AuthService.getAccessToken(), filePath);
    }

    /**
     * 发送请求识别身份证信息，正面id_card_side=front,带照片detect_photo=true
     * @param token
     * @param filePath
     * @return
     * @throws Exception
     */
    public static IDCardResult2 postIdcard(String token, String filePath) throws Exception{
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        // image 可以通过 getFileContentAsBase64("C:\fakepath\身份证照片.jpeg") 方法获取
        String image = getFileContentAsBase64(filePath);
        String imgParam = URLEncoder.encode(image, "UTF-8");
        RequestBody body = RequestBody.create(mediaType, "id_card_side=front&detect_photo=true&image="+imgParam);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" + token)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return parse(response.body().string());
    }

    /**
     * 转换身份证请求反馈的json数据
     * @param json
     * @return
     * @throws OCRError
     */
    private static IDCardResult2 parse(String json) throws OCRError {
        OCRError error;
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("error_code")) {
                error = new OCRError(jsonObject.optInt("error_code"), jsonObject.optString("error_msg"));
                error.setLogId(jsonObject.optLong("log_id"));
                throw error;
            } else {
                IDCardResult2 result = new IDCardResult2();
                result.setLogId(jsonObject.optLong("log_id"));
                result.setJsonRes(json);
                result.setDirection(jsonObject.optInt("direction", -1));
                result.setWordsResultNumber(jsonObject.optInt("words_result_num"));
                result.setRiskType(jsonObject.optString("risk_type"));
                result.setImageStatus(jsonObject.optString("image_status"));
                JSONObject wordResult = jsonObject.optJSONObject("words_result");
                String idCardSide = "front";
                result.setIdCardSide(idCardSide);
                if (wordResult != null) {
                    if ("front".equals(idCardSide)) {
                        result.setAddress(map(wordResult.optJSONObject("住址")));
                        result.setIdNumber(map(wordResult.optJSONObject("公民身份号码")));
                        result.setBirthday(map(wordResult.optJSONObject("出生")));
                        result.setGender(map(wordResult.optJSONObject("性别")));
                        result.setName(map(wordResult.optJSONObject("姓名")));
                        result.setEthnic(map(wordResult.optJSONObject("民族")));
                    } else if ("back".equals(idCardSide)) {
                        result.setSignDate(map(wordResult.optJSONObject("签发日期")));
                        result.setExpiryDate(map(wordResult.optJSONObject("失效日期")));
                        result.setIssueAuthority(map(wordResult.optJSONObject("签发机关")));
                    }
                }
                //设置照片
                result.setPhoto(jsonObject.getString("photo"));

                return result;
            }
        } catch (JSONException var5) {
            error = new OCRError(283505, "Server illegal response " + json, var5);
            throw error;
        }
    }
    private static Word map(JSONObject jsonObject) {
        Word word = null;
        if (jsonObject != null) {
            word = new Word();
            JSONObject locationObject = jsonObject.optJSONObject("location");
            word.getLocation().setLeft(locationObject.optInt("left"));
            word.getLocation().setTop(locationObject.optInt("top"));
            word.getLocation().setWidth(locationObject.optInt("width"));
            word.getLocation().setHeight(locationObject.optInt("height"));
            word.setWords(jsonObject.optString("words"));
        }

        return word;
    }
    /**
     * 参考文档，通过http请求
     * https://ai.baidu.com/ai-doc/OCR/rk3h7xzck#%E5%9C%A8%E7%BA%BF%E8%B0%83%E8%AF%95
     * @return
     */
    @Deprecated
    public static String idcard() {
        try {
            // 本地文件路径
            String filePath = "/Users/songpeng/Downloads/songpeng-doc/身份证照片.jpeg";//"[本地文件路径]";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "id_card_side=front&detect_photo=true" + "&image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "[调用鉴权接口获取的token]";
            String accessToken = AuthService.getAuth();
            System.out.println(accessToken);

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件base64编码
     * @param path 文件路径
     * @return base64编码信息，不带文件头
     * @throws IOException IO异常
     */
    static String getFileContentAsBase64(String path) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(path));
        return Base64.getEncoder().encodeToString(b);
    }
}
