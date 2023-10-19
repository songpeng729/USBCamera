package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.db.entity.User;
import com.finger.usbcamera.db.greendao.UserDao;
import com.finger.usbcamera.util.SharedPreferencesUtil;

import java.util.List;

import static com.finger.usbcamera.USBCameraAPP.DEFAULT_USER;

public class LoginActivity extends Activity implements View.OnClickListener{
    private UserDao userDao = USBCameraAPP.getInstances().getDaoSession().getUserDao();

    private EditText loginUserEdit, loginPwdEdit;
    private Button loginBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_login);
        String loginUserName = SharedPreferencesUtil.getStringValue(SharedPreferencesUtil.KEY_LOGIN_USER);
        if(!loginUserName.isEmpty()){
            //已登录跳转主页面
            login(loginUserName);
        }
        bindView();
    }
    private void bindView(){
        loginUserEdit = findViewById(R.id.login_user_edit);
        loginPwdEdit = findViewById(R.id.login_pwd_edit);
        loginPwdEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                login();
                break;
        }
    }
    private void login() {
//        String username = loginUserEdit.getText().toString().trim();
//        String password = loginPwdEdit.getText().toString().trim();
//        if ("".equals(username)) {
//            Toast.makeText(this, "请先输入用户名", Toast.LENGTH_SHORT).show();
//            return ;
//        }
//        if ("".equals(password)) {
//            Toast.makeText(this, "请先输入密码", Toast.LENGTH_SHORT).show();
//            return ;
//        }
        //取消登录校验
        login(USBCameraAPP.DEFAULT_USER);
    }

    private void login(String loginName){
        List<User> userList = userDao.queryRaw("where login_name=?", loginName);
        if(userList.size() > 0){
            User loginUser = userList.get(0);
            SharedPreferencesUtil.putStringValue(SharedPreferencesUtil.KEY_LOGIN_USER, loginName);
            USBCameraAPP.getInstances().setLoginUser(loginUser);
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
