package com.example.administrator.myhttptest.amvp.fragment.main.Multis;

import com.example.administrator.myhttptest.base.basemvp.BasePresenter;
import com.example.administrator.myhttptest.base.basemvp.BaseView;
import com.example.administrator.myhttptest.bean.MeiZhi;

/**
 * Created by lijie on 2017/12/19.
 */

public interface MultiContract {
    interface view extends BaseView{
        void setListData(MeiZhi meiZhi);
    }
    interface presenter extends BasePresenter{
        void getData(int page);
    }
}
