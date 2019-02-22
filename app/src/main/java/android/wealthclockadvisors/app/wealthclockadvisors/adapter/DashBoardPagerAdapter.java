package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentAccount;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentContactWhatsapp;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentDashboard;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentExplore;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentGoalTrack;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentInvestment;

public class DashBoardPagerAdapter  extends FragmentStatePagerAdapter {
    public DashBoardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0: return new FragmentDashboard();
            case 1: return  new FragmentInvestment();
            case 2: return new FragmentExplore();
            case 3: return  new FragmentGoalTrack();
            case 4: return new FragmentContactWhatsapp();
            default: return new FragmentDashboard();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }


}
