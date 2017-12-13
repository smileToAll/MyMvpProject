package com.example.administrator.myhttptest.amvp.home;

import com.example.administrator.myhttptest.amvp.base.BasePresenter;
import com.example.administrator.myhttptest.amvp.base.BaseView;
import com.example.administrator.myhttptest.bean.MeiZhi;

/**
 * Created by lijie on 2017/9/11.
 */

public interface HomeContract {
    interface View extends BaseView {
        void setListData(MeiZhi meiZhi);
        void refreshEnd();
        void loadEnd();
    }
    interface Presenter extends BasePresenter {
        void getData(int page);
    }
}
