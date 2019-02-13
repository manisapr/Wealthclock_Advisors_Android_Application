package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.NewsWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentDashboard;
import android.wealthclockadvisors.app.wealthclockadvisors.model.NewsModel;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<NewsModel> moviesList;

    private int lastPosition = -1;

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public ImageView img;


        public MyViewHolder(View view) {

            super(view);

            tv = (TextView) view.findViewById(R.id.tv);

            img = (ImageView) view.findViewById(R.id.img);

        }

    }

    public NewsAdapter(List<NewsModel> moviesList,Context fragmentDashboard) {
        this.moviesList = moviesList;
        this.context=fragmentDashboard;

    }


    @Override

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override

    public void onBindViewHolder(MyViewHolder holder, int position) {

        final NewsModel demoSetGet = moviesList.get(position);

        holder.tv.setText(demoSetGet.getTitle());
        if (demoSetGet.getImagePath()!=null || demoSetGet.getImagePath().isEmpty()) {
            Glide.with(context).load(demoSetGet.getImagePath()).into(holder.img);
        }

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewsWebViewActivity.class);
                intent.putExtra("link",demoSetGet.getLink());
                context.startActivity(intent);
            }
        });



        //setAnimation(holder.tv, position);

    }

    private void setAnimation(TextView tv, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated

        if (position > lastPosition)

        {

            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);

            //viewToAnimate.startAnimation(animation);

            lastPosition = position;

        }
    }


    @Override

    public int getItemCount() {
        return moviesList.size();

    }
}
