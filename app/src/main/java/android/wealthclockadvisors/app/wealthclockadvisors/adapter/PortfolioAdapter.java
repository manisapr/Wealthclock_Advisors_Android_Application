package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.FundDetailsActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.model.InvestMentDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.Viewholder> {
    private List<InvestMentDetailsModel> filterItemList;
    public Context mcontext;
    private String clientcode="p";



    public PortfolioAdapter(Context context, ArrayList<InvestMentDetailsModel> schemeName) {
        this.filterItemList = schemeName;
        this.mcontext = context;

    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_scheme_items, viewGroup, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final InvestMentDetailsModel menuItem = filterItemList.get(i);
        viewholder._fund_name.setText(menuItem.getFundName());
        viewholder._investAmount.setText(menuItem.getFundInvestAmount());
        viewholder._fundCurrentValue.setText(menuItem.getFundCurrentValue());
        viewholder._fundxirr.setText(menuItem.getFundXirr());

        double netInvest = Double.parseDouble(Utility.get_totalChangeFund());
        double fundInvest = Double.parseDouble(menuItem.getFundInvestAmount());


        double totalinvest = Double.parseDouble(Utility.get_totalinvest());

        double res =  fundInvest/totalinvest;
        double result = res*100;

        String r = String.valueOf(result);
        String f = String.format("%.2f", result);

        viewholder._totalchange.setText(f);
        viewholder._folioNo.setText("Folio no:- "+menuItem.getFolioNo());
        viewholder._layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,FundDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("folio",menuItem.getFolioNo());
                intent.putExtra("schemecode",menuItem.getSchemecode());
                intent.putExtra("clientcode",clientcode);
                mcontext.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return filterItemList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView _fund_name,_totalchange,_investAmount,_fundCurrentValue,_fundxirr,_folioNo;
        private LinearLayout _layout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            _fund_name = itemView.findViewById(R.id.tv_fund_name);
            _totalchange = itemView.findViewById(R.id.totalchange);
            _investAmount = itemView.findViewById(R.id.investAmount);
            _fundCurrentValue = itemView.findViewById(R.id.fundCurrentValue);
            _fundxirr  = itemView.findViewById(R.id.fundxirr);
            _folioNo = itemView.findViewById(R.id.folioNo);
            _layout = itemView.findViewById(R.id.layout);
        }
    }


    public void setfund_list(ArrayList<InvestMentDetailsModel> _fooditemList) {
        this.filterItemList = _fooditemList;
        notifyDataSetChanged();
    }

    public void setfund_listwithclient(ArrayList<InvestMentDetailsModel> _fooditemList,String c) {
        this.filterItemList = _fooditemList;
        this.clientcode=c;
        notifyDataSetChanged();
    }
}



