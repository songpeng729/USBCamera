package com.finger.usbcamera.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.baidu.ocr.FileUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.db.entity.Face;
import com.finger.usbcamera.db.greendao.FaceDao;
import com.finger.usbcamera.db.greendao.FingerDao;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

/**
 * 人像采集
 * 450*600的比例
 */
public class FaceActivity extends Activity implements View.OnClickListener, View.OnLongClickListener{
    private final String TAG = "FaceActivity";
    public static final int REQUEST_CODE_CAMERA = 100;//相机拍照
    private static final int REQUEST_CODE_PICK_IMAGE = 101;//从相册选择照片

    public static String EXTRA_NAME = "name";
    public static String EXTRA_IDCARDNO= "idcardno";
    public static String EXTRA_PERSONID= "personid";
    private String name = "", idcardno = "";
    private Long personId;
    FaceDao faceDao = USBCameraAPP.getInstances().getDaoSession().getFaceDao();

    private TextView faceTitle;
    private ImageView leftFace, centerFace, rightFace;
    private Button saveBtn;

    private int currentFaceIndex = 0;//当前选中的脸位

    public static final int FACE_INDEX_CENTER = 1;//正脸
    public static final int FACE_INDEX_LEFT = 2;//左脸
    public static final int FACE_INDEX_RIGHT = 4;//右脸

    private byte[] centerImage;//正脸数据
    private byte[] leftImage;//左脸
    private byte[] rightImage;//右脸

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        bindView();
        init();
    }
    private void bindView(){
        faceTitle = findViewById(R.id.face_title);
        leftFace = findViewById(R.id.face_left);
        centerFace = findViewById(R.id.face_center);
        rightFace = findViewById(R.id.face_right);
        saveBtn = findViewById(R.id.face_save_btn);

        leftFace.setOnClickListener(this);
        centerFace.setOnClickListener(this);
        rightFace.setOnClickListener(this);
        //长按删除事件
        leftFace.setOnLongClickListener(this);
        centerFace.setOnLongClickListener(this);
        rightFace.setOnLongClickListener(this);
        saveBtn.setOnClickListener(this);
    }
    private void init(){
        Intent intent = getIntent();
        if(intent != null){
            name = intent.getStringExtra(EXTRA_NAME);
            idcardno = intent.getStringExtra(EXTRA_IDCARDNO);
            personId = intent.getLongExtra(EXTRA_PERSONID, 0);
            faceTitle.setText(String.format("%s(%s)\r\n三面人像采集", name, idcardno));

            List<Face> faceList = faceDao.queryBuilder().where(FaceDao.Properties.PersonId.eq(personId)).list();
            if(faceList != null && faceList.size() > 0){
                Face face = faceList.get(0);
                centerFace.setImageBitmap(bytes2Bitmap(face.getCenterImage()));
                leftFace.setImageBitmap(bytes2Bitmap(face.getLeftImage()));
                rightFace.setImageBitmap(bytes2Bitmap(face.getRightImage()));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.face_left:
                this.currentFaceIndex = FACE_INDEX_LEFT;
                takePictureByCamera();
                break;
            case R.id.face_center:
                this.currentFaceIndex = FACE_INDEX_CENTER;
                takePictureByCamera();
                break;
            case R.id.face_right:
                this.currentFaceIndex = FACE_INDEX_RIGHT;
                takePictureByCamera();
                break;
            case R.id.face_save_btn:
                savePhoto();
                break;
        }
    }

    /**
     * 从相册选择照片
     */
    /*private void pickImage(){
        if(checkGalleryPermission()){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        }
    }
    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(FaceActivity.this, Manifest.permission
                .READ_EXTERNAL_STORAGE);
        if (ret != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FaceActivity.this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }*/

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.face_left:
                removeImage(FACE_INDEX_LEFT);
                break;
            case R.id.face_center:
                removeImage(FACE_INDEX_CENTER);
                break;
            case R.id.face_right:
                removeImage(FACE_INDEX_RIGHT);
                break;
        }
        return false;
    }

    /**
     * 相机拍照
     */
    private void takePictureByCamera(){
        Intent intent = new Intent(FaceActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication()).getAbsolutePath());
        //TODO 支持人像采集水印
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_GENERAL);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    protected void removeImage(final int faceIndex) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否删除此照片？"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (faceIndex){
                    case FACE_INDEX_LEFT:
                        leftFace.setImageResource(R.mipmap.face_default);
                        leftImage = null;
                        break;
                    case FACE_INDEX_CENTER:
                        centerFace.setImageResource(R.mipmap.face_default);
                        centerImage = null;
                        break;
                    case FACE_INDEX_RIGHT:
                        rightFace.setImageResource(R.mipmap.face_default);
                        rightImage = null;
                        break;
                }
                dialog.dismiss(); //关闭dialog
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                        setImage(this.currentFaceIndex, filePath);
                    }
                }
                break;
            case REQUEST_CODE_PICK_IMAGE:
                break;
            default:
                break;
        }
    }

    private void setImage(int faceIndex, String filePath){
        Bitmap bm = BitmapFactory.decodeFile(filePath); //lessenUriImage(filePath);
        byte[] imageData = bitmap2Bytes(bm);
        switch (faceIndex){
            case FACE_INDEX_LEFT:
                leftFace.setImageBitmap(bm);
                leftImage = imageData;
                break;
            case FACE_INDEX_CENTER:
                centerFace.setImageBitmap(bm);
                centerImage = imageData;
                break;
            case FACE_INDEX_RIGHT:
                rightFace.setImageBitmap(bm);
                rightImage = imageData;
                break;
        }
    }
    private byte[] bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }
    private Bitmap bytes2Bitmap(byte[] data){
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * 缩放图片
     * @param path
     * @return
     */
    private Bitmap lessenUriImage(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inJustDecodeBounds = false; // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = (int) (options.outHeight / (float) 320);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be; // 重新读入图片，注意此时已经把 options.inJustDecodeBounds
        // 设回 false 了
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.i(TAG, "lessenUriImage: width "+ w + " height " + h); // after zoom
        return bitmap;
    }

    /**
     * 保存照片
     */
    private void savePhoto() {
        if(centerImage == null || leftImage == null || rightImage == null){
            Toast.makeText(getApplicationContext(),"请采集三面人像数据!",Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否保存照片？"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Face face = new Face();
                face.setCenterImage(centerImage);
                face.setLeftImage(leftImage);
                face.setRightImage(rightImage);
                face.setPersonId(personId);
                faceDao.insert(face);

                dialog.dismiss(); //关闭dialog
                Toast.makeText(getApplicationContext(),"成功保存三面人像数据",Toast.LENGTH_SHORT).show();
                saveBtn.setBackgroundResource(R.color.gray_light);
                saveBtn.setEnabled(false);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
}
