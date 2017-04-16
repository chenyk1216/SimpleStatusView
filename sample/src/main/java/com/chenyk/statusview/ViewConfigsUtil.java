package com.chenyk.statusview;

import android.content.Context;

import com.chenyk.simplestatusviewlib.ViewConfigManager;

/**
 * Created by chenyk on 2017/4/15.
 * 视图配置工具类
 */

public class ViewConfigsUtil {
    /**
     * 配置默认视图设置，并返回
     *
     * @param context
     * @return
     */
    public static ViewConfigManager getDefConfig(Context context) {
        return new ViewConfigManager.Builder(context)
                .setEmptyView(R.layout.view_empty)
                .setErrorView(R.layout.view_error)
                .setNoNetView(R.layout.view_no_net)
                .setLoadingView(R.layout.view_loading)
                .build();
    }

    /**
     * 基于默认配置，配置用于RecyclerView的视图设置，并返回
     *
     * @param context
     * @return
     */
    public static ViewConfigManager getRecycleViewConfig(Context context) {
        return getDefConfig(context).newBuilder()
                .setEmptyView(R.layout.view_recyclerview_empty)
                .build();
    }
}
