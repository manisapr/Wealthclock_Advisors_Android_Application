package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.NewsAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.ReferandearnAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.NewsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Refferal_Analytics_Details;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Refer_and_earn_analytics extends Fragment implements View.OnClickListener{ ImageView share;

    TextView click,accepted,earned,received,balance_pending,tv1;
    String ClientCode=" ";
    String ReferCode=" ";
    String Email=" ";
    String ShortCode=" ";
    String User_Name,User_Email,User_Mob,Status,Click,Accepted,Earned,Received,Pending;
    Refferal_Analytics_Details refferal_analytics_details=new Refferal_Analytics_Details();
    ArrayList<Refferal_Analytics_Details> arrayList1=null;
    //Context context;
    private ReferandearnAdapter mAdapter;
    private RecyclerView _newsRecycler;
    private ArrayList<ReferandearnAdapter> newsModelArrayList;

    public Refer_and_earn_analytics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_refer_and_earn_analytics, container, false);
        click=view.findViewById(R.id.click);
        accepted=view.findViewById(R.id.accepted);
        earned=view.findViewById(R.id.earned);
        received=view.findViewById(R.id.received);
        balance_pending=view.findViewById(R.id.balance_pending);
        _newsRecycler=view.findViewById(R.id._newsRecycler);
        tv1=view.findViewById(R.id.tv1);
        ClientCode=SharedPreferenceManager.getClientCode(getContext());
        Email=SharedPreferenceManager.getUserEmail(getContext());
        Bundle arguments = getArguments();
        ShortCode = arguments.getString("custom1");
        ReferCode=arguments.getString("custom2");

        Click=arguments.getString("custom3");
        click.setText(Click);
        Accepted=arguments.getString("custom4");
        accepted.setText(Accepted);
        Earned=arguments.getString("custom5");
        earned.setText("₹"+Earned);
        Received=arguments.getString("custom6");
        received.setText("₹"+Received);
        Pending=arguments.getString("custom7");
        balance_pending.setText("₹"+Pending);
        System.out.println("Refer Code="+ReferCode);

        getData();
        return view;
    }

    private void getData() {
        String url="https://www.wealthclockadvisors.com/MobileAppApi/GetReferralAnalyticsDetails?ClientCode="+ClientCode+"&ReferCode="+ReferCode+"&Email="+Email+"&ShortCode="+ShortCode;
        System.out.println("Url:-"+url);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    arrayList1=new ArrayList<>();
                    System.out.println("Response:-"+response);

                    System.out.println("**********************************************");
                    JSONArray jarr1 = response.getJSONArray("ReferralReceiver");
                    if (jarr1.length()!=0) {
                        for (int k = 0; k < jarr1.length(); k++) {

                            String responseStream = String.valueOf(response);
                            final JSONObject jsonResponse1 = new JSONObject(responseStream);
                            JSONObject jsonInfo1 = jsonResponse1;
                            System.out.println("ReferralReceiver:- " + jsonInfo1);

                            JSONObject jobj1 = jarr1.getJSONObject(k);
                            System.out.println("Details1:-" + jobj1);

                            User_Name = jobj1.getString("User_Name");
                            User_Email = jobj1.getString("User_Email");
                            User_Mob = jobj1.getString("User_Mob");
                            Status = jobj1.getString("Status");


                            //Refferal_Analytics_Details refferal_analytics_details=new Refferal_Analytics_Details();
                            refferal_analytics_details.setUser_Name(User_Name);
                            refferal_analytics_details.setUser_Email(User_Email);
                            refferal_analytics_details.setUser_Mob(User_Mob);
                            refferal_analytics_details.setStatus(Status);

                            arrayList1.add(refferal_analytics_details);
                            System.out.println("**********************************************");
                        }
                    }
                    else
                    {
                        _newsRecycler.setEnabled(false);
                        _newsRecycler.setVisibility(View.GONE);
                        tv1.setText("No Data Found!!!");
                        tv1.setVisibility(View.VISIBLE);
                        System.out.println("Data Not Found!!!!");
                    }
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                    mAdapter = new ReferandearnAdapter(arrayList1,getContext());
                    _newsRecycler.setLayoutManager(mLayoutManager);
                    //lv.setItemAnimator(new DefaultItemAnimator());
                    _newsRecycler.setAdapter(mAdapter);
                    //mAdapter.notifyDataSetChanged();

                    final int speedScroll = 3000;
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        int count = 0;
                        @Override

                        public void run() {

                            if(count < arrayList1.size()){
                                _newsRecycler.scrollToPosition(++count);
                                handler.postDelayed(this,speedScroll);
                            }
                        }

                    };

                    handler.postDelayed(runnable,speedScroll);
                }
                catch (Exception e){
                    e.printStackTrace();
                    //Toast.makeText(getContext(), "Data Not Found!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {

    }
}
