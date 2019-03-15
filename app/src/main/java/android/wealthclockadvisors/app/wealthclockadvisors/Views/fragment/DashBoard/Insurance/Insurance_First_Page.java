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
public class Insurance_First_Page extends Fragment implements View.OnClickListener{


    public Insurance_First_Page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_insurance__first__page, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
