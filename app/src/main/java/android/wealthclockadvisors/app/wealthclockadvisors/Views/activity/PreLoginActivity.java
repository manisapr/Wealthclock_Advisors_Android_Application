package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class PreLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView _signinText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pre_login);

        _signinText = findViewById(R.id.signinText);
        _signinText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signinText:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
