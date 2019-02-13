package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.onBoard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.RelativeLayout;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModeOfHolding extends Fragment {
    private RelativeLayout _next;


    public ModeOfHolding() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mode_of_holding, container, false);
        _next = view.findViewById(R.id.next);
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                IdentityDetails signupFragment = new IdentityDetails();
                fragmentTransaction.replace(R.id.fragment_container, signupFragment, "identityDetails");
                //fragmentTransaction.addToBackStack("identityDetails");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });
        return view;
        
    }





}
