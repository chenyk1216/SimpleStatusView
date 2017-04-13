package com.chenyk.simplestatusviewlib;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by chenyk on 2017/4/13.
 * 多状态视图
 */

public class SimpleStatusView {
    private View actionView;
    private StatusViewConfig config;
    private Context context;

    public SimpleStatusView(View actionView, StatusViewConfig config) {
        this.actionView = actionView;
        this.config = config;
        init();
    }

    private void init() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.addView(actionView);
        config.getEmptyView();
    }

    public void showEmptyView() {

    }

    public void showErrorView() {

    }
    public void showEmptyView(final IClickRetry iClickRetry) {
        if(iClickRetry!=null)
            config.getEmptyView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickRetry.callback(v);
                }
            });
    }

    public void showNotNetView() {

    }

    public void showLoadingView() {

    }

    public static class Builder {
        private View actionView;
        private StatusViewConfig config;

        /**
         * 设置操作视图，即被替换的视图
         *
         * @param actionView 被替换视图
         * @return
         */
        public Builder setActionView(View actionView) {
            this.actionView = actionView;
            return this;
        }

        public Builder config(StatusViewConfig config) {
            this.config = config;
            return this;
        }

        public SimpleStatusView build() {
            return new SimpleStatusView(actionView, config);
        }
    }
}
