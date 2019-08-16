package com.example.administrator.myhttptest.module.main;

import com.example.administrator.myhttptest.common.base.BasePresenter;
import com.example.administrator.myhttptest.common.base.BaseView;
import com.example.administrator.myhttptest.net.bean.MeiZhi;

/**
 * Created by lijie on 2017/12/19.
 */

public class MeiZhiContract {
    interface View extends BaseView {
        void setListData(MeiZhi meiZhi);

        void refreshEnd();

        void loadEnd();
    }

    interface Presenter extends BasePresenter {
        void getData(int page);
    }
}
