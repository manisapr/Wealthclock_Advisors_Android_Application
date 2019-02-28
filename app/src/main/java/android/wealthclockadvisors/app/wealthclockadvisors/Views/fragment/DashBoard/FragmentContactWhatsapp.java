package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.FragmentTicketRegistration;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContactWhatsapp extends Fragment {

private RelativeLayout _contactButton,_ticketreg,_feedbacklayout;
    public FragmentContactWhatsapp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_contact_whatsapp, container, false);
        _contactButton = view.findViewById(R.id.contactButton);
        _ticketreg = view.findViewById(R.id.ticketreg);
        _feedbacklayout = view.findViewById(R.id.feedbacklayout);

        _feedbacklayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //QuickPurchaseFragment quickPurchaseFragment = new QuickPurchaseFragment();
                FragmentFeedBack fragmentTicketRegistration = new FragmentFeedBack();
                fragmentTransaction.replace(R.id.frag, fragmentTicketRegistration, "FragmentFeedBack");
                fragmentTransaction.addToBackStack("FragmentFeedBack");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });

        _ticketreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //QuickPurchaseFragment quickPurchaseFragment = new QuickPurchaseFragment();
                FragmentTicketRegistration fragmentTicketRegistration = new FragmentTicketRegistration();
                fragmentTransaction.replace(R.id.frag, fragmentTicketRegistration, "fragmentTicketRegistration");
                fragmentTransaction.addToBackStack("fragmentTicketRegistration");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        });

        _contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                boolean installed = appInstalledOrNot("com.whatsapp");
                System.out.println("checking whatsapp in support page:- "+installed);
                //intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));

                if (installed) {
                    intent.setData(Uri.parse(SharedPreferenceManager.getLinkForWhtsapp(getContext())));
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "Sorry! You dont have whatsapp yet.please install whatsapp and try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

}
