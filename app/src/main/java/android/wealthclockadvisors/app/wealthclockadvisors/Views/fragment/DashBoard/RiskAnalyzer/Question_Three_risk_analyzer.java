package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.RiskAnalyzer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Question_Three_risk_analyzer extends Fragment implements View.OnClickListener {
    Button previous,next;

    TextView option1,option2,option3,option4,option5;

    LinearLayout linear1,linear2,linear3,linear4,linear5;

    //public static int month=0;

    double sum=0;

    public Question_Three_risk_analyzer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question__three_risk_analyzer, container, false);

        next=view.findViewById(R.id.next);

        previous=view.findViewById(R.id.previous);





        option1=view.findViewById(R.id.option1);

        option2=view.findViewById(R.id.option2);

        option3=view.findViewById(R.id.option3);

        option4=view.findViewById(R.id.option4);

        option5=view.findViewById(R.id.option5);

        linear1=view.findViewById(R.id.linear1);

        linear2=view.findViewById(R.id.linear2);

        linear3=view.findViewById(R.id.linear3);

        linear4=view.findViewById(R.id.linear4);

        linear5=view.findViewById(R.id.linear5);





        next.setOnClickListener(this);

        previous.setOnClickListener(this);

        option1.setOnClickListener(this);

        option2.setOnClickListener(this);

        option3.setOnClickListener(this);

        option4.setOnClickListener(this);

        option5.setOnClickListener(this);



        linear1.setOnClickListener(this);

        linear2.setOnClickListener(this);

        linear3.setOnClickListener(this);

        linear4.setOnClickListener(this);

        linear5.setOnClickListener(this);

        next.setEnabled(false);
        return view;
    }


    @Override

    public void onClick(View v) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {

            case R.id.next:

                Question_Four_risk_analyzer question_four_risk_analyzer = new Question_Four_risk_analyzer();



                if (option1.isSelected())

                {

                    //month=10;

                    sum=10+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option2.isSelected())

                {

                    //month=8;

                    sum=8+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option3.isSelected())

                {

                    //month=6;

                    sum=6+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option4.isSelected())

                {

                    //month=4;

                    sum=4+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option5.isSelected())

                {

                    //month=2;

                    sum=2+Utility.getResult();

                    Utility.setResult(sum);

                }





                fragmentTransaction.replace(R.id.frag, question_four_risk_analyzer, "question_four_risk_analyzer");

                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();

                break;

            case R.id.previous:

                Question_Two_risk_analyzer question_two_risk_analyzer = new Question_Two_risk_analyzer();



                fragmentTransaction.replace(R.id.frag, question_two_risk_analyzer, "question_two_risk_analyzer");

                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();

                break;

            case R.id.option1:

                if (!v.isSelected()) {

                    //tv1.setBackgroundColor(Color.GREEN);

                    linear1.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();

                    next.setEnabled(false);

                    v.setSelected(false);

                }

                break;

            case R.id.option2:

                if (!v.isSelected()) {

                    //tv1.setBackgroundColor(Color.GREEN);

                    linear2.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();

                    next.setEnabled(false);

                    v.setSelected(false);

                }

                break;

            case R.id.option3:

                if (!v.isSelected()) {

                    //tv1.setBackgroundColor(Color.GREEN);

                    linear3.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();

                    next.setEnabled(false);

                    v.setSelected(false);

                }

                break;

            case R.id.option4:

                if (!v.isSelected()) {

                    //tv1.setBackgroundColor(Color.GREEN);

                    linear4.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();

                    next.setEnabled(false);

                    v.setSelected(false);

                }

                break;

            case R.id.option5:

                if (!v.isSelected()) {

                    //tv1.setBackgroundColor(Color.GREEN);

                    linear5.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    linear5.setBackgroundColor(getResources().getColor(R.color.back));

                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();

                    next.setEnabled(false);

                    v.setSelected(false);

                }

                break;

        }

    }

}
