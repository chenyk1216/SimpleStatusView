package com.chenyk.simplestatusviewlib;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by chenyk on 2017/4/13.
 * 状态视图配置管理器
 */

public class ViewConfigManager {
    private Context mContext;//上下文
    private int mEmptyVLayoutRes;
    private int mErrorVLayoutRes;
    private int mNotNetVLayoutRes;
    private int mLoadingVLayoutRes;

    private View mEmptyView;//空视图
    private View mErrorView;//出错视图
    private View mNotNetView;//网络异常视图
    private View mLoadingView;//正在加载视图

    public ViewConfigManager(Context context, int emptyViewIdRes, int errorViewIdRes, int notNetViewIdRes,
                             int loadingViewIdRes, View emptyView, View errorView,
                             View notNetView, View loadingView) {
        this.mContext = context;
        this.mEmptyView = emptyView;
        this.mErrorView = errorView;
        this.mNotNetView = notNetView;
        this.mLoadingView = loadingView;
        this.mEmptyVLayoutRes = emptyViewIdRes;
        this.mErrorVLayoutRes = errorViewIdRes;
        this.mNotNetVLayoutRes = notNetViewIdRes;
        this.mLoadingVLayoutRes = loadingViewIdRes;
        init();
    }

    public void init() {
        if (mEmptyVLayoutRes != 0) mEmptyView = createView(mEmptyVLayoutRes);
        if (mErrorVLayoutRes != 0) mErrorView = createView(mErrorVLayoutRes);
        if (mNotNetVLayoutRes != 0) mNotNetView = createView(mNotNetVLayoutRes);
        if (mLoadingVLayoutRes != 0) mLoadingView = createView(mLoadingVLayoutRes);
    }

    /**
     * 根据layouRes生成view
     *
     * @param layoutRes 布局资源
     * @return
     */
    private View createView(@LayoutRes int layoutRes) {
        return LayoutInflater.from(mContext).inflate(layoutRes, null);
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public View getErrorView() {
        return mErrorView;
    }

    public View getNotNetView() {
        return mNotNetView;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private Context mContext;
        private int mEmptyVLayoutRes;
        private int mErrorVLayoutRes;
        private int mNotNetVLayoutRes;
        private int mLoadingVLayoutRes;
        private View mEmptyView;
        private View mErrorView;
        private View mNotNetView;
        private View mLoadingView;

        public Builder(Context context) {
            //设置默认值
            this.mContext = context;
        }

        public Builder(ViewConfigManager viewConfigManager) {
            this.mContext = viewConfigManager.mContext;
            this.mEmptyVLayoutRes = viewConfigManager.mEmptyVLayoutRes;
            this.mErrorVLayoutRes = viewConfigManager.mErrorVLayoutRes;
            this.mNotNetVLayoutRes = viewConfigManager.mNotNetVLayoutRes;
            this.mLoadingVLayoutRes = viewConfigManager.mLoadingVLayoutRes;
            this.mEmptyView = viewConfigManager.mEmptyView;
            this.mErrorView = viewConfigManager.mErrorView;
            this.mNotNetView = viewConfigManager.mNotNetView;
            this.mLoadingView = viewConfigManager.mLoadingView;
        }

        public Builder setEmptyView(@LayoutRes int layoutRes) {
            this.mEmptyVLayoutRes = layoutRes;
            return this;
        }

        public Builder setEmptyView(View view) {
            this.mEmptyView = view;
            return this;
        }

        public Builder setErrorView(@LayoutRes int layoutRes) {
            this.mErrorVLayoutRes = layoutRes;
            return this;
        }

        public Builder setErrorView(View view) {
            this.mErrorView = view;
            return this;
        }

        public Builder setNoNetView(@LayoutRes int layoutRes) {
            this.mNotNetVLayoutRes = layoutRes;
            return this;
        }

        public Builder setNoNetView(View view) {
            this.mNotNetView = view;
            return this;
        }

        public Builder setLoadingView(@LayoutRes int layoutRes) {
            this.mLoadingVLayoutRes = layoutRes;
            return this;
        }

        public Builder setLoadingView(View view) {
            this.mLoadingView = view;
            return this;
        }

        public ViewConfigManager build() {
            return new ViewConfigManager(mContext, mEmptyVLayoutRes, mErrorVLayoutRes, mNotNetVLayoutRes,
                    mLoadingVLayoutRes, mEmptyView, mErrorView, mNotNetView, mLoadingView);
        }
    }

}
