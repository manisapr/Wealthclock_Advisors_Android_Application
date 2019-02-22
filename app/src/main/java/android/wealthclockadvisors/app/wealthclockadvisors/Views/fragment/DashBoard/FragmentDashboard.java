package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance.FirstPage_Insurance;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.RiskAnalyzer.Risk_Analyzer_First;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.NewsAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.constant.APIConstant;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.DashBoardModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.NewsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDashboard extends Fragment implements View.OnClickListener {

    private TextView _current_value,_total_investment,_total_gain_loss,_xirr;
    private RecyclerView _newsRecycler;
    private RelativeLayout insurance_dashboard;

    private View progressContainer;
    private RequestQueue _requestQueue;
    private RelativeLayout _lay_quick_purchase,_lay_redeem,_lay_start_sip,_newsRelative,_planGoal,_lay_save_tax,_lay_risk_analyzer;
    private NewsAdapter mAdapter;
    private ArrayList<NewsModel> newsModelArrayList;
    private KProgressHUD hud;


    public FragmentDashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getDasBoardInfo(SharedPreferenceManager.getClientCode(getContext()),getContext());*/

        View view = inflater.inflate(R.layout.fragment_fragment_dashboard, container, false);

        _current_value  = view.findViewById(R.id.current_value);
        _total_investment = view.findViewById(R.id.total_investment);
        _total_gain_loss = view.findViewById(R.id.total_gain_loss);
        _xirr = view.findViewById(R.id.xirr);
        _lay_quick_purchase = view.findViewById(R.id.lay_quick_purchase);
        _lay_redeem = view.findViewById(R.id.lay_redeem);
        _lay_start_sip = view.findViewById(R.id.start_sip_lay);
        _newsRecycler = view.findViewById(R.id.newsRecycler);
        _newsRelative = view.findViewById(R.id.newsRelative);
        _planGoal = view.findViewById(R.id.planGoal);
        _lay_save_tax = view.findViewById(R.id.lay_save_tax);
        insurance_dashboard = view.findViewById(R.id.insurance_dashboard);
        _lay_risk_analyzer = view.findViewById(R.id.lay_risk_analyzer);
        newsModelArrayList = new ArrayList<>();
    insurance_dashboard.setOnClickListener(this);

        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();

        /*ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getDasBoardInfo(SharedPreferenceManager.getClientCode(getContext()),getContext());*/


        _lay_quick_purchase.setOnClickListener(this);
        _lay_redeem.setOnClickListener(this);
        _lay_start_sip.setOnClickListener(this);
        _planGoal.setOnClickListener(this);
        _lay_save_tax.setOnClickListener(this);
        _lay_risk_analyzer.setOnClickListener(this);

        getData();

       /* _newsRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), _newsRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                // ...
                String url=newsModelArrayList.get(position).getLink();
                //Toast.makeText(getContext(), "url"+url, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);
            }

            public void onLongItemClick(View view, int position) {

            }


        }));*/

        /* _newsRecycler.addOnItemTouchListener(new Re);

        String url=newsModelArrayList.get(recyclerView.getPo).getLink();

        Toast.makeText(getContext(), "url"+url, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));

        startActivity(intent); */

        // ...

        return view;
    }

    private void getData() {
        String url="https://www.wealthclockadvisors.com/API/MobileAppApi/GetNewsFeed";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override

            public void onResponse(JSONObject response) {

                try
                {
                    //movieList=new ArrayList<>();
                    JSONArray jarr=response.getJSONArray("NewsFeed");

                    for (int j=0;j<jarr.length();j++)
                    {

                        JSONObject jobj=jarr.getJSONObject(j);
                        String title=jobj.getString("title");
                        String link=jobj.getString("link");
                        String imagepath=jobj.getString("imagepath");
                        //String Link=jobj.getString("Link");
                        NewsModel demoSetGet=new NewsModel();
                        demoSetGet.setTitle(title);
                        demoSetGet.setLink(link);
                        demoSetGet.setImagePath(imagepath);
                        System.out.println("news feed success");
                        newsModelArrayList.add(demoSetGet);

                    }

                    //MyAdapter ma=new MyAdapter();
                    //lv.setAdapter(mAdapter);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                    mAdapter = new NewsAdapter(newsModelArrayList,getContext());
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

                            if(count < newsModelArrayList.size()){
                                _newsRecycler.scrollToPosition(++count);
                                handler.postDelayed(this,speedScroll);

                            }

                        }

                    };



                    handler.postDelayed(runnable,speedScroll);


                }

                catch (Exception e)

                {

                    e.printStackTrace();

                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {



            }

        });

        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId())
        {
            case R.id.lay_quick_purchase:
                System.out.println("quick purchase");
                QuickPurchaseFragment quickPurchaseFragment = new QuickPurchaseFragment();
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "quickPurchaseFragment");
                fragmentTransaction.addToBackStack("quickPurchaseFragment");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;
            case R.id.lay_redeem:
                //FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                //FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                FragmentRedemption fragmentRedemption = new FragmentRedemption();
                fragmentTransaction.replace(R.id.frag, fragmentRedemption, "quickPurchaseFragment");
                fragmentTransaction.addToBackStack("quickPurchaseFragment");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;

            case R.id.start_sip_lay:
                //FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
                //FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                Fragment_StartSIP fragmentStartSIP = new Fragment_StartSIP();
                fragmentTransaction.replace(R.id.frag, fragmentStartSIP, "fragmentStartSIP");
                fragmentTransaction.addToBackStack("fragmentStartSIP");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;

            case R.id.planGoal:
                //FragmentManager fragmentManager3 = getActivity().getSupportFragmentManager();
                //FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                FragmentGoalTrack fragmentGoalTrack = new FragmentGoalTrack();
                fragmentTransaction.replace(R.id.frag, fragmentGoalTrack, "fragmentGoalTrack");
                fragmentTransaction.addToBackStack("fragmentGoalTrack");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;
            case R.id.lay_save_tax:
                fragment_fragment_tax_saving_money__save__tax fragment_fragment_tax_saving_money__save__tax = new fragment_fragment_tax_saving_money__save__tax();
                fragmentTransaction.replace(R.id.frag, fragment_fragment_tax_saving_money__save__tax, "fragmentGoalTrack");
                fragmentTransaction.addToBackStack("fragmentGoalTrack");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;

            case R.id.insurance_dashboard:
                FirstPage_Insurance firstPage_insurance = new FirstPage_Insurance();
                fragmentTransaction.replace(R.id.frag, firstPage_insurance, "fragmentGoalTrack");
                fragmentTransaction.addToBackStack("fragmentGoalTrack");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;

            case R.id.lay_risk_analyzer:
                Risk_Analyzer_First risk_analyzer_first  = new Risk_Analyzer_First();
                fragmentTransaction.replace(R.id.frag, risk_analyzer_first, "risk_analyzer_first");
                fragmentTransaction.addToBackStack("risk_analyzer_first");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
                break;
        }
    }

    private class ServerResultHandler implements ihttpResultHandler {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

       /* @Override
        public void onSuccess(Object message, String operation_flag) {

        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            System.out.println("onSuccess in fragdashboard "+operation_flag);
            if (operation_flag.equalsIgnoreCase("DasBoardInfo")) {
                DashBoardModel dashBoardModel = (DashBoardModel) message;

                _current_value.setText(dashBoardModel.getCurrentMarketValue());
                _total_investment.setText(dashBoardModel.getNetInvestment());
                _total_gain_loss.setText(dashBoardModel.getAbsoluteReturns());
                _xirr.setText(dashBoardModel.getXirr());

                System.out.println("dasboard onSuccess " + dashBoardModel.getCurrentMarketValue());



            }
        }

        @Override
        public void onError(Object message) {
            System.out.println("fragment dashboard error: "+message.toString());

        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        System.out.println("dashboard restored");

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("resumeee");
        System.out.println("Utility: "+Utility.get_currentvalue());
        /*_current_value.setText(Utility.get_currentvalue());
        _total_investment.setText(Utility.get_totalinvest());
        _total_gain_loss.setText(Utility.get_totalgain());
        _xirr.setText(Utility.getXirr());*/

        getInfo();


    }

    private void getInfo() {
        String url = APIConstant.BASE_URL +APIConstant.DASBOARD_URL;

        final JSONObject dasboardObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            dasboardObj.put("ClientCode",SharedPreferenceManager.getClientCode(getContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(getContext());
        }
        System.out.println("getDasBoardInfo: "+dasboardObj + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, dasboardObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DashBoardModel dashBoardModel  = new DashBoardModel();
                        String NetInvestment ="";
                        String CurrentMarketValue = "";
                        String AbsoluteReturns = "";
                        String XIRR = "";
                        String UserName = "";
                        String Path = "";
                        System.out.println("getDasBoardInfo response | getDasBoardInfo: " + response);
                        try {


                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("DashBoardDetails");
                            JSONObject loginObject = loginDetailsArray.getJSONObject(0);
                            NetInvestment = loginObject.getString("NetInvestment");
                            CurrentMarketValue = loginObject.getString("CurrentMarketValue");
                            AbsoluteReturns  = loginObject.getString("AbsoluteReturns");
                            XIRR = loginObject.getString("XIRR");
                            UserName = loginObject.getString("UserName");

                            SharedPreferenceManager.setUserName(getContext(),UserName);
                            SharedPreferenceManager.setImagePath(getContext(),Path);
                            dashBoardModel.setUserName(UserName);
                            dashBoardModel.setNetInvestment(NetInvestment);
                            dashBoardModel.setAbsoluteReturns(AbsoluteReturns);
                            dashBoardModel.setCurrentMarketValue(CurrentMarketValue);
                            dashBoardModel.setXirr(XIRR);
                            dashBoardModel.setImagePath(Path);
                            Utility.set_currentvalue(CurrentMarketValue);
                            Utility.set_totalinvest(NetInvestment);
                            Utility.setXirr(XIRR);
                            Utility.set_totalgain(AbsoluteReturns);

                            _current_value.setText(dashBoardModel.getCurrentMarketValue());
                            _total_investment.setText(dashBoardModel.getNetInvestment());
                            _total_gain_loss.setText(dashBoardModel.getAbsoluteReturns());
                            _xirr.setText(dashBoardModel.getXirr());


                            hud.dismiss();
                            //System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvv");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            //System.out.println("erorrrrrrrrrrrrrrrrrrrr");
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };
        //30 seconds timeout
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);

        _requestQueue.add(jsObjRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("pauseee");

    }
}
