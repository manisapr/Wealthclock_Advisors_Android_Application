package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.LoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.NewsWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.PaymentWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.AMCListModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundTypeModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MutualFundDetailsforModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuickPurchaseFragment extends Fragment {
    private Spinner spinnerAmc,_fund_type,_fund_name,_plan,_dividend_option,_folio_no;
    String amcList[] = {"Select AMC","policy 1","policy 1"};
    String fundtype[]={"Select Fund Type","fund 1","fund 2"};
    String fundname2[]={"Select Fund Name","fundname 1","fundname 2"};
    String plan1[]={"Select Plan","Growth","Dividend"};
    String dividenddivision1[]={"Select Dividend Division","dividend division 1","dividend division 1"};
    String folio1[] = {"Folio no.","New folio"};
    private LinearLayout _dividendLayout;
    private ArrayList<AMCListModel> amcListModels;
    private ArrayList<FundTypeModel>  fundDataList;
    private ArrayList<FundTypeModel> _folioList;

    private EditText _amount,_folioEditText;
    private Button _purchase;

    private String amcCodeText = "";
    private String schemeCodeText = "";
    private String fundNameData = "";
    private String _postion="";
    OrderEntryModel orderEntryModel;
    private MutualFundDetailsforModel mutualFundDetailsforModel;
    private TextView _minimumAmountText,_minimumPurchaseAmount,_MaximumPurchaseAmount;

    private Switch _onOffSwitch;
    private KProgressHUD hud;

    public QuickPurchaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_purchase, container, false);
        // Inflate the layout for this fragment
        orderEntryModel = new OrderEntryModel();
        mutualFundDetailsforModel = new MutualFundDetailsforModel();
        spinnerAmc = view.findViewById(R.id.amc);
        _fund_type = view.findViewById(R.id.fund_type);
        _fund_name = view.findViewById(R.id.fund_name);
        _plan= view.findViewById(R.id.plan);
        _dividend_option=view.findViewById(R.id.dividend_option);
        _folio_no=view.findViewById(R.id.folio_no);
        _dividendLayout = view.findViewById(R.id.dividendLayout);
        _amount = view.findViewById(R.id.amount);
        _folioEditText = view.findViewById(R.id.folioEditText);
        _purchase = view.findViewById(R.id.purchase);
        _minimumAmountText = view.findViewById(R.id.minimumAmountText);
        _minimumPurchaseAmount = view.findViewById(R.id.minimumPurchaseAmount);
        _MaximumPurchaseAmount = view.findViewById(R.id.MaximumPurchaseAmount);
        _onOffSwitch = view.findViewById(R.id.onOffSwitch);
        _purchase.setEnabled(false);
       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,travelreasons);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerAmc.setAdapter(adapter);*/
        /*CustomAdapter fundname = new CustomAdapter(getContext(),fundname2);

        CustomAdapter dividenddivision = new CustomAdapter(getContext(),dividenddivision1);
        //CustomAdapter folio = new CustomAdapter(getContext(),folio1);

        CustomAdapter fund = new CustomAdapter(getContext(),fundtype);
        _fund_type.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_type.setAdapter(fund);
        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(fundname);
        _dividend_option.setPopupBackgroundResource(R.color.backgroundcolor);
        _dividend_option.setAdapter(dividenddivision);*/

       /* _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
        _folio_no.setAdapter(folio);*/
        /*_fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(plan);
        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(dividenddivision);
        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(folio);

        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(folio);

        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(folio);

        plan.setPopupBackgroundResource(R.color.backgroundcolor);
        plan.setAdapter(folio);*/

        _fund_name.setEnabled(false);
        _fund_type.setEnabled(false);
        _plan.setEnabled(false);
        _folio_no.setEnabled(false);
        _amount.setEnabled(false);

        CustomAdapter plan = new CustomAdapter(getContext(),plan1);
        _plan.setPopupBackgroundResource(R.color.backgroundcolor);
        _plan.setAdapter(plan);

        CustomAdapter fund = new CustomAdapter(getContext(),fundtype);
        _fund_type.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_type.setAdapter(fund);

        CustomAdapter fundname = new CustomAdapter(getContext(),fundname2);
        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(fundname);

        CustomAdapter folio = new CustomAdapter(getContext(),folio1);
        _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
        _folio_no.setAdapter(folio);


        amcListModels = new ArrayList<>();
        fundDataList = new ArrayList<>();
        _folioList = new ArrayList<>();



        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();

        _plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2 )
                {
                    _dividendLayout.setVisibility(View.VISIBLE);
                }
                else {
                    _dividendLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerAmc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                if (parent.getPositionForView(view) == 0) {

                }
                else
                    {

                    String countryid = amcListModels.get(position-1).getAmcSchemeCode();
                        amcCodeText = countryid;
                        orderEntryModel.setAmcCode(countryid);
                        mutualFundDetailsforModel.setAmcCode(countryid);
                        System.out.println("spinner select item:- "+countryid);
                        ServerResultHandler  serverResultHandler = new ServerResultHandler();
                        serverResultHandler.setContext(getContext());
                        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                        hud.show();
                        UserHandler.getInstance().getFundType(SharedPreferenceManager.getClientCode(getContext()),countryid,getContext());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _fund_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getPositionForView(view) == 0) {

                }
                else
                {
                        schemeCodeText = fundtype[position];
                        mutualFundDetailsforModel.setSchemeType(schemeCodeText);
                    System.out.println("_fund_name 111:- "+amcCodeText + "bbbb:- "+schemeCodeText);
                    //String item = (String) parent.getItemAtPosition(position);

                    //schemeCodeText = countryid;
                    ServerResultHandler  serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    hud.show();
                    UserHandler.getInstance().getfundNameAllList(amcCodeText,schemeCodeText,getContext());

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _fund_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getPositionForView(view) == 0) {

                }
                else {
                    fundNameData = fundname2[position];
                    mutualFundDetailsforModel.setSchemeName(fundNameData);
                    //hud.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                {
                    ServerResultHandler  serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    hud.show();
                    UserHandler.getInstance().getMinimumAmountData(fundNameData,schemeCodeText,"Growth",getContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _folio_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (folio1.length>0) {
                    _postion = String.valueOf(position);
                    if ((folio1[position]).equalsIgnoreCase("Add folio"))
                    {
                        System.out.println("(folio1.length-2:- "+folio1.length + ",position:- "+position);
                        _folioEditText.setVisibility(View.VISIBLE);
                        orderEntryModel.setFolioNo(_folioEditText.getText().toString().trim());
                    }

                    if ((folio1[position]).equalsIgnoreCase("new folio"))
                    {
                        System.out.println("new folio in quick purchase");
                        orderEntryModel.setFolioNo(" ");
                        _folioEditText.setVisibility(View.GONE);
                    }
                    if (!(folio1[position]).equalsIgnoreCase("Add folio") && !(folio1[position]).equalsIgnoreCase("new folio")){
                        orderEntryModel.setFolioNo(folio1[position]);
                        System.out.println("new folio in quick purchase 2"+folio1[position]);
                        _folioEditText.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ServerResultHandler  serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getFundNameList(getContext());
        _purchase.setEnabled(true);
        mutualFundDetailsforModel.setAmount(_amount.getText().toString().trim());

        _purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hud.show();
                _purchase.setText(R.string.purchase);
                _purchase.setEnabled(false);
                _purchase.setAlpha(0.5f);
                ServerResultHandler  serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                UserHandler.getInstance().getSchemeCode(mutualFundDetailsforModel,getContext());
            }
        });

        _amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().isEmpty()) {
                    String a = String.valueOf(s);
                    orderEntryModel.setOrderVal(a);
                    _purchase.setEnabled(true);
                    _purchase.setAlpha(1.0f);
                    //System.out.println("text changed ");
                }
                /*int p = Integer.parseInt(_postion);
                if ((folio1[p]).equalsIgnoreCase("Add folio"))
                {
                    if (s != null && !s.toString().isEmpty()) {
                        String a = String.valueOf(s);
                        double am = Double.parseDouble(a);
                        if (am > 999) {
                            _minimumAmountText.setVisibility(View.GONE);
                            orderEntryModel.setOrderVal(String.valueOf(s));
                            _purchase.setEnabled(true);
                            System.out.println("amopunt is greater than 999 in quick purchase");

                        } else {
                            _minimumAmountText.setVisibility(View.VISIBLE);
                            _minimumAmountText.setTextColor(Color.parseColor("#ffffff"));
                            _minimumAmountText.setText("Minimum Amount:- ₹1000 ");
                            _purchase.setEnabled(false);
                            System.out.println("amopunt is less than 999 in quick purchase");
                        }
                    }
                }
                else if ((folio1[p]).equalsIgnoreCase("New folio"))
                {
                    if (s != null && !s.toString().isEmpty()) {
                        String a = String.valueOf(s);
                        double am = Double.parseDouble(a);
                        if (am > 4999) {
                            _minimumAmountText.setVisibility(View.GONE);
                            orderEntryModel.setOrderVal(String.valueOf(s));
                            _purchase.setEnabled(true);
                            System.out.println("amopunt is greater than 999 in quick purchase");
                        } else {
                            _minimumAmountText.setVisibility(View.VISIBLE);
                            _minimumAmountText.setTextColor(Color.parseColor("#ffffff"));
                            _minimumAmountText.setText("Minimum Amount:- ₹5000 ");
                            _purchase.setEnabled(false);
                            System.out.println("amopunt is less than 4999 in quick purchase");
                        }
                    }
                }
                else {
                    if (s != null && !s.toString().isEmpty()) {
                        String a = String.valueOf(s);
                        double am = Double.parseDouble(a);
                        if (am > 999) {
                            _minimumAmountText.setVisibility(View.GONE);
                            orderEntryModel.setOrderVal(String.valueOf(s));
                            _purchase.setEnabled(true);
                            System.out.println("amopunt is greater than 99 in quick purchase");

                        } else {
                            _minimumAmountText.setVisibility(View.VISIBLE);
                            _minimumAmountText.setTextColor(Color.parseColor("#ffffff"));
                            _minimumAmountText.setText("Minimum Amount:- ₹1000 ");
                            _purchase.setEnabled(false);
                            System.out.println("amopunt is less than 99 in quick purchase");
                        }
                    }
                }
            }*/
            }

        });
        _onOffSwitch.setChecked(true);
        orderEntryModel.setPaymentMode("yes");

        if (_amount.getText().toString().trim().isEmpty())
        {
            _purchase.setEnabled(false);
            _purchase.setAlpha(0.5f);
            //Toast.makeText(getContext(), "Please enter all the fields.", Toast.LENGTH_LONG).show();
        }


        _onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //System.out.println("switch test in qick purchase:- "+_onOffSwitch.isChecked());
                if (_onOffSwitch.isChecked())
                {
                    orderEntryModel.setPaymentMode("yes");
                }
                else {
                    orderEntryModel.setPaymentMode("no");
                }
            }
        });


        _folioEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().isEmpty()) {
                    String a = String.valueOf(s);
                    orderEntryModel.setFolioNo(a);

                }
            }
        });

        return view;
    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        String[] countryNames;
        LayoutInflater inflter;

        public CustomAdapter(Context applicationContext,  String[] countryNames) {
            this.context = applicationContext;
            this.countryNames = countryNames;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return countryNames.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.spinner_item, null);
            TextView textView  = view.findViewById(R.id.spText);
            textView.setText(countryNames[i]);
            return view;
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

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag)
        {
            _minimumPurchaseAmount.setVisibility(View.GONE);
            _MaximumPurchaseAmount.setVisibility(View.GONE);
            if (operation_flag.equalsIgnoreCase("getFundNameList"))
            {
                //hud.dismiss();
                ArrayList<AMCListModel> amcArrayList = (ArrayList<AMCListModel>) message;
                amcListModels.clear();
                amcListModels.addAll(amcArrayList);
                _folioEditText.setVisibility(View.GONE);
                amcList = new String[(amcArrayList.size()+1)];
                for (int i=0;i<amcArrayList.size();i++)
                {
                    amcList[i+1]  = amcListModels.get(i).getAmcSchemeName();
                    //System.out.println("quick purchase data: "+ amcList[i]);


                }
                amcList[0] = "Select AMC";
               /* _fund_name.setEnabled(true);
                _folio_no.setEnabled(true);
                _plan.setEnabled(true);
                _amount.setEnabled(true);
*/
                CustomAdapter customAdapter = new CustomAdapter(getContext(),amcList);
                spinnerAmc.setPopupBackgroundResource(R.color.backgroundcolor);
                spinnerAmc.setAdapter(customAdapter);
                spinnerAmc.setEnabled(true);
                hud.dismiss();
            }


            if (operation_flag.equalsIgnoreCase("getFundType"))
            {
                _folio_no.setEnabled(true);
                _fund_type.setEnabled(true);
                hud.dismiss();
                ArrayList<FundTypeModel> schemList = (ArrayList<FundTypeModel>) message;
                ArrayList<FundTypeModel>  folioList = (ArrayList<FundTypeModel>) messsage1;
                /*_fund_name.setEnabled(false);
                _plan.setEnabled(false);
                _amount.setEnabled(false);*/
                if (schemList.size()>0)
                {
                    _folioEditText.setVisibility(View.GONE);
                    /*_fund_type.setEnabled(true);
                    _folio_no.setEnabled(true);*/
                    fundDataList.clear();
                    fundDataList.addAll(schemList);
                    fundtype = new String[(schemList.size()+1)];
                    for (int i = 0; i < schemList.size(); i++) {
                        fundtype[i+1] = fundDataList.get(i).getSchemType();
                    }


                    fundtype[0] = "Select Fund Type";
                    System.out.println("QuickPurchaseFragment | onSuccess: " + fundtype[2]);
                    CustomAdapter fund = new CustomAdapter(getContext(), fundtype);
                    _fund_type.setPopupBackgroundResource(R.color.backgroundcolor);
                    _fund_type.setAdapter(fund);
                    hud.dismiss();
                }
                else {
                    hud.dismiss();
                }

                if (folioList.size()>0)
                {
                    _folioList.clear();
                    _folioList.addAll(folioList);
                    folio1 = new String[(folioList.size()+2)];
                    for (int i=0;i<folioList.size();i++)
                    {
                        folio1[i] = _folioList.get(i).getFolioNo();
                    }
                    orderEntryModel.setFolioNo(folio1[0]);
                    System.out.println("folio no in quick purchase fragment:- "+orderEntryModel.getFolioNo());
                    folio1[(folioList.size())] = "Add folio";
                    folio1[(folioList.size()+1)] = "New folio";
                    CustomAdapter folio = new CustomAdapter(getContext(),folio1);
                    _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
                    _folio_no.setAdapter(folio);
                    hud.dismiss();
                }
                else {
                    hud.dismiss();
                }
                if (folioList.size() == 0)
                {
                    folio1 = new String[3];
                    /*folio1[0] = "Folio no";
                    folio1[1] = "Add folio";
                    folio1[2] = "New folio";*/

                    folio1[2] = "Add folio";
                    folio1[1] = "New folio";
                    folio1[0] = "Select Folio";

                    orderEntryModel.setFolioNo(_folioEditText.getText().toString().trim());
                    System.out.println("folio list size:- "+folioList.size());
                    CustomAdapter folio = new CustomAdapter(getContext(),folio1);
                    _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
                    _folio_no.setAdapter(folio);
                    hud.dismiss();
                }
                else {
                    hud.dismiss();
                }

            }

            if (operation_flag.equalsIgnoreCase("getfundNameAllList"))
            {
                if (message!=null) {
                    _fund_name.setEnabled(true);
                    //_folioEditText.setVisibility(View.GONE);
                    ArrayList<String> schemList = (ArrayList<String>) message;
                    _fund_name.setEnabled(true);
                    _plan.setEnabled(true);
                    fundname2 = new String[schemList.size()+1];
                    for (int i = 0; i < schemList.size(); i++) {
                        System.out.println("schemList: " + schemList.get(i));
                        fundname2[i+1] = schemList.get(i);
                    }
                    fundname2[0] = "Select Fund Name";
                    CustomAdapter fundname = new CustomAdapter(getContext(), fundname2);
                    _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);

                    System.out.println("ffffffffffffffff");
                    _fund_name.setAdapter(fundname);
                    hud.dismiss();
                }
                else {
                    hud.dismiss();
                    Toast.makeText(context, "Error has occured.Please try again later", Toast.LENGTH_LONG).show();
                }
            }


            if (operation_flag.equalsIgnoreCase("getMinimumAmountData"))
            {

                //_folioEditText.setVisibility(View.GONE);
                String amount = (String) message;
                String addtionalAmount = (String) messsage1;
                _minimumPurchaseAmount.setVisibility(View.VISIBLE);
                _MaximumPurchaseAmount.setVisibility(View.VISIBLE);
                _minimumPurchaseAmount.setText("Minimum Fresh - ₹"+amount);
                _MaximumPurchaseAmount.setText("Minimum Additional  - ₹"+addtionalAmount);
                _amount.setText(amount);
                _amount.setEnabled(true);
                _amount.setTextColor(Color.parseColor("#ffffff"));
                orderEntryModel.setOrderVal(_amount.getText().toString().trim());
                mutualFundDetailsforModel.setAmount(_amount.getText().toString().trim());
                //_purchase.setEnabled(true);

                if (amount.isEmpty())
                {
                    _minimumPurchaseAmount.setVisibility(View.GONE);
                    _MaximumPurchaseAmount.setVisibility(View.GONE);
                    orderEntryModel.setOrderVal(_amount.getText().toString().trim());
                    mutualFundDetailsforModel.setAmount(_amount.getText().toString().trim());
                    _amount.setEnabled(true);
                    _amount.setHint("Enter amounts");
                    //_purchase.setEnabled(false);
                }
                hud.dismiss();
            }
            if (operation_flag.equalsIgnoreCase("getSchemeCode"))
            {
                String schemeCode = (String) message;
                System.out.println("success for sceme code in quick purchase:- "+orderEntryModel.getFolioNo());
                orderEntryModel.setSchemeCd(schemeCode);
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                hud.dismiss();
                UserHandler.getInstance().purchase(orderEntryModel,context);
            }
            if (operation_flag.equalsIgnoreCase("purchase"))
            {
                hud.dismiss();
                _purchase.setEnabled(true);
                _purchase.setAlpha(1.0f);
                String sucessmsg = (String) message;
                if (sucessmsg.equalsIgnoreCase("true"))
                {
                    if (orderEntryModel.getPaymentMode().equalsIgnoreCase("no")) {

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
                        String infohtml = (String) message2;
                        System.out.println("Quick | info html:- "+infohtml);
                        Intent intent = new Intent(getContext(),PaymentWebViewActivity.class);
                        intent.putExtra("link",infohtml);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    String desc = (String) messsage1;
                    Toast.makeText(context, desc, Toast.LENGTH_LONG).show();
                }
            }

        }

        @Override
        public void onError(Object message) {
            hud.dismiss();
            _purchase.setEnabled(true);
            _purchase.setAlpha(1.0f);
            Toast.makeText(context, "Error has occured.Please try again.", Toast.LENGTH_LONG).show();
        }
    }

}
