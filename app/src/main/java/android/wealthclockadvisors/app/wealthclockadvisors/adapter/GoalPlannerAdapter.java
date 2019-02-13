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

import java.util.ArrayList;
import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class GoalPlannerAdapter extends RecyclerView.Adapter<GoalPlannerAdapter.MyViewHolder> {
    private Context context;
    private List<Top3Funds> fundsList;

    public GoalPlannerAdapter(Context context, ArrayList<Top3Funds> goalFundList) {
    this.context = context;
    this.fundsList= goalFundList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
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
    }

    @Override
    public int getItemCount() {
        return fundsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView _fundname1,_price1;
        public MyViewHolder(View view) {
            super(view);
            _fundname1 = view.findViewById(R.id.fundname1);
            _price1 = view.findViewById(R.id.price1);

        }

    }
}
