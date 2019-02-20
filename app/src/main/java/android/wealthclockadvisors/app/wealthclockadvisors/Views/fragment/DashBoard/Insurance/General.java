package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class General extends Fragment implements View.OnClickListener {
    EditText age,email,mobile;

    Button purchase;

    String Age,Email,Mobile;

    RadioGroup sex;

    RadioButton male,female;

    public General() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_general, container, false);

        age=view.findViewById(R.id.age);

        email=view.findViewById(R.id.email);

        mobile=view.findViewById(R.id.mobile);

        purchase=view.findViewById(R.id.purchase);

        sex=view.findViewById(R.id.sex);

        male=view.findViewById(R.id.male);

        female=view.findViewById(R.id.female);

        email.setText(SharedPreferenceManager.getUserEmail(getContext()));



        purchase.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.purchase:

                Age = age.getText().toString();

                Email = email.getText().toString();

                Mobile = mobile.getText().toString();



                if (male.isChecked()) {



                }

                else if (female.isChecked())

                {



                }

                else {

                    Toast.makeText(getActivity(), "Please Select Your Sex", Toast.LENGTH_LONG).show();

                }

                if (TextUtils.isEmpty(Age)) {

                    age.setError("Please Enter Your Age");

                    return;

                }

                if (TextUtils.isEmpty(Mobile)) {

                    mobile.setError("Please Enter Your Mobile Number");

                    return;

                } else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    alertDialogBuilder.setCancelable(false);

                    alertDialogBuilder.setTitle(Html.fromHtml("<font color='#13a097'>Hello</font>"));

                    //alertDialogBuilder.setTitle("Hello,");

                    alertDialogBuilder.setIcon(R.drawable.logo_circle);

                    alertDialogBuilder.setMessage("Thank you for choosing Wealthclock Advisors, We will contact you soon!!!");

                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();



                }

                break;



        }

    }



}
