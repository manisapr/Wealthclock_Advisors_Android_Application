package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Step_One_Save_Tax extends Fragment {

    ImageView img1,img2,img3,img4,img5,back1,back2,back3,back4,back5,back6;

    RelativeLayout relative1,relative2,relative3,relative4,relative5,relative7;

    EditText et1,et2,et3,et4,et5,et6;

    int valu,valu7,valu8,valu9,valu10,valu11;

    Button next;
    public Fragment_Step_One_Save_Tax() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__step__one__save__tax, container, false);

        next=view.findViewById(R.id.next);

        et1=view.findViewById(R.id.et1);

        img1=view.findViewById(R.id.img1);

        img2=view.findViewById(R.id.img2);

        img3=view.findViewById(R.id.img3);

        img4=view.findViewById(R.id.img4);

        img5=view.findViewById(R.id.img5);

        back1=view.findViewById(R.id.back1);

        back2=view.findViewById(R.id.back2);

        back3=view.findViewById(R.id.back3);

        back4=view.findViewById(R.id.back4);

        back5=view.findViewById(R.id.back5);

        back6=view.findViewById(R.id.back6);



        et1=view.findViewById(R.id.et1);

        et2=view.findViewById(R.id.et2);

        et3=view.findViewById(R.id.et3);

        et4=view.findViewById(R.id.et4);

        et5=view.findViewById(R.id.et5);

        et6=view.findViewById(R.id.et6);



        relative1=view.findViewById(R.id.relative1);

        relative2=view.findViewById(R.id.relative2);

        relative3=view.findViewById(R.id.relative3);

        relative4=view.findViewById(R.id.relative4);

        relative5=view.findViewById(R.id.relative5);

        relative7=view.findViewById(R.id.relative7);



        //getPositiveNegative();



        back1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                relative1.setVisibility(View.VISIBLE);

            }

        });

        back2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                relative2.setVisibility(View.VISIBLE);

            }

        });

        back3.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                relative3.setVisibility(View.VISIBLE);

            }

        });

        back4.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                relative4.setVisibility(View.VISIBLE);

            }

        });

        back5.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                relative5.setVisibility(View.VISIBLE);

            }

        });

        back6.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                relative7.setVisibility(View.VISIBLE);

            }

        });

        next.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Our_Assessment_Save_Tax our_assessment_save_tax = new Fragment_Our_Assessment_Save_Tax();

                //String valu1  = et1.getText().toString().trim();

                //String valu2=et2.getText().toString().trim();

                //String valu3=et3.getText().toString().trim();

                //String valu4=et4.getText().toString().trim();

                //String valu5=et5.getText().toString().trim();



                if(TextUtils.isEmpty(et1.getText())){

                    et1.setText("0");
                }

                else

                {
                    String valu1  = et1.getText().toString().trim();

                    valu = new Integer(valu1).intValue();
                }



                if (TextUtils.isEmpty(et2.getText()))
                {
                    et2.setText("0");
                }

                else

                {
                    String valu2=et2.getText().toString().trim();
                    valu7=new Integer(valu2).intValue();

                }



                if (TextUtils.isEmpty(et3.getText()))
                {
                    et3.setText("0");
                }

                else

                {

                    String valu3=et3.getText().toString().trim();
                    valu8=new Integer(valu3).intValue();

                }



                if (TextUtils.isEmpty(et4.getText()))

                {
                    et4.setText("0");
                }

                else

                {

                    String valu4=et4.getText().toString().trim();
                    valu9=new Integer(valu4).intValue();

                }



                if (TextUtils.isEmpty(et5.getText()))

                {

                    et5.setText("0");

                }

                else

                {
                    String valu5=et5.getText().toString().trim();
                    valu10=new Integer(valu5).intValue();

                }



                if (TextUtils.isEmpty(et6.getText()))

                {
                    et6.setText("0");

                }

                else

                {

                    String value6=et6.getText().toString().trim();
                    valu11=new Integer(value6).intValue();

                }


                //int valu = new Integer(valu1).intValue();

                //int valu7=new Integer(valu2).intValue();

                //int valu8=new Integer(valu3).intValue();

                //int valu9=new Integer(valu4).intValue();

                //int valu10=new Integer(valu5).intValue();

                Bundle arguments = new Bundle();
                //arguments.putString ("custom1", valu1);
                int result1=valu+valu7+valu8+valu9+valu10+valu11;
                arguments.putString("custom2", String.valueOf(result1));
                our_assessment_save_tax.setArguments(arguments);
                int out_of=150000;
                int result=out_of-(valu+valu7+valu8+valu9+valu10+valu11);

                //String result4=String.valueOf(result);
                arguments.putString("custom1", String.valueOf(result));
                our_assessment_save_tax.setArguments(arguments);


                fragmentTransaction.replace(R.id.frag, our_assessment_save_tax, "Fragment_Our_Assessment_Save_Tax");
                fragmentTransaction.addToBackStack("Fragment_Our_Assessment_Save_Tax");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();

            }

        });


        return view;
    }

}
