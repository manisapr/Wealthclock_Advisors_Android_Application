package android.wealthclockadvisors.app.wealthclockadvisors.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.wealthclockadvisors.app.wealthclockadvisors.constant.APIConstant;
import android.wealthclockadvisors.app.wealthclockadvisors.constant.AppConstant;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpController;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.AMCListModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.AccountDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.CategoryDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.DashBoardModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundTypeModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.InvestMentDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MutualFundDetailsforModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.PortfolioDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.RedeemDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.wealthclockadvisors.app.wealthclockadvisors.model.User_DetailsForIMPS;
import android.wealthclockadvisors.app.wealthclockadvisors.model.xsipOrderEntryParamModel;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class httpController implements ihttpController {

    private static httpController _Instance;
    private RequestQueue _requestQueue;
    private ihttpResultHandler _iHttpResultHandler;

    public ihttpResultHandler get_iHttpResultHandler() {
        return _iHttpResultHandler;
    }

    public void set_iHttpResultHandler(ihttpResultHandler _iHttpResultHandler) {
        this._iHttpResultHandler = _iHttpResultHandler;
    }

    public static httpController getInstance() {

        if (_Instance == null)
            _Instance = new httpController();

        return _Instance;
    }

    private httpController() {

    }

    @Override
    public void sendRegistrationDetails(String name, String mob, String email, String pwd, Context context) {
        String url = "http://demo.wealthclockadvisors.com/MobileAppApi/MobileUserRegistration";

        JSONObject registrationObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            registrationObj.put("User_Name",name);
            registrationObj.put("User_Mob",mob);
            registrationObj.put("User_Password",pwd);
            registrationObj.put("User_Email",email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("jsonobj: "+registrationObj);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, registrationObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("sendRegistrationDetails response | sendRegistrationDetails: " + response);
                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess("success","","", " ","","","success");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sendRegistrationDetails onErrorResponse:| sendRegistrationDetails " + error.getMessage() + error);

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
    public void sendLoginData(String email, String password, final Context context) {
        String url = APIConstant.BASE_URL +APIConstant.LOGIN_URL;
        final JSONObject loginObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            loginObj.put("User_Email",email);
            loginObj.put("User_Password",password);
            loginObj.put("deviceType","2");
            loginObj.put("deviceInfo","");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("jsonobj: "+loginObj + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, loginObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        System.out.println("sendLoginData response | sendLoginData: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("LoginDetails");
                            JSONArray bankDetailsArray = jsonInfo.getJSONArray("OnboardDetails");
                            JSONObject bankObj = bankDetailsArray.getJSONObject(0);
                            JSONObject loginObject = loginDetailsArray.getJSONObject(0);
                             successCode = loginObject.getString("Code");
                             String clientCode = loginObject.getString("CLIENT_CODE");
                             String name = loginObject.getString("User_Name");
                             String path = loginObject.getString("Image");
                             String isXSIPActive = loginObject.getString("IsXSIPActive");

                            SharedPreferenceManager.setClientCode(context,clientCode);
                            SharedPreferenceManager.setUserName(context,name);
                            SharedPreferenceManager.setImagePath(context,path);
                            SharedPreferenceManager.setPanNo(context,bankObj.getString("PanNo"));
                            SharedPreferenceManager.setBankAccountNumber(context,bankObj.getString("BankAcntNumber"));
                            SharedPreferenceManager.setBankName(context,bankObj.getString("BankName"));
                            SharedPreferenceManager.setBankIfsceCode(context,bankObj.getString("IfscCode"));
                            SharedPreferenceManager.setIsXSIPActive(context,isXSIPActive);
                            SharedPreferenceManager.setIsISIPActive(context,loginObject.getString("IsISIPActive"));
                            SharedPreferenceManager.setManadateRegId(context,loginObject.getString("Mandate_Reg_ID"));

                            Utility.set_imagePath(path);


                            System.out.println("sendLoginData onErrorResponse:| sendLoginData " + response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(successCode,"","","","","","success");
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

        _requestQueue.add(jsObjRequest);

    }

    @Override
    public void getDasBoardInfo(String clientcode, final Context context) {
        System.out.println("gggggggggggg context: "+context);
        String url = APIConstant.BASE_URL +APIConstant.DASBOARD_URL;

        final JSONObject dasboardObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            dasboardObj.put("ClientCode",SharedPreferenceManager.getClientCode(context));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
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

                            SharedPreferenceManager.setUserName(context,UserName);
                            SharedPreferenceManager.setImagePath(context,Path);
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

                            System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvv");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("erorrrrrrrrrrrrrrrrrrrr");
                        }

                        if (_iHttpResultHandler != null)
                            System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh"+dashBoardModel.getCurrentMarketValue()+context);
                            _iHttpResultHandler.onSuccess(dashBoardModel,"call","","","","","DasBoardInfo");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getDasBoardInfo onErrorResponse:| getDasBoardInfo " + error.getMessage() + error);

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
    }

    @Override
    public void getAccountDetails(String email, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.ACCOUNT_URL;

        final JSONObject accountObj = new JSONObject();
        System.out.println("getAccountDetails: "+url + "obj: "+accountObj);
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            accountObj.put("Email",email);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }



        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, accountObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        AccountDetailsModel accountDetailsModel = new AccountDetailsModel();
                        String NetInvestment ="";
                        String CurrentMarketValue = "";
                        String AbsoluteReturns = "";
                        String XIRR = "";
                        String UserName = "";
                        String Path = "";
                        System.out.println("getAccountDetails response | getAccountDetails: " + response);
                        try {


                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("FirstApplicantDetails");
                            JSONArray addressArray = jsonInfo.getJSONArray("PermanentAddress");
                            JSONArray secondapplicantArray = jsonInfo.getJSONArray("SecondApplicantDetails");
                            JSONArray bankDetailsArray = jsonInfo.getJSONArray("BankDetails");
                            JSONArray nomineeDetailsArray = jsonInfo.getJSONArray("NomineeDetails");
                            JSONArray mandateDetailsArray = jsonInfo.getJSONArray("MandateDetails");


                            JSONObject loginObject = loginDetailsArray.getJSONObject(0);
                            JSONObject addressObj = addressArray.getJSONObject(0);
                            System.out.println("secondapplicantArray: "+secondapplicantArray.length());
                            if (secondapplicantArray.length()>0) {

                                JSONObject secondapplicationObj = secondapplicantArray.getJSONObject(0);
                                accountDetailsModel.setSecondApplicantName(secondapplicationObj.getString("Name"));
                                accountDetailsModel.setSecondApplicantName(secondapplicationObj.getString("DateOfBirth"));
                                accountDetailsModel.setSecondApplicantPanNo(secondapplicationObj.getString("PanNo"));
                                accountDetailsModel.setSecondApplicantGuardianName(secondapplicationObj.getString("GuardianName"));
                                accountDetailsModel.setSecondApplicantOccupationType(secondapplicationObj.getString("OccupationType"));

                            }
                            JSONObject bankObj = bankDetailsArray.getJSONObject(0);
                            JSONObject nomineeObj = nomineeDetailsArray.getJSONObject(0);
                            JSONObject mandatedetailsObj = mandateDetailsArray.getJSONObject(0);





                            /*if (secondapplicantArray !=null)
                            {
                                JSONObject secondapplicationObj = secondapplicantArray.getJSONObject(0);
                            }*/


                            /*JSONArray loginDetailsArray1 = jsonInfo.getJSONArray("SecondApplicantDetails");
                            JSONObject loginObject1 = loginDetailsArray1.getJSONObject(1);*/


                            accountDetailsModel.setFirstApplicantName(loginObject.getString("Name"));
                            accountDetailsModel.setFirstApplicantDateOfBirth(loginObject.getString("DateOfBirth"));
                            accountDetailsModel.setFirstApplicanPanNo(loginObject.getString("PanNo"));
                            accountDetailsModel.setFirstApplicantGuardianName(loginObject.getString("GuardianName"));
                            accountDetailsModel.setFirstApplicantOccupationType(loginObject.getString("OccupationType"));
                            accountDetailsModel.setPermanentAddress(addressObj.getString("PermanentAddress"));

                            accountDetailsModel.setBankName(bankObj.getString("BankName"));
                            accountDetailsModel.setBankAcntNo(bankObj.getString("BankAcntNumber"));
                            accountDetailsModel.setBankAccountType(bankObj.getString("BankAcntType"));
                            accountDetailsModel.setBankifsc(bankObj.getString("IfscCode"));


                            accountDetailsModel.setNomineeName(nomineeObj.getString("NomineeName"));
                            accountDetailsModel.setNomineeRelation(nomineeObj.getString("NomineeRelation"));
                            accountDetailsModel.setNomineeDob(nomineeObj.getString("NomineeDateofBirth"));
                            //CurrentMarketValue  = loginObject1.getString("PermanentAddress");


                            accountDetailsModel.setMandateId(mandatedetailsObj.getString("Mandate_Reg_ID"));


                            System.out.println("account deatils: "+loginObject+"address deatils: "+addressObj+", Xirr: -------" + secondapplicantArray + "bankObj: "+bankObj + "nomineeObj: "+nomineeObj + "mandatedetailsObj: "+mandatedetailsObj);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(accountDetailsModel,"","","","","","getAccountDetails");
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

        _requestQueue.add(jsObjRequest);
    }

    @Override
    public void getPortfolioData(String clientcode, Context context) {

        String url = APIConstant.BASE_URL +APIConstant.PORTFOLIO_URL;

        final JSONObject portfolio = new JSONObject();
        try {

            portfolio.put("ClientCode",clientcode);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, portfolio,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("cccccccccccccccccccccc: "+response);
                        ArrayList<InvestMentDetailsModel> investMentDetailsModelArrayList = new ArrayList<>();
                        ArrayList<CategoryDetailsModel> categoryDetailsModelArrayList = new ArrayList<>();
                        PortfolioDetailsModel  portfolioDetailsModel = new PortfolioDetailsModel();

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                                JSONArray currentHoldingDetailsArray = jsonInfo.getJSONArray("CurrentHoldingDetails");
                            JSONObject currentHoldingDetailsArrayJSONObject;
                            for (int i = 0; i < currentHoldingDetailsArray.length(); i++) {
                                InvestMentDetailsModel investMentDetailsModel  = new InvestMentDetailsModel();
                                 currentHoldingDetailsArrayJSONObject = currentHoldingDetailsArray.getJSONObject(i);
                                investMentDetailsModel.setFundName(currentHoldingDetailsArrayJSONObject.getString("schemeName"));
                                investMentDetailsModel.setFolioNo(currentHoldingDetailsArrayJSONObject.getString("folioNo"));
                                investMentDetailsModel.setFundInvestAmount(currentHoldingDetailsArrayJSONObject.getString("totalInv"));
                                investMentDetailsModel.setFundCurrentValue(currentHoldingDetailsArrayJSONObject.getString("currentValue"));
                                investMentDetailsModel.setFundXirr(currentHoldingDetailsArrayJSONObject.getString("XIRR"));
                                investMentDetailsModel.setSchemecode(currentHoldingDetailsArrayJSONObject.getString("schemecode"));
                                investMentDetailsModelArrayList.add(investMentDetailsModel);

                                System.out.println("currentHoldingDetailsArrayJSONObject: "+investMentDetailsModelArrayList );

                            }

                            JSONArray portfolioDetailsArray = jsonInfo.getJSONArray("PortfolioDetails");
                            JSONObject portfolioObj = portfolioDetailsArray.getJSONObject(0);
                            portfolioDetailsModel.setNetInvestment(portfolioObj.getString("NetInvestment"));
                            Utility.set_totalChangeFund(portfolioDetailsModel.getNetInvestment());
                            portfolioDetailsModel.setCurrentMarketValue(portfolioObj.getString("CurrentMarketValue"));
                            portfolioDetailsModel.setXirrValue(portfolioObj.getString("XirrValue"));


                            JSONArray categoryDetails = jsonInfo.getJSONArray("CategoryDetails");
                            JSONObject categoryDetailsObj;
                            for (int j = 0 ;j<categoryDetails.length();j++) {
                                CategoryDetailsModel categoryDetailsModel = new CategoryDetailsModel();
                                 categoryDetailsObj = categoryDetails.getJSONObject(j);
                                 categoryDetailsModel.setCategoryName(categoryDetailsObj.getString("categoryName"));
                                 categoryDetailsModel.setCategory_percentage(categoryDetailsObj.getString("category_percentage"));
                                System.out.println("category_percentage: "+categoryDetailsModel.getCategory_percentage());
                                categoryDetailsModelArrayList.add(categoryDetailsModel);
                            }

                            System.out.println("getPortfolioData | httpController: "+investMentDetailsModelArrayList + " categoryDetails: "+categoryDetailsModelArrayList.get(0) );



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(categoryDetailsModelArrayList, portfolioDetailsModel,investMentDetailsModelArrayList,"","","","getPortfolioData");
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

        _requestQueue.add(jsObjRequest);

    }

    @Override
    public void getFundDetails(String clientCode, String schemeCode, String foliono, Context context) {

        String url = APIConstant.BASE_URL +APIConstant.FUND_DETAILS_URL;

        final JSONObject funddetailsObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            funddetailsObj.put("ClientCode",clientCode);
            funddetailsObj.put("SchemeCode",schemeCode);
            funddetailsObj.put("FolioNumber",foliono);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getFundDetails: "+funddetailsObj + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, funddetailsObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        FundDetailsModel fundDetailsModel = new FundDetailsModel();
                        System.out.println("getFundDetails response | getFundDetails: " + response);
                        try {


                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundDetails");
                            JSONObject loginObject = loginDetailsArray.getJSONObject(0);


                            fundDetailsModel.setTotalInvest(loginObject.getString("totalInv"));
                            fundDetailsModel.setCurrentValue(loginObject.getString("currentValue"));
                            fundDetailsModel.setUnitBalance(loginObject.getString("unitBal"));
                            fundDetailsModel.setCategory(loginObject.getString("category"));
                            fundDetailsModel.setSchemecode(loginObject.getString("schemecode"));
                            fundDetailsModel.setFolioNo(loginObject.getString("folioNo"));
                            fundDetailsModel.setNavDetails(loginObject.getString("navDetails"));
                            fundDetailsModel.setAbsoluteReturn(loginObject.getString("absoluteReturn"));
                            fundDetailsModel.setReturn_percent(loginObject.getString("Return_percent"));
                            fundDetailsModel.setXIRR(loginObject.getString("XIRR"));
                            fundDetailsModel.setSchemeName(loginObject.getString("schemeName"));
                            fundDetailsModel.setGoalType(loginObject.getString("GoalType"));
                            fundDetailsModel.setDividendReunvestment(loginObject.getString("DividendReinvest"));



                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        if (_iHttpResultHandler != null)

                        _iHttpResultHandler.onSuccess(fundDetailsModel,"","","","","","getFundDetails");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getDasBoardInfo onErrorResponse:| getDasBoardInfo " + error.getMessage() + error);

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
    public void getFundNameList(Context context) {
        String url = APIConstant.BASE_URL + APIConstant.GET_AMC_LIST_URL;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<AMCListModel> AMCList = new ArrayList<>();

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray amcList = jsonInfo.getJSONArray("AmcDetails");

                            for (int i = 0; i < amcList.length(); i++) {
                                JSONObject amcObj = amcList.getJSONObject(i);
                                AMCListModel amcListModel = new AMCListModel();
                                amcListModel.setAmcSchemeName(amcObj.getString("AmcSchemeName"));
                                amcListModel.setAmcSchemeCode(amcObj.getString("AmcSchemeCode"));

                                AMCList.add(amcListModel);

                               // System.out.println("getFundNameList | respons: "+amcListModel.getAmcSchemeName());

                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("getFundNameList response | getFundNameList: " + response);
                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(AMCList,"","", " ","","","getFundNameList");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getFundNameList onErrorResponse:| getFundNameList " + error.getMessage() + error);

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
    public void getFundType(String clientCode, String amcCode, Context context) {

        String url = APIConstant.BASE_URL +APIConstant.FUND_TYPE_URL;
        final JSONObject fundtype = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            fundtype.put("ClientCode",clientCode);
            fundtype.put("amccode",amcCode);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("jsonobj: "+fundtype + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, fundtype,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String fundtype[] = {""};
                        ArrayList<FundTypeModel> stringArrayList = new ArrayList<>();
                        ArrayList<FundTypeModel> fundTypeModelArrayList = new ArrayList<>();
                        //System.out.println("getFundType response | getFundType: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundType");
                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            fundtype = new String[loginDetailsArray.length()];
                            for (int i=0;i<loginDetailsArray.length();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject loginObject = loginDetailsArray.getJSONObject(i);
                                fundTypeModel.setSchemType( loginObject.getString("SchemeType"));
                                stringArrayList.add(fundTypeModel);
                            }
                            if (foliodetails.length()>0)
                            {
                                for (int i=0;i<foliodetails.length();i++) {
                                    FundTypeModel fundTypeModel = new FundTypeModel();
                                    JSONObject folioobj = foliodetails.getJSONObject(i);
                                    fundTypeModel.setFolioNo(folioobj.getString("FolioNo"));

                                    fundTypeModelArrayList.add(fundTypeModel);
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(stringArrayList,fundTypeModelArrayList,"","","","","getFundType");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getFundType onErrorResponse:| getFundType " + error.getMessage() + error);

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
    public void getfundNameAllList(String amccode, String schemetype, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.FUND_NAME_LIST;
        final JSONObject fundObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            fundObj.put("amccode",amccode);
            fundObj.put("schemetype",schemetype);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        //System.out.println("getfundNameAllList: "+fundObj + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, fundObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String name[] = {""};
                        ArrayList<String> schemenameList = new ArrayList<>();
                        //System.out.println("getfundNameAllList response | getFundType: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundName");
                            name = new String[loginDetailsArray.length()];
                            for (int i=0;i<loginDetailsArray.length();i++) {
                                JSONObject loginObject = loginDetailsArray.getJSONObject(i);
                                name[i] = loginObject.getString("SchemeName");
                                schemenameList.add(i,loginObject.getString("SchemeName"));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            System.out.println("name list success: "+name);
                            _iHttpResultHandler.onSuccess(schemenameList,"","","","","","getfundNameAllList");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getFundType onErrorResponse:| getFundType " + error.getMessage() + error);

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
    public void getMinimumAmountData(String schemename, String schemetype, String plan, Context context) {
        String url = APIConstant.BASE_URL + APIConstant.GET_MINIMUM_FUND_AMOUNT;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject minimumamount = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            minimumamount.put("SchemeName",schemename);
            minimumamount.put("schemetype",schemetype);
            minimumamount.put("Plan",plan);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("getMinimumAmountData response | JSONOBJECT: "+minimumamount);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,minimumamount,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                       String minimumAmount = "";
                       String additionalAmount="";

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray amcList = jsonInfo.getJSONArray("MinAmount");
                            JSONObject amcObj = amcList.getJSONObject(0);
                            minimumAmount = amcObj.getString("MinimumPurchaseAmount");
                            additionalAmount = amcObj.getString("AdditionalPurchaseAmountMultiple");


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("getMinimumAmountData response | getMinimumAmountData: " + response);
                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(minimumAmount,additionalAmount,"", " ","","","getMinimumAmountData");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getMinimumAmountData onErrorResponse:| getMinimumAmountData " + error.getMessage() + error);

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
    public void purchase(final OrderEntryModel orderEntryModel, Context context) {
        String url="";
        if (orderEntryModel.getFolioNo().isEmpty() || orderEntryModel.getFolioNo().equalsIgnoreCase(" ")) {
             url = APIConstant.PURCHASE_WITHOUT_FOLIO;
        }
        else {
            url = APIConstant.PURCHASE_WITH_FOLIO;
        }
        final JSONObject purchase = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            purchase.put("TransCode","NEW");
            purchase.put("TransNo","");
            purchase.put("OrderId","");
            purchase.put("UserID","");
            purchase.put("MemberId","");
            purchase.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            purchase.put("PanNumber",SharedPreferenceManager.getPanno(context));
            purchase.put("SchemeCd",orderEntryModel.getSchemeCd());
            purchase.put("AmcCode",orderEntryModel.getAmcCode());
            purchase.put("BuySell","P");

            purchase.put("DPTxn","P");
            purchase.put("OrderVal",orderEntryModel.getOrderVal());
            purchase.put("Qty","");
            purchase.put("AllRedeem","Y");
            if (orderEntryModel.getFolioNo().isEmpty() || orderEntryModel.getFolioNo().equalsIgnoreCase(" ")) {
                purchase.put("FolioNo", "");
                purchase.put("BuySellType","FRESH");
            }
            else {
                purchase.put("FolioNo", orderEntryModel.getFolioNo());
                purchase.put("BuySellType","ADDITIONAL");
            }
            purchase.put("Remarks","");
            purchase.put("KYCStatus","");
            purchase.put("RefNo","");
            purchase.put("SubBrCode","");
            purchase.put("EUIN","E172987");
            purchase.put("EUINVal","Y");
            purchase.put("MinRedeem","Y");
            purchase.put("DPC","Y");
            purchase.put("IPAdd","");
            purchase.put("Password","");
            purchase.put("PassKey","");
            purchase.put("Param1","");
            purchase.put("Param2","");
            purchase.put("Param3","");
            purchase.put("BankacNo",SharedPreferenceManager.getBankAccountNumber(context));
            purchase.put("IFSC",SharedPreferenceManager.getBankIfscCode(context));
            purchase.put("BankName",SharedPreferenceManager.getBankName(context));
            purchase.put("PaymentMode",orderEntryModel.getPaymentMode());



        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("purchase obj: "+purchase + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, purchase,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String description = "";
                        String info = " ";
                        String fundtype[] = {""};

                        System.out.println("purchase response | purchase: " + response);
                        try {
                            String responseStream = String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo = jsonResponse;
                            //JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundType");
                            if (jsonInfo.getString("IsSuccess").equalsIgnoreCase("true")) {
                                successCode = jsonInfo.getString("IsSuccess");

                            }
                            else {
                                successCode = "false";
                                description = jsonInfo.getString("Description");
                            }
                            if (orderEntryModel.getPaymentMode().equalsIgnoreCase("yes"))
                            {
                                System.out.println("gsjyaysydgs");
                                info = jsonInfo.getString("Info");
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
                System.out.println("getFundType onErrorResponse:| getFundType " + error.getMessage() + error);

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
    public void getSchemeCode(MutualFundDetailsforModel mutualFundDetailsforModel, Context context) {
        String url = APIConstant.BASE_URL + APIConstant.GET_SCHEME_CODE_FOR_PURCHASE;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject schemeObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            schemeObj.put("AmcCode",mutualFundDetailsforModel.getAmcCode());
            schemeObj.put("SchemeName",mutualFundDetailsforModel.getSchemeName());
            schemeObj.put("SchemeType",mutualFundDetailsforModel.getSchemeType());
            schemeObj.put("Plan","Growth");
            schemeObj.put("DividendDivision","");
            schemeObj.put("Amount",mutualFundDetailsforModel.getAmount());
            schemeObj.put("Folio","new");
            schemeObj.put("PaymentMode","no");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("getSchemeCode response | JSONOBJECT: "+schemeObj);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,schemeObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String schemeCode = "";

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SchemeCode");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            schemeCode = amcObj.getString("SchemeCode");

                            System.out.println("schemeObj | respnse: "+response);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("getSchemeCode response | getSchemeCode: " + response);
                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(schemeCode,"","", " ","","","getSchemeCode");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getSchemeCode onErrorResponse:| getSchemeCode " + error.getMessage() + error);

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
    public void getRedeemtionSchemeList(String clientcode, String type,Context context) {
        String url = APIConstant.BASE_URL +APIConstant.REDEEM_URL;
        final JSONObject schemename = new JSONObject();
        try {
            if (type.equalsIgnoreCase("N")) {
                //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
                schemename.put("ClientCode", SharedPreferenceManager.getClientCode(context));
            }
            else {
                schemename.put("ClientCode", SharedPreferenceManager.getClientCode(context));
                schemename.put("Type","I");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getRedeemtionSchemeList: "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<FundTypeModel> stringArrayList = new ArrayList<>();

                        System.out.println("getRedeemtionSchemeList response | getRedeemtionSchemeList: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SchemeList");

                            for (int i=0;i<loginDetailsArray.length();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject loginObject = loginDetailsArray.getJSONObject(i);
                                fundTypeModel.setSchemeCode( loginObject.getString("SchemeCode"));
                                fundTypeModel.setSchemeName(loginObject.getString("SchemeName"));
                                stringArrayList.add(fundTypeModel);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(stringArrayList,"","","","","","getRedeemtionSchemeList");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getRedeemtionSchemeList onErrorResponse:| getRedeemtionSchemeList " + error.getMessage() + error);

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
    public void getRedeemFolioList(String clientCode, String schemeCode, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.REDEEM_FOLIO_URL;
        final JSONObject schemename = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            schemename.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            schemename.put("scheme",schemeCode);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getRedeemtionSchemeList: "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<FundTypeModel> stringArrayList = new ArrayList<>();

                        System.out.println("getRedeemtionSchemeList response | getRedeemtionSchemeList: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("FolioList");

                            for (int i=0;i<loginDetailsArray.length();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject loginObject = loginDetailsArray.getJSONObject(i);
                                fundTypeModel.setFolioNo( loginObject.getString("FolioNumber"));
                                stringArrayList.add(fundTypeModel);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(stringArrayList,"","","","","","getRedeemFolioList");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getRedeemtionSchemeList onErrorResponse:| getRedeemtionSchemeList " + error.getMessage() + error);

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
    public void getRedemptionDetails(String clientCode, String schemeCode, String folio, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.REDEEM_DETAILS;
        final JSONObject schemename = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            schemename.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            schemename.put("scheme",schemeCode);
            schemename.put("Folio",folio);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getRedemptionDetails: "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<RedeemDetailsModel> stringArrayList = new ArrayList<>();

                        System.out.println("getRedemptionDetails response | getRedemptionDetails: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("RedemptionDetails");

                            RedeemDetailsModel fundTypeModel = new RedeemDetailsModel();
                                JSONObject loginObject = loginDetailsArray.getJSONObject(0);
                                fundTypeModel.setAmount( loginObject.getString("Amount"));
                                fundTypeModel.setUnits(loginObject.getString("Units"));
                                stringArrayList.add(fundTypeModel);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(stringArrayList,"","","","","","getRedemptionDetails");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getRedemptionDetails onErrorResponse:| getRedemptionDetails " + error.getMessage() + error);

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
    public void redeemFund(OrderEntryModel orderEntryModel, Context context) {
        String url = APIConstant.REDEEM_AMOUNT_URL;
        final JSONObject schemename = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            schemename.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            schemename.put("SchemeCd",orderEntryModel.getSchemeCd());
            schemename.put("FolioNo",orderEntryModel.getFolioNo());
            System.out.println("OrderModel :  "+orderEntryModel.getSchemeCd());
                if (orderEntryModel.getOrderVal() != null || !orderEntryModel.getOrderVal().equalsIgnoreCase("null")) {
                    schemename.put("AllRedeem", "N");
                    schemename.put("OrderVal",orderEntryModel.getOrderVal());
                }

            if (orderEntryModel.getOrderVal().equalsIgnoreCase("entire")){
                schemename.put("AllRedeem", "Y");
                schemename.put("OrderVal","");
            }

            schemename.put("PanNumber",SharedPreferenceManager.getPanno(context));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("redeemFund: "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<RedeemDetailsModel> stringArrayList = new ArrayList<>();

                        System.out.println("redeemFund response | redeemFund: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            /*JSONArray loginDetailsArray = jsonInfo.getJSONArray("RedemptionDetails");

                            RedeemDetailsModel fundTypeModel = new RedeemDetailsModel();
                            JSONObject loginObject = loginDetailsArray.getJSONObject(0);
                            fundTypeModel.setAmount( loginObject.getString("Amount"));
                            fundTypeModel.setUnits(loginObject.getString("Units"));
                            stringArrayList.add(fundTypeModel);*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess("","","","","","","redeemFund");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("redeemFund onErrorResponse:| redeemFund " + error.getMessage() + error);

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
    public void getFamilyDetails(String clientcode, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.FAMILY_DETAILS_URL;

        final JSONObject portfolio = new JSONObject();
        try {

            portfolio.put("ClientCode",SharedPreferenceManager.getClientCode(context));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, portfolio,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("getFamilyDetails: "+response);
                        ArrayList<InvestMentDetailsModel> familyInvestmentList = new ArrayList<>();
                        String investmentDetails[]={"jhjh","gfgft","ghg"};


                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;

                            JSONArray currentHoldingDetailsArray = jsonInfo.getJSONArray("FamilyInvestMentDetails");
                            JSONArray   totalArray = jsonInfo.getJSONArray("TotalInvestmentDetails");
                            JSONObject currentHoldingDetailsArrayJSONObject;
                            JSONObject totalArrayObj;
                            for (int i = 0; i < currentHoldingDetailsArray.length(); i++) {
                                InvestMentDetailsModel investMentDetailsModel  = new InvestMentDetailsModel();
                                currentHoldingDetailsArrayJSONObject = currentHoldingDetailsArray.getJSONObject(i);
                                investMentDetailsModel.setClientCode(currentHoldingDetailsArrayJSONObject.getString("ClientCode"));
                                investMentDetailsModel.setNetInvestment(currentHoldingDetailsArrayJSONObject.getString("NetInvestment"));
                                investMentDetailsModel.setFundCurrentValue(currentHoldingDetailsArrayJSONObject.getString("CurrentMarketValue"));
                                investMentDetailsModel.setNetXirr(currentHoldingDetailsArrayJSONObject.getString("Xirr"));
                                investMentDetailsModel.setName(currentHoldingDetailsArrayJSONObject.getString("ClientName"));
                                investMentDetailsModel.setImagepath(currentHoldingDetailsArrayJSONObject.getString("ImagePath"));
                                investMentDetailsModel.setRelationship(currentHoldingDetailsArrayJSONObject.getString("Relationship"));
                                familyInvestmentList.add(investMentDetailsModel);

                            }

                            totalArrayObj = totalArray.getJSONObject(0);

                            investmentDetails[0] = totalArrayObj.getString("NetInvestment");
                            investmentDetails[1] = totalArrayObj.getString("CurrentMarketValue");

                            /*JSONArray portfolioDetailsArray = jsonInfo.getJSONArray("PortfolioDetails");
                            JSONObject portfolioObj = portfolioDetailsArray.getJSONObject(0);
                            portfolioDetailsModel.setNetInvestment(portfolioObj.getString("NetInvestment"));
                            Utility.set_totalChangeFund(portfolioDetailsModel.getNetInvestment());
                            portfolioDetailsModel.setCurrentMarketValue(portfolioObj.getString("CurrentMarketValue"));
                            portfolioDetailsModel.setXirrValue(portfolioObj.getString("XirrValue"));


                            JSONArray categoryDetails = jsonInfo.getJSONArray("CategoryDetails");
                            JSONObject categoryDetailsObj;
                            for (int j = 0 ;j<categoryDetails.length();j++) {
                                CategoryDetailsModel categoryDetailsModel = new CategoryDetailsModel();
                                categoryDetailsObj = categoryDetails.getJSONObject(j);
                                categoryDetailsModel.setCategoryName(categoryDetailsObj.getString("categoryName"));
                                categoryDetailsModel.setCategory_percentage(categoryDetailsObj.getString("category_percentage"));
                                System.out.println("category_percentage: "+categoryDetailsModel.getCategory_percentage());
                                categoryDetailsModelArrayList.add(categoryDetailsModel);
                            }*/

                            //System.out.println("getPortfolioData | httpController: "+investMentDetailsModelArrayList + " categoryDetails: "+categoryDetailsModelArrayList.get(0) );



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            System.out.println("getFamilyDetails: |  success: "+familyInvestmentList );
                            _iHttpResultHandler.onSuccess(familyInvestmentList, investmentDetails,"","","","","getFamilyDetails");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getFamilyDetails onErrorResponse:| getFamilyDetails " + error.getMessage() + error);

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
    public void sipDateApi(OrderEntryModel orderEntryModel, Context context) {
        String url = APIConstant.BASE_URL + APIConstant.SIP_DATE_URL ;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject schemeObj = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
           /* schemeObj.put("AmcCode",mutualFundDetailsforModel.getAmcCode());
            schemeObj.put("SchemeName",mutualFundDetailsforModel.getSchemeName());
            schemeObj.put("SchemeType",mutualFundDetailsforModel.getSchemeType());
            schemeObj.put("Plan","Growth");
            schemeObj.put("DividendDivision","");
            schemeObj.put("Amount",mutualFundDetailsforModel.getAmount());
            schemeObj.put("Folio","new");
            schemeObj.put("PaymentMode","no");*/

            schemeObj.put("Plan","Growth");
            schemeObj.put("DividendDivision","");
            schemeObj.put("FundName",orderEntryModel.getSchemeName());
            schemeObj.put("FundType",orderEntryModel.getSchemeCd());

            System.out.println("sipDateApi | orderEntryModel:- "+orderEntryModel.getSchemeCd() + "another:- "+orderEntryModel.getAmcCode()+"schemename:- "+orderEntryModel.getSchemeName());


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sipDateApi response | JSONOBJECT: "+schemeObj);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,schemeObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String dates = "";
                        String amount = "";

                        /*try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SchemeCode");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            schemeCode = amcObj.getString("SchemeCode");

                            System.out.println("schemeObj | respnse: "+response);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }*/


                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SipDates");
                            JSONArray loginDetailsArray1 = jsonInfo.getJSONArray("SipMinAmount");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            JSONObject amcObj1 = loginDetailsArray1.getJSONObject(0);
                            dates = amcObj.getString("SIP_DATES");
                            amount = amcObj1.getString("SIP_MINIMUM_INSTALLMENT_AMOUNT");

                            System.out.println("sipDateApi response | dates: " + dates);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        System.out.println("sipDateApi response | sipDateApi: " + response);
                        if (_iHttpResultHandler != null)
                            if (!dates.isEmpty())
                            _iHttpResultHandler.onSuccess(dates,amount,"", " ","","","sipDateApi");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sipDateApi onErrorResponse:| sipDateApi " + error.getMessage() + error);

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
    public void sip(final xsipOrderEntryParamModel xsipOrderEntryParamM, Context context) {
        String url =  APIConstant.SIP ;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject schemeObj = new JSONObject();
        try {
            //schemeObj.put("DividendDivision",xsipOrderEntryParamM.getd);
            schemeObj.put("panNumber",SharedPreferenceManager.getPanno(context));
            schemeObj.put("AmcCode",xsipOrderEntryParamM.getAmcCode());
            schemeObj.put("IFSC",SharedPreferenceManager.getBankIfscCode(context));
            schemeObj.put("BankacNo",SharedPreferenceManager.getBankAccountNumber(context));
            schemeObj.put("BankName",SharedPreferenceManager.getBankName(context));
            schemeObj.put("Param2",xsipOrderEntryParamM.getParam2());
            schemeObj.put("MandateID","");
            schemeObj.put("IsipStatus","True");
            schemeObj.put("FrequencyType","MONTHLY");
            schemeObj.put("InstallmentAmount",xsipOrderEntryParamM.getInstallmentAmount());
            schemeObj.put("NoOfInstallment",xsipOrderEntryParamM.getNoOfInstallment());
            schemeObj.put("SchemeCode",xsipOrderEntryParamM.getSchemeCode());
            schemeObj.put("StartDate",xsipOrderEntryParamM.getStartDate());
            schemeObj.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            schemeObj.put("PaymentMode",xsipOrderEntryParamM.getPaymentMode());
           /* if (xsipOrderEntryParamM.getFolioNo()!=null && !xsipOrderEntryParamM.getFolioNo().isEmpty()) {
                schemeObj.put("FolioNo", xsipOrderEntryParamM.getFolioNo());
            }
            else {
                schemeObj.put("FolioNo", "");
            }
            schemeObj.put("PaymentMode","no");*/

            schemeObj.put("FolioNo", xsipOrderEntryParamM.getFolioNo());
            /*schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);
            schemeObj.put("",);*/

        }
        catch (JSONException e)
        {

        }
        System.out.println("sip | schemeObj: " + schemeObj);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,schemeObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String description = "";
                        String successCode = "";
                        String info = " ";

                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            /*JSONArray loginDetailsArray = jsonInfo.getJSONArray("SipDates");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            dates = amcObj.getString("SIP_DATES");*/


                           /* String responseStream = String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo = jsonResponse;
                            //JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundType");
                            if (jsonInfo.getString("IsSuccess").equalsIgnoreCase("true")) {
                                successCode = jsonInfo.getString("IsSuccess");

                            }
                            else {
                                successCode = "false";
                                description = jsonInfo.getString("Description");
                            }*/



                            if (jsonInfo.getString("IsSuccess").equalsIgnoreCase("true")) {
                                successCode = jsonInfo.getString("IsSuccess");

                            }
                            else {
                                successCode = "false";
                            }

                            if (xsipOrderEntryParamM.getPaymentMode().equalsIgnoreCase("yes"))
                            {
                                System.out.println("gsjyaysydgs");
                                info = jsonInfo.getString("Info");
                            }
                            else {

                                description = jsonInfo.getString("Description");
                            }


                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        System.out.println("sip response | sip: " + response +successCode);
                        if (_iHttpResultHandler != null)
                                _iHttpResultHandler.onSuccess(successCode,description,info, " ","","","sip");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("sip onErrorResponse:| sip " + error.getMessage() + error);

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
    public void getMandatetId(xsipOrderEntryParamModel xsipOrderEntryParam, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.MANDATE_ID;
        final JSONObject schemename = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            //schemename.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            schemename.put("AmcCode",xsipOrderEntryParam.getAmcCode());
            schemename.put("IfscCode",SharedPreferenceManager.getBankIfscCode(context));
            schemename.put("MandateID",SharedPreferenceManager.getsetManadateRegId(context));
            schemename.put("ISip",SharedPreferenceManager.getIsISIPActive(context));
            schemename.put("XSip",SharedPreferenceManager.getIsXSIPActive(context));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getMandatetId: "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        xsipOrderEntryParamModel sipmodel = new xsipOrderEntryParamModel();
                        System.out.println("getMandatetId response | getMandatetId: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SipStatus");

                                JSONObject loginObject = loginDetailsArray.getJSONObject(0);
                                sipmodel.setParam2(loginObject.getString("Param2"));
                                sipmodel.setMandateID(loginObject.getString("MandateID"));
                                sipmodel.setXsipRegID(loginObject.getString("XsipStatus"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(sipmodel,"","","","","","getMandatetId");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getMandatetId onErrorResponse:| getMandatetId " + error.getMessage() + error);

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
    public void getFundListInGoalPlanner(Top3Funds top3Funds, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.FUND_GOAL_PLANNER;
        final JSONObject schemename = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            //schemename.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            //schemename.put("AmcCode",xsipOrderEntryParam.getAmcCode());
            schemename.put("amount",top3Funds.getInvestedAmount());
            schemename.put("type","Personal");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getFundListInGoalPlanner: "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        ArrayList<Top3Funds> top3FundsArrayList = new ArrayList<>();
                        System.out.println("getFundListInGoalPlanner response | getFundListInGoalPlanner: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("GoalPlanFund");
                            for (int i = 0 ;i<loginDetailsArray.length();i++) {
                                Top3Funds sipmodel = new Top3Funds();
                                JSONObject loginObject = loginDetailsArray.getJSONObject(i);
                                sipmodel.setFundname(loginObject.getString("FundName"));
                                sipmodel.setPrice(loginObject.getString("Price"));
                                sipmodel.setAmcSchemeCode(loginObject.getString("AMCCode"));
                                sipmodel.setSchemeType(loginObject.getString("_goalSchemeSchemeCode"));
                                top3FundsArrayList.add(sipmodel);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(top3FundsArrayList,"","","","","","getFundListInGoalPlanner");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getFundListInGoalPlanner onErrorResponse:| getFundListInGoalPlanner " + error.getMessage() + error);

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
    public void redeemIMPS(User_DetailsForIMPS user_detailsForIMPS, Context context) {
        String url = APIConstant.REDEEM_IMPS;
        final JSONObject schemename = new JSONObject();
        try {

            schemename.put("FolioNo",user_detailsForIMPS.getFolioNo());
            schemename.put("Scheme_Code",user_detailsForIMPS.getScheme_Code());
            schemename.put("Source",user_detailsForIMPS.getSource());
            schemename.put("RedeemAmount",user_detailsForIMPS.getRedeemAmount());
            schemename.put("BAL"," ");
            schemename.put("AUM"," ");
            schemename.put("RETURNCODE"," ");
            schemename.put("ALLOWIMPS"," ");
            schemename.put("MESSAGE"," ");
            schemename.put("Email",SharedPreferenceManager.getUserEmail(context));
            schemename.put("Mobile"," ");
            schemename.put("RedemOption"," ");




        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("redeemIMPS Object:- "+schemename + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, schemename,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String successCode ="";
                        String description = "";
                        String info = " ";
                            try {
                                String responseStream = String.valueOf(response);
                                final JSONObject jsonResponse = new JSONObject(responseStream);
                                JSONObject jsonInfo = jsonResponse;
                                //JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundType");
                                if (jsonInfo.getString("IsSuccess").equalsIgnoreCase("true")) {
                                    successCode = jsonInfo.getString("IsSuccess");

                                }
                                else {
                                    successCode = "false";
                                    description = jsonInfo.getString("Description");
                                }

                            }


                         catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(successCode,description,"","","","","redeemIMPS");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("redeemIMPS onErrorResponse:| redeemIMPS " + error.getMessage() + error);

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
    public void multifundSip(ArrayList<Top3Funds> top3FundsArrayList,ArrayList<Top3Funds> list,String id,String d1,String d2,String d3,String type,String paymentmode,Context context) {
        AndroidNetworking.initialize(context.getApplicationContext());
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        //change in date picket to set lenght list.size()
        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<top3FundsArrayList.size();i++)
        {
           JSONObject schemeObj = new JSONObject();
            Top3Funds top3Funds = top3FundsArrayList.get(i);
            try {
                //schemeObj.put("DividendDivision",xsipOrderEntryParamM.getd);

                schemeObj.put("SchemeCode", top3Funds.getSchemeType());
                schemeObj.put("IFSC", SharedPreferenceManager.getBankIfscCode(context));
                schemeObj.put("BankacNo", SharedPreferenceManager.getBankAccountNumber(context));
                schemeObj.put("BankName", SharedPreferenceManager.getBankName(context));
                schemeObj.put("Param2", id);
                schemeObj.put("PanNumber","");
                schemeObj.put("MandateID", "");
                schemeObj.put("IsipStatus", "");
                schemeObj.put("FrequencyType", "MONTHLY");
                double result = Double.parseDouble(top3Funds.getPrice());
                String cc=String.format("%.0f", result);
                schemeObj.put("InstallmentAmount",cc);
                double tim= Double.parseDouble(type)*12;
                String tymm= String.valueOf(tim);
                schemeObj.put("NoOfInstallment", String.format("%.0f", tim));
                if (i==0) {
                    schemeObj.put("StartDate", d1);
                }
                if (i==1) {
                    schemeObj.put("StartDate", d2);
                }
                if (i==2) {
                    schemeObj.put("StartDate", d3);
                }

                schemeObj.put("ClientCode", SharedPreferenceManager.getClientCode(context));
                schemeObj.put("PaymentMode", paymentmode);
                schemeObj.put("XsipRegID","");
                schemeObj.put("XsipStatus","");
                schemeObj.put("AmcCode","");
           /* if (xsipOrderEntryParamM.getFolioNo()!=null && !xsipOrderEntryParamM.getFolioNo().isEmpty()) {
                schemeObj.put("FolioNo", xsipOrderEntryParamM.getFolioNo());
            }
            else {
                schemeObj.put("FolioNo", "");
            }
            schemeObj.put("PaymentMode","no");*/
                //System.out.println("folio list size :L- "+list.get(0).getSipID());
                if (list.get(i).getSipID().trim()!=null && list.get(i).getSipID().trim().isEmpty())
                {
                    schemeObj.put("FolioNo", list.get(i).getSipID());
                }
                else {
                    schemeObj.put("FolioNo", list.get(i).getSipID());
                }
                jsonArray.put(schemeObj);

            } catch (JSONException e) {

            }
        }

        String url =  APIConstant.MULTI_FUND_SIP ;

        System.out.println("multifundSip:- "+jsonArray);

        AndroidNetworking.post("https://www.wealthclockadvisors.com/API/MultipleOrder/SipOrder")
                .addJSONArrayBody(jsonArray)
                .setTag("BSE")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("AndroidNetworking |onResponse:- "+response);
                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(response,"","","","","","multifundSip");
                    }

                    @Override
                    public void onError(ANError anError) {
                        System.out.println("AndroidNetworking |onResponse:- "+anError);
                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onError(anError.getMessage());
                    }
                });
    }

    @Override
    public void getMultiFundFolioList(Top3Funds arrayList, final String n, Context context) {
        String url = APIConstant.BASE_URL +APIConstant.FUND_TYPE_URL;
        final JSONObject fundtype = new JSONObject();
        try {
            //kitchenimageObj.put(APIConstant.PARAM_ID, kitchenImageModel.getId());
            fundtype.put("ClientCode",SharedPreferenceManager.getClientCode(context));
            fundtype.put("amccode",arrayList.getAmcSchemeCode());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        System.out.println("getMultiFundFolioList: "+fundtype + "url: "+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, fundtype,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //ArrayList<FundTypeModel> stringArrayList = new ArrayList<>();
                        ArrayList<Top3Funds> fundTypeModelArrayList = new ArrayList<>();
                        //System.out.println("getFundType response | getFundType: " + response);
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            //JSONArray loginDetailsArray = jsonInfo.getJSONArray("FundType");
                            JSONArray foliodetails = jsonInfo.getJSONArray("FolioNo");
                            //fundtype = new String[loginDetailsArray.length()];
                            /*for (int i=0;i<loginDetailsArray.length();i++) {
                                FundTypeModel fundTypeModel = new FundTypeModel();
                                JSONObject loginObject = loginDetailsArray.getJSONObject(i);
                                fundTypeModel.setSchemType( loginObject.getString("SchemeType"));
                                stringArrayList.add(fundTypeModel);
                            }*/

                            System.out.println("getMultiFundFolioList response:- "+response);

                                if (foliodetails.length()>0) {
                                    Top3Funds fundTypeModel = new Top3Funds();
                                    JSONObject folioobj = foliodetails.getJSONObject(0);
                                    System.out.println("objeeect:- " + folioobj);
                                    fundTypeModel.setSipID(folioobj.getString("FolioNo"));
                                    fundTypeModelArrayList.add(fundTypeModel);
                                }
                                else {
                                    Top3Funds fundTypeModel = new Top3Funds();
                                    fundTypeModel.setSipID(" ");
                                    fundTypeModelArrayList.add(fundTypeModel);
                                }

                            System.out.println("objeeect fundTypeModelArrayList:- "+fundTypeModelArrayList.size());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onSuccess(fundTypeModelArrayList," ","","","","","getMultiFundFolioList");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("getMultiFundFolioList onErrorResponse:| getMultiFundFolioList " + error.getMessage() + error);

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
    public void dateformultifund(String schemecd, Context context) {
        String url = APIConstant.BASE_URL + APIConstant.MULTI_SIP_DATE_URL ;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject schemeObj = new JSONObject();
        try {
            schemeObj.put("schemecode",schemecd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dateformultifund response | JSONOBJECT: "+schemeObj);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,schemeObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String dates = "";
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SipDates");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            dates = amcObj.getString("sip_dates");


                            System.out.println("dateformultifund response | dates: " + dates);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        System.out.println("dateformultifund response | dateformultifund: " + response);
                        if (_iHttpResultHandler != null)
                            if (!dates.isEmpty())
                                _iHttpResultHandler.onSuccess(dates,"","", " ","","","dateformultifund");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("dateformultifund onErrorResponse:| dateformultifund " + error.getMessage() + error);

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
    public void dateformultifund1(String schemecd, Context context) {
        String url = APIConstant.BASE_URL + APIConstant.MULTI_SIP_DATE_URL ;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject schemeObj = new JSONObject();
        try {
            schemeObj.put("schemecode",schemecd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dateformultifund1 response | JSONOBJECT: "+schemeObj);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,schemeObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String dates = "";
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SipDates");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            dates = amcObj.getString("sip_dates");


                            System.out.println("dateformultifund1 response | dates: " + dates);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        System.out.println("dateformultifund1 response | dateformultifund1: " + response);
                        if (_iHttpResultHandler != null)
                            if (!dates.isEmpty())
                                _iHttpResultHandler.onSuccess(dates,"","", " ","","","dateformultifund1");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("dateformultifund1 onErrorResponse:| dateformultifund1 " + error.getMessage() + error);

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
    public void dateformultifund2(String schemecd, Context context) {
        String url = APIConstant.BASE_URL + APIConstant.MULTI_SIP_DATE_URL ;
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        final JSONObject schemeObj = new JSONObject();
        try {
            schemeObj.put("schemecode",schemecd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dateformultifund2 response | JSONOBJECT: "+schemeObj);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,url,schemeObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String dates = "";
                        try {
                            String responseStream= String.valueOf(response);
                            final JSONObject jsonResponse;
                            jsonResponse = new JSONObject(responseStream);
                            JSONObject jsonInfo=jsonResponse;
                            JSONArray loginDetailsArray = jsonInfo.getJSONArray("SipDates");
                            JSONObject amcObj = loginDetailsArray.getJSONObject(0);
                            dates = amcObj.getString("sip_dates");


                            System.out.println("dateformultifund2 response | dates: " + dates);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        System.out.println("dateformultifund2 response | dateformultifund2: " + response);
                        if (_iHttpResultHandler != null)
                            if (!dates.isEmpty())
                                _iHttpResultHandler.onSuccess(dates,"","", " ","","","dateformultifund2");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (_iHttpResultHandler != null)
                    _iHttpResultHandler.onError(error.getMessage());
                System.out.println("dateformultifund2 onErrorResponse:| dateformultifund " + error.getMessage() + error);

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

}
