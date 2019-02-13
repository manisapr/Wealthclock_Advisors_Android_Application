package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Our_Assessment_Save_Tax extends Fragment {
    TextView tv1,tv2;



    Button proceed;



    String number;



    public Fragment_Our_Assessment_Save_Tax() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_fragment__our__assessment__save__tax, container, false);

        proceed = view.findViewById(R.id.proceed);



        tv1=view.findViewById(R.id.tv1);



        tv2=view.findViewById(R.id.tv2);







        Bundle arguments = getArguments();



        String desired_string = arguments.getString("custom2");



        String desired_amount= arguments.getString("custom1");



        tv1.setText(desired_string);



        //tv2.setText("₹ "+desired_amount);

        int valu11=new Integer(desired_amount).intValue();

        tv2.setText(desired_amount);

        if (valu11>0 && valu11<=150000)

        {

            proceed.setEnabled(true);

        }

        else

        {

            proceed.setEnabled(false);

            Toast.makeText(getActivity(),"Please Enter Amount Less Than ₹ 150000",Toast.LENGTH_LONG).show();



            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment_Step_One_Save_Tax taxSavingMoney_save_tax = new Fragment_Step_One_Save_Tax();

            fragmentTransaction.replace(R.id.frag, taxSavingMoney_save_tax , "taxSavingMoney_save_tax ");

            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

            fragmentTransaction.commit();

        }









        proceed.setOnClickListener(new View.OnClickListener() {



            @Override



            public void onClick(View v) {



                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();



                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



                Fragment_Invest_Angle_Save_Tax step_four_save_tax = new Fragment_Invest_Angle_Save_Tax();


                String value=tv2.getText().toString();



                Bundle arguments = new Bundle();



                arguments.putString("custom", value);



                step_four_save_tax.setArguments(arguments);







                fragmentTransaction.replace(R.id.frag, step_four_save_tax, "Fragment_Step_Four_Save_Tax");



                fragmentTransaction.addToBackStack("Fragment_Step_Four_Save_Tax");



                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);



                fragmentTransaction.commit();



            }



        });






        return view;
    }

}
