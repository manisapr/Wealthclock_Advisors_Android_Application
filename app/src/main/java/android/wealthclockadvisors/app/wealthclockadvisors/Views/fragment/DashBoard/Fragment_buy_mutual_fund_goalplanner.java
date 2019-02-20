package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.DashboardActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.PaymentWebViewActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.iisendPurchaseDetails;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_buy_mutual_fund_goalplanner extends Fragment implements iisendPurchaseDetails {

    private LinearLayout _container,_lay1,_lay2,_lay3;
    private Button _buy;
    private ArrayList<Top3Funds> cv = new ArrayList<>();
    //private ArrayList<Top3Funds> folio = new ArrayList<>();
    private String dateAraay[] = {"Day of sip","date1","date2"};
    private String date12[];
    private String date123[];
    private String date1234[];
    private String dateselectone;
    private String dateselecttwo;
    private String dateselectthree;
    int check = 0;
    int check1 = 0;
    int check2 = 0;
    private int year = 0;
    private int month = 0;
    private int day = 0;
     private Spinner _spin1,_spin2,_spin3;
     private CustomAdapterForDate sip;
    private ArrayList<Top3Funds> _goalFundList;
    private String amount = " ";
    private String yea = " ";
    private TextView _purchaseamount1,_purchaseamount2,_purchaseamount3,_name1,_name2,_name3;
    private KProgressHUD hud;
    private ArrayList<Top3Funds> _folioList;
    private int k=0;
    private int m=0;
    private Switch _onOffswitch;
    private String paymentmode = "yes";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.item_fund_details, container, false);
        _container = view.findViewById(R.id.containerLayout);
        _buy = view.findViewById(R.id.buy);
        _lay1 = view.findViewById(R.id.layout);
        _lay2 = view.findViewById(R.id.layout2);
        _lay3 = view.findViewById(R.id.layout3);
        _name1 = view.findViewById(R.id.name);
        _name2 = view.findViewById(R.id.name2);
        _name3 = view.findViewById(R.id.name3);
        _spin1 = view.findViewById(R.id.dateSpinner);
        _spin2 = view.findViewById(R.id.dateSpinner2);
        _spin3 = view.findViewById(R.id.dateSpinner3);
        _lay1.setVisibility(View.VISIBLE);
        _lay2.setVisibility(View.GONE);
        _lay3.setVisibility(View.GONE);
        _folioList = new ArrayList<>();

        _purchaseamount1 = view.findViewById(R.id.purchaseAmount);
        _purchaseamount2 = view.findViewById(R.id.purchaseAmount2);
        _purchaseamount3 = view.findViewById(R.id.purchaseAmount3);
        _goalFundList = new ArrayList<>();
        _onOffswitch = view.findViewById(R.id.onOffswitch);

        _onOffswitch.setChecked(true);
        _onOffswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (_onOffswitch.isChecked())
                {
                    paymentmode="yes";
                }
                else {
                    paymentmode="no";
                }
            }
        });

        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();


        System.out.println("Fragment_buy_mutual_fund_goalplanner | onCreateView:- ");
        //();
        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        Top3Funds  top3Funds= new Top3Funds();
        top3Funds.setInvestedAmount(amount);
        top3Funds.setYear(yea);
        UserHandler.getInstance().getFundListInGoalPlanner(top3Funds,getContext());

        _buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UserHandler.getInstance().multifundSip(cv,getContext());

                if (SharedPreferenceManager.getIsXSIPActive(getContext()).equalsIgnoreCase("false") && SharedPreferenceManager.getIsISIPActive(getContext()).equalsIgnoreCase("false")) {

                    Toast.makeText(getContext(), "Failed|One Time Mandate not approved for SIP Purchase. Contact us at +91 9702233617 for resolving this issue.", Toast.LENGTH_LONG).show();
                }
                else {

                    String mandateId = SharedPreferenceManager.getsetManadateRegId(getContext());
                    String[] separated = mandateId.split("-");
                    //System.out.println("cv size:- "+cv.size());
                    ServerResultHandler serverResultHandler = new ServerResultHandler();
                    serverResultHandler.setContext(getContext());
                    UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                    _buy.setAlpha(0.5f);
                    _buy.setText(R.string.purchase);
                    _buy.setEnabled(false);
                    UserHandler.getInstance().multifundSip(cv,_folioList,separated[0],dateselectone,dateselecttwo,dateselectthree,yea,paymentmode,getContext());
                }
            }
        });


        _spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ++check;
                if(check > 1) {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                    month = month + 1;
                    System.out.println("current date:- " + day + "month:- " + month);

                    int selectDate = Integer.parseInt(date12[position + 1]);
                    System.out.println("_sipDate:- " + selectDate + "month:- " + month + "day:- " + day);

                    if (day < selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate | month |day<selectDate :- " + month);
                    }

                    if (day == selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate |day | day == selectDate:- " + month + "bbb:- " + year);
                    }

                    if (day > selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate |day | day>selectDate:- " + month + "bbb:- " + year);
                    }
                    String mm = String.valueOf(month);
                    String dayd = String.valueOf(selectDate);


                    if (dayd.length() > 1) //5/12/2019
                    {
                        if (mm.length() > 1) {
                            String dd = (selectDate - 1) + "/" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectone=dd;
                            System.out.println("_sipDate |dd1:- " + dd);
                        } else {
                            String dd = (selectDate - 1) + "/" + "0" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectone=dd;
                            System.out.println("_sipDate |dd2:- " + dd);
                        }
                    } else {
                        if (mm.length() > 1) {
                            String dd = "0" + (selectDate - 1) + "/" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectone=dd;
                            System.out.println("_sipDate |dd3:- " + dd);
                        } else {
                            String dd = "0" + (selectDate - 1) + "/" + "0" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectone=dd;
                            System.out.println("_sipDate |dd4:- " + dd);
                        }
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ++check1;
                if(check1 > 1) {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                    month = month + 1;
                    System.out.println("current date2:- " + day + "month:- " + month);

                    int selectDate = Integer.parseInt(date1234[position ]);
                    System.out.println("_sipDate2:- " + selectDate + "month:- " + month + "day:- " + day);

                    if (day < selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate2 | month |day<selectDate :- " + month);
                    }

                    if (day == selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate2 |day | day == selectDate:- " + month + "bbb:- " + year);
                    }

                    if (day > selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate2 |day | day>selectDate:- " + month + "bbb:- " + year);
                    }
                    String mm = String.valueOf(month);
                    String dayd = String.valueOf(selectDate);


                    if (dayd.length() > 1) //5/12/2019
                    {
                        if (mm.length() > 1) {
                            String dd = (selectDate ) + "/" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselecttwo=dd;
                            System.out.println("_sipDate2 |dd1:- " + dd);
                        } else {
                            String dd = (selectDate ) + "/" + "0" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselecttwo=dd;
                            System.out.println("_sipDate2 |dd2:- " + dd);
                        }
                    } else {
                        if (mm.length() > 1) {
                            String dd = "0" + (selectDate ) + "/" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselecttwo=dd;
                            System.out.println("_sipDate2 |dd3:- " + dd);
                        } else {
                            String dd = "0" + (selectDate ) + "/" + "0" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselecttwo=dd;
                            System.out.println("_sipDate2 |dd4:- " + dd);
                        }
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ++check2;
                if(check2 > 1) {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                    month = month + 1;
                    System.out.println("current date:- " + day + "month:- " + month);

                    int selectDate = Integer.parseInt(date123[position + 1]);
                    System.out.println("_sipDate:- " + selectDate + "month:- " + month + "day:- " + day);

                    if (day < selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate | month |day<selectDate :- " + month);
                    }

                    if (day == selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate |day | day == selectDate:- " + month + "bbb:- " + year);
                    }

                    if (day > selectDate) {
                        month = month + 1;
                        if (month > 12) {
                            year = year + 1;
                            month = month - 12;
                        }
                        System.out.println("_sipDate |day | day>selectDate:- " + month + "bbb:- " + year);
                    }
                    String mm = String.valueOf(month);
                    String dayd = String.valueOf(selectDate);


                    if (dayd.length() > 1) //5/12/2019
                    {
                        if (mm.length() > 1) {
                            String dd = (selectDate - 1) + "/" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectthree=dd;
                            System.out.println("_sipDate |dd1:- " + dd);
                        } else {
                            String dd = (selectDate - 1) + "/" + "0" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectthree=dd;
                            System.out.println("_sipDate |dd2:- " + dd);
                        }
                    } else {
                        if (mm.length() > 1) {
                            String dd = "0" + (selectDate - 1) + "/" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectthree=dd;
                            System.out.println("_sipDate |dd3:- " + dd);
                        } else {
                            String dd = "0" + (selectDate - 1) + "/" + "0" + month + "/" + year;
                            //paramModel.setStartDate(dd);
                            dateselectthree=dd;
                            System.out.println("_sipDate |dd4:- " + dd);
                        }
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @Override
    public void sendpurchaseDetails(ArrayList<Top3Funds> top3FundsArrayList,ArrayList<Top3Funds> list,String a,String y,Context context) {
       /* cv.clear();
        cv.addAll(top3FundsArrayList);
        folio.addAll(list);*/
        amount= a;
        yea=y;
        cv.clear();
        cv.addAll(top3FundsArrayList);

        /*if (cv.size()==2)
        {
            _lay2.setVisibility(View.VISIBLE);
        }
        if (cv.size()==3)
        {
            _lay2.setVisibility(View.VISIBLE);
            _lay2.setVisibility(View.VISIBLE);
        }*/
        //System.out.println("pppppmm:- "+folio.size());
    }

    private void addview()
    {
        /*ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        for (int i=0;i<3;i++)
        {
            final View view1 = LayoutInflater.from(getContext()).inflate(R.layout.item_fund_details, null);
            Button calndr = view1.findViewById(R.id.calenderButton);
            final EditText date  = view1.findViewById(R.id.dateText);
            final TextView amount = view1.findViewById(R.id.purchaseAmount);
            final TextView fundname = view1.findViewById(R.id.name);
             datespin = view1.findViewById(R.id.dateSpinner);
            amount.setText(cv.get(i).getPrice());
            fundname.setText(cv.get(i).getFundname());


            datespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            calndr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mYear, mMonth, mDay, mHour, mMinute;
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    final int index = _container.indexOfChild(view1);
                                    Top3Funds orderModel = cv.get(index);
                                    orderModel.setYear(date.getText().toString());
                                    cv.add(orderModel);

                                }
                            }, mYear, mMonth+1, mDay);
                    datePickerDialog.show();

                }
            });

            _container.addView(view1, i);

            final int index = _container.indexOfChild(view1);
            Top3Funds orderModel = cv.get(index);
            UserHandler.getInstance().dateformultifund(orderModel.getSchemeType(),getContext());

            System.out.println("addview | Top3Funds:- "+orderModel.getSchemeType());
        }*/
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
            ServerResultHandler serverResultHandler = new ServerResultHandler();
            serverResultHandler.setContext(getContext());
            UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
            if (operation_flag.equalsIgnoreCase("multifundSip"))
            {
                if (paymentmode.trim().equalsIgnoreCase("no")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setCancelable(false);
                    dialog.setTitle("SIP Initiated Successfully");
                    dialog.setMessage("Kindly make the payment via One Time Mandate or Cheque");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //Action for "Delete".
                            //getActivity().getSupportFragmentManager().popBackStackImmediate();
                            Intent intent = new Intent(getContext(), DashboardActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
                else
                    {
                    String responseStream = String.valueOf(message);
                    final JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(responseStream);
                        JSONObject jsonInfo = jsonResponse;
                        String html = jsonInfo.getString("Info");
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

            if (operation_flag.equalsIgnoreCase("dateformultifund"))
            {
                if (message != null  ) {
                    String dates = (String) message;
                    String[] separated = dates.split(",");
                    date12 = new String[separated.length+1];
                    date12[0] = "Day of Sip";
                    //date = separated;
                    for (int i=0;i<separated.length;i++)
                    {
                        date12[i+1] = separated[i];
                    }



                    CustomAdapterForDate sip = new CustomAdapterForDate(getContext(),date12);
                    _spin1.setPopupBackgroundResource(R.color.backgroundcolor);
                    _spin1.setEnabled(true);
                    _spin1.setAdapter(sip);


                }

                //hud.dismiss();
            }

            if (operation_flag.equalsIgnoreCase("dateformultifund2"))
            {
                if (message != null  ) {
                    String dates = (String) message;
                    String[] separated = dates.split(",");
                    date123 = new String[separated.length+1];
                    date123[0] = "Day of Sip";
                    //date = separated;
                    for (int i=0;i<separated.length;i++)
                    {
                        date123[i+1] = separated[i];
                    }

                    System.out.println("dateformultifund2");

                    CustomAdapterForDate sip = new CustomAdapterForDate(getContext(),date123);
                    _spin3.setPopupBackgroundResource(R.color.backgroundcolor);
                    _spin3.setEnabled(true);
                    _spin3.setAdapter(sip);


                }

            }

            if (operation_flag.equalsIgnoreCase("dateformultifund1"))
            {
                if (message != null  ) {
                    String dates = (String) message;
                    String[] separated = dates.split(",");
                    date1234 = new String[separated.length+1];
                    date1234[0] = "Day of Sip";
                    //date = separated;
                    for (int i=0;i<separated.length;i++)
                    {
                        date1234[i+1] = separated[i];
                    }
                    System.out.println("dateformultifund1");


                    CustomAdapterForDate sip = new CustomAdapterForDate(getContext(),date1234);
                    _spin2.setPopupBackgroundResource(R.color.backgroundcolor);
                    _spin2.setEnabled(true);
                    _spin2.setAdapter(sip);


                }
            }


            if (operation_flag.equalsIgnoreCase("getFundListInGoalPlanner")) {
                ArrayList<Top3Funds> goalFundList = (ArrayList<Top3Funds>) message;
                _goalFundList.clear();
                _goalFundList.addAll(m,goalFundList);
                m++;

                for (int j=0;j<goalFundList.size();j++) {
                    Top3Funds fundsfolio = goalFundList.get(j);
                    UserHandler.getInstance().getMultiFundFolioList(fundsfolio, String.valueOf(j),getContext());
                }



                if (_goalFundList.size()==1) {
                    _lay1.setVisibility(View.VISIBLE);
                    double a = Double.parseDouble(_goalFundList.get(0).getPrice());
                    String cc=String.format("%.0f",a);
                    _purchaseamount1.setText("₹"+cc);
                    _name1.setText(_goalFundList.get(0).getFundname());
                    UserHandler.getInstance().dateformultifund(_goalFundList.get(0).getSchemeType(),getContext());
                }
                if (_goalFundList.size()==2)
                {
                    _lay2.setVisibility(View.VISIBLE);
                    _lay1.setVisibility(View.VISIBLE);
                    double a = Double.parseDouble(_goalFundList.get(0).getPrice());
                    double a1 = Double.parseDouble(_goalFundList.get(1).getPrice());
                    String cc=String.format("%.0f",a);
                    _purchaseamount1.setText("₹"+cc);
                    String cc1=String.format("%.0f", a1);
                    _purchaseamount2.setText("₹"+cc1);
                    _name1.setText(_goalFundList.get(0).getFundname());
                    _name2.setText(_goalFundList.get(1).getFundname());
                    UserHandler.getInstance().dateformultifund(_goalFundList.get(0).getSchemeType(),getContext());
                    UserHandler.getInstance().dateformultifund1(_goalFundList.get(1).getSchemeType(),getContext());
                }
                if (_goalFundList.size()==3)
                {
                    _lay3.setVisibility(View.VISIBLE);
                    _lay2.setVisibility(View.VISIBLE);
                    _lay1.setVisibility(View.VISIBLE);
                    double a = Double.parseDouble(_goalFundList.get(0).getPrice());
                    double a1 = Double.parseDouble(_goalFundList.get(1).getPrice());
                    double a2 = Double.parseDouble(_goalFundList.get(2).getPrice());
                    String cc=String.format("%.0f", a);
                    String cc1=String.format("%.0f",a1);
                    String cc2=String.format("%.0f",a2);
                    _purchaseamount1.setText("₹"+cc);
                    _purchaseamount2.setText("₹"+cc1);
                    _purchaseamount3.setText("₹"+cc2);
                    _name1.setText(_goalFundList.get(0).getFundname());
                    _name2.setText(_goalFundList.get(1).getFundname());
                    _name3.setText(_goalFundList.get(2).getFundname());
                    UserHandler.getInstance().dateformultifund(_goalFundList.get(0).getSchemeType(),getContext());
                    UserHandler.getInstance().dateformultifund1(_goalFundList.get(1).getSchemeType(),getContext());
                    UserHandler.getInstance().dateformultifund2(_goalFundList.get(2).getSchemeType(),getContext());
                }

            }
            if (operation_flag.equalsIgnoreCase("getMultiFundFolioList"))
            {
                ArrayList<Top3Funds> foliolist = (ArrayList<Top3Funds>) message;
                //System.out.println("successssss in frag:- "+foliolist.size()+k);

                _folioList.addAll(k,foliolist);
                k++;
                   /* if (!foliolist.get(0).getSipID().equalsIgnoreCase("null") && foliolist.get(0).getSipID() != null) {
                        System.out.println("foool 1");
                    }


                    if (!foliolist.get(1).getSipID().equalsIgnoreCase("null") && foliolist.get(0).getSipID() != null) {
                        System.out.println("foool 2");

                    }

                }
                else {

                }
                if (foliolist.size()>1) {
                    if (!foliolist.get(2).getSipID().equalsIgnoreCase("null") && foliolist.get(0).getSipID() != null) {
                        System.out.println("foool 3");

                    }*/



                //System.out.println("FragmentFundDistribution_Goalplanner | onSuccess"+foliolist.get(0).getSipID());

            }

            hud.dismiss();
            _buy.setAlpha(1.0f);
            _buy.setText("Purchase");
            _buy.setEnabled(true);
        }

        @Override
        public void onError(Object message) {
            _buy.setAlpha(1.0f);
            _buy.setText("Purchase");
            _buy.setEnabled(true);
            hud.dismiss();
            System.out.println("Fragment_buy_mutual_fund_goalplanner | ServerResultHandler |onError");
            Toast.makeText(context, "Error: Please try again later", Toast.LENGTH_LONG).show();
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

}
