package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.InvestMentDetailsModel;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class FamilyViewAdapter extends RecyclerView.Adapter<FamilyViewAdapter.Viewholder> {
    List<InvestMentDetailsModel>  familyMemberList;
    Context context;
    private iFoodItemAdapterClickListener _iFoodItemAdapterClickListener;
    private int row_index=0;

    public interface iFoodItemAdapterClickListener
    {
        void setFoodItemOnclick(InvestMentDetailsModel kitchenImageResult,int position);

    }


    public FamilyViewAdapter(Context context, ArrayList<InvestMentDetailsModel> arrayList,iFoodItemAdapterClickListener iFoodItemAdapterClickListener) {
        this.context = context;
        this.familyMemberList = arrayList;
        this._iFoodItemAdapterClickListener = iFoodItemAdapterClickListener;


    }

    @NonNull
    @Override
    public FamilyViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.familyrow, viewGroup, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FamilyViewAdapter.Viewholder viewholder, final int i) {
        final InvestMentDetailsModel menuItem = familyMemberList.get(i);

            viewholder._name.setText(menuItem.getName());

        if (!menuItem.getRelationship().equalsIgnoreCase("null") || menuItem.getRelationship()!=null) {
            viewholder._relation.setText(menuItem.getRelationship());
        }
        if (menuItem.getClientCode().equalsIgnoreCase(SharedPreferenceManager.getClientCode(context)))
        {
            viewholder._relation.setText("Self");

        }
        viewholder._investAmount.setText(menuItem.getNetInvestment());
        viewholder._fundCurrentValue.setText(menuItem.getFundCurrentValue());
        viewholder._fundxirr.setText(menuItem.getNetXirr());




        if (menuItem.getImagepath()!=null || !menuItem.getImagepath().equalsIgnoreCase("null"))
        Glide.with(context).load(menuItem.getImagepath()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                //System.out.println("onException" + e.getMessage());
                viewholder._profileIcon.setImageResource(R.drawable.profile_placeholder);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                //viewholder._profileIcon.setb(R.drawable.profile_placeholder);

                viewholder._placeholderImage.setVisibility(View.GONE);
                viewholder._profileIcon.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(viewholder._profileIcon);
        viewholder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //row_index=i;
                //notifyDataSetChanged();
                _iFoodItemAdapterClickListener.setFoodItemOnclick(familyMemberList.get(i),i);
            }
        });

        /*if(row_index==i){
            viewholder.view.setBackgroundColor(Color.parseColor("#13a097"));

        }
        else
        {
            viewholder.view.setBackgroundColor(Color.parseColor("#595959"));

        }*/
    }

    @Override
    public int getItemCount() {
        return familyMemberList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView _profileIcon,_placeholderImage;
        private TextView _name,_investAmount,_fundCurrentValue,_fundxirr,_relation;
        private View view;
        private CardView _tapCard;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            _profileIcon = itemView.findViewById(R.id.profileIcon);
            _name = itemView.findViewById(R.id.name);
            _investAmount = itemView.findViewById(R.id.investAmount);
            _fundCurrentValue = itemView.findViewById(R.id.fundCurrentValue);
            _fundxirr = itemView.findViewById(R.id.fundxirr);
            _relation = itemView.findViewById(R.id.totalchange);
            _placeholderImage = itemView.findViewById(R.id.placeholderImage);
            _tapCard = itemView.findViewById(R.id.tapCard);
            view = itemView;
        }
    }

    public void setfund_list(ArrayList<InvestMentDetailsModel> _fooditemList)
    {
        this.familyMemberList = _fooditemList;
        notifyDataSetChanged();
    }
}
