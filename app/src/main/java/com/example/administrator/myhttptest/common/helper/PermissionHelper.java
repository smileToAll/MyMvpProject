package com.example.administrator.myhttptest.common.helper;

import android.app.Activity;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by lijie on 2017/12/18.
 */

public class PermissionHelper {

    //请求权限
    public static void requestRxPermissions(final Activity context, String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean granted) throws Exception {
                if (granted){
                    Toast.makeText(context, "已获取权限", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static void requestEachRxPermission(final Activity context,String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(@NonNull Permission permission) throws Exception {
                if (permission.granted) {
                    Toast.makeText(context, "已获取权限"+ permission.name , Toast.LENGTH_SHORT).show();
                } else if (permission.shouldShowRequestPermissionRationale){
                    //拒绝权限请求
                } else {
                    // 拒绝权限请求,并不再询问
                    // 可以提醒用户进入设置界面去设置权限
                    Toast.makeText(context, "已拒绝相应权限并不再询问,可以到设置中打开对应权限", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
