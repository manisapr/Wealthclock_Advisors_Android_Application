package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


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
public class fragment_fragment_tax_saving_money__save__tax extends Fragment implements View.OnClickListener {


    public fragment_fragment_tax_saving_money__save__tax() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  view = inflater.inflate(R.layout.fragment_fragment_fragment_tax_saving_money__save__tax, container, false);
        Button btn_no=view.findViewById(R.id.btn_no);
        Button btn_yes=view.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm1=getFragmentManager();

        FragmentTransaction ft=fm1.beginTransaction();

        switch (v.getId())

        {

            case R.id.btn_no:
                Fragment_Step_One_Save_Tax obj2=new Fragment_Step_One_Save_Tax();
                ft.replace(R.id.frag,obj2,"Fragment_Step_One_Save_Tax");
                ft.addToBackStack("Fragment_Step_One_Save_Tax");
                ft.commit();

                break;

            case R.id.btn_yes:
                Fragment_Step_Four_Save_Tax obj1=new Fragment_Step_Four_Save_Tax();
                ft.replace(R.id.frag,obj1,"Fragment_Step_Four_Save_Tax");
                ft.addToBackStack("Fragment_Step_Four_Save_Tax");
                ft.commit();

                break;

        }


    }
}
