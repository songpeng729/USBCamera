package com.finger.usbcamera;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finger.usbcamera.db.MigrationDaoHelper;
import com.finger.usbcamera.db.entity.User;
import com.finger.usbcamera.db.greendao.DaoMaster;
import com.finger.usbcamera.db.greendao.DaoSession;

public class USBCameraAPP extends Application {
    //https://console.bce.baidu.com/iam/#/iam/accesslist
//    public static final String Access_Key = "21e132b876c64158bcaadc8489f68d5f";
//    public static final String Secret_Key = "d10dcf3a982742a6a293fce7f144827e";

    public static final String APP_ID = "26633100";
    public static final String APP_KEY = "quROaF6TH8uNqQyKvagI3n5o";
    public static final String SECRET_KEY = "ZGuuT69kVbYIe8KqR9LyVF6sai9kD548";
    public static final String AES_KEY = "e325f40cc6c5b7b5";
    public static String accessToken = "";

    private static Context context;
    private MigrationDaoHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static USBCameraAPP instances;

    private User loginUser;

    public static String EXTRA_NAME = "name";
    public static String EXTRA_IDCARDNO= "idcardno";
    public static String EXTRA_PERSONID= "person_id";

    public static USBCameraAPP getInstances(){
        return instances;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instances = this;
        setDatabase();
    }

    /**
     * 设置greenDAO
      通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
      可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO已经帮你做了。
      注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
      所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
     */
    private void setDatabase() {
//        DatabaseContext dbContext = new DatabaseContext(context);
//        mHelper = new MigrationDaoHelper(dbContext,"fingerusbcamera.db",null);
//        db = mHelper.getWritableDatabase();
        db = new DaoMaster.DevOpenHelper(context, "finger_usb_camera.db").getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

        //初始数据
        initDBData();
    }

    private void initDBData(){
        User user = new User();
        user.setLoginName("admin");
        user.setPassword("svgn");
        mDaoSession.getUserDao().insert(user);
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
