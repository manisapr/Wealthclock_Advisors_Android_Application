package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.RiskAnalyzer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.DashboardActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Question_One_risk_analyzer extends Fragment implements View.OnClickListener {

    Button next,previous;
    TextView option1,option2,option3,option4,option5,option6,option7,option8,option9,option10;
    LinearLayout linear1,linear2,linear3,linear4,linear5,linear6,linear7,linear8,linear9,linear10;
    public Question_One_risk_analyzer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question__one_risk_analyzer, container, false);

        next=view.findViewById(R.id.next);
        previous=view.findViewById(R.id.previous);


        option1=view.findViewById(R.id.option1);
        option2=view.findViewById(R.id.option2);
        option3=view.findViewById(R.id.option3);
        option4=view.findViewById(R.id.option4);
        option5=view.findViewById(R.id.option5);
        option6=view.findViewById(R.id.option6);
        option7=view.findViewById(R.id.option7);
        option8=view.findViewById(R.id.option8);
        option9=view.findViewById(R.id.option9);
        option10=view.findViewById(R.id.option10);




        linear1=view.findViewById(R.id.linear1);
        linear2=view.findViewById(R.id.linear2);
        linear3=view.findViewById(R.id.linear3);
        linear4=view.findViewById(R.id.linear4);
        linear5=view.findViewById(R.id.linear5);
        linear6=view.findViewById(R.id.linear6);
        linear7=view.findViewById(R.id.linear7);
        linear8=view.findViewById(R.id.linear8);
        linear9=view.findViewById(R.id.linear9);
        linear10=view.findViewById(R.id.linear10);


        next.setOnClickListener(this);
        previous.setOnClickListener(this);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);
        option7.setOnClickListener(this);
        option8.setOnClickListener(this);
        option9.setOnClickListener(this);
        option10.setOnClickListener(this);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        linear5.setOnClickListener(this);
        linear6.setOnClickListener(this);
        linear7.setOnClickListener(this);
        linear8.setOnClickListener(this);
        linear9.setOnClickListener(this);
        linear10.setOnClickListener(this);

        next.setEnabled(false);

        return view;
    }

        @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.next:
                Question_Two_risk_analyzer question_two_risk_analyzer = new Question_Two_risk_analyzer();

                if (option1.isSelected())
                {
                    //age=10;
                    Utility.setResult(10);
                }
                else if (option2.isSelected())
                {
                    //age=9;
                    Utility.setResult(9);
                }
                else if (option3.isSelected())
                {
                    //age=8;
                    Utility.setResult(8);
                }
                else if (option4.isSelected())
                {
                    //age=7;
                    Utility.setResult(7);
                }
                else if (option5.isSelected())
                {
                    //age=6;
                    Utility.setResult(6);
                }
                else if (option6.isSelected())
                {
                    //age=5;
                    Utility.setResult(5);
                }
                else if (option7.isSelected())
                {
                    //age=4;
                    Utility.setResult(4);
                }
                else if (option8.isSelected())
                {
                    //age=3;
                    Utility.setResult(3);
                }
                else if (option9.isSelected())
                {
                    //age=2;
                    Utility.setResult(2);
                }
                else if (option10.isSelected())
                {
                    //age=1;
                    Utility.setResult(1);
                }

                fragmentTransaction.replace(R.id.frag, question_two_risk_analyzer, "question_two_risk_analyzer");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;
            case R.id.previous:
                Risk_Analyzer_First risk_analyzer_first = new Risk_Analyzer_First();
                fragmentTransaction.replace(R.id.frag, risk_analyzer_first, "risk_analyzer_first");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                //Intent intent=new Intent(getContext(),DashboardActivity.class);
                //startActivity(intent);
                break;
            case R.id.option1:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    Bundle arguments=new Bundle();

                    linear1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option2:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option3:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option4:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option5:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option6:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option7:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));;
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option8:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option9:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;
            case R.id.option10:
                if (!v.isSelected()){
                    //tv1.setBackgroundColor(Color.GREEN);
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    next.setEnabled(true);
                    v.setSelected(true);
                }
                else
                {
                    linear1.setBackgroundColor(getResources().getColor(R.color.back));
                    linear2.setBackgroundColor(getResources().getColor(R.color.back));
                    linear3.setBackgroundColor(getResources().getColor(R.color.back));
                    linear4.setBackgroundColor(getResources().getColor(R.color.back));
                    linear5.setBackgroundColor(getResources().getColor(R.color.back));
                    linear6.setBackgroundColor(getResources().getColor(R.color.back));
                    linear7.setBackgroundColor(getResources().getColor(R.color.back));
                    linear8.setBackgroundColor(getResources().getColor(R.color.back));
                    linear9.setBackgroundColor(getResources().getColor(R.color.back));
                    linear10.setBackgroundColor(getResources().getColor(R.color.back));
                    Toast.makeText(getActivity(),"Please select one option",Toast.LENGTH_SHORT).show();
                    next.setEnabled(false);
                    v.setSelected(false);
                }
                break;


//            case R.id.linearlayout:
//                linearlayout.setBackgroundColor(getResources().getColor(R.color.red));
//                //tv1.setTextColor(R.color.red);
//                break;
        }
    }

}
