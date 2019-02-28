package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.LoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.AMCListModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundTypeModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.OrderEntryModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.RedeemDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.User_DetailsForIMPS;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRedemption extends Fragment {
    private Spinner _select_scheme,_folio_no1,_redeemptiontype;
    String schemeList[] = {"Select Scheme","scheme 1","scheme 2"};
    String folioList[]={"Select Fund Type","fund 1","fund 2"};
    String redeemAmount[]={"Select Fund Name","fundname 1","fundname 2"};
    String schmCode[] ={" "};
    String redeemAmountType[] = {"Select Redeemption Type","Redeem Entire Investment","Redeem Specific Amount"};
    private ArrayList<FundTypeModel> scheme;
    private ArrayList<FundTypeModel> folioarrayList;
    private ArrayList<RedeemDetailsModel> _redeemDetails;
    private TextView _units,_amount,_navtext;
    private EditText _Insertamount;
    private LinearLayout _redeemdetailsLayout;
    String smcode= "";
    private Button _submit;
    private KProgressHUD hud;
    private OrderEntryModel orderEntryModel;
    private Switch _redeemSwitch;
    private User_DetailsForIMPS user_detailsForIMPS;
    private ImageView _nofundimage1,_nofundimage2;
    private ScrollView _redeempage;
    ViewGroup viewGroup;
    private int mSelectedIndex = 0;
    public FragmentRedemption() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_redemption, container, false);
        _select_scheme = view.findViewById(R.id.select_scheme);
        _folio_no1 = view.findViewById(R.id.folio_no1);
        _redeemptiontype = view.findViewById(R.id.folio_no);
        _units = view.findViewById(R.id.units);
        _amount = view.findViewById(R.id.amount);
        _Insertamount = view.findViewById(R.id.Insertamount);
        _redeemdetailsLayout = view.findViewById(R.id.redeemdetailsLayout);
        _redeemSwitch = view.findViewById(R.id.redeemSwitch);
        _submit = view.findViewById(R.id.submit);
        _navtext = view.findViewById(R.id.navtext);
        _nofundimage1 = view.findViewById(R.id.nofundimage1);
        _nofundimage2 = view.findViewById(R.id.nofundimage2);
        _redeempage = view.findViewById(R.id.redeempage);
        viewGroup = view.findViewById(android.R.id.content);

        _redeemdetailsLayout.setVisibility(View.GONE);
        _Insertamount.setVisibility(View.GONE);
        user_detailsForIMPS = new User_DetailsForIMPS();
        user_detailsForIMPS.setMobile("");
        user_detailsForIMPS.setSource("WA");
        user_detailsForIMPS.setRedemOption("N");

        scheme = new ArrayList<>();
        folioarrayList = new ArrayList<>();
        _redeemDetails = new ArrayList<>();
        orderEntryModel = new OrderEntryModel();


        CustomAdapter fund = new CustomAdapter(getContext(),schemeList);

        _select_scheme.setPopupBackgroundResource(R.color.backgroundcolor);
        _select_scheme.setAdapter(fund);

        CustomAdapter fund1 = new CustomAdapter(getContext(),folioList);
        _folio_no1.setPopupBackgroundResource(R.color.backgroundcolor);
        _folio_no1.setAdapter(fund1);

        CustomAdapter fund2 = new CustomAdapter(getContext(),redeemAmountType);
        _redeemptiontype.setPopupBackgroundResource(R.color.backgroundcolor);
        _redeemptiontype.setAdapter(fund2);
        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();
        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getRedeemtionSchemeList(SharedPreferenceManager.getClientCode(getContext()),"N",getContext());

        _folio_no1.setEnabled(false);
        _redeemptiontype.setEnabled(false);
        _select_scheme.setEnabled(false);
        _submit.setEnabled(false);
        _submit.setAlpha(0.5f);

        _select_scheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                mSelectedIndex = position;
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                //System.out.println("scheme.get(position-1).getSchemeCode()+- "+schmCode[position]);
                if (schmCode.length>0) {
                    if (schmCode[position] != null) {
                        smcode = schmCode[position];
                        _folio_no1.setEnabled(true);
                        orderEntryModel.setSchemeCd(smcode);
                        user_detailsForIMPS.setScheme_Code(smcode);
                        UserHandler.getInstance().getRedeemFolioList(SharedPreferenceManager.getClientCode(getContext()), schmCode[position], getContext());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _folio_no1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                mSelectedIndex = position;
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);

                if (folioList[position] != null) {
                    orderEntryModel.setFolioNo(folioList[position]);
                    user_detailsForIMPS.setFolioNo(folioList[position]);
                    hud.show();
                    UserHandler.getInstance().getRedemptionDetails(SharedPreferenceManager.getClientCode(getContext()), smcode, folioList[position], getContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _redeemptiontype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedIndex = position;
                if (position ==2) {
                    _Insertamount.setVisibility(View.VISIBLE);

                }
                else {
                    _Insertamount.setVisibility(View.GONE);
                    orderEntryModel.setOrderVal("entire");
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //orderEntryModel.setOrderVal(_Insertamount.getText().toString().trim());
                hud.show();
                _submit.setText(R.string.redeem);
                _submit.setAlpha(0.5f);
                _submit.setEnabled(false);
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                if (_redeemSwitch.isChecked())
                {
                    UserHandler.getInstance().redeemIMPS(user_detailsForIMPS,getContext());
                }
                else {
                    UserHandler.getInstance().redeemFund(orderEntryModel,getContext());
                }


            }
        });

        _Insertamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().isEmpty()) {
                    orderEntryModel.setOrderVal(String.valueOf(s));
                    user_detailsForIMPS.setRedeemAmount(String.valueOf(s));
                }

            }
        });
        _redeemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (_redeemSwitch.isChecked())
                {
                    //orderEntryModel.setPaymentMode("yes");
                    System.out.println("FragmentRedemption | checked true");
                    UserHandler.getInstance().getRedeemtionSchemeList(SharedPreferenceManager.getClientCode(getContext()),"I",getContext());
                    //UserHandler.getInstance().redeemIMPS(user_detailsForIMPS,getContext());
                }
                else {
                    //orderEntryModel.setPaymentMode("no");
                    System.out.println("FragmentRedemption | checked false");
                    UserHandler.getInstance().getRedeemtionSchemeList(SharedPreferenceManager.getClientCode(getContext()),"N",getContext());
                }
            }
        });

        _nofundimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuickPurchaseFragment quickPurchaseFragment = new QuickPurchaseFragment();
                fragmentTransaction.replace(R.id.frag, quickPurchaseFragment, "quickPurchaseFragment");
                fragmentTransaction.addToBackStack("quickPurchaseFragment");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
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
            final TextView textView  = view.findViewById(R.id.spText);
            textView.setText(countryNames[i]);
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            convertView = inflter.inflate(R.layout.spinner_item, null);
            TextView tv = (TextView)convertView.findViewById(R.id.spText);
            ImageView  imageView = convertView.findViewById(R.id.imagev);
            //tv.setTextColor(Color.parseColor("#dedede"));
           /*tv.setPadding(10,10,10,10);
           tv.setTextSize(20);*/
            System.out.println("position:- "+position +mSelectedIndex);
            if(position == mSelectedIndex){
                // Set spinner selected popup item's text color
                tv.setTextColor(Color.parseColor("#008577"));

            }
            else {
                tv.setTextColor(Color.parseColor("#dedede"));

            }
            tv.setText(countryNames[position]);
            return convertView;
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

            _submit.setAlpha(1.0f);
            _submit.setEnabled(true);
            _submit.setText("Redeem");
        if (operation_flag.equalsIgnoreCase("getRedeemtionSchemeList"))
        {
            ArrayList<FundTypeModel> fundTypeModelArrayList = (ArrayList<FundTypeModel>) message;
            _redeemdetailsLayout.setVisibility(View.GONE);
            _Insertamount.setVisibility(View.GONE);
            scheme.clear();
            scheme.addAll(fundTypeModelArrayList);

            schemeList = new String[(fundTypeModelArrayList.size()+1)];
            schmCode = new String[(fundTypeModelArrayList.size()+1)];
            for (int i=0;i<fundTypeModelArrayList.size();i++)
            {
                schemeList[i+1] = scheme.get(i).getSchemeName();
                schmCode[i+1] = scheme.get(i).getSchemeCode();
            }
            schemeList[0] = "Select Scheme";
            CustomAdapter fund = new CustomAdapter(getContext(),schemeList);
            _select_scheme.setEnabled(true);
            _select_scheme.setPopupBackgroundResource(R.color.backgroundcolor);
            _select_scheme.setAdapter(fund);

            hud.dismiss();

            if (fundTypeModelArrayList.size()==0)
            {
                _redeempage.setVisibility(View.GONE);
                _nofundimage1.setVisibility(View.VISIBLE);
                _nofundimage2.setVisibility(View.VISIBLE);
            }

        }
        if (operation_flag.equalsIgnoreCase("getRedeemFolioList"))
        {
            ArrayList<FundTypeModel> foliolist = (ArrayList<FundTypeModel>) message;
            _redeemdetailsLayout.setVisibility(View.GONE);
            _Insertamount.setVisibility(View.GONE);
            folioarrayList.clear();
            folioarrayList.addAll(foliolist);
            folioList = new String[(foliolist.size()+1)];

            for (int i=0;i<foliolist.size();i++)
            {
                folioList[i+1] = folioarrayList.get(i).getFolioNo();

            }
            folioList[0] = "Select Folio";
            //_folio_no1.setEnabled(true);
            CustomAdapter fund1 = new CustomAdapter(getContext(),folioList);
            _folio_no1.setPopupBackgroundResource(R.color.backgroundcolor);
            _folio_no1.setAdapter(fund1);
            hud.dismiss();
        }
        if (operation_flag.equalsIgnoreCase("getRedemptionDetails"))
        {
            ArrayList<RedeemDetailsModel> redeemdetails = (ArrayList<RedeemDetailsModel>) message;
            if (redeemdetails.size()>0) {
                _Insertamount.setVisibility(View.GONE);
                System.out.println("redeemdetails | onSuccess");
                _redeemDetails.clear();
                _redeemptiontype.setEnabled(true);
                _redeemDetails.clear();
                _redeemDetails.addAll(redeemdetails);
                _redeemdetailsLayout.setVisibility(View.VISIBLE);
                _amount.setText(_redeemDetails.get(0).getAmount().trim());
                _units.setText(_redeemDetails.get(0).getUnits().trim());

                final Calendar c = Calendar.getInstance();
                int myear = c.get(Calendar.YEAR);
                final int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                String date = mDay+"-"+(mMonth+1)+"-"+myear;
                try {
                    //newDate = target.format(source.parse(date));
                    _navtext.setText("* Based On NAV Declared On As On "+date+".Value At The Time Of Transaction Processing Would Vary.");
                    System.out.println("date test: "+date +" df:- "+date);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            hud.dismiss();
        }
        if (operation_flag.equalsIgnoreCase("redeemFund"))
        {
            _submit.setText("Submit");
            _submit.setEnabled(true);
            _submit.setAlpha(1.0f);
           /* AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setCancelable(false);
            dialog.setTitle("Redemption Initiated Successfully");
            dialog.setMessage("Kindly complete the redemption authentication by replying via SMS or Email" );
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Action for "Delete".
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();*/
           showCustomDialog();
            hud.dismiss();
        }
        if (operation_flag.equalsIgnoreCase("redeemIMPS"))
        {

            _submit.setText("Submit");
            _submit.setEnabled(true);
            _submit.setAlpha(1.0f);
            hud.dismiss();
            String sucessmsg = (String) message;
            if (sucessmsg.equalsIgnoreCase("true")) {
               /* AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setCancelable(false);
                dialog.setTitle("Redemption  Successfull");
                dialog.setMessage("Success|Instant Redemption Successful.Kindly check your bank account for the credit. Happy Investing !!");
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        getActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                });

                final AlertDialog alert = dialog.create();
                alert.show();*/
               showCustomDialogforinstant();

            }
            else {
                showErrorDialog("Failed!");
                Toast.makeText(context, "Error: Some error has occured.Please try again.", Toast.LENGTH_LONG).show();
            }
        }

        }


        @Override
        public void onError(Object message) {
        hud.dismiss();
        }
    }


    private void showCustomDialog() {

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup

        //ViewGroup viewGroup = View.findViewById(android.R.id.content);



        //then we will inflate the custom alert dialog xml that we created

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.buttonOk);
        TextView tv1=dialogView.findViewById(R.id.tv1);
        TextView tv2  = dialogView.findViewById(R.id.tv2);
        tv2.setText("Kindly complete the redemption authentication by replying via SMS or Email");
        tv1.setText("Redemption  Successfull");
        //Now we need an AlertDialog.Builder objec
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().popBackStackImmediate();
                getActivity().getSupportFragmentManager().popBackStackImmediate();


            }

        });

    }
    private void showCustomDialogforinstant() {

        //before inflating the custom alert dialog layout, we will get the current activity viewgroup

        //ViewGroup viewGroup = View.findViewById(android.R.id.content);



        //then we will inflate the custom alert dialog xml that we created

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.buttonOk);
        TextView tv1=dialogView.findViewById(R.id.tv1);
        TextView tv2  = dialogView.findViewById(R.id.tv2);
        tv2.setText("Success|Instant Redemption Successful.Kindly check your bank account for the credit. Happy Investing !!");
        tv1.setText("Redemption  Successfull");
        //Now we need an AlertDialog.Builder objec
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().popBackStackImmediate();
                getActivity().getSupportFragmentManager().popBackStackImmediate();


            }

        });

    }
    private void showErrorDialog(String text)
    {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog_error, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.buttonOk);
        TextView tv1=dialogView.findViewById(R.id.tv1);
        tv1.setText("Failed!!!");
        TextView tv2 =  dialogView.findViewById(R.id.tv2);
        tv2.setText("Failed! Please try again later");
        //Now we need an AlertDialog.Builder objec
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().popBackStackImmediate();
                getActivity().getSupportFragmentManager().popBackStackImmediate();


            }

        });
    }

}
