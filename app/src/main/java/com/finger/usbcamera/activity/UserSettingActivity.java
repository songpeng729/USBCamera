package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.db.entity.User;
import com.finger.usbcamera.db.greendao.UserDao;

public class UserSettingActivity extends Activity implements View.OnClickListener{
    private String TAG = "UserSettingActivity";
    private Context mContext;

    private User loginUser = USBCameraAPP.getInstances().getLoginUser();
    private TextView loginName, name, idcardno, phone, unitCode, unitName;
    private Button saveBtn;

    private UserDao userDao = USBCameraAPP.getInstances().getDaoSession().getUserDao();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_user_setting);

        bindView();
    }
    private void bindView(){
        loginName = findViewById(R.id.user_login_name);
        name = findViewById(R.id.user_name_edit);
        idcardno = findViewById(R.id.user_idcardno_edit);
        phone = findViewById(R.id.user_phone_edit);
        unitCode = findViewById(R.id.user_unit_code_edit);
        unitName = findViewById(R.id.user_unit_name_edit);
        saveBtn = findViewById(R.id.user_save_btn);
        saveBtn.setOnClickListener(this);

        loginName.setText(loginUser.getLoginName());
        name.setText(loginUser.getName());
        idcardno.setText(loginUser.getIdCardNo());
        phone.setText(loginUser.getPhone());
        unitCode.setText(loginUser.getUnitCode());
        unitName.setText(loginUser.getUnitName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_save_btn:
                loginUser.setName(name.getText().toString());
                loginUser.setIdCardNo(idcardno.getText().toString());
                loginUser.setPhone(phone.getText().toString());
                loginUser.setUnitCode(unitCode.getText().toString());
                loginUser.setUnitName(unitName.getText().toString());
                userDao.save(loginUser);
                Toast.makeText(mContext,"保存成功！", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
