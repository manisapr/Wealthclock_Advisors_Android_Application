package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.wealthclockadvisors.app.wealthclockadvisors.adapter.DashBoardPagerAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.DashBoardModel;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class DashboardActivity extends AppCompatActivity {
    ImageView dashboard_bar, investment_bar, explore_bar, goal_track_bar, account_bar;
    ImageView img_dashboard, img_investment, img_goal_track, img_account;
    ImageView notification_icon,user_image;
    RelativeLayout relative_dashboard, relative_investment, relative_explore, relative_goal_track, relative_account;
    TextView user_name;
    Context context;
    RelativeLayout top_lay,lay_spinner;

    TextView tv_dashboard,tv_investment,tv_goal_tracker,tv_account;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        tv_dashboard=(TextView)findViewById(R.id.tv_dashboard);
        tv_investment=(TextView)findViewById(R.id.tv_investment);
        tv_goal_tracker=(TextView)findViewById(R.id.tv_goal_tracker);
        tv_account=(TextView)findViewById(R.id.tv_account);

        top_lay=(RelativeLayout)findViewById(R.id.top_lay);
        relative_dashboard = (RelativeLayout) findViewById(R.id.relative_dashboard);
        relative_investment = (RelativeLayout) findViewById(R.id.relative_investment);
        relative_explore = (RelativeLayout) findViewById(R.id.relative_explore);
        relative_goal_track = (RelativeLayout) findViewById(R.id.relative_goal_track);
        relative_account = (RelativeLayout) findViewById(R.id.relative_account);
        lay_spinner=(RelativeLayout)findViewById(R.id.lay_spinner);

        dashboard_bar = (ImageView) findViewById(R.id.dashboard_bar);
        investment_bar = (ImageView) findViewById(R.id.investment_bar);
        explore_bar = (ImageView) findViewById(R.id.explore_bar);
        goal_track_bar = (ImageView) findViewById(R.id.goal_track_bar);
        account_bar = (ImageView) findViewById(R.id.account_bar);

        img_dashboard = (ImageView) findViewById(R.id.img_dashboard);
        img_investment = (ImageView) findViewById(R.id.img_investment);
        //  img_explore_circle = (ImageView) findViewById(R.id.img_explore_circle);
        //  img_explore = (ImageView) findViewById(R.id.img_explore);
        img_goal_track = (ImageView) findViewById(R.id.img_goal_track);
        img_account = (ImageView) findViewById(R.id.img_account);

        notification_icon=(ImageView)findViewById(R.id.notification_icon);
        user_image=(ImageView)findViewById(R.id.user_image);

        user_name=(TextView)findViewById(R.id.user_name);




        dashboard_bar.setBackgroundResource(R.drawable.bar_selected);
        investment_bar.setBackgroundResource(0);
        explore_bar.setBackgroundResource(0);
        goal_track_bar.setBackgroundResource(0);
        account_bar.setBackgroundResource(0);

        img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenish));
        tv_dashboard.setTextColor(getResources().getColor(R.color.colorGreenish));

        img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
        tv_investment.setTextColor(getResources().getColor(R.color.colorWhite));

        img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
        tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorWhite));

        img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
        tv_account.setTextColor(getResources().getColor(R.color.colorWhite));



        DashBoardPagerAdapter mainActivityViewPagerAdapter = new DashBoardPagerAdapter(getSupportFragmentManager());
        final ViewPager main_view_pager = (ViewPager) findViewById(R.id.main_view_pager);
        main_view_pager.setAdapter(mainActivityViewPagerAdapter);
        main_view_pager.setOffscreenPageLimit(0);

       /* main_view_pager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setRotationY(position *-30);
            }
        });*/


        final View touchView = findViewById(R.id.main_view_pager);
        touchView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                return true;
            }
        });


        main_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                toggle(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });





        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "sorry!no action is set yet", Toast.LENGTH_SHORT).show();

            }
        });



        relative_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_view_pager.setCurrentItem(0, true);
            }
        });

        relative_investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_view_pager.setCurrentItem(1, true);
            }
        });

        relative_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                main_view_pager.setCurrentItem(2, true);

            }
        });

        relative_goal_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_view_pager.setCurrentItem(3, true);
            }
        });


        relative_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                main_view_pager.setCurrentItem(4, true);

            }
        });


        /*Glide.with(DashboardActivity.this).load(SharedPreferenceManager.getImagePath(DashboardActivity.this)).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                System.out.println("onException" + e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                System.out.println("onResourceReady:fooditem " + resource);
                return false;
            }
        }).into(user_image);*/


        Glide.with(DashboardActivity.this).load(SharedPreferenceManager.getImagePath(DashboardActivity.this)).asBitmap().centerCrop().into(new BitmapImageViewTarget(user_image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(DashboardActivity.this.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                user_image.setImageDrawable(circularBitmapDrawable);
            }
        });

        user_name.setText("Welcome"+" "+SharedPreferenceManager.getUserName(this));

        ServerResultHandler serverResultHandler  = new ServerResultHandler();
        serverResultHandler.setContext(DashboardActivity.this);
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getDasBoardInfo(SharedPreferenceManager.getClientCode(this),this);

        //set full screen color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            Window window = DashboardActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(DashboardActivity.this, R.color.backgroundcolor));

        }
    }

    public void toggle(int position) {

        switch (position) {
            case 0:
                dashboard_bar.setBackgroundResource(R.drawable.bar_selected);
                investment_bar.setBackgroundResource(0);
                explore_bar.setBackgroundResource(0);
                goal_track_bar.setBackgroundResource(0);
                account_bar.setBackgroundResource(0);

                img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenish));
                tv_dashboard.setTextColor(getResources().getColor(R.color.colorGreenish));

                img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_investment.setTextColor(getResources().getColor(R.color.colorWhite));

                img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorWhite));

                img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_account.setTextColor(getResources().getColor(R.color.colorWhite));


                top_lay.setVisibility(View.VISIBLE);

                user_name.setVisibility(View.VISIBLE);
                user_image.setVisibility(View.VISIBLE);
                lay_spinner.setVisibility(View.GONE);



                break;

            case 1:
                investment_bar.setBackgroundResource(R.drawable.bar_selected);
                dashboard_bar.setBackgroundResource(0);
                explore_bar.setBackgroundResource(0);
                goal_track_bar.setBackgroundResource(0);
                account_bar.setBackgroundResource(0);



                img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_dashboard.setTextColor(getResources().getColor(R.color.colorWhite));

                img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenish));
                tv_investment.setTextColor(getResources().getColor(R.color.colorGreenish));

                img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorWhite));

                img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_account.setTextColor(getResources().getColor(R.color.colorWhite));




                top_lay.setVisibility(View.VISIBLE);

                user_name.setVisibility(View.GONE);
                user_image.setVisibility(View.GONE);
                lay_spinner.setVisibility(View.VISIBLE);
                break;

            case 2:
                explore_bar.setBackgroundResource(R.drawable.bar_selected);
                dashboard_bar.setBackgroundResource(0);
                investment_bar.setBackgroundResource(0);
                goal_track_bar.setBackgroundResource(0);
                account_bar.setBackgroundResource(0);




                img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_dashboard.setTextColor(getResources().getColor(R.color.colorWhite));

                img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_investment.setTextColor(getResources().getColor(R.color.colorWhite));

                img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorWhite));

                img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_account.setTextColor(getResources().getColor(R.color.colorWhite));



                top_lay.setVisibility(View.VISIBLE);

                user_name.setVisibility(View.VISIBLE);
                user_image.setVisibility(View.VISIBLE);
                lay_spinner.setVisibility(View.GONE);
                break;

            case 3:
                goal_track_bar.setBackgroundResource(R.drawable.bar_selected);
                dashboard_bar.setBackgroundResource(0);
                investment_bar.setBackgroundResource(0);
                explore_bar.setBackgroundResource(0);
                account_bar.setBackgroundResource(0);


                img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_dashboard.setTextColor(getResources().getColor(R.color.colorWhite));

                img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_investment.setTextColor(getResources().getColor(R.color.colorWhite));

                img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenish));
                tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorGreenish));

                img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_account.setTextColor(getResources().getColor(R.color.colorWhite));




                top_lay.setVisibility(View.VISIBLE);

                user_name.setVisibility(View.VISIBLE);
                user_image.setVisibility(View.VISIBLE);
                lay_spinner.setVisibility(View.GONE);

                break;

            case 4:
                account_bar.setBackgroundResource(R.drawable.bar_selected);
                dashboard_bar.setBackgroundResource(0);
                investment_bar.setBackgroundResource(0);
                explore_bar.setBackgroundResource(0);
                goal_track_bar.setBackgroundResource(0);


                img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_dashboard.setTextColor(getResources().getColor(R.color.colorWhite));

                img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_investment.setTextColor(getResources().getColor(R.color.colorWhite));

                img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorWhite));

                img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenish));
                tv_account.setTextColor(getResources().getColor(R.color.colorGreenish));



                top_lay.setVisibility(View.GONE);
                break;


            default:
                dashboard_bar.setBackgroundResource(R.drawable.bar_selected);
                investment_bar.setBackgroundResource(0);
                explore_bar.setBackgroundResource(0);
                goal_track_bar.setBackgroundResource(0);
                account_bar.setBackgroundResource(0);

                img_dashboard.setColorFilter(ContextCompat.getColor(this, R.color.colorGreenish));
                tv_dashboard.setTextColor(getResources().getColor(R.color.colorGreenish));

                img_investment.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_investment.setTextColor(getResources().getColor(R.color.colorWhite));

                img_goal_track.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_goal_tracker.setTextColor(getResources().getColor(R.color.colorWhite));

                img_account.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite));
                tv_account.setTextColor(getResources().getColor(R.color.colorWhite));

                top_lay.setVisibility(View.VISIBLE);

                user_name.setVisibility(View.VISIBLE);
                user_image.setVisibility(View.VISIBLE);
                lay_spinner.setVisibility(View.GONE);
                break;


        }


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



        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {

                DashBoardModel dashBoardModel = (DashBoardModel) message;




        }

        @Override
        public void onError(Object message) {
            System.out.println("eror: "+message);
        }
    }


}
