package com.finger.usbcamera.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.finger.usbcamera.USBCameraActivity;
import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.util.PermissionsUtils;
import com.finger.usbcamera.R;
import com.finger.usbcamera.listener.MosaicImageListener;
import com.finger.usbcamera.view.MosaicSurfaceView;

import static android.content.ContentValues.TAG;

public class MosaicActivity extends Activity implements View.OnClickListener, MosaicImageListener {

    private String LOG_TAG = "MOSAIC_ACTIVITY";
    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private LinearLayout fingerInfo;
    private boolean isGathering = false;
    private Button startGatherBtn,saveBtn;//操作按钮
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

        fingerSurfaceView = new MosaicSurfaceView(this);
        fingerSurfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        fingerSurfaceView.setMosaicImageListener(this);
        fingerInfo.addView(fingerSurfaceView);

    }

    public void bindListener(){
        startGatherBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
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
                //API 29不支持
//                Intent intent = new Intent(this, USBCameraActivity.class);
//                startActivity(intent);
                break;
        }
    }
    /**
     * 开始采集
     */
    private void startGather(){
        startGatherBtn.setBackgroundResource(R.drawable.finger_btn_background3);
        startGatherBtn.setText(getString(R.string.stop_collect));

        fingerSurfaceView.startGather(false, 1);
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
                Log.i(LOG_TAG, "imageData "+ imageData);
                Toast.makeText(this, "采集完成", Toast.LENGTH_SHORT).show();
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
}
