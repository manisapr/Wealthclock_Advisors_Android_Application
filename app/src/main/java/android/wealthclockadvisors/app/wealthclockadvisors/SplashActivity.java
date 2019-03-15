package android.wealthclockadvisors.app.wealthclockadvisors;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.TutorialActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.DashboardActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.LoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.PreLoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("splash continue");
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(SplashActivity.this);
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                Utility.setEmailaddress(SharedPreferenceManager.getUserEmail(SplashActivity.this));
                UserHandler.getInstance().sendLoginData(SharedPreferenceManager.getUserEmail(SplashActivity.this),SharedPreferenceManager.getUserPassword(SplashActivity.this),SplashActivity.this);

            }
        },SPLASH_TIMEOUT);
    }

    private class ServerResultHandler implements ihttpResultHandler{
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }
       /* @Override
        public void onSuccess(Object message, String operation_flag) {

        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            System.out.println("splash success");
            if (message.toString().equalsIgnoreCase("200"))
            {
                //Intent intent = new Intent(SplashActivity.this,DashboardActivity.class);
                Intent intent = new Intent(SplashActivity.this,Drawer.class);
                startActivity(intent);
                finish();
            }
            else
                {
                Intent intent = new Intent(SplashActivity.this,TutorialActivity.class);
                startActivity(intent);
                finish();
            }
        }
        @Override
        public void onError(Object message) {

            Intent intent = new Intent(SplashActivity.this,PreLoginActivity.class);
            startActivity(intent);
            finish();
            //System.out.println("splash error: "+message.toString());
        }
    }
}
