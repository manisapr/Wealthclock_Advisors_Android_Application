package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;

import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.PaymentWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.AMCListModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundTypeModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.MutualFundDetailsforModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.xsipOrderEntryParamModel;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.kaopiz.kprogresshud.KProgressHUD;
import com.onebit.spinner2.Spinner2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInserAmountCalculator extends Fragment {

    private Spinner _amc,_fund_type,_fund_name,_plan,_folio_no,_sipDate;

    private String amcList[] = {"Select AMC","policy 1","policy 1"};
    private  String fundtype[]={"Select Fund Type","fund 1","fund 2"};
    private String fundname2[]={"Select Fund Name","fundname 1","fundname 2"};
    private String plan1[]={"Select Plan","Growth","Dividend"};
    private String folio1[]={"Select folio","folio1","folio2"};
    private String date[]  ;
    private String dateAraay[] = {"Day of sip","date1","date2"};

    private EditText _amount,_addfolioEditText;
    private String amcCodeText = "";
    private String schemeCodeText = "";
    private String _postion="";
    private String fundNameData = "";
    private ImageView _editImage;
    private Button _purchase;
    int check = 0;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private TextView _minimumAmountText;
    private Switch _onOffSwitch;
    private double validAmount=0.0;
    private double selectvalue = 0.0;


    private ArrayList<AMCListModel> amcListModels;
    private ArrayList<FundTypeModel>  fundDataList;
    private ArrayList<FundTypeModel> _folioList;
    private TextView _validationText;

    private LinearLayout _dividendLayout;
    private OrderEntryModel orderEntryModel;
    private MutualFundDetailsforModel mutualFundDetailsforModel;
    private xsipOrderEntryParamModel paramModel;

    private KProgressHUD hud;


    public FragmentInserAmountCalculator() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sip, container, false);
        orderEntryModel = new OrderEntryModel();
        mutualFundDetailsforModel = new  MutualFundDetailsforModel();
        _amc = view.findViewById(R.id.amc);
        _fund_type = view.findViewById(R.id.fund_type);
        _fund_name = view.findViewById(R.id.fund_name);
        _plan = view.findViewById(R.id.plan);
        _amount = view.findViewById(R.id.amount);
        _folio_no = view.findViewById(R.id.folio_no);
        _dividendLayout = view.findViewById(R.id.dividendLayout);
        _sipDate = view.findViewById(R.id.sipDate);
        _editImage = view.findViewById(R.id.editImage);
        paramModel = new xsipOrderEntryParamModel();
        _purchase = view.findViewById(R.id.purchase);
        _minimumAmountText = view.findViewById(R.id.minimumAmountText);
        _addfolioEditText = view.findViewById(R.id.addFolioEditText);
        _onOffSwitch = view.findViewById(R.id.onOffSwitch);
        _validationText = view.findViewById(R.id.warningtext);
        //_SpinnerAmc = view.findViewById(R.id.amc);

        amcListModels = new ArrayList<>();
        fundDataList = new ArrayList<>();
        _folioList = new ArrayList<>();

        _amc.setEnabled(false);
        _plan.setEnabled(false);
        _fund_type.setEnabled(false);
        _fund_name.setEnabled(false);
        _folio_no.setEnabled(false);
        _sipDate.setEnabled(false);
        _addfolioEditText.setVisibility(View.GONE);
        _onOffSwitch.setChecked(true);

        CustomAdapter fund = new CustomAdapter(getContext(),fundtype);
        _fund_type.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_type.setAdapter(fund);

        CustomAdapter amc = new CustomAdapter(getContext(),amcList);
        _amc.setPopupBackgroundResource(R.color.backgroundcolor);
        _amc.setAdapter(amc);

        CustomAdapter sip = new CustomAdapter(getContext(),dateAraay);
        _sipDate.setPopupBackgroundResource(R.color.backgroundcolor);
        _sipDate.setAdapter(sip);

        CustomAdapter fundname = new CustomAdapter(getContext(),fundname2);
        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(fundname);

        CustomAdapter plan = new CustomAdapter(getContext(),plan1);
        _plan.setPopupBackgroundResource(R.color.backgroundcolor);
        _plan.setAdapter(plan);

        CustomAdapter folioadapter = new CustomAdapter(getContext(),folio1);
        _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
        _folio_no.setAdapter(folioadapter);

        _amc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getPositionForView(view) == 0) {

                }
                else
                {
                    hud.show();
                    String countryid = amcListModels.get(position-1).getAmcSchemeCode();
                    amcCodeText = countryid;
                    orderEntryModel.setAmcCode(countryid);
                    mutualFundDetailsforModel.setAmcCode(countryid);
                    paramModel.setAmcCode(countryid);
                    ServerResultHandler serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    UserHandler.getInstance().getFundType(SharedPreferenceManager.getClientCode(getContext()),countryid,getContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2 )
                {
                    _dividendLayout.setVisibility(View.VISIBLE);
                }
                else {
                    _dividendLayout.setVisibility(View.GONE);
                    hud.show();
                    ServerResultHandler serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    UserHandler.getInstance().sipDateApi(orderEntryModel,getContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _fund_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getPositionForView(view) == 0)
                {

                }
                else
                    {
                        hud.show();
                    mutualFundDetailsforModel.setSchemeType(fundtype[position]);
                    orderEntryModel.setSchemeCd(fundtype[position]);
                    ServerResultHandler serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    UserHandler.getInstance().getfundNameAllList(amcCodeText, fundtype[position], getContext());
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
                    System.out.println("folio in insertamount:- "+_postion +"Position:- "+folio1[position]);
                    if ((folio1[position]).equalsIgnoreCase("Add folio"))
                    {
                        _addfolioEditText.setVisibility(View.VISIBLE);
                        System.out.println("folio in insertamount:- "+folio1[position] +_addfolioEditText);
                       // orderEntryModel.setFolioNo(_folioEditText.getText().toString().trim());
                    }
                    else {
                        _addfolioEditText.setVisibility(View.GONE);
                    }
                    if ((folio1[position]).equalsIgnoreCase("new folio"))
                    {
                        paramModel.setFolioNo(" ");
                        _addfolioEditText.setVisibility(View.GONE);
                    }
                    else {
                        //_addfolioEditText.setVisibility(View.GONE);
                        paramModel.setFolioNo(folio1[position]);
                       // _folioEditText.setVisibility(View.GONE);
                        orderEntryModel.setFolioNo(folio1[position]);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _fund_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fundNameData = fundname2[position];
                orderEntryModel.setSchemeName(fundNameData);
                _plan.setEnabled(true);
                mutualFundDetailsforModel.setSchemeName(fundNameData);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _sipDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ++check;
                if(check > 1) {

                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                    month=month+1;
                    System.out.println("current date:- "+day+"month:- "+month);

                    int selectDate = Integer.parseInt(date[position+1]);
                   // _purchase.setEnabled(true);
                    //_purchase.setAlpha(1f);
                    System.out.println("_sipDate:- " + selectDate + "month:- "+month + "day:- "+day);

                    if (day<selectDate)
                    {
                    month= month+1;
                        if (month>12)
                        {
                            year= year+1;
                            month = month-12;
                        }
                        System.out.println("_sipDate | month |day<selectDate :- " + month);
                    }
                    /*if (month>12)
                    {
                        year= year+1;
                        System.out.println("_sipDate | year:- " + year);
                    }*/
                    if (day == selectDate){
                        month = month+1;
                        if (month>12)
                        {
                            year= year+1;
                            month = month-12;
                        }
                        System.out.println("_sipDate |day | day == selectDate:- " + month + "bbb:- "+year);
                    }

                    if (day>selectDate){
                        month = month+1;
                        if (month>12)
                        {
                            year= year+1;
                            month = month-12;
                        }
                        System.out.println("_sipDate |day | day>selectDate:- " + month + "bbb:- "+year);
                    }
                    String mm = String.valueOf(month);
                    String dayd = String.valueOf(selectDate);


                    if (dayd.length()>1) //5/12/2019
                    {
                        if (mm.length()>1 ) {
                            String dd = (selectDate - 1) + "/" + month + "/" + year;
                            paramModel.setStartDate(dd);
                            System.out.println("_sipDate |dd1:- " + dd);
                        }
                        else {
                            String dd = (selectDate - 1) + "/" + "0" + month + "/" + year;
                            paramModel.setStartDate(dd);
                            System.out.println("_sipDate |dd2:- " + dd);
                        }
                    }
                    else {
                        if (mm.length()>1 ) {
                            String dd = "0"+(selectDate - 1) + "/" + month + "/" + year;
                            paramModel.setStartDate(dd);
                            System.out.println("_sipDate |dd3:- " + dd);
                        }
                        else
                            {
                            String dd = "0"+(selectDate - 1) + "/" + "0" + month + "/" + year;
                            paramModel.setStartDate(dd);
                            System.out.println("_sipDate |dd4:- " + dd);
                        }
                    }

                    //_purchase.setEnabled(true);
                    //_purchase.setAlpha(1f);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Bundle arguments = getArguments();
        if (arguments.getString("custom")!=null || !arguments.getString("custom").equalsIgnoreCase("null") && arguments.getString("radiovalue")!=null || !arguments.getString("radiovalue").equalsIgnoreCase("null")) {
            String desired_string = arguments.getString("custom");
            String radioYears = arguments.getString("radiovalue");
            _amount.setText("₹ "+desired_string);
            mutualFundDetailsforModel.setAmount(desired_string);
            paramModel.setInstallmentAmount(desired_string);
            paramModel.setNoOfInstallment(radioYears);
        }
        /*if (arguments.getString("picker")!=null || !arguments.getString("picker").equalsIgnoreCase("null")) {
            String pickerValue = arguments.getString("picker");
            _amount.setText("₹ "+pickerValue);
        }*/

        _editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _amount.setEnabled(true);
                _amount.setCursorVisible(true);
                _amount.requestFocus();
                _amount.setFocusable(true);
            }
        });

        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getFundNameList(getContext());

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
                    String c= a.trim().substring(1);
                    System.out.println("on text changed in sip :- "+a + " , "+c);
                    mutualFundDetailsforModel.setAmount(a);
                    paramModel.setInstallmentAmount(c.trim());
                     selectvalue= Double.parseDouble(c);
                    if (selectvalue>=validAmount)
                    {
                        _purchase.setEnabled(true);
                        _purchase.setAlpha(1f);
                        _validationText.setText(" ");
                    }
                    else {
                        _purchase.setEnabled(false);
                        _purchase.setAlpha(0.5f);
                       // _amount.setError("Please Enter the amount greater than " +validAmount);
                        _validationText.setText("Please enter the amount greater than "+validAmount);
                    }
                }

            }
        });
        _addfolioEditText.addTextChangedListener(new TextWatcher() {
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
                    paramModel.setFolioNo(a.trim());
                }
            }
        });
        _purchase.setEnabled(false);
        _purchase.setAlpha(0.5f);
        _purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                _purchase.setEnabled(false);
                _purchase.setAlpha(0.5f);
                hud.show();
                _purchase.setText(R.string.purchase_init);
                UserHandler.getInstance().getSchemeCode(mutualFundDetailsforModel,getContext());
            }
        });
        paramModel.setPaymentMode("yes");
        _onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (_onOffSwitch.isChecked())
                {
                    paramModel.setPaymentMode("yes");
                }
                else {
                    paramModel.setPaymentMode("no");
                }
            }
        });


        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();

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

    public class CustomAdapterForDate extends BaseAdapter {
        Context context;
        String[] countryNames;
        LayoutInflater inflter;

        public CustomAdapterForDate(Context applicationContext,  String[] countryNames) {
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
            view = inflter.inflate(R.layout.spinner_items_date, null);
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
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            if (operation_flag.equalsIgnoreCase("getFundNameList"))
            {
                ArrayList<AMCListModel> amcArrayList = (ArrayList<AMCListModel>) message;
                amcListModels.clear();
                amcListModels.addAll(amcArrayList);
                amcList = new String[(amcArrayList.size() + 1)];
                for (int i = 0; i < amcArrayList.size(); i++) {
                    amcList[i + 1] = amcListModels.get(i).getAmcSchemeName();

                }
                amcList[0] = "Select AMC";

                CustomAdapter customAdapter = new CustomAdapter(getContext(),amcList);
                _amc.setPopupBackgroundResource(R.color.backgroundcolor);
                _amc.setEnabled(true);
                _amc.setAdapter(customAdapter);

                hud.dismiss();
            }


            if (operation_flag.equalsIgnoreCase("getFundType"))
            {

                CustomAdapter fundname = new CustomAdapter(getContext(),fundname2);
                _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
                _fund_name.setAdapter(fundname);



                ArrayList<FundTypeModel> schemList = (ArrayList<FundTypeModel>) message;
                ArrayList<FundTypeModel>  folioList = (ArrayList<FundTypeModel>) messsage1;
                System.out.println("FragmentInserAmountCalculator | getFundType"+schemList.size());
                if (schemList.size()>0)
                {
                    fundDataList.clear();
                    fundDataList.addAll(schemList);
                    fundtype = new String[(schemList.size()+2)];
                    for (int i = 0; i < schemList.size(); i++) {
                        fundtype[i+1] = fundDataList.get(i).getSchemType();
                    }


                    fundtype[0] = "Select Fund Type";
                    CustomAdapter fund = new CustomAdapter(getContext(), fundtype);
                    _fund_type.setPopupBackgroundResource(R.color.backgroundcolor);
                    _fund_type.setEnabled(true);
                    _folio_no.setEnabled(true);
                    _fund_type.setAdapter(fund);
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
                    folio1[(folioList.size())] = "Add folio";
                    folio1[(folioList.size()+1)] = "New folio";
                    CustomAdapter folio = new CustomAdapter(getContext(),folio1);
                    _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
                    _folio_no.setAdapter(folio);
                }
                if (folioList.size() == 0)
                {
                    folio1 = new String[3];
                    /*folio1[0] = "Folio no";
                    folio1[1] = "Add folio";
                    folio1[2] = "New folio";*/

                    folio1[1] = "Add folio";
                    folio1[0] = "New folio";
                    CustomAdapter folio = new CustomAdapter(getContext(),folio1);
                    _folio_no.setPopupBackgroundResource(R.color.backgroundcolor);
                    _folio_no.setAdapter(folio);
                }



                hud.dismiss();


            }

            if (operation_flag.equalsIgnoreCase("getfundNameAllList"))
            {
                if (message!=null ) {

                    ArrayList<String> schemList = (ArrayList<String>) message;
                    fundname2 = new String[schemList.size()+1];
                    for (int i = 0; i < schemList.size(); i++) {
                        //System.out.println("schemList: " + schemList.get(i));
                        fundname2[i+1] = schemList.get(i);
                    }
                    fundname2[0] = "Select Fund Name";
                    CustomAdapter fundname = new CustomAdapter(getContext(), fundname2);
                    _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
                    _fund_name.setEnabled(true);
                    _fund_name.setAdapter(fundname);
                }

                hud.dismiss();
            }

            if (operation_flag.equalsIgnoreCase("sipDateApi"))
            {
                if (message != null  ) {
                    String amount = (String) messsage1;
                    String dates = (String) message;
                    validAmount= Double.parseDouble(amount);
                    _minimumAmountText.setText("Minimum Amount:- "+amount.trim());
                    double aaamou= Double.parseDouble(amount);
                    String c= _amount.getText().toString().trim().substring(1);
                    double cam = Double.parseDouble(c);
                    if (cam>=aaamou)
                    {
                        _validationText.setText("");
                        _purchase.setEnabled(true);
                        _purchase.setAlpha(1f);
                    }
                    else {
                        _purchase.setEnabled(false);
                        _purchase.setAlpha(0.5f);
                        _validationText.setText("Please enter the amount greater than "+validAmount);
                    }

                    String[] separated = dates.split(",");
                    date = new String[separated.length+1];
                    date[0] = "Day of Sip";
                    //date = separated;
                        for (int i=0;i<separated.length;i++)
                        {
                            date[i+1] = separated[i];
                        }


                    System.out.println("FragmentInserAmountCalculator | sipdates | serverresultHandler:- " + amount + ":- " + dates + ":- " + date[1]);
                    CustomAdapterForDate sip = new CustomAdapterForDate(getContext(),date);
                    _sipDate.setPopupBackgroundResource(R.color.backgroundcolor);
                    _sipDate.setEnabled(true);
                    _sipDate.setAdapter(sip);
                }

                hud.dismiss();
            }

            if (operation_flag.equalsIgnoreCase("getSchemeCode"))
            {

                String schemeCode = (String) message;
                paramModel.setSchemeCode(schemeCode);
                if (SharedPreferenceManager.getIsXSIPActive(getContext()).equalsIgnoreCase("false") && SharedPreferenceManager.getIsISIPActive(getContext()).equalsIgnoreCase("false")) {

                    Toast.makeText(context, "Failed|One Time Mandate not approved for SIP Purchase. Contact us at +91 9702233617 for resolving this issue.", Toast.LENGTH_LONG).show();
                }
                else {
                    ServerResultHandler serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    UserHandler.getInstance().getMandatetId(paramModel, context);
                }

                hud.dismiss();
            }

            if (operation_flag.equalsIgnoreCase("getMandatetId"))
            {
                xsipOrderEntryParamModel sipOrderEntryParamModel = (xsipOrderEntryParamModel) message;
                paramModel.setParam2(sipOrderEntryParamModel.getParam2());
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);

                UserHandler.getInstance().sip(paramModel, getContext());
            hud.dismiss();
            }
            if (operation_flag.equalsIgnoreCase("sip"))
            {
                _purchase.setEnabled(true);
                _purchase.setAlpha(1f);
                _purchase.setText("Purchase");
                String sucessmsg = (String) message;
                System.out.println("SIP successful"+sucessmsg);
                if (sucessmsg.equalsIgnoreCase("true"))
                {
                    //System.out.println("SIP successful3"+sucessmsg);
                    if (paramModel.getPaymentMode().equalsIgnoreCase("no")) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setCancelable(false);
                        dialog.setTitle("SIP Initiated Successfully");
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
                        System.out.println("SIP successful2" + sucessmsg);
                    }
                    else {
                        String infohtml = (String) message2;
                        System.out.println("FragmentInserAmount | info html:- "+infohtml);
                        Intent intent = new Intent(getContext(),PaymentWebViewActivity.class);
                        intent.putExtra("link",infohtml);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    String desc = (String) messsage1;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setCancelable(false);
                    dialog.setTitle("Error!");
                    dialog.setMessage(desc);
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Action for "Delete".
                            getActivity().getSupportFragmentManager().popBackStackImmediate();
                        }
                    });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                    Toast.makeText(context, desc, Toast.LENGTH_LONG).show();
                    _purchase.setEnabled(true);
                    _purchase.setAlpha(1f);
                }
                hud.dismiss();
            }
        }

        @Override
        public void onError(Object message) {
            _purchase.setEnabled(true);
            _purchase.setAlpha(1f);
            hud.dismiss();
        }
    }

    /*public class CustomDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        //public TextView _minimumAmount;
        public Button yes, no;


        public CustomDialogClass(Context a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_ACTION_BAR);
            setContentView(R.layout.success_dialog);
            yes = (Button) findViewById(R.id.btn_yes);
            no = (Button) findViewById(R.id.btn_no);
            //_minimumAmount = findViewById(R.id.minimumAmount);
            _txt_dia_amount = (EditText)findViewById(R.id.txt_dia);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);

            _txt_dia_amount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String am = String.valueOf(s);
                    if (!am.isEmpty()) {
                        double amo = Double.parseDouble(am);
                        if (amo > 999.00) {
                            yes.setEnabled(true);
                            //_minimumAmount.setVisibility(View.GONE);
                        } else {
                            yes.setEnabled(false);
                            _txt_dia_amount.setError("Minimum Investment Allowed ₹ 1000");
                            //_minimumAmount.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    numberPicker.setVisibility(View.GONE);
                    _customAmountSelect.setVisibility(View.VISIBLE);
                    _customAmountSelect.setText("₹"+_txt_dia_amount.getText().toString().trim());
                    valu  = _txt_dia_amount.getText().toString().trim();
                    break;
                case R.id.btn_no:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }*/

}
