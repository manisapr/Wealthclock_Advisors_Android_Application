package android.wealthclockadvisors.app.wealthclockadvisors.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.wealthclockadvisors.app.wealthclockadvisors.SplashActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.LoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.TutorialViewPagerAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class TutorialActivity extends AppCompatActivity {
    ImageView selector1,selector2,selector3,selector4,selector5;
    int numOfPages=0;
    int currentPages=0;
    Timer timer;
    TimerTask timerTask;
    TextView skip_tv,explore_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutorial);



        skip_tv=(TextView)findViewById(R.id.skip_tv);
        explore_tv=(TextView)findViewById(R.id.explore_tv);

        selector1=(ImageView)findViewById(R.id.selector1);
        selector2=(ImageView)findViewById(R.id.selector2);
        selector3=(ImageView)findViewById(R.id.selector3);
        selector4=(ImageView)findViewById(R.id.selector4);
        selector5=(ImageView)findViewById(R.id.selector5);

        selector1.setBackgroundResource(R.drawable.bullet_selected);
        selector2.setBackgroundResource(R.drawable.bullet);
        selector3.setBackgroundResource(R.drawable.bullet);
        selector4.setBackgroundResource(R.drawable.bullet);
        selector5.setBackgroundResource(R.drawable.bullet);



        skip_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TutorialActivity.this, LoginActivity.class));
                finish();
                //set first preference to here
                //ConnectionHandler.myPreferences.edit().putBoolean("isFirst",false).commit();
            }
        });

        explore_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TutorialActivity.this, LoginActivity.class));
                finish();

            }
        });


        TutorialViewPagerAdapter tutorialViewPagerAdapter =new TutorialViewPagerAdapter(getSupportFragmentManager());

        final ViewPager view_pager=(ViewPager)findViewById(R.id.view_pager);

        view_pager.setAdapter(tutorialViewPagerAdapter);

        numOfPages=5;
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if (currentPages==numOfPages){
                    currentPages=0;
                }
                view_pager.setCurrentItem(currentPages++,true);
            }
        };
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        };
        timer.schedule(timerTask,1000,2000);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toggle(position);
                currentPages=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void toggle(int position){
        switch (position){

            case 0: selector1.setBackgroundResource(R.drawable.bullet_selected);
                selector2.setBackgroundResource(R.drawable.bullet);
                selector3.setBackgroundResource(R.drawable.bullet);
                selector4.setBackgroundResource(R.drawable.bullet);
                selector5.setBackgroundResource(R.drawable.bullet);
                break;

            case 1:selector2.setBackgroundResource(R.drawable.bullet_selected);
                selector1.setBackgroundResource(R.drawable.bullet);
                selector3.setBackgroundResource(R.drawable.bullet);
                selector4.setBackgroundResource(R.drawable.bullet);
                selector5.setBackgroundResource(R.drawable.bullet);
                break;

            case 2: selector3.setBackgroundResource(R.drawable.bullet_selected);
                selector1.setBackgroundResource(R.drawable.bullet);
                selector2.setBackgroundResource(R.drawable.bullet);
                selector4.setBackgroundResource(R.drawable.bullet);
                selector5.setBackgroundResource(R.drawable.bullet);

                break;

            case 3: selector4.setBackgroundResource(R.drawable.bullet_selected);
                selector1.setBackgroundResource(R.drawable.bullet);
                selector2.setBackgroundResource(R.drawable.bullet);
                selector3.setBackgroundResource(R.drawable.bullet);
                selector5.setBackgroundResource(R.drawable.bullet);

                break;
            case 4: selector5.setBackgroundResource(R.drawable.bullet_selected);
                selector1.setBackgroundResource(R.drawable.bullet);
                selector2.setBackgroundResource(R.drawable.bullet);
                selector3.setBackgroundResource(R.drawable.bullet);
                selector4.setBackgroundResource(R.drawable.bullet);

                break;

            default: selector1.setBackgroundResource(R.drawable.bullet_selected);
                selector2.setBackgroundResource(R.drawable.bullet);
                selector3.setBackgroundResource(R.drawable.bullet);
                selector4.setBackgroundResource(R.drawable.bullet);
                selector5.setBackgroundResource(R.drawable.bullet);

                break;

        }
    }
}
