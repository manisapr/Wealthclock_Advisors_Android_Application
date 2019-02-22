package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.ImageAdapter;
import android.widget.RelativeLayout;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPage_Insurance extends Fragment implements View.OnClickListener {

    RelativeLayout terminsurance,lifeinsurance,motorinsurance,healthinsurance,generalinsurance;
    ViewPager imageslider;
    public FirstPage_Insurance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_page__insurance, container, false);

        terminsurance=view.findViewById(R.id.terminsurance);
        lifeinsurance=view.findViewById(R.id.lifeinsurance);
        motorinsurance=view.findViewById(R.id.motorinsurance);
        healthinsurance=view.findViewById(R.id.healthinsurance);
        generalinsurance=view.findViewById(R.id.generalinsurance);
        imageslider=view.findViewById(R.id.imageslider);
        terminsurance.setOnClickListener(this);
        lifeinsurance.setOnClickListener(this);
        motorinsurance.setOnClickListener(this);
        healthinsurance.setOnClickListener(this);
        generalinsurance.setOnClickListener(this);
        ImageAdapter adapterView=new ImageAdapter(getActivity());
        imageslider.setAdapter(adapterView);
        imageslider.setPadding(10,10,10,10);
        imageslider.setPageMargin(50);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.terminsurance:
                Intent intent1 = new Intent(getActivity(), Second_Page_Insurance.class);
                startActivity(intent1);
                break;

            case R.id.lifeinsurance:
                Intent intent2 = new Intent(getActivity(), Second_Page_Insurance.class);
                startActivity(intent2);
                break;

            case R.id.motorinsurance:
                Intent intent3 = new Intent(getActivity(), Second_Page_Insurance.class);
                startActivity(intent3);

                break;

            case R.id.healthinsurance:
                Intent intent4=new Intent(getActivity(),Second_Page_Insurance.class);
                startActivity(intent4);

            case R.id.generalinsurance:
                Intent intent5 = new Intent(getActivity(), Second_Page_Insurance.class);
                startActivity(intent5);

                break;

        }

    }
}
