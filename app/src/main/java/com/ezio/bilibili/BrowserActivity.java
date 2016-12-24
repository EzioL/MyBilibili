package com.ezio.bilibili;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ezio.bilibili.base.RxBaseActivity;
import com.ezio.bilibili.widget.ProgressWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BrowserActivity extends RxBaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.webView)
    ProgressWebView mWebView;

    private String url, mTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_browser;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        url = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");
        setupWebView();
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle(TextUtils.isEmpty(mTitle) ? "详情" : mTitle);
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void setupWebView() {
        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.getSettings().setBlockNetworkImage(true);
        mWebView.setWebViewClient(new WebViewClientBase());
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.loadUrl(url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class WebViewClientBase extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            mWebView.getSettings().setBlockNetworkImage(false);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            super.onReceivedError(view, request, error);
            String errorHtml = "<html><body><h2>找不到网页</h2></body></html>";
            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
        }
    }

}
