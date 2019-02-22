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
public class Question_ten_risk_analyzer extends Fragment implements View.OnClickListener {
    Button previous,next;

    TextView option1,option2,option3,option4;

    LinearLayout linear1,linear2,linear3,linear4;

    //public static int val10=0;

    //public static String riskanalyzer_type=" ";

    //public static String finalres=" ";

    //public static String Sentence=" ";

    double sum=0;

    public Question_ten_risk_analyzer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_ten_risk_analyzer, container, false);

        next=view.findViewById(R.id.next);

        previous=view.findViewById(R.id.previous);





        option1=view.findViewById(R.id.option1);

        option2=view.findViewById(R.id.option2);

        option3=view.findViewById(R.id.option3);

        option4=view.findViewById(R.id.option4);

        linear1=view.findViewById(R.id.linear1);

        linear2=view.findViewById(R.id.linear2);

        linear3=view.findViewById(R.id.linear3);

        linear4=view.findViewById(R.id.linear4);





        next.setOnClickListener(this);

        previous.setOnClickListener(this);

        option1.setOnClickListener(this);

        option2.setOnClickListener(this);

        option3.setOnClickListener(this);

        option4.setOnClickListener(this);



        linear1.setOnClickListener(this);

        linear2.setOnClickListener(this);

        linear3.setOnClickListener(this);

        linear4.setOnClickListener(this);

        next.setEnabled(false);


        return view;
    }


    @Override

    public void onClick(View v) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {

            case R.id.next:

                Result_risk_analyzer result_risk_analyzer = new Result_risk_analyzer();



                if (option1.isSelected())

                {

                    //val10=10;

                    sum=10+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option2.isSelected())

                {

                    //val10=8;

                    sum=8+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option3.isSelected())

                {

                    //val10=6;

                    sum=6+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option4.isSelected())

                {

                    //val10=4;

                    sum=4+Utility.getResult();

                    Utility.setResult(sum);

                }



                double total=sum;

                System.out.println("Sum"+sum);

                double avg=(total/10);

                System.out.println("Average"+avg);

                Utility.setResult(avg);



//                float total;

//                total = age + age2 + month + val4 + val5 + val6 + val7 + val8 + val9 + val10;

//                float avg = (total / 10);





                fragmentTransaction.replace(R.id.frag, result_risk_analyzer, "result_risk_analyzer");

                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();





                //Toast.makeText(getActivity(),"You are a Investor"+" "+finalres,Toast.LENGTH_SHORT).show();



                break;

            case R.id.previous:

                Question_Nine_risk_analyzer question_nine_risk_analyzer = new Question_Nine_risk_analyzer();



              /*  if (option1.isSelected())

                {

                    val10=10;

                    sum=10+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option2.isSelected())

                {

                    val10=8;

                    sum=8+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option3.isSelected())

                {

                    val10=6;

                    sum=6+Utility.getResult();

                    Utility.setResult(sum);

                }

                else if (option4.isSelected())

                {

                    val10=4;

                    sum=4+Utility.getResult();

                    Utility.setResult(sum);

                } */



                fragmentTransaction.replace(R.id.frag, question_nine_risk_analyzer, "question_nine_risk_analyzer");

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

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

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

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

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

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

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

                    next.setEnabled(true);

                    v.setSelected(true);

                } else {

                    linear3.setBackgroundColor(getResources().getColor(R.color.back));

                    linear2.setBackgroundColor(getResources().getColor(R.color.back));

                    linear1.setBackgroundColor(getResources().getColor(R.color.back));

                    linear4.setBackgroundColor(getResources().getColor(R.color.back));

                    Toast.makeText(getActivity(), "Please select one option", Toast.LENGTH_SHORT).show();

                    next.setEnabled(false);

                    v.setSelected(false);

                }

                break;

        }

    }
}