package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.DashboardActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermInsurance extends Fragment implements View.OnClickListener {
    EditText email,mobile;
    Spinner age;
    Button purchase;
    String Age,Email,Mobile;
    RadioGroup sex;
    RadioButton male,female;
    ViewGroup viewGroup;
    String arr[]={"Select Your Age","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45",
            "46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70"};
    public TermInsurance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_term_insurance, container, false);

        age=view.findViewById(R.id.age);
        email=view.findViewById(R.id.email);
        mobile=view.findViewById(R.id.mobile);
        purchase=view.findViewById(R.id.purchase);
        sex=view.findViewById(R.id.sex);
        male=view.findViewById(R.id.male);
        female=view.findViewById(R.id.female);

        email.setText(SharedPreferenceManager.getUserEmail(getContext()));
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getContext(),R.layout.custom_textview_to_spinner,arr );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_textview_to_spinner);

        age.setAdapter(spinnerArrayAdapter);
        age.setPopupBackgroundResource(R.color.backgroundcolor);

        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Age=age.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        purchase.setOnClickListener(this);

        return view;
    }
    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        //ViewGroup viewGroup = View.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog_for_insurance, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.buttonOk);
        TextView tv1=dialogView.findViewById(R.id.tv1);
        TextView tv2=dialogView.findViewById(R.id.tv2);


        //Now we need an AlertDialog.Builder object
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        //tv1.setText("Hello");
        //tv2.setText("Abhinandan Singha");
        alertDialog.setCancelable(false);
        alertDialog.show();
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().popBackStackImmediate();
                /*Intent intent = new Intent(getContext(), DashboardActivity.class);
                startActivity(intent);
                getActivity().finish();
                alertDialog.dismiss(); */
                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentDashboard fragmentDashboard = new FragmentDashboard();
                fragmentTransaction.replace(R.id.frag, fragmentDashboard, "fragmentDashboard");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();*/
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.purchase:
                //Age=age.getText().toString();
                Email=email.getText().toString();
                Mobile=mobile.getText().toString();
                if (sex.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getContext(), "Please Select Your Sex", Toast.LENGTH_SHORT).show();
                    //purchase.setEnabled(false);
                    // no radio buttons are checked
                }
                else if (age.getSelectedItem().toString().trim().equals("Select Your Age")) {
                    //purchase.setEnabled(false);
                    Toast.makeText(getContext(), "Please Select Your Age", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(Email))
                {
                    email.setError("Please Enter Your E-Mail Id");
                }
                else if (TextUtils.isEmpty(Mobile)) {
                    mobile.setError("Please Enter Your Mobile Number");
                    //purchase.setEnabled(false);

                }

//                if(male.isChecked())
//
//                {
//
//
//
//                }
//
//                else if (female.isChecked())
//
//                {
//
//                }
//
//                else
//
//                {
//                    male.setEnabled(false);
//                    female.setEnabled(false);
//                    Toast.makeText(getActivity(),"Please Select Your Sex",Toast.LENGTH_LONG).show();
//
//                }
//
////                if (TextUtils.isEmpty(Age)) {
////                    //age.setError("Please Enter Your Age");
////                    return;
////
////                }
//                if (age.getSelectedItem().toString().trim().equals("Select Your Age")) {
//
//                                        Toast.makeText(getContext(), "Please Select Your Age", Toast.LENGTH_SHORT).show();
//                }
//
//                if (TextUtils.isEmpty(Mobile)) {
//                    mobile.setError("Please Enter Your Mobile Number");
//                    return;
//
//                }

                else {
                    //purchase.setEnabled(true);
                    showCustomDialog();
                    /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setTitle(Html.fromHtml("<font color='#13a097'>Hello</font>"));
                    //alertDialogBuilder.setTitle("Hello,");alertDialogBuilder.setIcon(R.drawable.logo_circle);
                    alertDialogBuilder.setMessage("Thank you for choosing Wealthclock Advisors, We will contact you soon!!!");
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();*/

                }



                break;

        }

    }
}