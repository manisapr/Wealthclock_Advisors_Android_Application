package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.NoInternetConnectionActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    private TextView _loginTxt;
    private EditText _name,_mobile,_emailId,_pwd;
    private Button _signupButton;
    private UserHandler _userHandler;
    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        _loginTxt = view.findViewById(R.id.tv_login);
        _name = view.findViewById(R.id.name_as_per_pan);
        _mobile = view.findViewById(R.id.mobile);
        _emailId = view.findViewById(R.id.email);
        _pwd = view.findViewById(R.id.password);
        _signupButton = view.findViewById(R.id.signUp_btn);

        _userHandler = UserHandler.getInstance();

        final String mobile = _mobile.getText().toString().trim();
        final String email = _emailId.getText().toString().trim();
        final String pwd = _pwd.getText().toString().trim();


        _loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isInternetOn(getContext())) {
                    if (validate()) {
                        ServerResultHandler serverResultHandler = new ServerResultHandler();
                        serverResultHandler.setContext(getContext());
                        _userHandler.set_ihttpResultHandler(serverResultHandler);
                        _userHandler.sendRegistrationDetails(_name.getText().toString().trim(), _mobile.getText().toString().trim(), _emailId.getText().toString().trim(), _pwd.getText().toString().trim(), getContext());
                    }
                }
                else {
                    Intent intent = new Intent(getActivity(), NoInternetConnectionActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private class ServerResultHandler implements ihttpResultHandler {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

       /* @Override
        public void onSuccess(Object message, String operation_flag) {


        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            System.out.println("sign up successfull");
            Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().popBackStack();
        }

        @Override
        public void onError(Object message) {
            Toast.makeText(context, "Error has occured please try again", Toast.LENGTH_LONG).show();

        }
    }

    private boolean validate() {
        String email = _emailId.getText().toString().trim();
        String pwd = _pwd.getText().toString().trim();
        String name = _name.getText().toString().trim();
        String mobile = _mobile.getText().toString().trim();

        if (name.isEmpty())
        {
            _name.setError(getString(R.string.name_required));
            //showProgress(false);
            return false;
        }

        if (mobile.isEmpty()) {
            _mobile.setError(getString(R.string.phno_required));
            //showProgress(false);
            return false;
        }

        if (email.isEmpty()) {
            _emailId.setError(getString(R.string.email_required));
            //showProgress(false);
            return false;
        }
        if (pwd.isEmpty()) {
            _pwd.setError(getString(R.string.password_required));
            //showProgress(false);
            return false;
        }
        if (pwd.length() < 6) {
            _pwd.setError(getString(R.string.password_required_length));
            //showProgress(false);
            return false;
        }


        return true;
    }
}
