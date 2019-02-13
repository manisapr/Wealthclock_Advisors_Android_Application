package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class NewsWebViewActivity extends Activity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);


        webview=(WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        String id = intent.getStringExtra("link");



        WebSettings webSettings = webview.getSettings();

        webview.getSettings().setLoadsImagesAutomatically(true);

        webview.getSettings().setJavaScriptEnabled(true);

        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webview.setWebViewClient(new MyBrowser());

        webview.loadUrl(id);


    }

    private class MyBrowser extends WebViewClient {

        @Override

        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;

        }

    }

    }

