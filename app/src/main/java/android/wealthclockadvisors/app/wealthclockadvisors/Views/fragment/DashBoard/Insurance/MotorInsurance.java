package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.Insurance;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotorInsurance extends Fragment implements View.OnClickListener{
    EditText motor_reg,email,mobile;
    Button purchase;
    String Motor_reg,Email,Mobile;
    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motor_insurance, container, false);
        motor_reg=view.findViewById(R.id.motor_reg);
        email=view.findViewById(R.id.email);
        mobile=view.findViewById(R.id.mobile);
        purchase=view.findViewById(R.id.purchase);
        email.setText(SharedPreferenceManager.getUserEmail(getContext()));
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        purchase.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        switch (v.getId())
        {
            case R.id.purchase:
                Motor_reg=motor_reg.getText().toString();
                Email=email.getText().toString();
                Mobile=mobile.getText().toString();
                if (TextUtils.isEmpty(Motor_reg))
                {
                    motor_reg.setError("Please Enter Motor Registration Number");
                }
                else if (TextUtils.isEmpty(Email))
                {
                    email.setError("Please Enter Your ");
                }
                else if (TextUtils.isEmpty(Mobile)) {
                    mobile.setError("Please Enter Your Mobile Number");
                }
                else {
                    showCustomDialog();
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//                    alertDialogBuilder.setCancelable(false);
//                    alertDialogBuilder.setTitle(Html.fromHtml("<font color='#13a097'>Hello</font>"));
//                    //alertDialogBuilder.setTitle("Hello,");
//                    alertDialogBuilder.setIcon(R.drawable.logo_circle);
//                    alertDialogBuilder.setMessage("Thank you for choosing Wealthclock Advisors, We will contact you soon!!!");
//                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    AlertDialog alertDialog = alertDialogBuilder.create();
//                    alertDialog.show();

                }
                break;
        }
    }
}