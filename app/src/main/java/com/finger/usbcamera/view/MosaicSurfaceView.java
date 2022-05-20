package com.finger.usbcamera.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.finger.usbcamera.USBCameraHelper;
import com.finger.usbcamera.listener.MosaicImageListener;
import com.serenegiant.usb.USBMonitor;
import com.serenegiant.usb.UVCCamera;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import centipede.livescan.MosaicFetcher;
import centipede.livescan.MosaicNative;

import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_END;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_FAIL;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_MESSAGE;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_START;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_SUCCESS;

/**
 * Created by songpeng on 2022/5/6.
 */
public class MosaicSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private final String TAG = "MosaicSurfaceView";
    public static final int MODEL_NORMAL = 0; //正常模式
    private final int GAIN_MAX = 48; //最大亮度
    private final int EXP_MAX = 1050;//最大对比度

    private final int GAIN_DEFAULT = 24; //亮度默认值
    private final int EXP_DEFAULT = 500;//对比度默认值
    private final int WIDTH_DEFAULT = 640; // 固定宽度640
    private final int HEIGHT_DEFAULT = 640; // 固定高度640

    private MosaicFetcher mosaicFetcher;//指纹采集类
    private int gain = GAIN_DEFAULT; //当前亮度
    private int exp = EXP_DEFAULT;//当前对比度
    private int width = WIDTH_DEFAULT; // 宽度
    private int height = HEIGHT_DEFAULT; // 高度
    private byte[] imgDataBuffer;//图像数据
    private byte[] imgDataBufferWhite;//空白图像据

    private byte[] featureData;//特征数据

    private Bitmap bitmap;
    private Bitmap bitmapRgb;
    private Canvas canvas;
    private Paint paint;
    private Matrix matrix;
    private SurfaceHolder surfaceHolder;

    private MosaicImageListener mosaicImageListener;//拼接图像监听

    public MosaicSurfaceView(Context context){
        super(context);
//        usbCameraHelper = new USBCameraHelper(context);
        init();
    }

    /**
     * 初始化数据
     */
    private void init(){
        mosaicFetcher = new MosaicFetcher();

        // 初始化图像数据
        imgDataBuffer = new byte[width * height];
        imgDataBufferWhite = new byte[width * height];
        for (int i = 0; i < imgDataBuffer.length; i++) {
            imgDataBufferWhite[i] = -1;
            imgDataBuffer[i] = -1;
        }

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);

        bitmapRgb = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmapRgb);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

        matrix = new Matrix();
        matrix.setScale(1, 1);
        matrix.postScale(1, 1);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Canvas canvas = holder.lockCanvas(null);
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }

    public void setMosaicImageListener(MosaicImageListener mosaicImageListener) {
        this.mosaicImageListener = mosaicImageListener;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopGather();
        stopPreview();
    }

    //线程池
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 开始采集
     */
    public void startGather(USBMonitor.UsbControlBlock ctrlBlock){
        if(isPreview){
            return;
        }
        Log.i(TAG, "startGather");
        if(ctrlBlock == null){
            onMosaicStatusChanged(MOSAIC_STATUS_MESSAGE, "usb未连接");
            return;
        }
        //先空画布
        clearImage();
        //采集指纹比较耗时，放到子线程里处理
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                if (!mosaicFetcher.isRunning()) {
                    Log.i(TAG, "mosaicFetcher.start");
                    mosaicFetcher.start(callback, ctrlBlock);
                }
            }
            MosaicFetcher.MosaicCallback callback = new MosaicFetcher.MosaicCallback() {
                @Override
                public void initMosaic() {
                    onMosaicStatusChanged(MOSAIC_STATUS_START, "initMosaic");
                    //初始化完成，先预览图像
                    MosaicNative.ReadImg(imgDataBuffer);
                    drawImage(imgDataBuffer);
                }

                @Override
                public void afterMosaic(final int ret, byte[] bytes) {
                    Log.i(TAG, "afterMosaic ret "+ ret);
                    if(ret == 0){
                        imgDataBuffer = bytes;
                        //校验图像
//                        if(ImageConverter.checkImageQuality(false,bytes)){
                            //提取特征
//                            featureData = FeatureExtractor.extractFeature( 1, false, bytes);
                            onMosaicStatusChanged(MOSAIC_STATUS_SUCCESS, "采集完成");
//                        }else{
//                            onMosaicStatusChanged(MOSAIC_STATUS_FAIL, "质量不合格");
//                        }
                        stopGather();
                    }else if (ret < 0){
                        onMosaicStatusChanged(MOSAIC_STATUS_FAIL, "", ret);
                        stopGather();
                    }
                    drawImage(bytes);
                }
            };
        });

    }
    public void stopGather(){
        Log.i(TAG, "stopGather");
        if(mosaicFetcher != null){
            if(mosaicFetcher.isRunning()){
                mosaicFetcher.stop();
            }
        }
    }

    /**
     * 清除画布图像
     */
    public void clearImage() {
        System.arraycopy(imgDataBufferWhite, 0, imgDataBuffer, 0, imgDataBuffer.length);
        drawImage(imgDataBuffer);
    }

    /**
     * 画图
     * @param buffer 图像数据
     */
    private void drawImage(byte[] buffer) {
        //白色背景画布
        Canvas canvas_bg = surfaceHolder.lockCanvas(null);
        if (canvas == null)
            Log.e(TAG, "drawImage canvas is null");
        else {
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            byteBuffer.rewind();
            bitmap.copyPixelsFromBuffer(byteBuffer);
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(bitmap, 0, 0, paint);
            canvas_bg.drawColor(Color.WHITE);
            canvas_bg.drawBitmap(bitmapRgb, matrix, paint);

            surfaceHolder.unlockCanvasAndPost(canvas_bg);
        }
    }

    /**
     * 当拼接图像状态更改
     * @param status
     * @param message
     */
    private void onMosaicStatusChanged(int status, String message) {
        onMosaicStatusChanged(status, message, 0);
    }
    private void onMosaicStatusChanged(int status, String message, int code) {
        Log.i(TAG, "status:"+ status + " message:"+ message + " code:"+ code);
        Message msg = mosaicSurfaceViewHandler.obtainMessage(status);
        msg.what= status;
        msg.arg1 = code;
        msg.obj = message;
        mosaicSurfaceViewHandler.sendMessage(msg);
    }

    @SuppressLint("HandlerLeak")
    Handler mosaicSurfaceViewHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(mosaicImageListener == null){
                Log.w(TAG, "mosaicImageListener is null");
                return;
            }
            switch (msg.what){
                case MOSAIC_STATUS_START:
                    Log.d(TAG, "MOSAIC_STATUS_START");
                    mosaicImageListener.onMosaicStatusChanged(msg.what, msg.obj.toString());
                    break;
                case MOSAIC_STATUS_FAIL:
                    Log.i(TAG, "MOSAIC_STATUS_FAIL:"+ msg.arg1);
                    String message = msg.obj.toString();
                    switch (msg.arg1){
                        case -101:
                            message = "手指回滚了";
                            break;
                        case -102:
                            message = "模糊";
                            break;
                        case -103:
                            message = "尺寸太小";
                            break;
                        case -104:
                            message = "手指太干";
                            break;
                        case -105:
                            message = "手指太湿";
                            break;
                        case -106:
                            message = "手指模糊";
                            break;
                        case -107:
                            message = "core偏左";
                            break;
                        case -108:
                            message = "core偏右";
                            break;
                        case -109:
                            message = "core太靠顶部";
                            break;
                        case -110:
                            message = "core太靠下部";
                            break;
                        case -111:
                            message = "没有core";
                            break;
                        case -112:
                            message = "重心偏左";
                            break;
                        case -113:
                            message = "重心偏右";
                            break;
                        case -114:
                            message = "重心偏上";
                            break;
                        case -115:
                            message = "重心偏下";
                            break;
                        case -116:
                            message = "不支持平面采集";
                            break;
                    }
                    mosaicImageListener.onMosaicStatusChanged(msg.what, message);
                    break;
                case MOSAIC_STATUS_SUCCESS:
                case MOSAIC_STATUS_END:
                    stopGather();
                    mosaicImageListener.onMosaicStatusChanged(msg.what, msg.obj.toString());
                    break;
            }
        }
    };

    public int getGain(){
        return MosaicNative.GetGain();
    }
    public int getExp(){
        return MosaicNative.GetExposure();
    }

    public void setGain(int gain) {
        this.gain = Math.min(GAIN_MAX, Math.max(1, gain));
        MosaicNative.SetGain(this.gain);
    }

    public void setExp(int exp) {
        this.exp = Math.min(EXP_MAX, Math.max(1, exp));
        MosaicNative.SetExposure(this.exp);
    }

    /**
     * 是否正在采集
     * @return
     */
    public boolean isGathering(){
        return mosaicFetcher.isRunning();
    }
    /**
     * 获取指纹图像数据，当采集完成后调用
     * @return
     */
    public byte[] getImgData() {
        //由于imgDataBuffer有可能会被清空，新建对象
        byte[] img = new byte[imgDataBuffer.length];
        System.arraycopy(imgDataBuffer, 0, img, 0, imgDataBuffer.length);
        return img;
    }

    /**
     * 获取特征数据，当采集完成后调用
     * @return
     */
    public byte[] getFeatureData() {
        return featureData;
    }


    /************以下是预览相机功能************/

    private boolean isPreview = false;//是否预览相机
    public boolean isPreview(){
        return isPreview;
    }
    public void startPreview(USBMonitor.UsbControlBlock ctrlBlock){
        MosaicNative.ReadInit(ctrlBlock.getVenderId(), ctrlBlock.getProductId(),
                ctrlBlock.getFileDescriptor(),
                ctrlBlock.getBusNum(),
                ctrlBlock.getDevNum(),
                UVCCamera.getUSBFSName(ctrlBlock));
        startPreview();
    }
    /**
     * 开始预览
     */
    public void startPreview(){
        if(isGathering()){
            Log.w(TAG, "startPreview 正在采集");
            return;
        }
        isPreview = true;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (isPreview) {
                    MosaicNative.ReadImg(imgDataBuffer);
                    drawImage(imgDataBuffer);
                }
                MosaicNative.ReadEnd();
            }
        });
    }
    public void stopPreview(){
        isPreview = false;
    }
}
