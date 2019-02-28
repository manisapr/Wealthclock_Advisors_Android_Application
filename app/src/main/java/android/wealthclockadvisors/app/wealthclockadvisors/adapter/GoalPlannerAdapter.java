package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class GoalPlannerAdapter extends RecyclerView.Adapter<GoalPlannerAdapter.MyViewHolder> {
    private Context context;
    private List<Top3Funds> fundsList;

    public GoalPlannerAdapter(Context context, ArrayList<Top3Funds> goalFundList)
    {
    this.context = context;
    this.fundsList= goalFundList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rowinvestangle, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    Top3Funds funds = fundsList.get(i);
    myViewHolder._fundname1.setText(funds.getFundname());
    double p= Double.parseDouble(funds.getPrice());
    String cc=String.format("%.0f", p);
    myViewHolder._price1.setText("₹"+cc);
        myViewHolder.fund_type.setText(funds.getFundType());
        myViewHolder.risk.setText(funds.getRisk());
        myViewHolder.return_value.setText(funds.getReturnvalue()+"%");
        myViewHolder.rating.setText(funds.getRating());
        //if (top3Funds.getAmcImage()!=null || top3Funds.getAmcImage().isEmpty())
        //{
        Glide.with(context).load(funds.getAmcImage()).into(myViewHolder.image2);
    }

    @Override
    public int getItemCount() {
        return fundsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView _fundname1,_price1,risk,fund_type,return_value,rating,Price;
        public ImageView image2;
        public MyViewHolder(View view) {
            super(view);
            _fundname1 = view.findViewById(R.id.fundname1);
            _price1 = view.findViewById(R.id.price1);
            image2=(ImageView)itemView.findViewById(R.id.image2);
            risk=(TextView)itemView.findViewById(R.id.risk);
            fund_type=(TextView)itemView.findViewById(R.id.fund_type);
            return_value=(TextView)itemView.findViewById(R.id.return_value);
            rating=(TextView)itemView.findViewById(R.id.rating);
        }

    }
}
