package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.views.PullScrollView;
import com.gyf.immersionbar.ImmersionBar;

public class MainActivity extends AppCompatActivity {
    private PullScrollView pullScrollView;
    private ImageView iv_title;
    private Button bt_go_home;
    private FrameLayout ll_two_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        setContentView(R.layout.activity_main);
        bt_go_home = findViewById(R.id.bt_go_home);
        pullScrollView = findViewById(R.id.pull_scroll);
        iv_title = findViewById(R.id.iv_title);
        ll_two_view = findViewById(R.id.ll_two_view);
        pullScrollView.setHeadView(iv_title);
        pullScrollView.setTwoView(ll_two_view);

        final ColorDrawable titleBgDrawable = new ColorDrawable(Color.WHITE);
        pullScrollView.setScrollStateListener(new PullScrollView.ScrollStateListener() {
            @Override
            public void scrollState(float scrollScale) {
                titleBgDrawable.setAlpha((int) (scrollScale * 255));
                iv_title.setBackground(titleBgDrawable);
                Log.i("TTT", "scrollState:" + scrollScale);
            }

            @Override
            public void changedState(boolean isOpen) {
                Log.i("TTT", "changedState:" + isOpen);
            }


        });
        pullScrollView.setScrollTwoViewListener(new PullScrollView.ScrollTwoViewListener() {
            @Override
            public void showTwoView(boolean isShow) {
                iv_title.setVisibility(isShow ? View.GONE : View.VISIBLE);
            }

            @Override
            public void openTwoView() {
                bt_go_home.setVisibility(View.VISIBLE);
            }

            @Override
            public void closeTwoView() {
                bt_go_home.setVisibility(View.GONE);
            }
        });
    }

    public void closeTwoView(View view) {
        pullScrollView.closeTwoView();
    }

    @Override
    public void onBackPressed() {
        if (pullScrollView.isTwoViewOpen()) {
            pullScrollView.closeTwoView();
        } else {
            super.onBackPressed();
        }
    }
}
