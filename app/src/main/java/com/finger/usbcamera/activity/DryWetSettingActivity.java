package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.finger.usbcamera.USBCameraHelper;
import com.finger.usbcamera.view.MosaicSurfaceView;
import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.R;
import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.USBMonitor;
import com.serenegiant.widget.UVCCameraTextureView;

import java.util.List;

/**
 * 指纹干湿指设置
 */
public class DryWetSettingActivity extends Activity implements View.OnClickListener{
    private String TAG = "DryWetSettingActivity";

    private Context mContext;
    private USBMonitor mUSBMonitor;
    private USBMonitor.UsbControlBlock usbControlBlock;

    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private SeekBar gainSeekBar, expSeekBar;
    private TextView gainTextView, expTextView, param, dryValue, wetValue;
    private Button startButton,saveDryButton,saveWetButton,exitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不自动息屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_dry_wet_setting);
        bindView();
        bindListener();
    }

    private void bindView(){
        FrameLayout fingerViewLayout = findViewById(R.id.drywet_finger_view_layout);
        fingerSurfaceView = new MosaicSurfaceView(this);
        fingerSurfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        fingerViewLayout.addView(fingerSurfaceView);

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
        exitButton.setOnClickListener(this);

        gainSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dryValue.setText(""+progress);
                fingerSurfaceView.setGain(progress);
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
                fingerSurfaceView.setExp(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mContext = this;
        mUSBMonitor = new USBMonitor(mContext, new USBMonitor.OnDeviceConnectListener() {
            @Override
            public void onAttach(UsbDevice device) {
                Toast.makeText(mContext, "onAttach", Toast.LENGTH_SHORT).show();
                final List<DeviceFilter> filter = DeviceFilter.getDeviceFilters(mContext,
                        com.finger.usbcamera.R.xml.device_filter);
                final List<UsbDevice> deviceList = mUSBMonitor.getDeviceList(filter);

                if (deviceList == null || deviceList.size() == 0) {
                    return;
                }

                if (mUSBMonitor != null) {
                    mUSBMonitor.requestPermission(deviceList.get(0));
                }
            }

            @Override
            public void onDettach(UsbDevice device) {
                Toast.makeText(mContext, "onDettach", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
                Toast.makeText(mContext, "onConnect", Toast.LENGTH_SHORT).show();
                usbControlBlock = ctrlBlock;// 得到UsbControlBlock,用于链接usb设备
                fingerSurfaceView.releaseCamera();
            }

            @Override
            public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
                Toast.makeText(mContext, "onDisconnect", Toast.LENGTH_SHORT).show();
                fingerSurfaceView.releaseCamera();
            }

            @Override
            public void onCancel(UsbDevice device) {

            }
        });
        mUSBMonitor.register();//注册监听
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drywet_start_btn:
                Log.d(TAG, "onClick: drywet_start_btn");
                fingerSurfaceView.startPreview(usbControlBlock);
                break;
            case  R.id.drywet_dry_save_btn:
                int gain = fingerSurfaceView.getGain();
                Log.d(TAG, "getGain:"+ gain);
                break;
            case  R.id.drywet_wet_save_btn:
                int exp = fingerSurfaceView.getExp();
                Log.d(TAG, "getExp:"+ exp);
                break;
            case  R.id.drywet_exit_btn:
                fingerSurfaceView.stopPreview();
                finish();
                break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
