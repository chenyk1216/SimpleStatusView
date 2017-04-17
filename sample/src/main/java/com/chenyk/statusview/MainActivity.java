package com.chenyk.statusview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenyk.simplestatusviewlib.IClickRetry;
import com.chenyk.simplestatusviewlib.SimpleStatusView;

public class MainActivity extends AppCompatActivity {
    private TextView tvSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSample = (TextView) findViewById(R.id.tv_sample);

        SimpleStatusView simpleStatusView = new SimpleStatusView.Builder(this)
                .config(ViewConfigsUtil.getRecycleViewConfig(this))
                .setTargetView(tvSample)
                .setLayoutParams(700, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setRetryClick(new IClickRetry() {
                    @Override
                    public void callback(View v) {
                        Toast.makeText(MainActivity.this, "就是你点我的", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        simpleStatusView.showErrorView();
        simpleStatusView.showLoadingView();
        simpleStatusView.hideLoadingView();
    }
}
