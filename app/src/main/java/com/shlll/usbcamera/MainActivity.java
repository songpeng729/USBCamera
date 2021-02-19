package com.shlll.usbcamera;

import android.Manifest;
import android.animation.Animator;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.serenegiant.widget.UVCCameraTextureView;
import com.shlll.libusbcamera.USBCameraHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.function.Function;


public class MainActivity extends AppCompatActivity {
    private UVCCameraTextureView mUVCCameraView;
    private USBCameraHelper mUSBCameraHelper;
    boolean isRightFABOpen = false;

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
                mUSBCameraHelper.saveCapturePicture();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.diag_about_title))
                    .setMessage(getResources().getString(R.string.diag_about_message));

            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (id == R.id.action_usbinfo) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

    @Override
    protected void onDestroy() {
        mUSBCameraHelper.destory();
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
