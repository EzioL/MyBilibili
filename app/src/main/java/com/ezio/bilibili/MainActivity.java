package com.ezio.bilibili;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezio.bilibili.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //初始化Fragment
        initFragments();
        //初始化侧滑菜单
        initNavigationView();
    }

    private void initFragments() {

    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = mNavigationView.getHeaderView(0);
        CircleImageView mUserAvatarView = (CircleImageView) headerView.findViewById(R.id.user_avatar_view);
        TextView mUserName = (TextView) headerView.findViewById(R.id.user_name);
        TextView mUserSign = (TextView) headerView.findViewById(R.id.user_other_info);
        ImageView mSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        //设置头像
        mUserAvatarView.setImageResource(R.drawable.ic_hotbitmapgg_avatar);
        //设置用户名 签名
        mUserName.setText(getResources().getText(R.string.ezio));
        mUserSign.setText(getResources().getText(R.string.about_user_head_layout));
        //设置日夜间模式切换
        //mSwitchMode.setOnClickListener(v -> switchNightMode());

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                // changeFragmentIndex(item, 0);
                return true;

            case R.id.item_download:
                // 离线缓存
                // startActivity(new Intent(MainActivity.this, OffLineDownloadActivity.class));
                return true;

            case R.id.item_vip:
                //大会员
                //startActivity(new Intent(MainActivity.this, VipActivity.class));
                return true;

            case R.id.item_favourite:
                // 我的收藏
                // changeFragmentIndex(item, 1);
                return true;

            case R.id.item_history:
                // 历史记录
                //changeFragmentIndex(item, 2);
                return true;

            case R.id.item_group:
                // 关注的人
                //changeFragmentIndex(item, 3);
                return true;

            case R.id.item_tracker:
                // 我的钱包
                // changeFragmentIndex(item, 4);
                return true;

            case R.id.item_theme:
                // 主题选择
//                CardPickerDialog dialog = new CardPickerDialog();
//                dialog.setClickListener(this);
//                dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
                return true;

            case R.id.item_app:
                // 应用推荐

                return true;

            case R.id.item_settings:
                // 设置中心
                //changeFragmentIndex(item, 5);
                return true;
        }

        return false;
    }
}
