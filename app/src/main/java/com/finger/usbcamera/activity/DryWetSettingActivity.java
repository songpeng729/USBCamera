package com.finger.usbcamera.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.finger.usbcamera.USBCameraHelper;
import com.finger.usbcamera.view.MosaicSurfaceView;
import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.R;
import com.serenegiant.widget.UVCCameraTextureView;

/**
 * 指纹干湿指设置
 */
public class DryWetSettingActivity extends Activity implements View.OnClickListener{
    private String TAG = "DryWetSettingActivity";

    private UVCCameraTextureView uvcCameraView;
    private USBCameraHelper usbCameraHelper;
//    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private SeekBar gainSeekBar, expSeekBar;
    private TextView gainTextView, expTextView, param, dryValue, wetValue;
    private Button startButton,saveDryButton,saveWetButton,exitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_wet_setting);
        bindView();
        bindListener();
    }

    private void bindView(){
        FrameLayout fingerViewLayout = findViewById(R.id.drywet_finger_view_layout);
        uvcCameraView = findViewById(R.id.camera_view);
        usbCameraHelper = new USBCameraHelper(this, uvcCameraView, fingerViewLayout);

        startButton = findViewById(R.id.drywet_start_btn);

        gainSeekBar = findViewById(R.id.drywet_gain_sb);
        expSeekBar = findViewById(R.id.drywet_exp_sb);
        saveDryButton = findViewById(R.id.drywet_dry_save_btn);
        saveWetButton = findViewById(R.id.drywet_wet_save_btn);
        exitButton = findViewById(R.id.drywet_exit_btn);
        gainTextView = findViewById(R.id.drywet_gain_tv);
        expTextView = findViewById(R.id.drywet_exp_tv);
        dryValue = findViewById(R.id.drywet_dry_value_tv);
        wetValue = findViewById(R.id.drywet_wet_value_tv);

        gainSeekBar.setProgress(gainSeekBar.getMax() / 2);
        expSeekBar.setProgress(expSeekBar.getMax() / 2);
    }
    private void bindListener(){
        startButton.setOnClickListener(this);
        saveDryButton.setOnClickListener(this);
        saveWetButton.setOnClickListener(this);

        gainSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dryValue.setText(""+progress);
                usbCameraHelper.setGain(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        expSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wetValue.setText(""+progress);
                usbCameraHelper.setExp(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drywet_start_btn:
                Log.d(TAG, "onClick: drywet_start_btn");
                usbCameraHelper.start();
                break;
            case  R.id.drywet_dry_save_btn:
//                int gain = usbCameraHelper.getGain();
//                Log.d(TAG, "getGain:"+ gain);
                break;
            case  R.id.drywet_wet_save_btn:
//                int exp = usbCameraHelper.getExp();
//                Log.d(TAG, "getExp:"+ exp);
                break;
            case  R.id.drywet_exit_btn:
                finish();
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
//        usbCameraHelper.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        usbCameraHelper.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usbCameraHelper.destory();
    }
}
