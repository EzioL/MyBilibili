package com.ezio.bilibili.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ezio.bilibili.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Author：Ezio on 2016/12/19.
 * <p/>
 * activity 基类
 */
public abstract class RxBaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initToolBar();

    public void loadData() {
    }

    public void showProgressBar() {
    }

    public void hideProgressBar() {
    }

    public void initRecyclerView() {
    }

    public void initRefreshLayout() {
    }

    public void finishTask() {
    }

//    @Override
//    public void onConfirm(int currentTheme) {
//
//        if (ThemeHelper.getTheme(RxBaseActivity.this) != currentTheme) {
//            ThemeHelper.setTheme(RxBaseActivity.this, currentTheme);
//            ThemeUtils.refreshUI(RxBaseActivity.this, new ThemeUtils.ExtraRefreshable() {
//
//
//                        @Override
//                        public void refreshGlobal(Activity activity) {
//
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                                final RxBaseActivity context = RxBaseActivity.this;
//                                ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null,
//                                        null, ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
//                                setTaskDescription(taskDescription);
//                                getWindow().setStatusBarColor(ThemeUtils.getColorById(context,
//                                        R.color.theme_color_primary_dark));
//                            }
//                        }
//
//                        @Override
//                        public void refreshSpecificView(View view) {
//
//                        }
//                    }
//            );
//        }
//    }
//
//    @Override
//    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//
//        super.onPostCreate(savedInstanceState);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(ThemeUtils.getColorById(this, R.color.theme_color_primary_dark));
//            ActivityManager.TaskDescription description = new ActivityManager.TaskDescription(null, null,
//                    ThemeUtils.getThemeAttrColor(this, android.R.attr.colorPrimary));
//            setTaskDescription(description);
//        }
//    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
