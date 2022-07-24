package com.finger.usbcamera.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.IOException;

/**
 * 用于支持对存储在SD卡上的数据库的访问
 **/
public class DatabaseContext extends ContextWrapper{

    /**
     * 构造函数
     * @param    base 上下文环境
     */
    public DatabaseContext(Context base){
        super(base);
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象对象
     * @param    name
     */
    @Override
    public File getDatabasePath(String name) {
        //获取sd卡路径
        String dbDir = "/FingerUsbCamera";
        File dbFile = new File(dbDir+"/"+name);//数据库路径
        if(!dbFile.exists()){
            try {
                if(dbFile.createNewFile()){
                    return dbFile;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return dbFile;
        }
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param    name
     * @param    mode
     * @param    factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     *              SQLiteDatabase.CursorFactory,
     *              DatabaseErrorHandler)
     * @param    name
     * @param    mode
     * @param    factory
     * @param     errorHandler
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        return result;
    }
}
