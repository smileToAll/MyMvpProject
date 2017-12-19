package com.example.administrator.myhttptest.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.administrator.myhttptest.R;

/**
 * Created by lijie on 2017/12/1.
 */

public class MyProgressDialog extends ProgressDialog{
    public MyProgressDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog_progress);
    }
}
