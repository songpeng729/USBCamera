package com.finger.usbcamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class USBCameraActivity extends AppCompatActivity {
    private static String TAG = "USBCameraActivity";
    private ImageView fingerImageView;//相机
    private USBCameraHelper usbCameraHelper;
    public boolean previewFlag = false;//是否预览相机

    public static int picw = 640, pich = 640;
    //fingerImageView显示的图像数据
    Bitmap bitmap = Bitmap.createBitmap(picw, pich, Bitmap.Config.ARGB_8888);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        usbCameraHelper = new USBCameraHelper(this);
        fingerImageView = findViewById(R.id.cp_imageView);
        //拍照按钮
        FloatingActionButton fab_capture = findViewById(R.id.fab_capture);
        fab_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCap();
            }
        });
    }
    /**
     * 更新指纹图像
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            fingerImageView.setImageBitmap(bitmap);
        }
    };

    //捕获相机图像线程，用于刷新指纹图像
    private Thread capThread =  new Thread(new Runnable() {
        @Override
        public void run() {
            while (previewFlag)
            {
                try {
                    Thread.sleep(0, 1);
                    byte[] imgData = new byte[pich * picw];

                    int ret = usbCameraHelper.cameraSensorGetImg(imgData);
                    //更新图像
                    if(ret == 1) {
                        int[] pixels = BitmapUtil.convToImage(imgData);
                        bitmap.setPixels(pixels, 0, picw, 0, 0, picw, pich);
                        mHandler.sendMessage(mHandler.obtainMessage());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    //捕获相机图像
    private void startCap() {
        previewFlag = true;
        if(!capThread.isAlive()) {
            capThread.start();
        }

    }

    @Override
    protected void onDestroy() {
        usbCameraHelper.destory();
        previewFlag = false;
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        usbCameraHelper.start();
    }

    @Override
    protected void onStop() {
        usbCameraHelper.stop();
        super.onStop();
    }
}
