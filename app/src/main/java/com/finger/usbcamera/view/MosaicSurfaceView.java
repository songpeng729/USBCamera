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

import com.finger.usbcamera.listener.MosaicImageListener;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import centipede.livescan.MosaicFetcher;

import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_END;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_FAIL;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_START;
import static com.finger.usbcamera.listener.MosaicImageListener.MOSAIC_STATUS_SUCCESS;

/**
 * Created by songpeng on 2018/3/11.
 */

public class MosaicSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private final String LOG_TAG = "MOSAIC_SURFACE_VIEW";
    public static final int MODEL_NORMAL = 0; //正常模式

    private final int GAIN_DEFAULT = 24; //亮度默认值
    private final int EXP_DEFAULT = 500;//对比度默认值


    private MosaicFetcher mosaicFetcher;//指纹采集类
    private int gain = GAIN_DEFAULT; //当前亮度
    private int exp = EXP_DEFAULT;//当前对比度
    private int width;
    private int height;
    private byte[] imgDataBuffer;//图像数据
    private byte[] imgDataBufferWhite;//空白图像据

    private Bitmap bitmap;
    private Bitmap bitmapRgb;
    private Canvas canvas;
    private Paint paint;
    private Matrix matrix;
    private SurfaceHolder surfaceHolder;

    private MosaicImageListener mosaicImageListener;//拼接图像监听


    public MosaicSurfaceView(Context context){
        super(context);
        init();
    }
    private void init(){
        mosaicFetcher = new MosaicFetcher();
        width = 640;
        height = 640;
        Log.i(LOG_TAG, "init width:"+ width +" height:"+ height);

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
        Log.i(LOG_TAG, "surfaceChanged");
        Canvas canvas = holder.lockCanvas(null);
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }

    public void setMosaicImageListener(MosaicImageListener mosaicImageListener) {
        this.mosaicImageListener = mosaicImageListener;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private ExecutorService executorService = Executors.newSingleThreadExecutor();//线程池，只有一个子线程
    /**
     * 开始采集
     * @param isFlat 是否平面
     */
    public void startGather(boolean isFlat){
        startGather(isFlat, 1);
    }

    /**
     * 开始采集
     * @param isFlat 是否平面
     */
    public void startGather(final boolean isFlat, final int fgp){
        Log.i(LOG_TAG, "startGather");
        //先空画布
        clearImage();
        //采集指纹比较耗时，放到子线程里处理
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                if (!mosaicFetcher.isRunning()) {
                    Log.i(LOG_TAG, "mosaicFetcher.start");
                    mosaicFetcher.start(callback);
                }
            }
            MosaicFetcher.MosaicCallback callback = new MosaicFetcher.MosaicCallback() {
                @Override
                public void initMosaic() {
                    onMosaicStatusChanged(MOSAIC_STATUS_START, "initMosaic");
                }

                @Override
                public void afterMosaic(final int ret, byte[] bytes) {
                    Log.i(LOG_TAG, "afterMosaic ret "+ ret);
                    if(ret == 0){
                        imgDataBuffer = bytes;
//                        //校验图像
//                        if(ImageConverter.checkImageQuality(isFlat,bytes)){
//                            //提取特征
//                            featureData = FeatureExtractor.extractFeature( fgp, isFlat, bytes);
//                            onMosaicStatusChanged(MOSAIC_STATUS_SUCCESS, "采集完成");
//                        }else{
//                            onMosaicStatusChanged(MOSAIC_STATUS_FAIL, "质量不合格");
//                        }
                        onMosaicStatusChanged(MOSAIC_STATUS_SUCCESS, "采集完成");
                    }else if (ret < 0){
                        onMosaicStatusChanged(MOSAIC_STATUS_FAIL, "采集异常("+ ret +")");
                    }
                    drawImage(bytes);
                }
            };
        });

    }
    public void stopGather(){
        Log.i(LOG_TAG, "stopGather");
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
        Log.i(LOG_TAG, "drawImage buffer length:"+ buffer.length);
        //白色背景画布
        Canvas canvas_bg = surfaceHolder.lockCanvas(null);
        if (canvas == null)
            Log.e(LOG_TAG, "drawImage canvas is null");
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
        Log.i(LOG_TAG, "status:"+ status + " message:"+ message);
        Message msg = fingerSurfaceViewHandler.obtainMessage(status);
        msg.arg1 = status;
        msg.obj = message;
        fingerSurfaceViewHandler.sendMessage(msg);
    }

    @SuppressLint("HandlerLeak")
    Handler fingerSurfaceViewHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(mosaicImageListener == null){
                Log.w(LOG_TAG, "mosaicImageListener is null");
                return;
            }
            switch (msg.what){
                case MOSAIC_STATUS_START:
                    Log.d(LOG_TAG, "MOSAIC_STATUS_START");
                    mosaicImageListener.onMosaicStatusChanged(msg.what, msg.obj.toString());
                    break;
                case MOSAIC_STATUS_FAIL:
                    Log.i(LOG_TAG, "MOSAIC_STATUS_FAIL:"+ msg.arg1);
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
        return gain;
    }
    public int getExp(){
        return exp;
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

}
