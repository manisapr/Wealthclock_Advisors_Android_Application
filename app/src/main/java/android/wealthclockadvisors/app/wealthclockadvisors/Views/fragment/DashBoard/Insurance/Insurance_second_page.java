package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Insurance_second_page extends Fragment implements View.OnClickListener{


    public Insurance_second_page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_insurance_second_page, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
