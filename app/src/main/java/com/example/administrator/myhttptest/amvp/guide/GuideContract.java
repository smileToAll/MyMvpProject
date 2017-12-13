package com.example.administrator.myhttptest.amvp.guide;

import com.example.administrator.myhttptest.base.basemvp.BasePresenter;
import com.example.administrator.myhttptest.base.basemvp.BaseView;
import com.example.administrator.myhttptest.bean.MeiZhi;

/**
 * Created by lijie on 2017/12/13.
 */

public interface GuideContract {
    interface View extends BaseView{
        void getGuideData(MeiZhi meiZhi);
    }
    interface Presenter extends BasePresenter{
        void getGuideImages(int page);
    }
}
