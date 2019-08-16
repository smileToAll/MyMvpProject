package com.example.administrator.myhttptest.common.widget.utils;

import android.content.SharedPreferences;

/**
 * Created by wangce on 16/1/27.
 */
public class SpUtil {
    private static SharedPreferences mSp;

    public static void init(SharedPreferences sp) {
        mSp = sp;
    }

    public static void saveOrUpdate(String key, String value) {
        mSp.edit().putString(key, value).apply();
    }

    public static String find(String key) {
        return mSp.getString(key, null);
    }

    public static void delete(String key) {
        mSp.edit().remove(key).apply();
    }

    public static void clearAll() {
        mSp.edit().clear().apply();
    }

    public static boolean has(String key) {
        return mSp.contains(key);
    }

    public static void saveOrUpdateInt(String key,int value){
        mSp.edit().putInt(key,value).apply();
    }
    public static int findInt(String key){
        return mSp.getInt(key,-1);
    }


}
