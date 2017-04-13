package com.chenyk.simplestatusviewlib;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by chenyk on 2017/4/13.
 * 状态视图配置器
 */

public class StatusViewConfig implements IStatusView {
    private int emptyViewIdRes;
    private int errorViewIdRes;
    private int notNetViewIdRes;
    private int loadingViewIdRes;
    private ViewStub emptyView;
    private ViewStub errorView;
    private ViewStub notNetView;
    private ViewStub loadingView;

    public StatusViewConfig(int emptyViewIdRes, int errorViewIdRes, int notNetViewIdRes,
                            int loadingViewIdRes, ViewStub emptyView, ViewStub errorView,
                            ViewStub notNetView, ViewStub loadingView) {
        this.emptyViewIdRes = emptyViewIdRes;
        this.errorViewIdRes = errorViewIdRes;
        this.notNetViewIdRes = notNetViewIdRes;
        this.loadingViewIdRes = loadingViewIdRes;
        this.emptyView = emptyView;
        this.errorView = errorView;
        this.notNetView = notNetView;
        this.loadingView = loadingView;
    }

    public void handleActive() {
    }

    @Override
    public void setEmptyView(@LayoutRes int layoutRes) {

    }

    @Override
    public void setEmptyView(View view) {

    }

    @Override
    public void setErrorView(@LayoutRes int layoutRes) {

    }

    @Override
    public void setErrorView(View view) {

    }

    @Override
    public void setNoNetView(@LayoutRes int layoutRes) {

    }

    @Override
    public void setNoNetView(View view) {

    }

    @Override
    public void setLoading(@LayoutRes int layoutRes) {

    }

    @Override
    public void setLoadingView(View view) {

    }

    @Override
    public void callbackClickErrorView() {

    }

    @Override
    public View getEmptyView() {
        return emptyView;
    }

    @Override
    public View getErrorView() {
        return errorView;
    }

    @Override
    public View getNoNetView() {
        return notNetView;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private Context context;
        private int emptyViewIdRes;
        private int errorViewIdRes;
        private int notNetViewIdRes;
        private int loadingViewIdRes;
        private ViewStub emptyView;
        private ViewStub errorView;
        private ViewStub notNetView;
        private ViewStub loadingView;

        public Builder() {
            //设置默认值
        }

        public Builder(Context context) {

        }

        public Builder(StatusViewConfig statusViewConfig) {
            this.emptyViewIdRes = statusViewConfig.emptyViewIdRes;
            this.errorViewIdRes = statusViewConfig.errorViewIdRes;
            this.notNetViewIdRes = statusViewConfig.notNetViewIdRes;
            this.loadingViewIdRes = statusViewConfig.loadingViewIdRes;
            this.emptyView = statusViewConfig.emptyView;
            this.errorView = statusViewConfig.errorView;
            this.notNetView = statusViewConfig.notNetView;
            this.loadingView = statusViewConfig.loadingView;
        }

        public Builder setEmptyView(@LayoutRes int layoutRes) {
            this.emptyViewIdRes = layoutRes;
            return this;
        }

        public Builder setEmptyView(ViewStub view) {
            this.emptyView = view;
            return this;
        }

        public Builder setErrorView(@LayoutRes int layoutRes) {
            this.errorViewIdRes = layoutRes;
            return this;
        }

        public Builder setErrorView(ViewStub view) {
            this.errorView = view;
            return this;
        }

        public Builder setNoNetView(@LayoutRes int layoutRes) {
            this.notNetViewIdRes = layoutRes;
            return this;
        }

        public Builder setNoNetView(ViewStub view) {
            this.notNetView = view;
            return this;
        }

        public Builder setLoadingView(@LayoutRes int layoutRes) {
            this.loadingViewIdRes = layoutRes;
            return this;
        }

        public Builder setLoadingView(ViewStub view) {
            this.loadingView = view;
            return this;
        }

        public StatusViewConfig build() {
            return new StatusViewConfig(emptyViewIdRes, errorViewIdRes, notNetViewIdRes,
                    loadingViewIdRes, emptyView, errorView, notNetView, loadingView);
        }
    }
}
