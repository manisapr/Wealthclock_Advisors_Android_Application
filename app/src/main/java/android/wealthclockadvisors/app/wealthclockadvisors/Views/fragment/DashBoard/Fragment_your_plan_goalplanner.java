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

import java.util.Calendar;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_your_plan_goalplanner extends Fragment {

    private String _amount = " ";
    private String _year = " ";
    private TextView _goalTargetValue,_monthlyInvest,_time;
    private Button _nextButton;
    private String _typ=" ";

    public Fragment_your_plan_goalplanner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal_planner, container, false);
        _goalTargetValue = view.findViewById(R.id.goalTargetValue);
        _monthlyInvest = view.findViewById(R.id.monthlyInvest);
        _nextButton = view.findViewById(R.id.nextButton);
        _time = view.findViewById(R.id.time);

        Bundle arguments = getArguments();
        //we have to this code ..this the right code cooment for upload
        if (arguments.getString("amount")!=null && arguments.get("year")!=null)
        {
            String desired_string = arguments.getString("amount");
            String radioYears = arguments.getString("year");
            String radioYears1 = arguments.getString("type");
            _typ = radioYears1;
            _time.setText(radioYears+" year(s)");
            _time.setTextSize(15f);

            //Target Amount *(1+Inflation Rate(7.5%)^number of years )
            _amount = desired_string;
            _year = radioYears;
            double a = Float.parseFloat(_amount);
            double b = Float.parseFloat(_year);
            double cdd = a*(Math.pow(1.08,b));
            System.out.println("cddd value:- "+cdd);
            _goalTargetValue.setText(String.format("%.2f", cdd));
            float y = Calendar.getInstance().get(Calendar.YEAR);
            double c =  b-y;

            double g = ((Math.pow((1 + ((12.00 / 100) / 12)), (b * 12))) - 1);
            double g1 = (((12.00 / 100) / 12.00));

            //((((Math.pow((1 + ((i / 100) / 12)), (n * 12))) - 1) / (((i / 100) / 12))) * (1 + (((i / 100) / 12))));
            double total1=Math.pow((1+((7.5)/100)),b);
            double  cd =a * total1;
            String Result = String.valueOf((cd / ((g / g1) * (1 + (((12.00 / 100) / 12.00))))));
            String res = String.valueOf(Result);
            double result = Double.parseDouble(Result);
            _amount=String.format("%.2f", result);
            _monthlyInvest.setText(String.format("%.2f", result));
            //System.out.println("resulttttttttttttttttttttt: "+Result + "totalll:- "+total1 +"ggggg:- " + g +"valuess:- "+(Math.pow((1 + ((12 / 100) / 12)), (3 * 12))));
        }

        _nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("amount",_amount);
                bundle.putString("year",_year);
                bundle.putString("type",_typ);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentFundDistribution_Goalplanner fragmentInsertAmountGoalPlanner = new FragmentFundDistribution_Goalplanner();
                fragmentInsertAmountGoalPlanner.setArguments(bundle);
                fragmentTransaction.replace(R.id.frag, fragmentInsertAmountGoalPlanner, "FragmentFundDistribution_Goalplanner");
                fragmentTransaction.addToBackStack("FragmentFundDistribution_Goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}
