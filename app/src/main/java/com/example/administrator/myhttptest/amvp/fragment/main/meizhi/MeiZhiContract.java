package com.example.administrator.myhttptest.amvp.fragment.main.meizhi;

import com.example.administrator.myhttptest.base.basemvp.BasePresenter;
import com.example.administrator.myhttptest.base.basemvp.BaseView;
import com.example.administrator.myhttptest.bean.MeiZhi;

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
