package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Drawer;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class PaymentWebViewActivity extends Activity {
    private WebView webView;
    Button home;
    private boolean exit = false;
    String call = " ";
    Context context;
    ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_view);


        Intent intent = getIntent();
        String id = intent.getStringExtra("link");

        ViewGroup viewGroup = findViewById(android.R.id.content);
        webView = findViewById(R.id.webview);
        home=(Button)findViewById(R.id.home);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        String webData =  id;
        webView.loadData(webData,"text/html","UTF-8");
//        home.setOnClickListener(new View.OnClickListener() {
//        });

    }
//    private void showErrorDialog(String text)
//    {
//        View dialogView = LayoutInflater.from(PaymentWebViewActivity.this).inflate(R.layout.my_dialog_error, viewGroup, false);
//        Button buttonOk=dialogView.findViewById(R.id.buttonOk);
//        TextView tv1=dialogView.findViewById(R.id.tv1);
//        tv1.setText("Failed!!!");
//        TextView tv2 =  dialogView.findViewById(R.id.tv2);
//        tv2.setText(text);
//        //Now we need an AlertDialog.Builder objec
//        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(PaymentWebViewActivity.this);
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//        //finally creating the alert dialog and displaying it
//        final android.support.v7.app.AlertDialog alertDialog = builder.create();
//        alertDialog.setCancelable(true);
//        alertDialog.show();
//        buttonOk.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//
//            public void onClick(View v) {
//                Intent i=new Intent(PaymentWebViewActivity.this,Drawer.class);
//                startActivity(i);
//                finish();
//                alertDialog.dismiss();
//            }
//
//        });
//    }

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
