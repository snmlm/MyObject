package com.ds.myapp.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.ds.myapp.R;

/**
 * 显示新闻页
 * Created by xxxxx on 2016/11/7.
 */

public class ShowNews extends BaseActivity {
    private WebView mWvShowNews;
    private WebSettings settings;
    private Toolbar mTbShowNews;
    private ProgressBar mPbShownews;

    private void assignViews() {

        mWvShowNews = (WebView) findViewById(R.id.wv_show_news);
        mTbShowNews = (Toolbar) findViewById(R.id.tb_show_news);
        mPbShownews = (ProgressBar) findViewById(R.id.pb_show_news);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_show_news);
        assignViews();
        initToolbar();
        initWebView();


    }

    private void initToolbar() {
        setSupportActionBar(mTbShowNews);
        mTbShowNews.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_news_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_show_news_1:
                settings.setTextZoom(50);
                break;
            case R.id.menu_show_news_2:
                settings.setTextZoom(75);
                break;
            case R.id.menu_show_news_3:
                settings.setTextZoom(100);
                break;
            case R.id.menu_show_news_4:
                settings.setTextZoom(150);
                break;
            case R.id.menu_show_news_5:
                settings.setTextZoom(200);
                break;
        }
        return true;
    }

    private void initWebView() {
        mWvShowNews.loadUrl(getIntent().getStringExtra("url"));
        settings = mWvShowNews.getSettings();
        settings.supportZoom();
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);


        mWvShowNews.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWvShowNews.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTbShowNews.setTitle(title);
                mTbShowNews.setVisibility(View.VISIBLE);
                mPbShownews.setVisibility(View.GONE);
                Toast.makeText(ShowNews.this, title + "", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
