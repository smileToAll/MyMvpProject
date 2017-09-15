package com.example.administrator.myhttptest.handler;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.administrator.myhttptest.base.inters.ProgressHandlerIml;


/**
 * Created by lijie on 2017/8/14.
 */

public class ProgressHandler implements ProgressHandlerIml {
    private Context context;
    private ProgressDialog progressDialog;
    public ProgressHandler(Context context) {
        this.context = context;
    }

    @Override
    public void loadingStart() {
        if(progressDialog==null){
            try {
                progressDialog=new ProgressDialog(context);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(true);
                progressDialog.show();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    @Override
    public void loadingComplete() {
        if(progressDialog!=null&&progressDialog.isShowing()){
            try {
                progressDialog.dismiss();
                progressDialog=null;
            }catch (Exception e){
                progressDialog=null;
                e.printStackTrace();
            }

        }
    }
}
