package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class PaymentWebViewActivity extends Activity {
    private WebView webView;
    private boolean exit = false;
    String call = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_view);


        Intent intent = getIntent();
        String id = intent.getStringExtra("link");


        webView = findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        String webData =  id;
        webView.loadData(webData,"text/html","UTF-8");

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(PaymentWebViewActivity.this);
            dialog.setCancelable(false);
            dialog.setTitle("Purchase Alert!");
            dialog.setMessage("Are you sure to cancel your transaction process");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Action for "Delete".
                    Intent intent = new Intent(PaymentWebViewActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();
            //finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to cancel your transaction.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}
