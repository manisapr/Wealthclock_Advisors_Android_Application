package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import android.wealthclockadvisors.app.wealthclockadvisors.Drawer;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.SoftKeyBoardDetect;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.SignupFragment;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class LoginActivity extends AppCompatActivity  {
    private Button _signupButton,_loginButton;
    private EditText _email,_pwd;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private KProgressHUD hud;
    private CheckBox _checkboxPwd;
    private RelativeLayout _rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        _signupButton = findViewById(R.id.signUp_btn);
        _email = findViewById(R.id.email);
        _pwd = findViewById(R.id.password);
        _rootLayout = findViewById(R.id.rootLayout);
        _checkboxPwd = findViewById(R.id.checkboxPwd);
        _loginButton = findViewById(R.id.login_btn);
        _loginButton.setEnabled(true);



        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isInternetOn(LoginActivity.this))
                {
                    /*startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                    finish();*/
                    if (validate())
                    {
                        _loginButton.setAlpha(0.5f);
                        _loginButton.setEnabled(false);
                        _loginButton.setText(R.string.login);
                         hud = KProgressHUD.create(LoginActivity.this)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                .setDimAmount(0.5f)
                                .setLabel("Please Wait")
                                .setCancellable(false);
                        hud.show();
                        ServerResultHandler serverResultHandler = new ServerResultHandler();
                        serverResultHandler.setContext(LoginActivity.this);
                        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                        Utility.setEmailaddress(_email.getText().toString().trim());
                        UserHandler.getInstance().sendLoginData(_email.getText().toString().trim(),_pwd.getText().toString().trim(),LoginActivity.this);
                    }
                }
                else {
                    startActivity(new Intent(LoginActivity.this,NoInternetConnectionActivity.class));
                }
            }
        });


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isInternetOn(LoginActivity.this))
                {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        SignupFragment signupFragment = new SignupFragment();
                        fragmentTransaction.replace(R.id.fragment_container, signupFragment, "SignupFragment");
                        fragmentTransaction.addToBackStack("SignupFragment");
                        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragmentTransaction.commit();

                }
                else {
                    startActivity(new Intent(LoginActivity.this,NoInternetConnectionActivity.class));

                }
            }
        });

    _checkboxPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
            {
                _pwd.setTransformationMethod(null);
            }
            else {
                //_pwd.setTransformationMethod(new DoNothingTransformation());
                _pwd.setTransformationMethod(new PasswordTransformationMethod());
            }
        }
    });
    }

    private boolean validate() {
        String email = _email.getText().toString().trim();
        String pwd = _pwd.getText().toString().trim();
        if (email.isEmpty()) {
            _email.setError(getString(R.string.email_required));
            //showProgress(false);
            return false;
        }
        if (pwd.isEmpty()) {
            _pwd.setError(getString(R.string.password_required));
            //showProgress(false);
            return false;
        }
        if (pwd.length() < 1) {
            _pwd.setError(getString(R.string.password_required_length));
            //showProgress(false);
            return false;
        }

        return true;
    }



    private class ServerResultHandler implements ihttpResultHandler {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        /*@Override
        public void onSuccess(Object message, String operation_flag) {
            System.out.println("login successful");

        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            if (message.toString().trim().equalsIgnoreCase("200")) {

                if (!messsage1.toString().trim().equalsIgnoreCase("") && !messsage1.toString().trim().equalsIgnoreCase(""))
                {

                    SharedPreferenceManager.setUserEmail(LoginActivity.this, _email.getText().toString().trim());
                    SharedPreferenceManager.setUserPassword(LoginActivity.this, _pwd.getText().toString().trim());
                    Toast.makeText(LoginActivity.this, "Welcome to Wealthclock Advisors", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, Drawer.class);
                    startActivity(intent);
                    hud.dismiss();
                    _loginButton.setAlpha(1.0f);
                    _loginButton.setEnabled(true);
                    finish();

                }
                else {
                   /* Intent intent = new Intent(LoginActivity.this, IdentityDetailsActivity.class);
                    startActivity(intent);
                    finish();*/
                }
            }
            else {
                _loginButton.setAlpha(1.0f);
                _loginButton.setEnabled(true);
                _loginButton.setText("LogIn");
                hud.dismiss();
                Toast.makeText(LoginActivity.this, "You have entered invalid credential!!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onError(Object message) {
            hud.dismiss();
            _loginButton.setAlpha(1.0f);
            _loginButton.setEnabled(true);
            _loginButton.setText("LogIn");
            System.out.println("login error");

        }
    }
}
