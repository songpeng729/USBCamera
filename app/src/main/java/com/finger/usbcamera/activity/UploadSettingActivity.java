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
import com.finger.usbcamera.util.SharedPreferencesUtil;

public class UploadSettingActivity extends Activity{
    private Context mContext;

    private TextView uploadUrl;
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_upload_setting);

        bindView();
    }
    private void bindView(){
        uploadUrl = findViewById(R.id.upload_url_edit);
        uploadUrl.setText(SharedPreferencesUtil.getStringValue(SharedPreferencesUtil.KEY_UPLOAD_URL, "http://0.0.0.0:10000"));

        saveBtn = findViewById(R.id.upload_set_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = uploadUrl.getText().toString();
                SharedPreferencesUtil.putStringValue(SharedPreferencesUtil.KEY_UPLOAD_URL, url);
                Toast.makeText(mContext,"Save Success！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
