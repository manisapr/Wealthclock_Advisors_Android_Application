package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tutorial2 extends Fragment {
    ImageView img_tutorial2;


    public Tutorial2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tutorial2, container, false);

        img_tutorial2=(ImageView)view.findViewById(R.id.img_tutorial2);

        return view;
    }

}
