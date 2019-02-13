package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInsertYearGoalPlanner extends Fragment {
    private Croller croller;
    private Button _nextButton;

    String[] displayedValues = {"₹ 1000", "₹ 2500", "₹ 5000", "₹ 10000","₹ 15000"};

     private TextView tv;


    public FragmentInsertYearGoalPlanner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_insert_year_goal_planner, container, false);

        croller=(Croller)view.findViewById(R.id.cr);
        tv=(TextView)view.findViewById(R.id.tv);
        croller.setIndicatorWidth(10);
        croller.setBackCircleColor(Color.parseColor("#85929E"));
        croller.setMainCircleColor(Color.WHITE);
        //croller.setMin(displayedValues.length);

        //croller.setMax(displayedValues.length-1);

        croller.setMin(0);
        croller.setMax(100);
        croller.setStartOffset(45);
        croller.setIsContinuous(false);
        croller.setLabelColor(Color.WHITE);
        croller.setLabel("Amount");
        croller.setLabelSize(30);
        croller.setVisibility(View.VISIBLE);
        croller.setProgressPrimaryColor(Color.parseColor("#0B3C49"));

        croller.setIndicatorColor(Color.parseColor("#82E0AA"));
        croller.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));


        croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {

            }

            @Override
            public void onStartTrackingTouch(Croller croller) {
                int min=croller.getMin();

                //Toast.makeText(getContext(), "Amount="+min, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                int max=croller.getProgress();
                tv.setText("₹ "+max);

                //Toast.makeText(getContext(), "Amount="+max, Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment

        return view;

    }
    }

