package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.NewsWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.model.NewsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Refferal_Analytics_Details;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class ReferandearnAdapter extends RecyclerView.Adapter<ReferandearnAdapter.MyViewHolder> {
    private List<Refferal_Analytics_Details> moviesList;

    private int lastPosition = -1;

    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView email,mobileno,status,name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.email);
            mobileno=itemView.findViewById(R.id.mobileno);
            status=itemView.findViewById(R.id.status);
            name=itemView.findViewById(R.id.name);
        }
    }
    public ReferandearnAdapter(List<Refferal_Analytics_Details> moviesList,Context refer_and_earn_analytics) {
        this.moviesList = moviesList;
        this.context=refer_and_earn_analytics;

    }
    public ReferandearnAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_referandearn, viewGroup, false);
        return new ReferandearnAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferandearnAdapter.MyViewHolder myViewHolder, int i) {
        final Refferal_Analytics_Details demoSetGet = moviesList.get(i);

        myViewHolder.name.setText(demoSetGet.getUser_Name());
        myViewHolder.email.setText(demoSetGet.getUser_Email());
        myViewHolder.mobileno.setText(demoSetGet.getUser_Mob());
        myViewHolder.status.setText(demoSetGet.getStatus());
        }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
