package com.shlll.usbcamera;

import android.Manifest;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.serenegiant.widget.UVCCameraTextureView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int PREVIEW_PIXEL_BYTES = 4;
    private UVCCameraTextureView mUVCCameraView;
    boolean isRightFABOpen = false;
    private ImageView cPimageView;

    private USBCameraHelper mUSBCameraHelper;
    public boolean previewFlag = true;
    private int[] pixFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] permission = new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.VIBRATE
        };
        PermissionsUtils.getInstance().chekPermissions(this, permission, permissionsResult);

        mUVCCameraView = (UVCCameraTextureView)findViewById(R.id.camera_view);
        FrameLayout frame_layout = findViewById(R.id.camera_view_frame);
        mUSBCameraHelper = new USBCameraHelper(this, mUVCCameraView, frame_layout);
        cPimageView = findViewById(R.id.cp_imageView);
        FloatingActionButton fab_capture = findViewById(R.id.fab_capture);

        mUSBCameraHelper.setOnCameraButtonListener(new USBCameraHelper.OnCameraButtonListener() {
            @Override
            public void onCameraButton() {
                mUSBCameraHelper.saveCapturePicture();
            }
        });

        fab_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mUSBCameraHelper.saveCapturePicture();
                startCap();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    int gainCnt = 0, expCnt = 0;
    //public static int picw = 1024, pich = 656;
    public static int picw = 640, pich = 640;
    Bitmap bmpFilter = Bitmap.createBitmap(picw, pich, Bitmap.Config.ARGB_8888);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //-------------专用接口测试程序------------------
        int id = item.getItemId();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getResources().getString(R.string.diag_about_title))
                .setMessage(getResources().getString(R.string.diag_about_message));

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_gain) {
            gainCnt = gainCnt +10;
            int ret = mUSBCameraHelper.cameraSensorSetgain(gainCnt);
            builder.setMessage("返回值：" + ret);
            AlertDialog dialog = builder.create();
            dialog.show();

        }else if (id == R.id.action_exp) {
            expCnt = expCnt + 10;
            int ret = mUSBCameraHelper.cameraSensorSetExp(expCnt);
            builder.setMessage("返回值：" + ret);
            AlertDialog dialog = builder.create();
            dialog.show();

        }else if (id == R.id.action_image) {
           startCap();

        } else if (id == R.id.action_usbinfo) {
            builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.diag_usbinfo_title));

            String diag_str = "";
            final List<UsbDevice> device_list =  mUSBCameraHelper.getDeviceList();

            for (UsbDevice device: device_list) {
                diag_str += "Class:" + device.getDeviceClass()
                        + ",subClass:" + device.getDeviceSubclass()
                        + ",VID:" + device.getVendorId()
                        + ",PID:" + device.getProductId() + "\n";
            }

            builder.setMessage(diag_str);

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return true;
    }

    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);

            //view.invalidate();//此处更新view内容
            if(pixFilter != null) {
                bmpFilter.setPixels(pixFilter, 0, picw, 0, 0, picw, pich);
                cPimageView.setImageBitmap(bmpFilter);
            }
            //cPimageView.setBackgroundColor(Color.RED);
        }
    };
    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            while (previewFlag)
            {
                try {
                    Thread.sleep(0, 1);
                    byte[] pixsOut = new byte[pich * picw];

                    int ret = mUSBCameraHelper.cameraSensorGetImg(pixsOut);
                    //保存图片
                    pixFilter = BitmapUtil.convToImage(pixsOut);
                    if(ret == 1) {
                        mHandler.sendMessage(mHandler.obtainMessage());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Thread capThread =  new Thread(runable);

    private void startCap() {
        previewFlag = true;
        if(!capThread.isAlive() || previewFlag ==false) {
            capThread.start();
        }
        byte[] pixsOut = new byte[pich * picw];

        int ret = mUSBCameraHelper.cameraSensorGetImg(pixsOut);
        //保存图片
        pixFilter = BitmapUtil.convToImage(pixsOut);
        if(ret == 1) {
            mHandler.sendMessage(mHandler.obtainMessage());
        }
    }

    @Override
    protected void onDestroy() {
        mUSBCameraHelper.destory();
        previewFlag = false;
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUSBCameraHelper.start();
    }

    @Override
    protected void onStop() {
        mUSBCameraHelper.stop();
        super.onStop();
    }

    private PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
        }

        @Override
        public void forbitPermissons() {
        }
    };
}
