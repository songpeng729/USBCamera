package com.finger.usbcamera.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.baidu.ocr.FileUtil;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.greendao.PersonDao;

import org.acra.data.StringFormat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static com.finger.usbcamera.activity.FingerActivity.EXTRA_IDCARDNO;
import static com.finger.usbcamera.activity.FingerActivity.EXTRA_NAME;
import static com.finger.usbcamera.activity.FingerActivity.EXTRA_PERSONID;

/**
 * 身份证采集
 */
public class IDCardActivity extends AppCompatActivity {
    private final String TAG = "IDCardActivity";
    private static final int REQUEST_CODE_PICK_IMAGE_FRONT = 201;
    private static final int REQUEST_CODE_PICK_IMAGE_BACK = 202;
    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_GATHER_FINGER = 101;

    private TextView name, idCardNo, ethnic, birthday, address;
    private Button saveBtn;
    private Spinner gender;

    private TextView infoTextView;
    private AlertDialog.Builder alertDialog;//OCR 弹框
    private PersonDao personDao;
    private Context mContext;

    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(IDCardActivity.this, Manifest.permission
                .READ_EXTERNAL_STORAGE);
        if (ret != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IDCardActivity.this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }
    /**
     * 自定义license的文件路径和文件名称，以license文件方式初始化
     */
    private void initAccessTokenLicenseFile() {
        OCR.getInstance(getApplicationContext()).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
//                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("自定义文件路径licence方式获取token失败", error.getMessage());
            }
        }, "aip-ocr.license", getApplicationContext());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        setContentView(R.layout.activity_idcard);
        alertDialog = new AlertDialog.Builder(this);
        infoTextView = (TextView) findViewById(R.id.info_text_view);
        name = findViewById(R.id.id_card_name_edit);
        idCardNo = findViewById(R.id.id_card_idcard_edit);
        gender = findViewById(R.id.id_card_gender_edit);
        ethnic = findViewById(R.id.id_card_ethnic_edit);
        address = findViewById(R.id.id_card_address_edit);
        birthday = findViewById(R.id.id_card_birthday_edit);
        saveBtn = findViewById(R.id.id_card_save);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePerson();
            }
        });

        //通过文件方式进行授权
        initAccessTokenLicenseFile();

        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
                new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }
                        infoTextView.setText("本地质量控制初始化错误，错误原因： " + msg);
                    }
                });

        //相册选择照片
        findViewById(R.id.gallery_button_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGalleryPermission()) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE_FRONT);
                }
            }
        });

        // 身份证正面拍照
        findViewById(R.id.id_card_front_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IDCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        // 身份证正面扫描
        findViewById(R.id.id_card_front_button_native).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IDCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                        true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                        true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
    }

    /**
     *
     * @param idCardSide 身份证正反面
     * @param filePath 身份证照片路径
     */
    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    setIDCardResult(result);
//                    saveIDCardResult(result);
                }
            }

            @Override
            public void onError(OCRError error) {
                alertText("", error.getMessage());
            }
        });
    }

    /**
     * 保存身份证信息
     * @param idCardResult
     */
    private void saveIDCardResult(IDCardResult idCardResult){
        Person person = new Person();
        person.setName(idCardResult.getName().getWords());
        person.setGender(idCardResult.getGender().getWords());
        person.setAddress(idCardResult.getAddress().getWords());
        person.setIdCardNo(idCardResult.getIdNumber().getWords());
        person.setEthnic(idCardResult.getEthnic().getWords());
        person.setBirthday(idCardResult.getBirthday().getWords());
        person.setGatherDate(new Date());

        person.setId(generatePersonId(person.getIdCardNo()));
        personDao.insert(person);
    }

    /**
     * 生成人员编号, 当前使用P+身份证号{18}+随机4位
     * fpt5 asjxgrybh 正则表达式: |(P[0-9]{6}([0-9]|[A-Z]){6}[0-9]{4}(0[1-9]|1[0-2])([0-9]|[A-Z]){4})
     * @return
     */
    private String generatePersonId(String idcardno){
        return String.format("P%s%04d", idcardno, (int)(Math.random()*10000));
    }
    private void savePerson(){
        if(name.getText().toString().isEmpty() || idCardNo.getText().toString().isEmpty()){
            Toast.makeText(this, "请输入姓名和身份证!", Toast.LENGTH_SHORT).show();
            return;
        }
        Person person = new Person();
        person.setName(name.getText().toString());
        person.setGender(gender.getSelectedItem().toString());
        person.setAddress(address.getText().toString());
        person.setIdCardNo(idCardNo.getText().toString());
        person.setEthnic(ethnic.getText().toString());
        person.setBirthday(birthday.getText().toString());
        person.setGatherDate(new Date());

        person.setId(generatePersonId(person.getIdCardNo()));
        personDao.insert(person);

        alertText("保存成功", "继续采集指纹！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, FingerActivity.class);
                intent.putExtra(EXTRA_NAME, person.getName());
                intent.putExtra(EXTRA_IDCARDNO, person.getIdCardNo());
                intent.putExtra(EXTRA_PERSONID, person.getId());
                startActivityForResult(intent, REQUEST_CODE_GATHER_FINGER);
            }
        });
    }

    private void setIDCardResult(IDCardResult idCardResult){
        name.setText(idCardResult.getName().getWords());
        setGender(idCardResult.getGender().getWords());
        address.setText(idCardResult.getAddress().getWords());
        idCardNo.setText(idCardResult.getIdNumber().getWords());
        ethnic.setText(idCardResult.getEthnic().getWords());
        birthday.setText(idCardResult.getBirthday().getWords());
    }

    /**
     * 设置性别, 下拉框选中
     * @param genderString
     */
    private void setGender(String genderString){
        SpinnerAdapter adapter= gender.getAdapter();
        for(int i = 0; i < adapter.getCount(); i++){
            if(adapter.getItem(i).toString().equals(genderString)){
                gender.setSelection(i);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE_FRONT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
        }

        if (requestCode == REQUEST_CODE_PICK_IMAGE_BACK && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }

        if(requestCode == REQUEST_CODE_GATHER_FINGER){
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    private void alertText(final String title, final String message) {
        alertText(title, message, null);
    }
    private void alertText(final String title, final String message, DialogInterface.OnClickListener listener) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", listener)
                        .setCancelable(false)//不能取消
                        .show();
            }
        });
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        CameraNativeHelper.release();
        super.onDestroy();
    }
}
