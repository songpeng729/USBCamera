package com.finger.usbcamera;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.widget.Toast;

import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.IButtonCallback;
import com.serenegiant.usb.USBMonitor;
import com.serenegiant.usb.UVCCamera;
import com.serenegiant.utils.HandlerThreadHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class USBCameraHelper {
    private static final String TAG = "USBCameraHelper";
    private final Object mSync = new Object();
    private USBMonitor mUSBMonitor;
    private UVCCamera mUVCCamera;
    private Context mContext;
    private OnCameraButtonListener mButtonListener = null;
    private Vibrator mVibraotor = null;//震动器

    /** Event Handler */
    private Handler mWorkerHandler;
    private long mWorkerThreadID = -1;

    public interface OnCameraButtonListener {
        public void onCameraButton();
    }

    public void setOnCameraButtonListener(OnCameraButtonListener listener) {
        mButtonListener = listener;
    }

    public USBCameraHelper(final Context context) {
        if (mWorkerHandler == null) {
            mWorkerHandler = HandlerThreadHandler.createHandler(TAG);
            mWorkerThreadID = mWorkerHandler.getLooper().getThread().getId();
        }

        mContext = context;
        mUSBMonitor = new USBMonitor(mContext, mOnDeviceConnectListener);
        mVibraotor = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * Get current USB device list.
     * @return List of UsbDeice object.
     */
    public List<UsbDevice> getDeviceList() {
        final List<DeviceFilter> filter = DeviceFilter.getDeviceFilters(mContext, com.finger.usbcamera.R.xml.device_filter);
        final List<UsbDevice> deviceList = mUSBMonitor.getDeviceList(filter);

        return deviceList;
    }

    public synchronized void start() {
        mUSBMonitor.register();
        synchronized (mSync) {
            if (mUVCCamera != null) {
                mUVCCamera.startPreview();
            }
        }
    }

    public synchronized void stop() {
        synchronized (mSync) {
            if (mUVCCamera != null) {
                mUVCCamera.stopPreview();
            }
            if (mUSBMonitor != null) {
                mUSBMonitor.unregister();
            }
        }
    }

    public synchronized void destory() {
        synchronized (mSync) {
            releaseCamera();

            if (mUSBMonitor != null) {
                mUSBMonitor.destroy();
                mUSBMonitor = null;
            }
        }
    }

    /**
     * Save current picture to system and add picture to gallery.
     */
    public void saveBitmap2Picture(Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        OutputStream fos = null;
        Uri imageUri = null;
        final ContentResolver resolver = mContext.getContentResolver();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                String storagePath = Environment.DIRECTORY_PICTURES + File.separator + "USBCamera";
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageFileName);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
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
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
                galleryAddPic(image.toString());
            }

            if (mVibraotor != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    mVibraotor.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                else
                    mVibraotor.vibrate(100);
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

    private boolean checkCameraOpened() {
        if (mUVCCamera == null) {
            showShortMsg(mContext.getResources().getString(R.string.msg_camera_open_fail));
            return false;
        }

        return true;
    }

    private final USBMonitor.OnDeviceConnectListener mOnDeviceConnectListener = new USBMonitor.OnDeviceConnectListener() {
        @Override
        public void onAttach(final UsbDevice device) {
            showShortMsg(mContext.getResources().getString(R.string.msg_usb_device_attached));
            requestPermission();
        }

        @Override
        public void onConnect(final UsbDevice device, final USBMonitor.UsbControlBlock ctrlBlock, final boolean createNew) {
            releaseCamera();
            queueEvent(new Runnable() {
                @Override
                public void run() {
                    final UVCCamera camera = new UVCCamera();
                    camera.open(ctrlBlock);
                    camera.setButtonCallback(new IButtonCallback() {
                        @Override
                        public void onButton(final int button, final int state) {
                            ((Activity)mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (mButtonListener != null)
                                        mButtonListener.onCameraButton();
                                }
                            });
                        }
                    });

                    try {
                        camera.setPreviewSize(UVCCamera.DEFAULT_PREVIEW_WIDTH, UVCCamera.DEFAULT_PREVIEW_HEIGHT, UVCCamera.FRAME_FORMAT_MJPEG);
                    } catch (final IllegalArgumentException e) {
                        // fallback to YUV mode
                        try {
                            camera.setPreviewSize(UVCCamera.DEFAULT_PREVIEW_WIDTH, UVCCamera.DEFAULT_PREVIEW_HEIGHT, UVCCamera.DEFAULT_PREVIEW_MODE);
                        } catch (final IllegalArgumentException e1) {
                            camera.destroy();
                            return;
                        }
                    }
                    synchronized (mSync) {
                        mUVCCamera = camera;
                        mUVCCamera.updateCameraParams();//更新相机参数!!!总要，否则无法获取到相机亮度和对比度
                    }
                }
            }, 0);
        }

        @Override
        public void onDisconnect(final UsbDevice device, final USBMonitor.UsbControlBlock ctrlBlock) {
            releaseCamera();
        }

        @Override
        public void onDettach(final UsbDevice device) {
            showShortMsg(mContext.getResources().getString(R.string.msg_usb_device_detached));
        }

        @Override
        public void onCancel(final UsbDevice device) {
        }
    };

    /**
     * Request USB device permission.
     */
    private void requestPermission() {
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

    /**
     * Release the USB camera device.
     */
    private synchronized void releaseCamera() {
        synchronized (mSync) {
            if (mUVCCamera != null) {
                try {
                    mUVCCamera.setStatusCallback(null);
                    mUVCCamera.setButtonCallback(null);
                    mUVCCamera.close();
                    mUVCCamera.destroy();
                } catch (final Exception e) {
                    //
                }
                mUVCCamera = null;
            }
        }
    }

    private void galleryAddPic(String photoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
    }

    /**
     * Run runnable specified on worker thread
     * the same runnable that is unexecuted is cancelled (executed only later)
     * @param task
     * @param delayMillis
     */
    protected final synchronized void queueEvent(final Runnable task, final long delayMillis) {
        if ((task == null) || (mWorkerHandler == null)) return;
        try {
            mWorkerHandler.removeCallbacks(task);
            if (delayMillis > 0) {
                mWorkerHandler.postDelayed(task, delayMillis);
            } else if (mWorkerThreadID == Thread.currentThread().getId()) {
                task.run();
            } else {
                mWorkerHandler.post(task);
            }
        } catch (final Exception e) {
        }
    }

    private void showShortMsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public int getGain(){
        return mUVCCamera.nativeGetGain();
    }
    public int getExp(){
        return mUVCCamera.nativeGetExp();
    }
    public void setGain(int gain){
        mUVCCamera.nativeSetGain(gain);
    }
    public void setExp(int exp){
        mUVCCamera.nativeSetExp(exp);
    }
}
