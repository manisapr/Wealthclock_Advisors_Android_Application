package android.wealthclockadvisors.app.wealthclockadvisors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.DashboardActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.LoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentAccount;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentDashboard;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Refer_and_earn;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.DashBoardPagerAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.DashBoardModel;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView dashboard_bar, investment_bar, explore_bar, goal_track_bar, account_bar;
    ImageView img_dashboard, img_investment, img_goal_track, img_account;
    ImageView notification_icon,user_image,_profile_image;
    RelativeLayout relative_dashboard, relative_investment, relative_explore, relative_goal_track, relative_account;
    TextView user_name,_tv_name,_clientid;
    Context context;
    RelativeLayout top_lay,lay_spinner;

    TextView tv_dashboard,tv_investment,tv_goal_tracker,tv_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

        _tv_name = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        _profile_image=navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        _clientid = navigationView.getHeaderView(0).findViewById(R.id.clientid);



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

        user_name.setText("Welcome"+" "+SharedPreferenceManager.getUserName(this));
        ServerResultHandler serverResultHandler  = new ServerResultHandler();
        serverResultHandler.setContext(Drawer.this);
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getDasBoardInfo(SharedPreferenceManager.getClientCode(this),this);
        //UserHandler.getInstance().sendLoginData(SharedPreferenceManager.getUserEmail(Drawer.this),SharedPreferenceManager.getUserPassword(Drawer.this),Drawer.this);

        //set full screen color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            Window window = Drawer.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(Drawer.this, R.color.backgroundcolor));

        }

        //navigationView.setItemTextColor(ColorStateList.valueOf(Color.GREEN));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.drawer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentAccount fragmentAccount = new FragmentAccount();
            fragmentTransaction.add(R.id.frag, fragmentAccount, "fragmentAccount");
            fragmentTransaction.addToBackStack("fragmentAccount");
            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
            // Handle the camera action
        }
        else if (id==R.id.nav_dashboard)
        {
//            Intent intent = new Intent(this, DashboardActivity.class);
//            startActivity(intent);
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.isDrawerOpen(GravityCompat.START);
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            FragmentDashboard fragmentDashboard = new FragmentDashboard();
//            fragmentTransaction.add(R.id.frag, fragmentDashboard, "fragmentDashboard");
//            //fragmentTransaction.addToBackStack("fragmentDashboard");
//            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            fragmentTransaction.commit();


            Intent intent = new Intent(this, Drawer.class);
            startActivity(intent);
            finish();
        }
            else if (id == R.id.nav_gallery) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Refer_and_earn refer_and_earn = new Refer_and_earn();
            fragmentTransaction.add(R.id.frag, refer_and_earn, "refer_and_earn");
            fragmentTransaction.addToBackStack("refer_and_earn");
            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {


        } else if (id == R.id.nav_manage) {
            SharedPreferences sharedPreferences = null ;
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(this,LoginActivity.class);
            //getActivity().getSupportFragmentManager().popBackStackImmediate();
            Utility.deleteCache(this);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(Drawer.this).load(SharedPreferenceManager.getImagePath(Drawer.this)).asBitmap().centerCrop().into(new BitmapImageViewTarget(user_image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(Drawer.this.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                _profile_image.setImageDrawable(circularBitmapDrawable);
                user_image.setImageDrawable(circularBitmapDrawable);
            }
        });
       /* Glide.with(Drawer.this).load(SharedPreferenceManager.getImagePath(Drawer.this)).asBitmap().centerCrop().into(new BitmapImageViewTarget(user_image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(Drawer.this.getResources(), resource);
                circularBitmapDrawable.setCircular(true);

            }
        });*/
        _tv_name.setText(SharedPreferenceManager.getUserName(Drawer.this));
        _clientid.setText("Wealthclock Id: "+SharedPreferenceManager.getClientCode(this));

    }
}
