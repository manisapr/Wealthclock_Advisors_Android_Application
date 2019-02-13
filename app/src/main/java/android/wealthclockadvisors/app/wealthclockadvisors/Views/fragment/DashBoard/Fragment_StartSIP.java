package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_StartSIP extends Fragment {
    int NUMBER_OF_VALUES = 5;
    TextView numberView,_customAmountSelect;
    String[] displayedValues = {"₹ 1000", "₹ 2500", "₹ 5000", "₹ 10000","₹ 15000"};
    private Button _custom_amount,_nextText;
    private NumberPicker numberPicker;
    private RadioButton _until,_threeyears,_fiveyears,_tenyears;
    private RadioGroup _radioGroup;

    private Spinner _amc,_fund_type,_fund_name,_plan;
    private String amcList[] = {"Select AMC","policy 1","policy 1"};
    private  String fundtype[]={"Select Fund Type","fund 1","fund 2"};
    private String fundname2[]={"Select Fund Name","fundname 1","fundname 2"};
    private String plan1[]={"Select Plan","Growth","Dividend"};

    public EditText _txt_dia_amount;
    String valu = "2500";
    String valu1 = "";
    String radioValue = " ";

    public Fragment_StartSIP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_fragment__start_si, container, false);

        numberView =view.findViewById(R.id.numberview);
        _custom_amount = view.findViewById(R.id.custom_amount);
        _customAmountSelect = view.findViewById(R.id.customAmountSelect);
         numberPicker = view.findViewById(R.id.numberpicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(displayedValues.length-1);
        numberPicker.setDisplayedValues(displayedValues);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setValue(1);
        _nextText = view.findViewById(R.id.nextText);
        _until = view.findViewById(R.id.untilRadio);
        _threeyears = view.findViewById(R.id.threeYearsRadio);
        _fiveyears = view.findViewById(R.id.fiveYearsRadio);
        _tenyears = view.findViewById(R.id.tenYearsRadio);
        _radioGroup = view.findViewById(R.id.radiogroup);
        _nextText.setEnabled(true);


        //System.out.println("numberPicker | onValueChange:- "+_radioGroup.getCheckedRadioButtonId());
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if (newVal==0) {
                    valu = " 1000";
                    numberPicker.setWrapSelectorWheel(false);
                }

                if (newVal==1) {
                    valu = " 2500";

                }

                if (newVal==2) {
                    valu = "5000";
                }

                if (newVal==3) {
                    valu = "10000";
                }
                if (newVal==4){
                    valu = "15000";
                    numberPicker.setWrapSelectorWheel(false);
                }

            }
        });


        _nextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                FragmentInserAmountCalculator fragmentStartSIP = new FragmentInserAmountCalculator();
                Bundle arguments = new Bundle();
                arguments.putString("custom", valu);
                arguments.putString("radiovalue",radioValue);
                //arguments.putString( "picker" , valu);
                fragmentStartSIP.setArguments(arguments);
                fragmentTransaction2.replace(R.id.frag, fragmentStartSIP, "FragmentInserAmountCalculator");
                fragmentTransaction2.addToBackStack("FragmentInserAmountCalculator");
                fragmentTransaction2.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction2.commit();
            }
        });

        _custom_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                FragmentInserAmountCalculator fragmentStartSIP = new FragmentInserAmountCalculator();
                fragmentTransaction2.replace(R.id.frag, fragmentStartSIP, "FragmentInserAmountCalculator");
                fragmentTransaction2.addToBackStack("FragmentInserAmountCalculator");
                fragmentTransaction2.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction2.commit();*/

                CustomDialogClass cdd=new CustomDialogClass(getContext());
                cdd.setTitle("Amount Invest");
                cdd.show();
                cdd.setCancelable(false);


            }
        });

       /* _amc = view.findViewById(R.id.amc);
        _fund_type = view.findViewById(R.id.fund_type);
        _fund_name = view.findViewById(R.id.fund_name);
        _plan = view.findViewById(R.id.plan);

        CustomAdapter fund = new CustomAdapter(getContext(),fundtype);
        _fund_type.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_type.setAdapter(fund);

        CustomAdapter amc = new CustomAdapter(getContext(),amcList);
        _amc.setPopupBackgroundResource(R.color.backgroundcolor);
        _amc.setAdapter(amc);

        CustomAdapter fundname = new CustomAdapter(getContext(),amcList);
        _fund_name.setPopupBackgroundResource(R.color.backgroundcolor);
        _fund_name.setAdapter(fundname);

        CustomAdapter plan = new CustomAdapter(getContext(),amcList);
        _plan.setPopupBackgroundResource(R.color.backgroundcolor);
        _plan.setAdapter(plan);*/

       _tenyears.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               radioValue = "120";
               _nextText.setEnabled(true);
           }
       });

        _until.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioValue="999";
                _nextText.setEnabled(true);
            }
        });

        _threeyears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioValue = "36";
                _nextText.setEnabled(true);
            }
        });

        _fiveyears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioValue = "60";
                _nextText.setEnabled(true);
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

    public class CustomDialogClass extends Dialog implements
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
            setContentView(R.layout.custom_dialog);
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
    }


}
