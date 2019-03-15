package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.wealthclockadvisors.app.wealthclockadvisors.Views.ProgressCircle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class AccountRegistration extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout lay_status1, lay_status2,lay_status3,lay_status4,lay_status5,lay_status6,lay_status7;
    RelativeLayout arrow1,arrow2,arrow3,arrow4,arrow5,arrow6,arrow7;
    LinearLayout lay_details1,lay_details2,lay_details3,lay_details4,lay_details5,lay_details6,lay_details7;
    ProgressCircle progress;
    RelativeLayout img1;

    /*ControllerOnBoard controllerOnBoard;
    List<OnBoardData> onBoardData;*/
    ImageView img_step1,img_step2,img_step3,img_step4,img_step5,img_step6,img_step7;
    TextView complete1,complete2,complete3,complete4,complete5,complete6,complete7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registration);

        progress=(ProgressCircle)findViewById(R.id.progress);

        img1=(RelativeLayout)findViewById(R.id.img1);

        lay_status1=(RelativeLayout)findViewById(R.id.lay_status1);
        lay_status2=(RelativeLayout)findViewById(R.id.lay_status2);
        lay_status3=(RelativeLayout)findViewById(R.id.lay_status3);
        lay_status4=(RelativeLayout)findViewById(R.id.lay_status4);
        lay_status5=(RelativeLayout)findViewById(R.id.lay_status5);
        lay_status6=(RelativeLayout)findViewById(R.id.lay_status6);
        lay_status7=(RelativeLayout)findViewById(R.id.lay_status7);

        lay_details1=(LinearLayout)findViewById(R.id.lay_details1);
        lay_details2=(LinearLayout)findViewById(R.id.lay_details2);
        lay_details3=(LinearLayout)findViewById(R.id.lay_details3);
        lay_details4=(LinearLayout)findViewById(R.id.lay_details4);
        lay_details5=(LinearLayout)findViewById(R.id.lay_details5);
        lay_details6=(LinearLayout)findViewById(R.id.lay_details6);
        lay_details7=(LinearLayout)findViewById(R.id.lay_details7);



        arrow1=(RelativeLayout)findViewById(R.id.arrow1);
        arrow2=(RelativeLayout)findViewById(R.id.arrow2);
        arrow3=(RelativeLayout)findViewById(R.id.arrow3);
        arrow4=(RelativeLayout)findViewById(R.id.arrow4);
        arrow5=(RelativeLayout)findViewById(R.id.arrow5);
        arrow6=(RelativeLayout)findViewById(R.id.arrow6);
        arrow7=(RelativeLayout)findViewById(R.id.arrow7);

        img_step1=(ImageView)findViewById(R.id.img_step1);
        img_step2=(ImageView)findViewById(R.id.img_step2);
        img_step3=(ImageView)findViewById(R.id.img_step3);
        img_step4=(ImageView)findViewById(R.id.img_step4);
        img_step5=(ImageView)findViewById(R.id.img_step5);
        img_step6=(ImageView)findViewById(R.id.img_step6);
        img_step7=(ImageView)findViewById(R.id.img_step7);

        complete1=(TextView)findViewById(R.id.complete1);
        complete2=(TextView)findViewById(R.id.complete2);
        complete3=(TextView)findViewById(R.id.complete3);
        complete4=(TextView)findViewById(R.id.complete4);
        complete5=(TextView)findViewById(R.id.complete5);
        complete6=(TextView)findViewById(R.id.complete6);
        complete7=(TextView)findViewById(R.id.complete7);

        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lay_status1.getVisibility()==View.VISIBLE)
                {
                    lay_status1.setVisibility(View.GONE);
                }
                else {
                    lay_status1.setVisibility(View.VISIBLE);
                }





            }
        });
        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_status2.getVisibility()==View.VISIBLE){
                    lay_status2.setVisibility(View.GONE);
                }
                else {
                    lay_status2.setVisibility(View.VISIBLE);
                }
            }
        });
        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lay_status3.getVisibility()==View.VISIBLE){
                    lay_status3.setVisibility(View.GONE);
                }
                else {
                    lay_status3.setVisibility(View.VISIBLE);
                }

            }
        });
        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_status4.getVisibility()==View.VISIBLE){
                    lay_status4.setVisibility(View.GONE);
                }
                else {
                    lay_status4.setVisibility(View.VISIBLE);
                }
            }
        });
        arrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lay_status5.getVisibility()==View.VISIBLE){
                    lay_status5.setVisibility(View.GONE);
                }
                else {
                    lay_status5.setVisibility(View.VISIBLE);
                }
            }
        });
        arrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lay_status6.getVisibility()==View.VISIBLE){
                    lay_status6.setVisibility(View.GONE);
                }
                else {
                    lay_status6.setVisibility(View.VISIBLE);
                }
            }
        });
        arrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_status7.getVisibility()==View.VISIBLE){
                    lay_status7.setVisibility(View.GONE);
                }
                else {
                    lay_status7.setVisibility(View.VISIBLE);
                }
            }
        });


        complete1.setOnClickListener(this);
        complete2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.complete1:
               /* FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ModeOfHolding signupFragment = new ModeOfHolding();
                fragmentTransaction.replace(R.id.fragment_container, signupFragment, "modeOfHolding");
                fragmentTransaction.addToBackStack("modeOfHolding");
                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();*/
                break;
            case R.id.complete2:
                /*IdentityDetails identityDetails = new IdentityDetails();
                showFragment(identityDetails);*/
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //CookingProcessInitialFragment cookingProcessInitialFragment = new CookingProcessInitialFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
