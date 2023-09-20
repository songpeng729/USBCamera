package com.finger.usbcamera.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.finger.usbcamera.USBCameraAPP;

/**
 * SharedPreferences工具类，对文件配置进行快速的存储
 */
public class SharedPreferencesUtil {
    private final static String SP_NAME_DEFAULT = "default_config";
    private static final SharedPreferences defaultPreferences = USBCameraAPP.getInstances().getSharedPreferences(SP_NAME_DEFAULT, Activity.MODE_PRIVATE);
    private static final SharedPreferences.Editor defaultPeditor = defaultPreferences.edit();

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static final String KEY_LOGIN_USER = "LOGIN_USER";
    //指纹采集质量校验分数
    public static final String KEY_QUALITY_LIMIT = "QUALITY_LIMIT";
    //上报地址
    public static final String KEY_UPLOAD_URL = "UPLOAD_URL";

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesUtil(Context context, String spName) {
        this.preferences = context.getSharedPreferences(spName, Activity.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void putInt(final String name, final int value) {
        editor.putInt(name, value);
        editor.commit();
    }
    public void putBoolean(final String name, final boolean value) {
        editor.putBoolean(name, value);
        editor.commit();
    }
    public void putString(final String name, final String value) {
        editor.putString(name, value);
        editor.commit();
    }
    public String getString(String name) {
        return preferences.getString(name, "");
    }
    public int getInt(String name) {
        return preferences.getInt(name, 0);
    }
    public boolean getBoolean(String name) {
        return preferences.getBoolean(name, false);
    }

    /*  =====公共方法=====  */
    public static void putIntValue(String key, int value) {
        defaultPeditor.putInt(key, value).apply();
    }
    public static void putBooleanValue(String key, boolean value) {
        defaultPeditor.putBoolean(key, value).apply();
    }
    public static void putStringValue(String key, String value) {
        defaultPeditor.putString(key, value).apply();
    }
    public static String getStringValue(String name) {
        return defaultPreferences.getString(name,"");
    }
    public static String getStringValue(String name, String defValue) {
        return defaultPreferences.getString(name,defValue);
    }
    public static int getIntValue(String name) {
        return defaultPreferences.getInt(name,0);
    }
    public static int getIntValue(String name, int defValue) {
        return defaultPreferences.getInt(name, defValue);
    }
    public static boolean getBooleanValue(String name) {
        return defaultPreferences.getBoolean(name, false);
    }

}
