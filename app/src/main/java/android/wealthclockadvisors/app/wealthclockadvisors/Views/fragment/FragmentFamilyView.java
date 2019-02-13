package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.FundDetailsActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.FamilyViewAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.PortfolioAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.model.InvestMentDetailsModel;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFamilyView extends Fragment implements FamilyViewAdapter.iFoodItemAdapterClickListener{
    private RecyclerView _familyMemberRecycler;
    private FamilyViewAdapter _FamilyViewAdapter;
    private ArrayList<InvestMentDetailsModel> arrayList;
    private TextView _netInvestAmount,_currentMarketValue,_currentHoldings;
    private String investmentDetails [] = {"ee","22","ssd"};
    ArrayList<InvestMentDetailsModel> familyList=new ArrayList<>();
    PortfolioAdapter _portfolioAdapter;
    private RecyclerView _recycle_scheme_name;
    private LinearLayout _placeholder;
    private KProgressHUD hud;
    String ccode= " ";

    public FragmentFamilyView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_family_view, container, false);
        _familyMemberRecycler = view.findViewById(R.id.familyMemberRecycler);
        _netInvestAmount = view.findViewById(R.id.netInvestAmount);
        _currentMarketValue = view.findViewById(R.id.currentMarketValue);
        _recycle_scheme_name = view.findViewById(R.id.recycle_scheme_name);
        _currentHoldings = view.findViewById(R.id.currentHoldings);
        _placeholder = view.findViewById(R.id.placeholder);
        _placeholder.setVisibility(View.VISIBLE);
        _recycle_scheme_name.setVisibility(View.GONE);
        arrayList = new ArrayList<>();

        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();

        setRecyclerView();
        setRecyclerViewForFamilyDetails();

        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getFamilyDetails("",getContext());


        return view;
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        _FamilyViewAdapter =new FamilyViewAdapter(getContext(),arrayList,this);
        _familyMemberRecycler.setHasFixedSize(true);
        _familyMemberRecycler.smoothScrollToPosition(0);
        _familyMemberRecycler.setLayoutManager(mLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(_familyMemberRecycler);
        _familyMemberRecycler.setAdapter(_FamilyViewAdapter);
    }


    private void setRecyclerViewForFamilyDetails() {
        _portfolioAdapter =new PortfolioAdapter(getContext(),familyList);
        _recycle_scheme_name.setAdapter(_portfolioAdapter);
        _recycle_scheme_name.setHasFixedSize(true);
        _recycle_scheme_name.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setFoodItemOnclick(InvestMentDetailsModel kitchenImageResult, int position) {
        System.out.println("FragmentFamilyView | setFoodItemOnclick:- "+kitchenImageResult.getClientCode());
        _currentHoldings.setText("Current Holdings:- "+kitchenImageResult.getName());
        _currentHoldings.setTextColor(Color.parseColor("#13a097"));

        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();
        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        ccode = kitchenImageResult.getClientCode();
        UserHandler.getInstance().getPortfolioData(kitchenImageResult.getClientCode(),getContext());
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
            hud.dismiss();
            if (operation_flag.equalsIgnoreCase("getFamilyDetails")) {
                ArrayList<InvestMentDetailsModel> investMentDetailsModelArrayList = (ArrayList<InvestMentDetailsModel>) message;
                arrayList.addAll(investMentDetailsModelArrayList);
                _familyMemberRecycler.smoothScrollToPosition(arrayList.size());
                _FamilyViewAdapter.setfund_list(arrayList);

                investmentDetails = (String[]) messsage1;
                _netInvestAmount.setText(investmentDetails[0]);
                _currentMarketValue.setText(investmentDetails[1]);
            }

            if (operation_flag.equalsIgnoreCase("getPortfolioData"))
            {
                familyList.clear();
                ArrayList<InvestMentDetailsModel> fundlist  = (ArrayList<InvestMentDetailsModel>) message2;
                familyList.addAll(fundlist);
                _portfolioAdapter.setfund_listwithclient(familyList,ccode);
                _placeholder.setVisibility(View.GONE);
                _recycle_scheme_name.setVisibility(View.VISIBLE);

            }


        }

        @Override
        public void onError(Object message) {

        }
    }



}
