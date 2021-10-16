package com.finger.usbcamera.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.R;

/**
 * 指纹干湿指设置
 */
public class DryWetSettingActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_wet_setting);
    }

    @Override
    public void onClick(View v) {

    }
}
