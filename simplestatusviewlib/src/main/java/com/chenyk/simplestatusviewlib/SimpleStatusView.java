package com.chenyk.simplestatusviewlib;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

/**
 * Created by chenyk on 2017/4/13.
 * 多状态视图
 * 1、显示和隐藏各种状态视图{@link #showEmptyView()} {@link #hideEmptyView()}
 * 2、目标视图默认自动隐藏和显示
 * 3、空视图、出错视图和网络异常视图互斥，仅接受一个视图可见，加载视图可与其他视图同时可见
 * 4、状态视图大小默认为目标视图大小，亦可自行定制{@link Builder#setLayoutParams(int, int)}
 * 5、设置重试监听回调，默认点击状态视图有效，亦可自行设置重试id{@link Builder#setRetryId(int)}
 */

public class SimpleStatusView implements View.OnClickListener {
    private static final int KEY_TAG = 8 << 24;

    @Override
    public void onClick(View v) {
        if (mIClickRetry != null) mIClickRetry.callback(v);
    }

    //视图类型
    interface TYPE {
        int EMPTY_VIEW = 0x0001;
        int ERROE_VIEW = 0x0002;
        int NOT_NET_VIEW = 0x003;
        int LOADING_VIEW = 0x004;
    }

    private Context mContext;
    private View mTargetView;
    private int mRetryIdRes;
    private int mWidth;
    private int mHeight;
    private ViewConfigManager mConfig;
    private IClickRetry mIClickRetry;
    private FrameLayout mFramelayout;
    private View mEmptyView;
    private View mErrorView;
    private View mNotNetView;
    private View mLoadingView;

    public SimpleStatusView(Context context, View targetView, int retryIdRes, int width, int height, ViewConfigManager config, IClickRetry iClickRetry) {
        this.mContext = context;
        this.mTargetView = targetView;
        this.mRetryIdRes = retryIdRes;
        this.mWidth = width;
        this.mHeight = height;
        this.mConfig = config;
        this.mIClickRetry = iClickRetry;
        init();
    }

    private void init() {
        createLayout();
        initAllView();
    }

    /**
     * 创建一个跟目标视图宽度高度一致的layout，并添加
     */
    private void createLayout() {
        if (mTargetView != null) {
            ViewGroup mViewGroup = (ViewGroup) mTargetView.getParent();
            LayoutParams lp;
            if (mWidth > 0 && mHeight > 0) {
                lp = new LayoutParams(mWidth, mHeight);
            } else {
                ViewGroup.LayoutParams layoutParams = mTargetView.getLayoutParams();//获取目标视图的长宽配置信息
                lp = new LayoutParams(layoutParams.width, layoutParams.height);
            }
            mFramelayout = new FrameLayout(mContext);
            mFramelayout.setLayoutParams(lp);
            mViewGroup.addView(mFramelayout);
        }
    }

    /**
     * 初始化所有视图
     */
    private void initAllView() {
        if (mConfig != null) {
            mEmptyView = mConfig.getEmptyView();
            mErrorView = mConfig.getErrorView();
            mNotNetView = mConfig.getNotNetView();
            mLoadingView = mConfig.getLoadingView();

            if (mEmptyView != null) {
                configView(TYPE.EMPTY_VIEW, mEmptyView);
            }
            if (mErrorView != null) {
                configView(TYPE.ERROE_VIEW, mErrorView);
                if (mRetryIdRes > 0 && mErrorView.findViewById(mRetryIdRes) != null) {
                    mErrorView.findViewById(mRetryIdRes).setOnClickListener(this);
                } else mErrorView.setOnClickListener(this);
            }

            if (mNotNetView != null) {
                configView(TYPE.NOT_NET_VIEW, mNotNetView);
                if (mRetryIdRes > 0 && mNotNetView.findViewById(mRetryIdRes) != null) {
                    mNotNetView.findViewById(mRetryIdRes).setOnClickListener(this);
                } else mNotNetView.setOnClickListener(this);
            }

            if (mLoadingView != null) {
                configView(TYPE.LOADING_VIEW, mLoadingView);
            }

        }
    }

    /**
     * 处理配置视图
     *
     * @param tag
     * @param view
     */
    private void configView(int tag, View view) {
        view.setTag(KEY_TAG, tag);
        mFramelayout.addView(view);
        view.setVisibility(View.GONE);
    }

    /**
     * 获取新增视图的根layout
     *
     * @return
     */
    public FrameLayout getFramelayout() {
        return mFramelayout;
    }

    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
        showOrHideTargetView();
    }

    public void hideEmptyView() {
        mEmptyView.setVisibility(View.GONE);
        showOrHideTargetView();
    }

    public void showErrorView() {
        mErrorView.setVisibility(View.VISIBLE);
        showOrHideTargetView();
    }

    public void hideErrorView() {
        mErrorView.setVisibility(View.GONE);
        showOrHideTargetView();
    }

    public void showNotNetView() {
        mNotNetView.setVisibility(View.VISIBLE);
        showOrHideTargetView();
    }

    public void hideNotNetView() {
        mNotNetView.setVisibility(View.GONE);
        showOrHideTargetView();
    }

    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
        showOrHideTargetView();
    }

    public void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
        showOrHideTargetView();
    }

    /**
     * 如果其他视图可见，自动隐藏目标视图，反之显示
     */
    private void showOrHideTargetView() {
        mTargetView.setVisibility(isViewShow() ? View.GONE : View.VISIBLE);
    }

    /**
     * 除目标视图以外的视图是否处于可见状态
     *
     * @return
     */
    private boolean isViewShow() {
        boolean b = false;
        for (int i = 0; i < mFramelayout.getChildCount(); i++) {
            View view = mFramelayout.getChildAt(i);
            if (view.getTag(KEY_TAG) == null) return b;
            if (TYPE.EMPTY_VIEW == (Integer) view.getTag(KEY_TAG) ||
                    TYPE.ERROE_VIEW == (Integer) view.getTag(KEY_TAG) ||
                    TYPE.NOT_NET_VIEW == (Integer) view.getTag(KEY_TAG) ||
                    TYPE.LOADING_VIEW == (Integer) view.getTag(KEY_TAG)) {
                if (view.getVisibility() == View.VISIBLE)
                    b = true;
            }
        }
        return b;
    }

    /**
     * 获取当前显示视图，只考虑出错视图和无网络视图
     * 而且只取一个可见视图，因为这两个视图一次只能显示一个
     *
     * @return
     */
    private View getShowView() {
        View showView = null;
        for (int i = 0; i < mFramelayout.getChildCount(); i++) {
            View view = mFramelayout.getChildAt(i);
            if (view.getTag(KEY_TAG) == null) return showView;
            if (TYPE.NOT_NET_VIEW == (Integer) view.getTag(KEY_TAG) || TYPE.ERROE_VIEW == (Integer) view.getTag(KEY_TAG)) {
                if (view.getVisibility() == View.VISIBLE) {
                    showView = view;
                }
            }
        }
        return showView;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private Context mContext;
        private View mTargetView;
        private int mRetryIdRes;
        private int mWidth;
        private int mHeight;
        private ViewConfigManager mConfig;
        private IClickRetry mIClickRetry;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder(SimpleStatusView simpleStatusView) {
            this.mContext = simpleStatusView.mContext;
            this.mTargetView = simpleStatusView.mTargetView;
            this.mRetryIdRes = simpleStatusView.mRetryIdRes;
            this.mWidth = simpleStatusView.mWidth;
            this.mHeight = simpleStatusView.mHeight;
            this.mConfig = simpleStatusView.mConfig;
            this.mIClickRetry = simpleStatusView.mIClickRetry;
        }

        /**
         * 设置目标视图，即被替换的视图
         *
         * @param targetView 被替换视图
         * @return
         */
        public Builder setTargetView(View targetView) {
            this.mTargetView = targetView;
            return this;
        }

        /**
         * 设置重试控件的id值，必须包含在出错视图或网络异常视图中，否则无效
         *
         * @param retryIdRes 控件id
         */
        public Builder setRetryId(@IdRes int retryIdRes) {
            this.mRetryIdRes = retryIdRes;
            return this;
        }

        /**
         * 设置视图大小
         *
         * @param width  宽度
         * @param height 高度
         */
        public Builder setLayoutParams(int width, int height) {
            this.mWidth = width;
            this.mHeight = height;
            return this;
        }


        /**
         * 设置视图配置信息
         *
         * @param config
         * @return
         */
        public Builder config(ViewConfigManager config) {
            this.mConfig = config;
            return this;
        }

        /**
         * 设置重试点击事件
         *
         * @param iClickRetry
         */
        public Builder setRetryClick(IClickRetry iClickRetry) {
            mIClickRetry = iClickRetry;
            return this;
        }

        /**
         * 构建SimpleStatusView实例并返回
         *
         * @return
         */
        public SimpleStatusView build() {
            return new SimpleStatusView(mContext, mTargetView, mRetryIdRes, mWidth, mHeight, mConfig, mIClickRetry);
        }
    }
}
