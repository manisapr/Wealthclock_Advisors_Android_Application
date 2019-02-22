package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.RiskAnalyzer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Risk_Analyzer_First extends Fragment {

    private Button _startassesment;

    public Risk_Analyzer_First() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_risk__analyzer__first, container, false);
        _startassesment = view.findViewById(R.id.startassesment);
        _startassesment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Question_One_risk_analyzer risk_analyzer_first = new Question_One_risk_analyzer();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag, risk_analyzer_first, "risk_analyzer_first");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();

            }
        });
        return view;
    }
}
