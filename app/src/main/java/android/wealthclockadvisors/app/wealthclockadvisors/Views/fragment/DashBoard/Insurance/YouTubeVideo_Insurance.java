package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class YouTubeVideo_Insurance extends Activity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_video__insurance);

        webview=(WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        String id = "https://www.youtube.com/watch?v=-2C52p-S9lQ&t=9s";

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
