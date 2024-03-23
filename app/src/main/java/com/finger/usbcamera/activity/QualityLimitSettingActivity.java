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
import com.finger.usbcamera.util.SharedPreferencesUtil;

public class QualityLimitSettingActivity extends Activity{
    private Context mContext;

    private TextView qualityLimit;
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_quality_limit_setting);

        bindView();
    }
    private void bindView(){
        qualityLimit = findViewById(R.id.quality_limit_edit);
        qualityLimit.setText(String.format("%d", SharedPreferencesUtil.getIntValue(SharedPreferencesUtil.KEY_QUALITY_LIMIT, USBCameraAPP.DEFAULT_QUALITY_LIMIT)));

        saveBtn = findViewById(R.id.quality_limit_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limit = Integer.parseInt(qualityLimit.getText().toString());
                if(limit > 0 && limit < 100){
                    SharedPreferencesUtil.putIntValue(SharedPreferencesUtil.KEY_QUALITY_LIMIT, limit);
                    Toast.makeText(mContext,"Save Success！", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(mContext,"Limit [1-99]", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
