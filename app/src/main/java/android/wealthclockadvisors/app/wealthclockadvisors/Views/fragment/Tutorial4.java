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
public class Tutorial4 extends Fragment {

    ImageView img_tutorial4;
    public Tutorial4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial4, container, false);

        img_tutorial4=(ImageView)view.findViewById(R.id.img_tutorial4);
        return view;
    }

}
