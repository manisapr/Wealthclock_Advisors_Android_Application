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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class General extends Fragment implements View.OnClickListener {
    EditText age,email,mobile;
    Button purchase;
    String City,Email,Mobile;
    CheckBox travel,overseas,fire,accident;
    ViewGroup viewGroup;
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



        travel=view.findViewById(R.id.travel);

        overseas=view.findViewById(R.id.overseas);

        fire=view.findViewById(R.id.fire);

        accident=view.findViewById(R.id.accident);
        ViewGroup viewGroup = view.findViewById(android.R.id.content);



        email.setText(SharedPreferenceManager.getUserEmail(getContext()));

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
                City = age.getText().toString();
                Email = email.getText().toString();
                Mobile = mobile.getText().toString();

                if (TextUtils.isEmpty(City)) {
                    age.setError("Please Enter City Name");
                    return;

                }
                else if (TextUtils.isEmpty(Email))
                {
                    email.setError("Please Enter Your E-Mail Id");
                }

                else if (TextUtils.isEmpty(Mobile)) {
                    mobile.setError("Please Enter Your Mobile Number");
                    return;

                } else {

                    showCustomDialog();
                }
                break;

        }

    }

}
