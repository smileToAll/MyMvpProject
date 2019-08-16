package com.example.administrator.myhttptest.module.guide;


import com.example.administrator.myhttptest.common.base.BasePresenter;
import com.example.administrator.myhttptest.common.base.BaseView;
import com.example.administrator.myhttptest.net.bean.MeiZhi;

/**
 * Created by lijie on 2017/12/13.
 */

public interface GuideContract {
    interface View extends BaseView {
        void setGuideData(MeiZhi meiZhi);
    }

    interface Presenter extends BasePresenter {
        void getGuideImages(int page);
    }
}
