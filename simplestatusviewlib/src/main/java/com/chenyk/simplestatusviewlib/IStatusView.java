package com.chenyk.simplestatusviewlib;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by chenyk on 2017/4/13.
 */

public interface IStatusView {
    void setEmptyView(@LayoutRes int layoutRes);//设置空视图

    void setEmptyView(View view);//设置空视图

    void setErrorView(@LayoutRes int layoutRes);//设置出錯视图

    void setErrorView(View view);//设置出錯视图

    void setNoNetView(@LayoutRes int layoutRes);//设置无网络视图

    void setNoNetView(View view);//设置无网络视图

    void setLoading(@LayoutRes int layoutRes);//设置加载中视图

    void setLoadingView(View view);//设置加载中视图

    void callbackClickErrorView();//点击错误视图回调

    View getEmptyView();//获取空视图

    View getErrorView();//获取出錯视图

    View getNoNetView();//获取无网络视图
}
