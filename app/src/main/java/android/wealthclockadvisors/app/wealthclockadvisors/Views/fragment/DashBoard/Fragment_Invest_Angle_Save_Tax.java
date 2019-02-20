package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.PaymentWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.adapter.SaveTaxAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.constant.APIConstant;
import android.wealthclockadvisors.app.wealthclockadvisors.controller.httpController;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundTypeModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MultipleFundModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MutualFundDetailsforModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

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
public class Fragment_Invest_Angle_Save_Tax extends Fragment {

    String investment=" ";

    ArrayList<Top3Funds> arrayList1=null;
    ArrayList<FundTypeModel> fundTypeModelArrayList=null;
    //ArrayList<OrderEntryModel> multiplefundpurchase=new ArrayList<>();

    RecyclerView lv1;

    private SaveTaxAdapter mAdapter;

    TextView tv1;

    OrderEntryModel orderEntryModel;
    Top3Funds top3Funds;

    Button investnow;
    Switch onOffSwitch;
    Context context;

    private MutualFundDetailsforModel mutualFundDetailsforModel;

    private static httpController _Instance;
    private RequestQueue _requestQueue;
    private ihttpResultHandler _iHttpResultHandler;
    private ArrayList<Top3Funds> _goalFundList;
    private  SaveTaxAdapter _saveTaxAdapter;
    ArrayList<String> listfundname=new ArrayList<>();
    ArrayList<String> listschemecode=new ArrayList<>();
    ArrayList<String> listamccode=new ArrayList<>();
    ArrayList<String> listprice=new ArrayList<>();
    ArrayList<String> listfolio=new ArrayList<>();
    //ArrayList<String> listPayment=new ArrayList<>();

    String ClientCode="";
    String Amccode;
    String foliono = " ";
    private String _amount = " ";
    String fundname,Price,Build_ID,AmcSchemeCode,UniqueNo,SipID,proportion,position,Returnvalue,Year,isDeleted,InvestedAmount,PotentialValue,SaveTax_ID,RegularIncome_ID,Park_ID,SchemeType,SchemeCode;
    private String info="";


    public Fragment_Invest_Angle_Save_Tax() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__invest__angle__save__tax, container, false);
        orderEntryModel = new OrderEntryModel();

        Bundle arguments = getArguments();
        investment = arguments.getString("custom");
        lv1=view.findViewById(R.id.lv1);
        tv1=view.findViewById(R.id.tv1);
        investnow=view.findViewById(R.id.investnow);
        onOffSwitch=view.findViewById(R.id.onOffSwitch);
        listfolio.add(0,"NEW");
        listfolio.add(1,"NEW");
        _goalFundList=new ArrayList<>();

        getData();
        //getFolio();
        onOffSwitch.setChecked(true);
        orderEntryModel.setPaymentMode("yes");

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onOffSwitch.isChecked())
                {
                    orderEntryModel.setPaymentMode("yes");

                }
                else {
                    orderEntryModel.setPaymentMode("no");
                }
            }
        });

        investnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                investnow.setAlpha(0.5f);
                investnow.setEnabled(false);
                investnow.setText(R.string.purchase);
                //getPaymentMode();
                if (listamccode.size()==1)
                {
                    getFolio1();
                    //System.out.println("Abcd");
                }
                else if (listamccode.size()>1)
                {
                    getFolio2();
                    getFolio();
                    //getPurchase();
                    //System.out.println("efgh");
                }
                //getFolio1();
                //getFolio();


            }
        });
        return view;
    }


    private void getPaymentMode() {
        /*if (orderEntryModel.getPaymentMode().equalsIgnoreCase("yes"))
        {
            String infohtml = "<html>\n" +
                    "<head>\n" +
                    "<title>\n" +
                    "A Simple HTML Document\n" +
                    "</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<p>This is a very simple HTML document</p>\n" +
                    "<p>It only has two paragraphs</p>\n" +
                    "</body>\n" +
                    "</html>";
            System.out.println("Quick | info html:- "+infohtml);
            Intent intent = new Intent(getContext(),PaymentWebViewActivity.class);
            intent.putExtra("link",infohtml);
            startActivity(intent);
            getActivity().finish();
        }
        else if (orderEntryModel.getPaymentMode().equalsIgnoreCase("no"))
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setTitle("Purchase Initiated Successfully");
            alertDialogBuilder.setMessage("Kindly make the payment via One Time Mandate or Cheque");
            alertDialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                           *//* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            FragmentDashboard taxSavingMoney_save_tax = new FragmentDashboard();
                            fragmentTransaction.replace(R.id.frag, taxSavingMoney_save_tax , "taxSavingMoney_save_tax ");
                            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            fragmentTransaction.commit();
                            fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-3).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
*//*
                            getActivity().getSupportFragmentManager().popBackStackImmediate();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }*/
    }


    private void getPurchase() {
        String url = "https://www.wealthclockadvisors.com/API/MultipleOrder/NormalOrder";
        JSONArray multipurchase=new JSONArray();
        try {
            for (int i=0;i<listamccode.size();i++)
            {
                JSONObject purchase = new JSONObject();
                purchase.put("TransCode", "NEW");
                purchase.put("TransNo", "");
                purchase.put("OrderId", "");
                purchase.put("UserID", "");
                purchase.put("MemberId", "");
                purchase.put("ClientCode", SharedPreferenceManager.getClientCode(getContext()));
                purchase.put("PanNumber", SharedPreferenceManager.getPanno(getContext()));
                purchase.put("SchemeCd", listschemecode.get(i));
                purchase.put("AmcCode", listamccode.get(i));
                purchase.put("BuySell", "P");
                //purchase.put("BuySellType", "FRESH");
                purchase.put("DPTxn", "P");
                purchase.put("OrderVal", listprice.get(i));
                //purchase.put("OrderVal", "500");
                purchase.put("Qty", "");
                purchase.put("AllRedeem", "Y");
                if (listfolio.get(i).equalsIgnoreCase("NEW"))
                {
                    purchase.put("BuySellType","FRESH");
                }
                else
                {
                    purchase.put("BuySellType","ADDITIONAL");
                }

                purchase.put("FolioNo", listfolio.get(i));
                //purchase.put("FolioNo", listfolio.get(i));
                purchase.put("Remarks", "");
                purchase.put("KYCStatus", "");
                purchase.put("RefNo", "");
                purchase.put("SubBrCode", "");
                purchase.put("EUIN", "E172987");
                purchase.put("EUINVal", "Y");
                purchase.put("MinRedeem", "Y");
                purchase.put("DPC", "Y");
                purchase.put("IPAdd", "");
                purchase.put("Password", "");
                purchase.put("PassKey", "");
                purchase.put("Param1", "");
                purchase.put("Param2", "");
                purchase.put("Param3", "");
                purchase.put("BankacNo", SharedPreferenceManager.getBankAccountNumber(getContext()));
                purchase.put("IFSC", SharedPreferenceManager.getBankIfscCode(getContext()));
                purchase.put("BankName", SharedPreferenceManager.getBankName(getContext()));
                purchase.put("PaymentMode", orderEntryModel.getPaymentMode());
                multipurchase.put(purchase);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        if (_requestQueue == null)
        {
            _requestQueue = Volley.newRequestQueue(getContext());
        }


        System.out.println("purchase obj: "+multipurchase + "url: "+url);
      /*  JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.POST, url,multipurchase,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String successCode ="";
                        String description = "";
                        //String info = " ";

                        System.out.println("purchase response:" + response);
                        try {
                            String responseStream = String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo = jsonResponse;
                            if (jsonInfo.getString("IsSuccess").equalsIgnoreCase("true"))
                            {
                                successCode = jsonInfo.getString("IsSuccess");
                                System.out.println("Purchase Successful"+successCode);
                                Toast.makeText(getActivity(),"Fund Purchase Successful",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                successCode = "false";
                                description = jsonInfo.getString("Description");
                                System.out.println("Unsuccessfull"+description);
                                Toast.makeText(getActivity(),"Fund Purchase Unsuccessful",Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch (Exception e)
                        {

                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(successCode,description,info,"","","","purchase");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sendLoginData onErrorResponse:| sendLoginData " + error.getMessage() + error);

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

        _requestQueue.add(jsObjRequest);*/

        AndroidNetworking.post("https://www.wealthclockadvisors.com/API/MultipleOrder/NormalOrder")
                .addJSONArrayBody(multipurchase)
                .setTag("BSE")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {

                if (orderEntryModel.getPaymentMode().trim().equalsIgnoreCase("no"))
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setCancelable(false);
                    dialog.setTitle("Purchase Initiated Successfully");
                    dialog.setMessage("Kindly make the payment via One Time Mandate or Cheque");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Action for "Delete".
                            getActivity().getSupportFragmentManager().popBackStackImmediate();
                        }
                    });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
                else {
                    String responseStream = String.valueOf(response);
                    final JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(responseStream);
                        JSONObject jsonInfo = jsonResponse;
                       String html = jsonInfo.getString("Info");
                        //System.out.println("Quick | info html:- "+infohtml);
                        Intent intent = new Intent(getContext(),PaymentWebViewActivity.class);
                        intent.putExtra("link",html);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
    private void getFolio1() {

        String url = APIConstant.BASE_URL +"/GetFundTypeFolio";
        JSONObject portfolio = null;
        try {
            portfolio = new JSONObject();
            portfolio.put("ClientCode",SharedPreferenceManager.getClientCode(getContext()));
            portfolio.put("amccode", listamccode.get(0));
            System.out.println("folioooooooooooo2 "+portfolio);


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(getContext());
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, portfolio,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String fundtype[] = {""};
                        fundTypeModelArrayList = new ArrayList<>();

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            System.out.println("Folio2-"+jsonInfo);
                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            if (foliodetails.length()>0)
                            {


                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject folioobj = foliodetails.getJSONObject(0);
                                fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                if(fundTypeModel.getFolioNo().isEmpty()|| fundTypeModel.getFolioNo().equalsIgnoreCase("null"))
                                {

                                }
                                else
                                {
                                    listfolio.add(0,fundTypeModel.getFolioNo());
                                }

                                //getPurchase();

                            }
                            else {
                                listfolio.add(0,"NEW");
                            }
                           getPurchase();



                            /*String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            System.out.println("Folio List:-"+foliodetails);
                            for(int i=0;i<listschemecode.size();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject folioobj = foliodetails.getJSONObject(i);
                                fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                String FolioNo = folioobj.getString("FolioNo");
                                foliono = FolioNo;
                                listfolio.add(0,foliono);

                                System.out.println("My Folio Listt:-" + listfolio.get(i)); */


                            // getPurchase();

                            System.out.println("bolllllllll:- "+listfolio.get(0));



                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess("", "","","","","","getPortfolioData");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sendLoginData onErrorResponse:| sendLoginData " + error.getMessage() + error);

            }
        })
        {
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
        String f=foliono;


    }
    private void getFolio2() {

        String url = APIConstant.BASE_URL +"/GetFundTypeFolio";
        JSONObject portfolio = null;
        try {
            portfolio = new JSONObject();
            portfolio.put("ClientCode",SharedPreferenceManager.getClientCode(getContext()));
            portfolio.put("amccode", listamccode.get(0));
            System.out.println("folioooooooooooo2 "+portfolio);


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(getContext());
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, portfolio,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String fundtype[] = {""};
                        fundTypeModelArrayList = new ArrayList<>();

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            System.out.println("Folio2-"+jsonInfo);
                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            if (foliodetails.length()>0)
                            {


                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject folioobj = foliodetails.getJSONObject(0);
                                fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                if(fundTypeModel.getFolioNo().isEmpty()|| fundTypeModel.getFolioNo().equalsIgnoreCase("null"))
                                {

                                }
                                else
                                {
                                    listfolio.add(0,fundTypeModel.getFolioNo());
                                }

                                //getPurchase();

                            }
                            else {
                                listfolio.add(0,"NEW");
                            }




                            /*String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            System.out.println("Folio List:-"+foliodetails);
                            for(int i=0;i<listschemecode.size();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject folioobj = foliodetails.getJSONObject(i);
                                fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                String FolioNo = folioobj.getString("FolioNo");
                                foliono = FolioNo;
                                listfolio.add(0,foliono);

                                System.out.println("My Folio Listt:-" + listfolio.get(i)); */


                            // getPurchase();

                            System.out.println("bolllllllll:- "+listfolio.get(0));



                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess("", "","","","","","getPortfolioData");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sendLoginData onErrorResponse:| sendLoginData " + error.getMessage() + error);

            }
        })
        {
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
        String f=foliono;


    }

    private void getFolio() {

        String url = APIConstant.BASE_URL +"/GetFundTypeFolio";
        JSONObject portfolio = null;
        try {
            portfolio = new JSONObject();
            portfolio.put("ClientCode",SharedPreferenceManager.getClientCode(getContext()));
            portfolio.put("amccode", listamccode.get(1));
            System.out.println("folioooooooooooo 11111:- "+portfolio);


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(getContext());
        }
        System.out.println("folioooooooooooo1:- "+portfolio);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, portfolio,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String fundtype[] = {""};
                        fundTypeModelArrayList = new ArrayList<>();

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                            System.out.println("Folio1-"+jsonInfo);

                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            if (foliodetails.length()>0)
                            {

                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject folioobj = foliodetails.getJSONObject(0);
                                fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                if(fundTypeModel.getFolioNo().isEmpty()|| fundTypeModel.getFolioNo().equalsIgnoreCase("null"))
                                {

                                }
                                else
                                {
                                    listfolio.add(1,fundTypeModel.getFolioNo());
                                }



                            }
                            else {
                                listfolio.add(1,"NEW");
                            }
                            getPurchase();


                            /*String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            System.out.println("Folio List:-"+foliodetails);
                            for(int i=0;i<listschemecode.size();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject folioobj = foliodetails.getJSONObject(i);
                                fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                String FolioNo = folioobj.getString("FolioNo");
                                foliono = FolioNo;
                                listfolio.add(1,foliono);

                                System.out.println("My Folio Listt:-" + listfolio.get(i));
                            } */

                            //getPurchase();



                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess("", "","","","","","getPortfolioData");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sendLoginData onErrorResponse:| sendLoginData " + error.getMessage() + error);

            }
        })
        {
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
        String f=foliono;


    }

    private void getData() {

        String url="https://www.wealthclockadvisors.com/API/MobileAppApi/SaveTaxRecomendedPlan?inv="+investment;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    arrayList1=new ArrayList<>();

                    ArrayList<MultipleFundModel> stringArrayList = new ArrayList<>();

                    JSONArray jarr = response.getJSONArray("Top3Funds");
                    for(int j=0;j<jarr.length();j++)
                    {
                        OrderEntryModel orderEntryModel1=new OrderEntryModel();
                        String responseStream= String.valueOf(response);
                        final JSONObject jsonResponse = new JSONObject(responseStream);
                        JSONObject jsonInfo=jsonResponse;
                        System.out.println("Top3Funds:- "+jsonInfo);

                        JSONObject jobj = jarr.getJSONObject(j);
                        System.out.println("dddddddddd:-"+jobj);

                        fundname = jobj.getString("fundname");
                        listfundname.add(fundname);

                        Price = jobj.getString("Price");
                        Price=Price;
                        listprice.add(Price);

                        Build_ID=jobj.getString("Build_ID");
                        AmcSchemeCode=jobj.getString("AmcSchemeCode");
                        Amccode=AmcSchemeCode;
                        listamccode.add(Amccode);

                        UniqueNo=jobj.getString("UniqueNo");
                        SipID=jobj.getString("SipID");
                        proportion=jobj.getString("proportion");
                        position=jobj.getString("position");
                        Returnvalue=jobj.getString("Returnvalue");
                        Year=jobj.getString("Year");
                        isDeleted=jobj.getString("isDeleted");
                        InvestedAmount=jobj.getString("InvestedAmount");
                        PotentialValue=jobj.getString("PotentialValue");
                        SaveTax_ID=jobj.getString("SaveTax_ID");
                        RegularIncome_ID=jobj.getString("RegularIncome_ID");
                        Park_ID=jobj.getString("Park_ID");
                        SchemeType=jobj.getString("SchemeType");
                        SchemeCode=jobj.getString("SchemeCode");
                        SchemeCode=SchemeCode;
                        listschemecode.add(SchemeCode);
                        System.out.println("Scheeeme:"+SchemeCode);


                        Top3Funds top3Funds=new Top3Funds();
                        top3Funds.setFundname(fundname);
                        top3Funds.setPrice("₹ "+Price);
                        arrayList1.add(top3Funds);


                    }
                    //getFolio();

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    mAdapter = new SaveTaxAdapter(arrayList1,getContext());
                    lv1.setLayoutManager(mLayoutManager);
                    lv1.setAdapter(mAdapter);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {

//          @Override

//          public Map<String, String> getHeaders() throws AuthFailureError {

//              HashMap<String, String> headers = new HashMap<String, String>();

//              headers.put("Content-Type", "application/json");

//              return headers;

//           }

        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

    private class ServerResultHandler implements ihttpResultHandler
    {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {

        }

        @Override
        public void onError(Object message) {

        }
    }



}
