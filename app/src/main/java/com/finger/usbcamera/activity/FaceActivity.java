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
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.baidu.ocr.FileUtil;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.finger.usbcamera.R;

/**
 * 人像采集
 * 450*600的比例
 */
public class FaceActivity extends Activity implements View.OnClickListener, View.OnLongClickListener{
    private final String TAG = "FaceActivity";
    public static final int REQUEST_CODE_CAMERA = 100;//相机拍照
    private static final int REQUEST_CODE_PICK_IMAGE = 101;//从相册选择照片

    private ImageView leftFace, centerFace, rightFace;
    private Button saveBtn;

    private int currentFaceIndex = 0;//当前选中的脸位

    private static final String IMAGE_FILE_NAME = "face_image";// 头像文件名称

    public static final int FACE_INDEX_CENTER = 1;//正脸
    public static final int FACE_INDEX_LEFT = 2;//左脸
    public static final int FACE_INDEX_RIGHT = 4;//右脸

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        bindView();
    }
    private void bindView(){
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
                        break;
                    case FACE_INDEX_CENTER:
                        centerFace.setImageResource(R.mipmap.face_default);
                        break;
                    case FACE_INDEX_RIGHT:
                        rightFace.setImageResource(R.mipmap.face_default);
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
        switch (faceIndex){
            case FACE_INDEX_LEFT:
                leftFace.setImageBitmap(bm);
                break;
            case FACE_INDEX_CENTER:
                centerFace.setImageBitmap(bm);
                break;
            case FACE_INDEX_RIGHT:
                rightFace.setImageBitmap(bm);
                break;
        }
    }

    /**
     * 缩放图片, TODO 是否固定照片大小，有没有必要缩放
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
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否保存照片？"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
