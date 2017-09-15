package com.example.administrator.myhttptest.net;


import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijie on 2017/8/15.
 */

public class RxUtil {
    public static <T> ObservableTransformer<T, T> bindLifecycleAndApplySchedulers(final LifecycleProvider lifecycleProvider) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
              return upstream.subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .compose(lifecycleProvider.<T>bindToLifecycle());
            }
        };
    }
}
