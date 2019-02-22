package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.widget.Button;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContactWhatsapp extends Fragment {

private Button _contactButton;
    public FragmentContactWhatsapp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_contact_whatsapp, container, false);
        _contactButton = view.findViewById(R.id.contactButton);

        _contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                intent.setData(Uri.parse(SharedPreferenceManager.getLinkForWhtsapp(getContext())));
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }
        });
        return view;
    }

}
