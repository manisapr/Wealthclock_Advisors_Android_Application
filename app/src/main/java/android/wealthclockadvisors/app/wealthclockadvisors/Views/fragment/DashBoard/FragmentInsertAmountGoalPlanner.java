package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class FragmentInsertAmountGoalPlanner extends Fragment implements View.OnClickListener {


    private Button _addGoalButton, _plusButton, _minusButton;

    private EditText _amountEditText, _yearsEditText;

    private String amount = "0.00";

    private String year = " ";

    private String _type = " ";


    public FragmentInsertAmountGoalPlanner() {

        // Required empty public constructor

    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState)

    {

        View view = inflater.inflate(R.layout.fragment_fragment_insert_amount_goal_planner, container, false);

        _addGoalButton = view.findViewById(R.id.addGoalButton);

        _amountEditText = view.findViewById(R.id.amountEditText);

        _plusButton = view.findViewById(R.id.plusButton);

        _minusButton = view.findViewById(R.id.minusButton);

        _yearsEditText = view.findViewById(R.id.yearsEditText);


        _amountEditText.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }


            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }


            @Override

            public void afterTextChanged(Editable s) {

                if (s != null && !s.toString().isEmpty()) {

                    amount = String.valueOf(s);

                    double amm = Double.parseDouble(amount);

                    if (!_yearsEditText.getText().toString().trim().isEmpty())

                    {

                        if (amm >= 500000) {

                            _addGoalButton.setEnabled(true);

                            _addGoalButton.setAlpha(1.0f);

                        } else {

                            _addGoalButton.setEnabled(false);

                            _addGoalButton.setAlpha(0.5f);

                            _amountEditText.setError("Amount greater than 500000");

                        }

                    } else {

                        _addGoalButton.setEnabled(false);

                        _addGoalButton.setAlpha(0.5f);

                    }

                }

            }

        });

        _yearsEditText.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }


            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }


            @Override

            public void afterTextChanged(Editable s) {


                if (s != null && !s.toString().isEmpty())

                {

                    year = String.valueOf(s);

                    double amm = Double.parseDouble(amount);

                    if (amm >= 500000) {

                        _addGoalButton.setEnabled(true);

                        _addGoalButton.setAlpha(1.0f);

                        // _amountEditText.setError("");

                    } else {

                        _addGoalButton.setEnabled(false);

                        _addGoalButton.setAlpha(0.5f);

                        _amountEditText.setError("Amount greater than 500000");

                    }

                } else {

                    //_addGoalButton.setEnabled(false);

                    //_addGoalButton.setAlpha(0.5f);

                    double amm = Double.parseDouble(amount);

                    if (amm >= 500000) {

                        _addGoalButton.setEnabled(true);

                        _addGoalButton.setAlpha(1.0f);

                        // _amountEditText.setError("");

                    } else {

                        _addGoalButton.setEnabled(false);

                        _addGoalButton.setAlpha(0.5f);

                        _amountEditText.setError("Amount greater than 500000");

                    }

                }

            }

        });


        _addGoalButton.setEnabled(false);

        _addGoalButton.setAlpha(0.5f);


        Bundle arguments = getArguments();

        if (arguments.getString("type") != null)

        {

            String desired_string = arguments.getString("type");

            _type = desired_string;

        }


        _addGoalButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("amount", amount);

                bundle.putString("year", year);

                bundle.putString("type", _type);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment_your_plan_goalplanner fragmentInsertAmountGoalPlanner = new Fragment_your_plan_goalplanner();

                fragmentInsertAmountGoalPlanner.setArguments(bundle);

                fragmentTransaction.replace(R.id.frag, fragmentInsertAmountGoalPlanner, "fragmentInsertAmountGoalPlanner");

                fragmentTransaction.addToBackStack("fragmentInsertAmountGoalPlanner");

                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();


            }

        });

        _plusButton.setOnClickListener(this);

        _minusButton.setOnClickListener(this);


        return view;

    }


    @Override

    public void onClick(View v) {

        switch (v.getId())

        {

            case R.id.plusButton:

                double am = Double.parseDouble(amount);

                am = am + 25000;

                String s = String.valueOf(am);

                _amountEditText.setText(s);

                break;

            case R.id.minusButton:

                double am1 = Double.parseDouble(amount);

                am1 = am1 - 25000;

                if (am1 >= 0) {

                    String s1 = String.valueOf(am1);

                    _amountEditText.setText(s1);

                } else {

                    _amountEditText.setText("0.00");

                }


                break;

        }

    }


    public boolean validation()

    {

        if ((_amountEditText.getText().toString().trim() != null && !_amountEditText.getText().toString().trim().isEmpty() && !_amountEditText.getText().toString().trim().equals("null")) && (_yearsEditText.getText().toString().trim() != null && !_yearsEditText.getText().toString().trim().isEmpty() && !_yearsEditText.getText().toString().trim().equals("null")))

        {

            return true;

        } else {

            if (_amountEditText.getText().toString().trim().isEmpty()) {

                _amountEditText.setError("Enter the amount");

            } else {

                _yearsEditText.setError("Enter the uear");

            }

            return false;

        }


    }
}
