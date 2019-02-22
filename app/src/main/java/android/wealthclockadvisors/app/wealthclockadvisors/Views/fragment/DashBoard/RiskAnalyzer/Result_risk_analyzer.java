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
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Result_risk_analyzer extends Fragment implements View.OnClickListener {

    TextView tv1,tv2;

    Button previous,next;

    String riskanalyzer_type=" ";

    String Sentence=" ";

    String finalres=" ";
    public Result_risk_analyzer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_risk_analyzer, container, false);

        tv1=view.findViewById(R.id.tv1);

        tv2=view.findViewById(R.id.tv2);



        previous=view.findViewById(R.id.previous);

        next=view.findViewById(R.id.next);







        previous.setOnClickListener(this);

        next.setOnClickListener(this);



        double avg= Utility.getResult();

        if (avg <= 10.0 && avg >= 9.0)

        {

            finalres = "Aggressive";

            riskanalyzer_type="Aggressive1";

            Sentence="Your Risk Profile is Aggressive. You are an investor who is comfortable with a high volatility and high level of risk in order to achieve relatively higher returns over long term. Your objective is to accumulate assets over long term by primarily investing in growth assets. As an aggressive investor, you might expect your portfolio to be allocated 100% in growth assets.";

        }

        else if (avg <= 8.9 && avg >= 7.5)

        {

            finalres = "Aggressive";

            riskanalyzer_type="Aggressive2";

            Sentence="Your Risk Profile is Aggressive. You are an investor who is comfortable with a high volatility and high level of risk in order to achieve relatively higher returns over long term. Your objective is to accumulate assets over long term by primarily investing in growth assets. As an aggressive investor, you might expect your portfolio to be allocated 100% in growth assets.";

        }

        else if (avg <= 7.4 && avg >= 6.0)

        {

            finalres = "Moderate";

            riskanalyzer_type="Moderate1";

            Sentence="Your Risk Profile is Moderate. You are an investor who would like to invest in both income and growth assets. You will be comfortable with calculated risks to achieve good returns, however, you require an investment strategy that adequately deals with the effects of inflation and tax. As a moderate investor, you might expect your portfolio to be allocated approximately 50% in growth assets, with the remainder in defensive assets and debt mutual funds.";

        }

        else if (avg <= 5.9 && avg >= 4.0) {

            finalres = "Moderate";

            riskanalyzer_type="Moderate2";

            Sentence = "Your Risk Profile is Moderate. You are an investor who would like to invest in both income and growth assets. You will be comfortable with calculated risks to achieve good returns, however, you require an investment strategy that adequately deals with the effects of inflation and tax. As a moderate investor, you might expect your portfolio to be allocated approximately 50% in growth assets, with the remainder in defensive assets and debt mutual funds.";

        }

        else if (avg <= 3.9 && avg >= 2.0)

        {

            finalres = "Conservative";

            riskanalyzer_type="Conservative1";

            Sentence="Your Risk Profile is Conservative. You are an investor who has expectations of low to moderate kind of returns with lower levels of risk in order to preserve your capital. As a conservative investor, you might expect your portfolio to be allocated approximately 25% in growth assets, with the remainder in defensive assets and an allocation to debt product.";

        }

        else if (avg <= 1.9 && avg >= 0.0)

        {

            finalres = "Conservative";

            riskanalyzer_type="Conservative2";

            Sentence="Your Risk Profile is Conservative. You are an investor who has expectations of low to moderate kind of returns with lower levels of risk in order to preserve your capital. As a conservative investor, you might expect your portfolio to be allocated approximately 25% in growth assets, with the remainder in defensive assets and an allocation to debt product.";

        }

        tv1.setText(finalres);

        tv2.setText(Sentence);
        return view;
    }
    @Override

    public void onClick(View v) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {

            case R.id.next:

                Buy_Fund_Risk_Analyzer buy_fund_risk_analyzer = new Buy_Fund_Risk_Analyzer();



                Bundle arguments=new Bundle();

                arguments.putString("custom",riskanalyzer_type);

                buy_fund_risk_analyzer.setArguments(arguments);

                fragmentTransaction.replace(R.id.frag, buy_fund_risk_analyzer, "buy_fund_risk_analyzer");

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();

                break;



            case R.id.previous:

                Question_One_risk_analyzer question_one_risk_analyzer = new Question_One_risk_analyzer();



                fragmentTransaction.replace(R.id.frag, question_one_risk_analyzer, "question_one_risk_analyzer");



                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();

                break;

        }

    }



}




