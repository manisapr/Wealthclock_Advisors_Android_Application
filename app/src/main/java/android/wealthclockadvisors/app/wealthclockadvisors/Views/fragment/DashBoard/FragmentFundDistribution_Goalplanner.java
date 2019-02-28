package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.GoalPlannerAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.iisendPurchaseDetails;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.widget.Button;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFundDistribution_Goalplanner extends Fragment {

    private RecyclerView _fundRecyler;
    private String _amount = " ";
    private String _year = " ";
     private  GoalPlannerAdapter _GoalPlannerAdapter;
    private ArrayList<Top3Funds> _goalFundList;
    private ArrayList<Top3Funds> _folioList;
    private Button _continueButton;
    private iisendPurchaseDetails _iisendPurchaseDetails;
    private int k=0;
    private int l=0;
    private KProgressHUD hud;
    private String _typee = " ";


    


    public iisendPurchaseDetails get_IisendPurchaseDetails() {
        return _iisendPurchaseDetails;
    }

    public void set_IisendPurchaseDetails(iisendPurchaseDetails _IisendPurchaseDetails) {
        this._iisendPurchaseDetails = _IisendPurchaseDetails;
    }

    public FragmentFundDistribution_Goalplanner() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_fragment_fund_distribution__goalplanner, container, false);
        _fundRecyler = view.findViewById(R.id.fundRecyler);
        _continueButton = view.findViewById(R.id.continueButton);
        _goalFundList = new ArrayList<>();
        _folioList = new ArrayList<>();
        l=0;
        k=0;

        _continueButton.setEnabled(false);

        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();

        //UserHandler.getInstance().getAccountDetails(Utility.getEmailaddress(),getContext());

        Bundle arguments = getArguments();
        //we have to this code ..this the right code cooment for upload
        if (arguments.getString("amount")!=null && arguments.get("year")!=null)
        {
            String desired_string = arguments.getString("amount");
            String radioYears = arguments.getString("year");
            String radioYears1 = arguments.getString("type");
            _typee = radioYears1;
            _amount = desired_string;
            _year = radioYears;
        }

        setRecyclerView();

        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        Top3Funds  top3Funds= new Top3Funds();
        top3Funds.setInvestedAmount(_amount);
        top3Funds.setYear(_year);
        UserHandler.getInstance().getFundListInGoalPlanner(top3Funds,getContext());

        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_buy_mutual_fund_goalplanner quickPurchaseFragment = new Fragment_buy_mutual_fund_goalplanner();
                //quickPurchaseFragment.setArguments(bundle);
                quickPurchaseFragment.sendpurchaseDetails(_goalFundList,_folioList,_amount,_year,getContext());
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.addToBackStack("Fragment_buy_mutual_fund_goalplanner");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void setRecyclerView() {
        _GoalPlannerAdapter = new GoalPlannerAdapter(getContext(),_goalFundList);
        _fundRecyler.setAdapter(_GoalPlannerAdapter);
        _fundRecyler.setHasFixedSize(true);
        _fundRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private class ServerResultHandler implements ihttpResultHandler {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            if (operation_flag.equalsIgnoreCase("getFundListInGoalPlanner")) {
                ArrayList<Top3Funds> goalFundList = (ArrayList<Top3Funds>) message;
                _goalFundList.clear();
                _goalFundList.addAll(l,goalFundList);
                l++;
                _GoalPlannerAdapter.notifyDataSetChanged();

                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                for (int j=0;j<goalFundList.size();j++) {
                    Top3Funds fundsfolio = goalFundList.get(j);
                    UserHandler.getInstance().getMultiFundFolioList(fundsfolio, String.valueOf(j),getContext());
                }
            }
            if (operation_flag.equalsIgnoreCase("getMultiFundFolioList"))
            {
                ArrayList<Top3Funds> foliolist = (ArrayList<Top3Funds>) message;
                //System.out.println("successssss in frag:- "+foliolist.size()+k);
                    _folioList.addAll(k,foliolist);
                    k++;
                   /* if (!foliolist.get(0).getSipID().equalsIgnoreCase("null") && foliolist.get(0).getSipID() != null) {
                        System.out.println("foool 1");
                    }


                    if (!foliolist.get(1).getSipID().equalsIgnoreCase("null") && foliolist.get(0).getSipID() != null) {
                        System.out.println("foool 2");

                    }

                }
                else {

                }
                if (foliolist.size()>1) {
                    if (!foliolist.get(2).getSipID().equalsIgnoreCase("null") && foliolist.get(0).getSipID() != null) {
                        System.out.println("foool 3");

                    }*/



                //System.out.println("FragmentFundDistribution_Goalplanner | onSuccess"+foliolist.get(0).getSipID());
                hud.dismiss();
                _continueButton.setEnabled(true);
            }
            /*if (_iisendPurchaseDetails!=null) {
                _iisendPurchaseDetails.sendpurchaseDetails(goalFundList);
            }*/



        }

        @Override
        public void onError(Object message) {
            Toast.makeText(context, "Some error has occured.", Toast.LENGTH_LONG).show();
            _continueButton.setEnabled(true);
        }
    }

}
