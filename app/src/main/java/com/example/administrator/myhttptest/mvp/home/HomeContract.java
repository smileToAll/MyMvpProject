package com.example.administrator.myhttptest.mvp.home;

import com.example.administrator.myhttptest.mvp.base.BasePresenter;
import com.example.administrator.myhttptest.mvp.base.BaseView;
import com.example.administrator.myhttptest.mvp.home.bean.MeiZhi;

/**
 * Created by lijie on 2017/9/11.
 */

public interface HomeContract {
    interface View extends BaseView {
        void setData(MeiZhi meiZhi);
    }
    interface Presenter extends BasePresenter {
        void getData(int page);
        void getDataSuccess(MeiZhi meiZhi);
    }
    interface Model{
        void getListData(int page);
    }
}
