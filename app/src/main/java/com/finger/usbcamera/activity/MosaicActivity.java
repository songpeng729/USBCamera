package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.R;
import com.finger.usbcamera.listener.MosaicImageListener;
import com.finger.usbcamera.view.MosaicSurfaceView;
import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.USBMonitor;

import java.util.List;

public class MosaicActivity extends Activity implements View.OnClickListener, MosaicImageListener{

    private String TAG = "MosaicActivity";
    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private LinearLayout fingerViewLayout;
    private boolean isGathering = false;
    private Button startGatherBtn, previewBtn, dryWetBtn;//操作按钮

    private USBMonitor mUSBMonitor;
    private USBMonitor.UsbControlBlock usbControlBlock;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不自动息屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_mosaic);
        bindView();
        bindListener();
    }
    private void bindView() {
        fingerViewLayout = findViewById(R.id.mosaic_finger_view_layout);
        startGatherBtn = findViewById(R.id.mosaic_gather_btn);
        previewBtn = findViewById(R.id.mosaic_preview_btn);
        dryWetBtn = findViewById(R.id.dry_wet_btn);

        fingerSurfaceView = new MosaicSurfaceView(this);
        fingerSurfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        fingerViewLayout.addView(fingerSurfaceView);

    }

    public void bindListener(){
        fingerSurfaceView.setMosaicImageListener(this);
        startGatherBtn.setOnClickListener(this);
        previewBtn.setOnClickListener(this);
        dryWetBtn.setOnClickListener(this);

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
                usbControlBlock = null;
                fingerSurfaceView.releaseCamera();
            }

            @Override
            public void onCancel(UsbDevice device) {

            }
        });
        mUSBMonitor.register();//注册监听
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mosaic_gather_btn://开始采集
                if(isGathering){
                    stopGather();
                }else if(!fingerSurfaceView.isPreview()){
                    startGather();
                }
                break;
            case R.id.mosaic_preview_btn:
                if(!fingerSurfaceView.isPreview()){
                    previewBtn.setBackgroundResource(R.drawable.finger_btn_background3);
                    previewBtn.setText(getString(R.string.stop_preview));
                    fingerSurfaceView.startPreview(usbControlBlock);
                }else{
                    previewBtn.setBackgroundResource(R.drawable.finger_btn_background);
                    previewBtn.setText(getString(R.string.start_preview));
                    fingerSurfaceView.stopPreview();
                }
                break;
            case R.id.dry_wet_btn:
                Log.d(TAG, "onClick: dry_wet_btn");
                startActivity(new Intent(this, DryWetSettingActivity.class));
                break;

        }
    }

    /**
     * 开始采集
     */
    private void startGather(){
        startGatherBtn.setBackgroundResource(R.drawable.finger_btn_background3);
        startGatherBtn.setText(getString(R.string.stop_collect));

        fingerSurfaceView.startGather(usbControlBlock);
        isGathering = true;
    }

    /**
     * 停止采集
     */
    private void stopGather(){
        startGatherBtn.setBackgroundResource(R.drawable.finger_btn_background);
        startGatherBtn.setText(getString(R.string.start_collect));
        fingerSurfaceView.stopGather();
        isGathering = false;
    }

    @Override
    public void onMosaicStatusChanged(int status, String message) {
        Log.i(TAG, " status:"+ status + " message:"+ message);
        switch (status){
            case MOSAIC_STATUS_START:
                Toast.makeText(this, "开始采集", Toast.LENGTH_SHORT).show();
                break;
            case MOSAIC_STATUS_SUCCESS:
                //获取指纹数据
                byte[] imageData = fingerSurfaceView.getImgData();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                stopGather();
                break;
            case MOSAIC_STATUS_FAIL:
                Toast.makeText(this, "采集失败"+message, Toast.LENGTH_SHORT).show();
                stopGather();
                break;
            case MOSAIC_STATUS_MESSAGE:
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUSBMonitor.destroy();
    }
}
