package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Step_Four_Save_Tax extends Fragment {

    public  EditText et1;
    private TextView btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_10,btn_11,btn_12,_messageText;
    private Button next;
    public Fragment_Step_Four_Save_Tax() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__step__four__save__tax, container, false);
        btn_1=view.findViewById(R.id.btn_1);
        btn_2=view.findViewById(R.id.btn_2);
        btn_3=view.findViewById(R.id.btn_3);
        btn_4=view.findViewById(R.id.btn_4);
        btn_5=view.findViewById(R.id.btn_5);
        btn_6=view.findViewById(R.id.btn_6);
        btn_7=view.findViewById(R.id.btn_7);
        btn_8=view.findViewById(R.id.btn_8);
        btn_9=view.findViewById(R.id.btn_9);
        btn_10=view.findViewById(R.id.btn_10);
        btn_11=view.findViewById(R.id.btn_11);
        btn_12=view.findViewById(R.id.btn_12);
        _messageText = view.findViewById(R.id.messageText);



        et1=view.findViewById(R.id.et1);
        Editable etext = et1.getText();
        next=view.findViewById(R.id.next);
        next.setEnabled(false);


        et1.setShowSoftInputOnFocus(false);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"1");
            }
        });


        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"2");
            }
        });


        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"3");

            }
        });


        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"4");
            }
        });


        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"5");
            }
        });


        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"6");
            }
        });


        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"7");
            }
        });


        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(et1.getText()+"8");
            }
        });


        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et1.setText(et1.getText()+"9");
            }
        });


        btn_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et1.setText("");
            }
        });


        btn_11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                et1.setText(et1.getText()+"0");
            }

        });


        btn_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et1.getText().toString();
                if (str.length() > 1) {
                    str = str.substring(0, str.length() - 1);
                    et1.setText(str);
                } else if (str.length() <= 1) {
                    et1.setText("");
                }
                else if (str.length()==0)
                {
                    et1.setText("");
                }
            }



        });



        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String amount = String.valueOf(s);
                if (!amount.isEmpty())
                {
                    double amount1 = Double.parseDouble(amount);

                    if (amount1 > 4999.00 && amount1 <= 150000.00)
                    {
                        //Toast.makeText(getActivity(),"Enter Amount between ₹ 5000 to ₹ 150000",Toast.LENGTH_SHORT).show();

                        if (amount1%1000==0) {
                            next.setEnabled(true);
                            next.setVisibility(View.VISIBLE);
                            _messageText.setText(" ");

                        }
                        else {
                            _messageText.setText("Please enter the amount of 1000 multiple");
                        }

                    }
                    else
                    {
                        next.setEnabled(false);
                        _messageText.setText("Please enter the amount  between ₹ 5000 to ₹ 150000");
                        //next.setVisibility(View.GONE);
                    }

                }
                else
                {
                    next.setEnabled(false);
                    _messageText.setText("Amount can not be empty");
                    //next.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String amount = String.valueOf(s);
                if (!amount.isEmpty())
                {
                    double amount1 = Double.parseDouble(amount);

                    if (amount1 > 4999.00 && amount1 <= 150000.00)
                    {
                        //Toast.makeText(getActivity(),"Enter Amount between ₹ 5000 to ₹ 150000",Toast.LENGTH_SHORT).show();

                        if (amount1%1000==0) {
                            next.setEnabled(true);
                            next.setVisibility(View.VISIBLE);
                            _messageText.setText(" ");

                        }
                        else {
                            _messageText.setText("Please enter the amount of 1000 multiple");
                        }

                    }
                    else
                    {
                        next.setEnabled(false);
                        _messageText.setText("Please enter the amount  between ₹ 5000 to ₹ 150000");
                        //next.setVisibility(View.GONE);
                    }

                }
                else
                {
                    next.setEnabled(false);
                    _messageText.setText("Amount can not be empty");
                    //next.setVisibility(View.GONE);
                }

            }

        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_Invest_Angle_Save_Tax invest_angle_save_tax=new Fragment_Invest_Angle_Save_Tax();
                String valu1=et1.getText().toString().trim();
                //int valu=new Integer(valu1).intValue();
                Bundle arguments=new Bundle();
                arguments.putString("custom",valu1);
                invest_angle_save_tax.setArguments(arguments);
                fragmentTransaction.replace(R.id.frag,invest_angle_save_tax,"Fragment_Invest_Angle_Save_Tax");
                fragmentTransaction.addToBackStack("Fragment_Invest_Angle_Save_Tax");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }


        });
        return view;
    }

}
