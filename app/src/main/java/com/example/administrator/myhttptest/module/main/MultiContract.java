package com.example.administrator.myhttptest.module.main;


import com.example.administrator.myhttptest.common.base.BasePresenter;
import com.example.administrator.myhttptest.common.base.BaseView;
import com.example.administrator.myhttptest.net.bean.MeiZhi;

/**
 * Created by lijie on 2017/12/19.
 */

public interface MultiContract {
    interface view extends BaseView {
        void setListData(MeiZhi meiZhi);
    }

    interface presenter extends BasePresenter {
        void getData(int page);
    }
}
