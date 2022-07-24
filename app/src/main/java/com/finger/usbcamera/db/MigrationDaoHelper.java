package com.finger.usbcamera.db;

import android.content.Context;
//import android.database.Cursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
//import android.text.TextUtils;

//import com.finger.usbcamera.BuildConfig;
import com.finger.usbcamera.db.greendao.DaoMaster;
import com.finger.usbcamera.db.greendao.PersonDao;

//import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
//import org.greenrobot.greendao.internal.DaoConfig;

//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;

public class MigrationDaoHelper extends DaoMaster.OpenHelper {

    public MigrationDaoHelper(Context context, String name) {
        super(context, name);
    }

    public MigrationDaoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by execSql");
        //case 后不加break ,可保证不同版本数据库升级
        switch (oldVersion + 1) {
            case 2:
            case 3:
        }

    }
    public void alterAddColumn(Database db,String tableName,String columnName,String executeSql){
        if(!checkColumnExists(tableName,columnName,db)){
            db.execSQL(executeSql);
        }
    }

    /**
     * 检查表中某列是否存在
     * @param tableName 表名
     * @param columnName 列名
     * @return
     */
    public static boolean checkColumnExists(String tableName, String columnName,Database db) {
        boolean result = false ;
        Cursor cursor = null ;

        try{
            cursor = db.rawQuery( "select * from sqlite_master where name = ? and sql like ?"
                    , new String[]{tableName , "%" + columnName + "%"} );
            result = null != cursor && cursor.moveToFirst() ;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(null != cursor && !cursor.isClosed()){
                cursor.close() ;
            }
        }
        return result ;
    }
}