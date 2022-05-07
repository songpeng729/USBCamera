package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.finger.usbcamera.USBCameraActivity;
import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.R;
import com.finger.usbcamera.listener.MosaicImageListener;
import com.finger.usbcamera.view.MosaicSurfaceView;
import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.USBMonitor;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MosaicActivity extends Activity implements View.OnClickListener, MosaicImageListener{

    private String LOG_TAG = "MosaicActivity";
    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private LinearLayout fingerInfo;
    private boolean isGathering = false;
    private Button startGatherBtn,saveBtn, cameraBtn;//操作按钮

    private USBMonitor mUSBMonitor;
    private USBMonitor.UsbControlBlock usbControlBlock;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosaic);
        bindView();
        bindListener();
    }
    private void bindView() {
        fingerInfo = findViewById(R.id.finger_view_layout);
        startGatherBtn = findViewById(R.id.mosaic_gather_btn);
        saveBtn = findViewById(R.id.mosaic_save_btn);
        cameraBtn = findViewById(R.id.mosaic_camera_btn);

        fingerSurfaceView = new MosaicSurfaceView(this);
        fingerSurfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        fingerSurfaceView.setMosaicImageListener(this);
        fingerInfo.addView(fingerSurfaceView);

    }

    public void bindListener(){
        startGatherBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        cameraBtn.setOnClickListener(this);

        mContext = this;
        mUSBMonitor = new USBMonitor(mContext, new USBMonitor.OnDeviceConnectListener() {
            @Override
            public void onAttach(UsbDevice device) {
                Toast.makeText(mContext, "onAttach", Toast.LENGTH_SHORT);
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
                Toast.makeText(mContext, "onDettach", Toast.LENGTH_SHORT);
            }

            @Override
            public void onConnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
                Log.d(TAG, "ReadInit onConnect: "+ctrlBlock.getVenderId());
                Toast.makeText(mContext, "onConnect", Toast.LENGTH_SHORT);
                usbControlBlock = ctrlBlock;// 得到UsbControlBlock,用于链接usb设备
//                MosaicNative.ReadInit(ctrlBlock.getVenderId(), ctrlBlock.getProductId(),
//                        ctrlBlock.getFileDescriptor(),
//                        ctrlBlock.getBusNum(),
//                        ctrlBlock.getDevNum(),
//                        getUSBFSName(ctrlBlock));
            }
            private final String getUSBFSName(final USBMonitor.UsbControlBlock ctrlBlock) {
                String result = null;
                final String name = ctrlBlock.getDeviceName();
                final String[] v = !TextUtils.isEmpty(name) ? name.split("/") : null;
                if ((v != null) && (v.length > 2)) {
                    final StringBuilder sb = new StringBuilder(v[0]);
                    for (int i = 1; i < v.length - 2; i++)
                        sb.append("/").append(v[i]);
                    result = sb.toString();
                }
                if (TextUtils.isEmpty(result)) {
                    Log.w(TAG, "failed to get USBFS path, try to use default path:" + name);
                    result = "/dev/bus/usb";
                }
                return result;
            }


            @Override
            public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
                Toast.makeText(mContext, "onDisconnect", Toast.LENGTH_SHORT);
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
                }else{
                    startGather();
                }
                break;
            case R.id.mosaic_save_btn:
                fingerSurfaceView.initMosaic(usbControlBlock);
                break;
            case R.id.mosaic_camera_btn:
                Intent intent = new Intent(this, USBCameraActivity.class);
                startActivity(intent);
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
        Log.i(LOG_TAG, " status:"+ status + " message:"+ message);
        switch (status){
            case MOSAIC_STATUS_START:
                Toast.makeText(this, "开始采集", Toast.LENGTH_SHORT).show();
                break;
            case MOSAIC_STATUS_SUCCESS:
                //TODO 使用ThreadPool
                //获取指纹数据
                byte[] imageData = fingerSurfaceView.getImgData();
                Toast.makeText(this, "采集完成", Toast.LENGTH_SHORT).show();
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
