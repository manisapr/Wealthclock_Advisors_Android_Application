package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.Tutorial1;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.Tutorial2;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.Tutorial3;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.Tutorial4;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.Tutorial5;

public class TutorialViewPagerAdapter extends FragmentStatePagerAdapter {
    public TutorialViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0 : return  new Tutorial1();
            case 1: return  new Tutorial2();
            case 2:return  new Tutorial3();
            case  3: return  new Tutorial4();
            case 4: return new Tutorial5();
            default: return new Tutorial1();

        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
