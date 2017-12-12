package com.example.administrator.myhttptest.amvp.home;

import android.content.Context;
import android.util.Log;

import com.example.administrator.myhttptest.bean.MeiZhi;
import com.example.administrator.myhttptest.handler.progresshandler.ProgressHandler;
import com.example.administrator.myhttptest.model.HomeModel;
import com.example.administrator.myhttptest.net.BaseDataObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijie on 2017/9/11.
 */

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View view;
    Context context;
    HomeModel model;

    public HomePresenter(HomeContract.View view, Context context) {
        this.view = view;
        this.context = context;
        model=new HomeModel();
    }

    @Override
    public void getData(int page) {
      model.getListData(page)
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.newThread())
              .subscribe(new BaseDataObserver<MeiZhi>(new ProgressHandler() {
                  @Override
                  public void loadingStart() {
                     view.showProgressDialog();
                  }

                  @Override
                  public void loadingComplete() {
                      view.dismissProgressDialog();
                  }
              }) {
                  @Override
                  public void onDataNext(MeiZhi meiZhi) {
                      view.setListData(meiZhi);
                      Log.e("onDataNext", "======" + "走到了这一步");
                  }
              });
    }
}
