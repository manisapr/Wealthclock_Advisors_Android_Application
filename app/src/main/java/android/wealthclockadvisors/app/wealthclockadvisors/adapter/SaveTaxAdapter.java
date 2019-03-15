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

import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;



public class SaveTaxAdapter extends RecyclerView.Adapter<SaveTaxAdapter.MyViewHolder> {
    private List<Top3Funds> savetax;

    private Context context;





    @NonNull

    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowinvestangle, viewGroup, false);

        return new MyViewHolder(itemView);



    }





    @Override

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Top3Funds top3Funds = savetax.get(i);

        myViewHolder.fundname.setText(top3Funds.getFundname());

        myViewHolder.Price.setText(top3Funds.getPrice());

        myViewHolder.fund_type.setText(top3Funds.getFundType());

        myViewHolder.risk.setText(top3Funds.getRisk());

        //myViewHolder.return_value.setText(top3Funds.getReturnvalue());

        myViewHolder.return_value.setText(top3Funds.getFiveyearreturn());

        myViewHolder.rating.setText(top3Funds.getRating());

        //if (top3Funds.getAmcImage()!=null || top3Funds.getAmcImage().isEmpty())

        //{

        Glide.with(context).load(top3Funds.getAmcImage()).into(myViewHolder.image2);

        //}



    }







    @Override

    public int getItemCount()

    {

        return savetax.size();

    }







    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView fundname,risk,fund_type,return_value,rating,Price;

        public ImageView image2;



        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            fundname=(TextView)itemView.findViewById(R.id.fundname1);

            Price=(TextView)itemView.findViewById(R.id.price1);

            image2=(ImageView)itemView.findViewById(R.id.image2);

            risk=(TextView)itemView.findViewById(R.id.risk);

            fund_type=(TextView)itemView.findViewById(R.id.fund_type);

            return_value=(TextView)itemView.findViewById(R.id.return_value);

            rating=(TextView)itemView.findViewById(R.id.rating);



        }



    }



    public SaveTaxAdapter(List<Top3Funds> savetax, Context fragmentinvestangle) {

        this.savetax = savetax;

        this.context=fragmentinvestangle;

    }



}







//public class SaveTaxAdapter extends RecyclerView.Adapter<SaveTaxAdapter.MyViewHolder> {

//

//    private List<Top3Funds> savetax;

//

//    private Context context;

//

//

//

//    @NonNull

//

//    @Override

//

//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

//

//        View itemView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowinvestangle, viewGroup, false);

//

//        return new MyViewHolder(itemView);

//

//    }

//

//

//

//    @Override

//

//    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

//

//        final Top3Funds top3Funds = savetax.get(i);

//

//

//

//        myViewHolder.fundname.setText(top3Funds.getFundname());

//

//        myViewHolder.Price.setText(top3Funds.getPrice());

//

//    }

//

//

//

//    @Override

//

//    public int getItemCount()

//

//    {

//

//

//

//        return savetax.size();

//

//    }

//

//

//

//    public class MyViewHolder extends RecyclerView.ViewHolder{

//

//        public TextView fundname,Price;

//

//

//

//        public MyViewHolder(@NonNull View itemView) {

//

//            super(itemView);

//

//            fundname=(TextView)itemView.findViewById(R.id.fundname1);

//

//            Price=(TextView)itemView.findViewById(R.id.price1);

//

//        }

//

//    }

//

//    public SaveTaxAdapter(List<Top3Funds> savetax, Context fragmentinvestangle) {

//

//        this.savetax = savetax;

//

//        this.context=fragmentinvestangle;

//

//

//

//    }

//

//}

//


