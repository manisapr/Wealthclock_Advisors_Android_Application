package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.iisendPlanYourGoalType;
import android.widget.RelativeLayout;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGoalTrack extends Fragment {
private RelativeLayout _lay_personal,_lay_buy_home,_lay_buy_car,_lay_education_inner,_lay_vacation_inner,_laywedding,_layretirement;
private iisendPlanYourGoalType   _iisendPlanYourGoalType;

    public iisendPlanYourGoalType get_iisendPlanYourGoalType() {
        return _iisendPlanYourGoalType;
    }

    public void set_iisendPlanYourGoalType(iisendPlanYourGoalType _iisendPlanYourGoalType) {
        this._iisendPlanYourGoalType = _iisendPlanYourGoalType;
    }

    public FragmentGoalTrack() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_goal_track, container, false);
        _lay_personal = view.findViewById(R.id.lay_personal);
        _lay_buy_home = view.findViewById(R.id.lay_buy_home);
        _lay_buy_car=view.findViewById(R.id.lay_buy_car);
        _lay_education_inner=view.findViewById(R.id.lay_education_inner);
        _lay_vacation_inner=view.findViewById(R.id.lay_vacation_inner);
        _laywedding  = view.findViewById(R.id.lay_wedding_inner);
        _layretirement  = view.findViewById(R.id.lay_retirement);

        _lay_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","personal");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();
                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "FragmentInsertAmountGoalPlanner");
                fragmentTransaction.addToBackStack("FragmentInsertAmountGoalPlanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });

        _lay_buy_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","Home");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();
                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "FragmentInsertYearGoalPlanner");
                fragmentTransaction.addToBackStack("FragmentInsertYearGoalPlanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        _lay_buy_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","Car");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();

                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "Fragment_your_plan_goalplanner");
                fragmentTransaction.addToBackStack("Fragment_your_plan_goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        _lay_education_inner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","Education");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();
                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "FragmentFundDistribution_Goalplanner");
                fragmentTransaction.addToBackStack("FragmentFundDistribution_Goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        _lay_vacation_inner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","Vacation");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();
                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.addToBackStack("Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });

        _laywedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","Wedding");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();
                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.addToBackStack("Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });

        _layretirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("type","Retirement");
                FragmentInsertAmountGoalPlanner quickPurchaseFragment = new FragmentInsertAmountGoalPlanner();
                quickPurchaseFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.addToBackStack("Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}
