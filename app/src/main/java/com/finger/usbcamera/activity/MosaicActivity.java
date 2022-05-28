package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.finger.usbcamera.USBCameraHelper;
import com.finger.usbcamera.util.BitmapUtil;
import com.serenegiant.annotation.Nullable;
import com.finger.usbcamera.R;
import com.finger.usbcamera.listener.MosaicImageListener;
import com.finger.usbcamera.view.MosaicSurfaceView;
import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.USBMonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MosaicActivity extends Activity implements View.OnClickListener, MosaicImageListener{

    private String TAG = "MosaicActivity";
    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private LinearLayout fingerViewLayout;
    private Button startGatherBtn, previewBtn,savePreviewBtn, dryWetBtn;//操作按钮

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
        savePreviewBtn = findViewById(R.id.mosaic_save_preview_btn);
        dryWetBtn = findViewById(R.id.dry_wet_btn);

        fingerSurfaceView = new MosaicSurfaceView(this);
        fingerSurfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        fingerViewLayout.addView(fingerSurfaceView);

    }

    public void bindListener(){
        fingerSurfaceView.setMosaicImageListener(this);
        startGatherBtn.setOnClickListener(this);
        previewBtn.setOnClickListener(this);
        savePreviewBtn.setOnClickListener(this);
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
                stopGather();
                stopPreview();
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
                if(fingerSurfaceView.isGathering()){
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
                    stopPreview();
                }
                break;
            case R.id.mosaic_save_preview_btn:
                if(fingerSurfaceView.isPreview()){
                    byte[] imageData = fingerSurfaceView.getImgData();
                    saveImageData2Picture(imageData);
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
    }

    /**
     * 停止采集
     */
    private void stopGather(){
        startGatherBtn.setBackgroundResource(R.drawable.finger_btn_background);
        startGatherBtn.setText(getString(R.string.start_collect));
        fingerSurfaceView.stopGather();
    }
    private void stopPreview(){
        previewBtn.setBackgroundResource(R.drawable.finger_btn_background);
        previewBtn.setText(getString(R.string.start_preview));
        fingerSurfaceView.stopPreview();
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
//                byte[] imageData = fingerSurfaceView.getImgData();
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

    @Override
    protected void onStop() {
        super.onStop();
        stopGather();
        stopPreview();
        fingerSurfaceView.releaseCamera();
    }

    public void saveImageData2Picture(byte[] imageData) {
        int[] pix = BitmapUtil.convert2Pixels(imageData);
        Bitmap bitmap = Bitmap.createBitmap(640, 640, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pix, 0, 640, 0, 0, 640, 640);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String imageFileName = timeStamp + ".png";
        OutputStream fos = null;
        Uri imageUri = null;
        final ContentResolver resolver = mContext.getContentResolver();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                String storagePath = Environment.DIRECTORY_PICTURES + File.separator + "USBCamera";
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageFileName);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, storagePath);

                imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = resolver.openOutputStream(imageUri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            } else {
                final String storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "USBCamera";
                File storageDir = new File(storagePath);
                if (!storageDir.exists()) {
                    storageDir.mkdir();
                }

                File image = new File(storagePath, imageFileName);
                fos = new FileOutputStream(image);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
                galleryAddPic(image.toString());
            }

            showShortMsg(mContext.getResources().getString(R.string.msg_capturesaved));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (imageUri != null)
            {
                // Don't leave an orphan entry in the MediaStore
                resolver.delete(imageUri, null, null);
            }
            e.printStackTrace();
        }
    }
    private void galleryAddPic(String photoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
    }
    private void showShortMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
