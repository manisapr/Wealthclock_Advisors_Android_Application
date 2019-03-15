package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MultipleFundModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.ReferralDetails;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Refferal_Analytics_Details;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class Refer_and_earn extends Fragment implements View.OnClickListener{

    ImageView share;

    TextView sharelink,click,transacted,earnedmoney1,signup,transactedcount,pending_balance;
    LinearLayout ln;
    String ClientCode=" ";
    String Sharablelink=" ";
    String id,RefCode,LinksCount,SentCount;
    String pending2="0";
    int EarnedMoney1,RedeemedMoney1,Pending_bal;
    //ReferralDetails referralDetails=new ReferralDetails();
    ArrayList<ReferralDetails> arrayList1=null;
    ArrayList<String> listShortcode=new ArrayList<>();


    String ReferCode1=" ";
    String Email=" ";
    String ShortCode=" ";
    String ShortCode1=" ";
    String EarnedMoney="0";
    String RedeemedMoney="0";
    String SignUpCount,TransactedCount,User_Name,User_Email,User_Mob,Status;
    Refferal_Analytics_Details refferal_analytics_details=new Refferal_Analytics_Details();
    ArrayList<Refferal_Analytics_Details> arrayList2=null;
    public Refer_and_earn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_refer_and_earn, container, false);
        share=view.findViewById(R.id.share);

        sharelink=view.findViewById(R.id.sharelink);
        click=view.findViewById(R.id.click);
        transacted=view.findViewById(R.id.transacted);
        earnedmoney1=view.findViewById(R.id.earnedmoney1);
        signup=view.findViewById(R.id.signup);
        transactedcount=view.findViewById(R.id.transactedcount);
        pending_balance=view.findViewById(R.id.pending_balance);
        ln=view.findViewById(R.id.ln);
        share.setOnClickListener(this);
        ln.setOnClickListener(this);
        //ln.setEnabled(false);

        ClientCode=SharedPreferenceManager.getClientCode(getContext());
        getData();

        Email=SharedPreferenceManager.getUserEmail(getContext());
        //getData1();
        return view;
    }
    private void getData1()
    {
        ReferCode1=RefCode;
        ShortCode1=ShortCode;
        String url1="https://www.wealthclockadvisors.com/MobileAppApi/GetReferralAnalyticsDetails?ClientCode="+ClientCode+"&ReferCode="+ReferCode1+"&Email="+Email+"&ShortCode="+ShortCode1;
        System.out.println("Url:-"+url1);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    arrayList2=new ArrayList<>();
                    System.out.println("Response:-"+response);

                    JSONArray jarr3 = response.getJSONArray("AccountDetails");
                    for(int m=0;m<jarr3.length();m++)
                    {

                        String responseStream= String.valueOf(response);
                        final JSONObject jsonResponse = new JSONObject(responseStream);
                        JSONObject jsonInfo=jsonResponse;
                        System.out.println("AccountDetails:- "+jsonInfo);

                        JSONObject jobj = jarr3.getJSONObject(m);
                        System.out.println("Details:-"+jobj);

                        SignUpCount=jobj.getString("SignUpCount");
                        TransactedCount=jobj.getString("TransactedCount");
                        LinksCount=jobj.getString("LinksCount");



                        //Refferal_Analytics_Details refferal_analytics_details=new Refferal_Analytics_Details();
                        refferal_analytics_details.setSignUpCount(SignUpCount);
                        signup.setText(SignUpCount);
                        refferal_analytics_details.setTransactedCount(TransactedCount);
                        transacted.setText(TransactedCount);

                        refferal_analytics_details.setLinksCount(LinksCount);

//                        int s=Integer.parseInt(SignUpCount);
//                        if (s==0)
//                        {
//                            ln.setEnabled(false);
//                        }
//                        else
//                        {
//                            ln.setEnabled(true);
//                        }

                        arrayList2.add(refferal_analytics_details);
                    }
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

                            arrayList2.add(refferal_analytics_details);
                        }
                    }
                    else
                    {
                        System.out.println("No Referral Receiver Data Found!!!");
                    }
                    System.out.println("**********************************************");
                    JSONArray jarr2 = response.getJSONArray("TransactionDetails");
                    if (jarr2.length()!=0) {
                        for (int l = 0; l < jarr2.length(); l++) {

                            String responseStream = String.valueOf(response);
                            final JSONObject jsonResponse2 = new JSONObject(responseStream);
                            JSONObject jsonInfo2 = jsonResponse2;
                            System.out.println("TransactionDetails " + jsonInfo2);

                            JSONObject jobj2 = jarr2.getJSONObject(l);
                            System.out.println("Details2:-" + jobj2);

                            EarnedMoney = jobj2.getString("EarnedMoney");
                            RedeemedMoney = jobj2.getString("RedeemedMoney");


                            //Refferal_Analytics_Details refferal_analytics_details=new Refferal_Analytics_Details();
                            refferal_analytics_details.setEarnedMoney(EarnedMoney);
                            refferal_analytics_details.setRedeemedMoney(RedeemedMoney);
                            System.out.println("Earned Money="+EarnedMoney);
                            System.out.println("Received Money="+RedeemedMoney);
                            earnedmoney1.setText("₹"+EarnedMoney);
                            transactedcount.setText("₹"+RedeemedMoney);

                            //int EarnedMoney1,RedeemedMoney1,Pending_bal;

                            EarnedMoney1=Integer.parseInt(EarnedMoney);
                            System.out.println(EarnedMoney1);
                            RedeemedMoney1=Integer.parseInt(RedeemedMoney);
                            System.out.println(RedeemedMoney1);
                            Pending_bal=EarnedMoney1-RedeemedMoney1;
                            pending2=Integer.toString(Pending_bal);
                            System.out.println("Pending_bal="+pending2);
                            pending_balance.setText(Pending_bal);

                            arrayList2.add(refferal_analytics_details);
                        }
                    }
                    else
                    {
                        System.out.println("Data Not Found!!!!");
                    }

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
    private void getData()
    {
        String url="https://www.wealthclockadvisors.com/API/MobileAppApi/GetReferralDetails?ClientCode="+ClientCode;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    arrayList1=new ArrayList<>();

                    ArrayList<MultipleFundModel> stringArrayList = new ArrayList<>();

                    JSONArray jarr = response.getJSONArray("ReferralDetails");
                    for(int j=0;j<jarr.length();j++)
                    {

                        String responseStream= String.valueOf(response);
                        final JSONObject jsonResponse = new JSONObject(responseStream);
                        JSONObject jsonInfo=jsonResponse;
                        System.out.println("ReferralDetails:- "+jsonInfo);

                        JSONObject jobj = jarr.getJSONObject(j);
                        System.out.println("Details:-"+jobj);

                        id=jobj.getString("id");
                        ClientCode=jobj.getString("ClientCode");
                        Email=jobj.getString("Email");
                        RefCode=jobj.getString("RefCode");
                        ReferCode1=RefCode;
                        System.out.println("Refer Code="+ReferCode1);
                        LinksCount=jobj.getString("LinksCount");
                        ShortCode=jobj.getString("ShortCode");
                        ShortCode1=ShortCode;
                        SentCount=jobj.getString("SentCount");
                        listShortcode.add(ShortCode);

                        System.out.println("ShortCode="+ShortCode);


                        ReferralDetails referralDetails=new ReferralDetails();
                        referralDetails.setId(id);
                        referralDetails.setEmail(Email);
                        referralDetails.setRefCode(RefCode);
                        referralDetails.setLinksCount(LinksCount);
                        referralDetails.setShortCode(ShortCode);
                        referralDetails.setSentCount(SentCount);
                        sharelink.setText(ShortCode);
                        click.setText(LinksCount);
                        Sharablelink=RefCode;
                        System.out.println("Sharable Link="+Sharablelink);
                        arrayList1.add(referralDetails);
                        getData1();
                    }
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
        switch (v.getId()) {
            case R.id.share:
                String shareMessage= "\nWealthClock Advisors\n\n";
                //shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" +"app.wealthclockadvisors.app.wealthclockadvisors";
                shareMessage=shareMessage+"http://www.wealthclockadvisors.com";
                //String messgae="ABCD";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Wealthclock Advisors");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Sharablelink);
                startActivity(Intent.createChooser(sharingIntent, "share using"));
                break;
            case R.id.ln:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Refer_and_earn_analytics refer_and_earn_analytics = new Refer_and_earn_analytics();

                Bundle arguments = new Bundle();

                arguments.putString("custom1", ShortCode1);
                arguments.putString("custom2",ReferCode1);
                //int pending=Integer.parseInt(EarnedMoney)-Integer.parseInt(RedeemedMoney);
                //String pending1=String.valueOf(pending);
                System.out.println("pending:-"+pending2);

                arguments.putString("custom3",LinksCount);
                arguments.putString("custom4",SignUpCount);

                    arguments.putString("custom5", EarnedMoney);
                System.out.println("abc="+EarnedMoney);
                    arguments.putString("custom6", RedeemedMoney);
                System.out.println("def="+RedeemedMoney);
                    arguments.putString("custom7", pending2);
                System.out.println("ghi="+pending2);
//                else
//                {
//                    arguments.putString("custom5","");
//                    arguments.putString("custom6","");
//                    arguments.putString("custom7","");
//                }


                refer_and_earn_analytics.setArguments(arguments);

                fragmentTransaction.replace(R.id.frag, refer_and_earn_analytics, "Refer_and_earn_analytics");
                fragmentTransaction.addToBackStack("Refer_and_earn_analytics");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;
        }
    }
}
