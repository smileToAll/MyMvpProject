package com.example.administrator.myhttptest.common.handler.progresshandler;

import android.content.Context;

import com.example.administrator.myhttptest.common.widget.dialog.MyProgressDialog;


/**
 * Created by lijie on 2017/8/14.
 */

public class ProgressHandlerImp implements ProgressHandler {
    private Context context;
    private MyProgressDialog progressDialog;
    public ProgressHandlerImp(Context context) {
        this.context = context;
    }

    @Override
    public void loadingStart() {
        if(progressDialog==null){
            try {
                progressDialog=new MyProgressDialog(context);
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
