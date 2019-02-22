package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class Second_Page_Insurance extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewpager;
    ListView lv;
    TextView tv;
    LinearLayout main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__page__insurance);

        tablayout=(TabLayout)findViewById(R.id.tablayout);
        viewpager=(ViewPager)findViewById(R.id.viewpager);
        main_layout=(LinearLayout)findViewById(R.id.main_layout);
        MyAdapter myadapter=new MyAdapter(getSupportFragmentManager());
        viewpager.setAdapter(myadapter);
        tablayout.setupWithViewPager(viewpager);
        openMainFragment();

    }

    private void openMainFragment() {

        LifeInsurance nextFrag= new LifeInsurance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_layout, nextFrag).commit();

    }

    public class MyAdapter extends FragmentPagerAdapter
    {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position)
            {

                case 0:
                    TermInsurance term=new TermInsurance();
                    return term;

                case 1:

                    LifeInsurance life=new LifeInsurance();
                    return life;

                case 2:

                    MotorInsurance motor=new MotorInsurance();
                    return motor;

                case 3:
                    Health health=new Health();
                    return health;

                case 4:
                    General general=new General();
                    return general;

                default:
                    return null;

            }

        }



        @Override
        public CharSequence getPageTitle(int position) {

            switch (position)
            {
                case 0:
                    return getResources().getString(R.string.Term);
                case 1:
                    return "Life";
                case 2:
                    return "Motor";
                case 3:
                    return "Health";
                case 4:

                    return "Other";

                default:

                    return null;

            }

        }



        @Override

        public int getCount() {
            return 5;

        }

    }

}




