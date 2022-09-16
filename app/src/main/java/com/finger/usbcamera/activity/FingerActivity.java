package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.finger.usbcamera.R;
import com.finger.usbcamera.listener.MosaicImageListener;
import com.finger.usbcamera.view.MosaicSurfaceView;
import com.finger.usbcamera.vo.FingerData;
import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.USBMonitor;

import java.util.List;

import static com.finger.usbcamera.vo.FingerData.FINGER_STATUS_NORMAL;

/**
 * 采集指纹
 */
public class FingerActivity extends Activity implements View.OnClickListener, MosaicImageListener {
    private final String TAG = "FingerActivity";

    private MosaicSurfaceView fingerSurfaceView;//指纹显示
    private LinearLayout fingerViewLayout;

    private boolean isGathering = false;
    private int currentFingerIndex = 0;//当前选中的指位[0-9]
    private boolean isFlat = false;//当前指纹类型是否是平面
    private String fingerTypeName;//当前指纹类型名称
    private RadioButton rollBtn, flatBtn;//平指滚指切换按钮
    private GridLayout fingerGridLayout;
    //指纹按钮
    private Button fingerRThumbBtn, fingerRIndexBtn, fingerRMiddleBtn, fingerRRingBtn, fingerRLittleBtn, fingerLThumbBtn, fingerLIndexBtn, fingerLMiddleBtn, fingerLRingBtn, fingerLLittleBtn;
    private Button[] fingerButtonList;
    private String[] fingerButtonNameList;
    private FingerData[] fingerDataList = new FingerData[20];//指纹数据

    private TextView gatherStatusTextView;//提示信息

    private Button startGatherBtn, saveBtn;//操作按钮

    private USBMonitor mUSBMonitor;
    private USBMonitor.UsbControlBlock usbControlBlock;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不自动息屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_gather_finger);

        bindView();
        bindListener();
        init();
    }
    private void bindView(){
        fingerViewLayout = findViewById(R.id.finger_view);
        fingerSurfaceView = new MosaicSurfaceView(this);
        fingerSurfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1));
        fingerViewLayout.addView(fingerSurfaceView);

        fingerTypeName = getString(R.string.finger_roll);

        rollBtn = findViewById(R.id.finger_roll_btn);
        flatBtn = findViewById(R.id.finger_flat_btn);

        fingerRThumbBtn = findViewById(R.id.finger_r_thumb_btn);
        fingerRIndexBtn = findViewById(R.id.finger_r_index_btn);
        fingerRMiddleBtn = findViewById(R.id.finger_r_middle_btn);
        fingerRRingBtn = findViewById(R.id.finger_r_ring_bnt);
        fingerRLittleBtn = findViewById(R.id.finger_r_little_btn);
        fingerLThumbBtn = findViewById(R.id.finger_l_thumb_btn);
        fingerLIndexBtn = findViewById(R.id.finger_l_index_btn);
        fingerLMiddleBtn = findViewById(R.id.finger_l_middle_btn);
        fingerLRingBtn = findViewById(R.id.finger_l_ring_bnt);
        fingerLLittleBtn = findViewById(R.id.finger_l_little_btn);
        fingerButtonList = new Button[]{fingerRThumbBtn, fingerRIndexBtn, fingerRMiddleBtn, fingerRRingBtn, fingerRLittleBtn, fingerLThumbBtn, fingerLIndexBtn, fingerLMiddleBtn, fingerLRingBtn, fingerLLittleBtn};
        fingerButtonNameList = new String[]{getString(R.string.finger_r_thumb), getString(R.string.finger_r_index), getString(R.string.finger_r_middle), getString(R.string.finger_r_ring), getString(R.string.finger_r_little),
                getString(R.string.finger_l_thumb), getString(R.string.finger_l_index), getString(R.string.finger_l_middle), getString(R.string.finger_l_ring), getString(R.string.finger_l_little)};

        gatherStatusTextView = findViewById(R.id.gather_status_tv);

        startGatherBtn = findViewById(R.id.finger_start_gather_btn);
        saveBtn = findViewById(R.id.finger_save_btn);

    }
    public void bindListener(){
        startGatherBtn.setOnClickListener(this);
//        dryWetSettingBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
//        gatherPhotoBtn.setOnClickListener(this);

        rollBtn.setOnClickListener(this);
        flatBtn.setOnClickListener(this);

        fingerRThumbBtn.setOnClickListener(this);
        fingerRIndexBtn.setOnClickListener(this);
        fingerRMiddleBtn.setOnClickListener(this);
        fingerRRingBtn.setOnClickListener(this);
        fingerRLittleBtn.setOnClickListener(this);
        fingerLThumbBtn.setOnClickListener(this);
        fingerLIndexBtn.setOnClickListener(this);
        fingerLMiddleBtn.setOnClickListener(this);
        fingerLRingBtn.setOnClickListener(this);
        fingerLLittleBtn.setOnClickListener(this);

        mContext = this;
        mUSBMonitor = new USBMonitor(mContext, new USBMonitor.OnDeviceConnectListener() {
            @Override
            public void onAttach(UsbDevice device) {
                final List<DeviceFilter> filter = DeviceFilter.getDeviceFilters(mContext,
                        com.finger.usbcamera.R.xml.device_filter);
                final List<UsbDevice> deviceList = mUSBMonitor.getDeviceList(filter);

                if (deviceList.size() == 0) {
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
                Toast.makeText(mContext, "onConnect vid:"+ ctrlBlock.getVenderId() + " pid:"+ ctrlBlock.getProductId(), Toast.LENGTH_SHORT).show();
                usbControlBlock = ctrlBlock;// 得到UsbControlBlock,用于链接usb设备
                fingerSurfaceView.releaseCamera();//释放usb设备
            }

            @Override
            public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
                Toast.makeText(mContext, "onDisconnect", Toast.LENGTH_SHORT).show();
                stopGather();
                fingerSurfaceView.releaseCamera();
                usbControlBlock = null;
            }

            @Override
            public void onCancel(UsbDevice device) {
            }
        });
        mUSBMonitor.register();//注册监听
    }
    private void init(){
        //初始化指纹信息
        for (int i=0; i < 20; i++){
            fingerDataList[i] = new FingerData(i + 1);
        }
//        Intent intent = getIntent();
//        if(intent != null){
//            String name = intent.getStringExtra("name");
//            String idcardno = intent.getStringExtra("idcardno");
//            String gender = intent.getStringExtra("gender");
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finger_start_gather_btn:
                if(isGathering){
                    stopGather();
                }else{
                    startGather();
                }
                break;
            case R.id.finger_save_btn:
                break;
            case R.id.finger_r_thumb_btn:
            case R.id.finger_r_index_btn:
            case R.id.finger_r_middle_btn:
            case R.id.finger_r_ring_bnt:
            case R.id.finger_r_little_btn:
            case R.id.finger_l_thumb_btn:
            case R.id.finger_l_index_btn:
            case R.id.finger_l_middle_btn:
            case R.id.finger_l_ring_bnt:
            case R.id.finger_l_little_btn:
                checkFingerIndexButton(v);
                break;
            case R.id.finger_roll_btn:
                checkFingerType(false);
                break;
            case R.id.finger_flat_btn:
                checkFingerType(true);
                break;
        }
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
                FingerData fingerData = getCurrentFingerData();
                fingerData.setImage(imageData);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                stopGather();
//                checkNextFinger();
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

    /**
     * 切换平指滚指
     * @param isFlat
     */
    private void checkFingerType(boolean isFlat){
        if(this.isFlat == isFlat || isGathering)
            return;

        Log.i(TAG, "changeFingerType isPlain:"+ isFlat);
        this.isFlat = isFlat;

        if(isFlat){
            fingerTypeName = getString(R.string.finger_flat);
        }else{
            fingerTypeName = getString(R.string.finger_roll);
        }

        for (int i = 0; i < 10; i++){
            FingerData fingerData = isFlat ? fingerDataList[i+10] : fingerDataList[i];
            if(fingerData.getImage() != null){
                //如果采集了指纹，背景绿色;
                fingerButtonList[i].setBackgroundColor(Color.GREEN);
            }else if(fingerData.getStatus() != FINGER_STATUS_NORMAL){
                // 如果状态缺指，背景红色
                fingerButtonList[i].setBackgroundColor(Color.RED);
            }else {
                // 其他情况背景白色
                fingerButtonList[i].setBackgroundColor(Color.WHITE);
            }
        }

        fingerSurfaceView.showFingerData(getCurrentFingerData());
    }
    /**
     * 切换指位
     * @param fingerIndex 指纹索引[0-9]
     */
    private void checkFingerIndex(int fingerIndex){
        if(this.currentFingerIndex == fingerIndex){
            return;
        }
        Log.i(TAG, "checkFingerIndex:"+fingerIndex);
        this.currentFingerIndex = fingerIndex;
        for (int i = 0; i < fingerButtonList.length; i++){
            Button btn = fingerButtonList[i];
            if(i != fingerIndex){
                if(btn.isSelected())
                    btn.setSelected(false);
            }else{
                btn.setSelected(true);
            }
        }
        showGatherStatus(fingerTypeName + "-->"+ fingerButtonNameList[currentFingerIndex], false);

        fingerSurfaceView.showFingerData(getCurrentFingerData());
//        //如果已经采集了指纹信息，显示指纹图像
//        if(isGathering){
//            //采集状态，开始采集指纹
//            startGather();
//        }else{
//            FingerData fingerData = getFingerData(fingerIndex);
//            //设置状态
//            fingerStatusSp.setSelection(fingerData.getStatus());
//            fingerSurfaceView.switchFinger(fingerData);
//        }
    }

    /**
     * 选中下一个指纹
     */
    private void checkNextFinger(){
        if(this.currentFingerIndex < 9){
            checkFingerIndex(this.currentFingerIndex + 1);
        }
    }

    /**
     * 选中指位Button
     * @param v
     */
    private void checkFingerIndexButton(View v){
        if(isGathering) //采集状态，不可切换
            return;

        for (int i = 0; i < fingerButtonList.length; i++){
            if(fingerButtonList[i].getId() == v.getId()){
                checkFingerIndex(i);
            }
        }
    }

    /**
     * 停止采集,手动终止或采集完成后调用
     */
    private void stopGather(){
        saveBtn.setEnabled(true);
        saveBtn.setAlpha(1f);

        startGatherBtn.setBackgroundResource(R.drawable.finger_btn_background);
        startGatherBtn.setText(getString(R.string.start_collect));

        fingerSurfaceView.stopGather();
        isGathering = false;
        rollBtn.setEnabled(true);
        flatBtn.setEnabled(true);
    }

    /**
     * 开始采集
     */
    private void startGather(){
        isGathering = true;
        rollBtn.setEnabled(false);
        flatBtn.setEnabled(false);

        //禁用保存
        saveBtn.setEnabled(false);
        saveBtn.setAlpha(0.3f);

        startGatherBtn.setBackgroundResource(R.drawable.finger_btn_background3);
        startGatherBtn.setText(getString(R.string.stop_collect));

        fingerSurfaceView.startGather(usbControlBlock);
    }

    /**
     * 指纹状态信息显示
     * @param message
     * @param isError 异常信息，显示红色
     */
    private void showGatherStatus(String message, boolean isError){
        if(isError){
            gatherStatusTextView.setTextColor(Color.RED);
        }else{
            gatherStatusTextView.setTextColor(Color.WHITE);
        }
        gatherStatusTextView.setText(message);
    }

    /**
     * 当前指位[1-20]，根据isFlat和currentFingerIndex计算
     * @return
     */
    private int getCurrentFgp(){
        if(isFlat){
            return currentFingerIndex + 11;
        }else{
            return currentFingerIndex + 1;
        }
    }

    /**
     * 当前选中指纹数据
     * @return
     */
    private FingerData getCurrentFingerData(){
        return fingerDataList[getCurrentFgp()-1];
    }
}