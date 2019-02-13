package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.FragmentFamilyView;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.SignupFragment;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInvestment extends Fragment {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public FragmentInvestment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //UserHandler.getInstance().getPortfolioData(SharedPreferenceManager.getClientCode(getContext()),getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_investment, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getActivity().getSupportFragmentManager();

        final Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        spinner.setSelection(0, true);
        View v = spinner.getSelectedView();
        ((TextView) v).setTextColor(Color.WHITE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    View v = spinner.getSelectedView();
                    ((TextView) v).setTextColor(Color.WHITE);
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Portfolio signupFragment = new Portfolio();
                    fragmentTransaction.replace(R.id.fragment_container, signupFragment, "Portfolio");
                    fragmentTransaction.addToBackStack("Portfolio");
                    fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.commit();
                }
                else {
                    View v = spinner.getSelectedView();
                    ((TextView) v).setTextColor(Color.WHITE);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new FragmentFamilyView(), "family");
                    fragmentTransaction.commitAllowingStateLoss();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,new Portfolio());
        fragmentTransaction.commitAllowingStateLoss();


    }
}
