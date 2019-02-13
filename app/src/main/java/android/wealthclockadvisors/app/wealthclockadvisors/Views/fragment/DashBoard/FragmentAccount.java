package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.DashboardActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.activity.LoginActivity;
import android.wealthclockadvisors.app.wealthclockadvisors.constant.AppConstant;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.AccountDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends Fragment implements View.OnClickListener {

    RelativeLayout lay1,lay2,lay3,lay4,lay5,lay6;
    LinearLayout lay1_details,lay2_details,lay3_details,lay4_details,lay5_details,lay6_details;
    RelativeLayout capture_image;

    private TextView _tv_name,_fullName,_panDetails,_dobDetails,_occupationDetails,_permanentDetails,_bankName,_acntNumber,_ifscCode,_nomineeName,_nomineeRelationship,_bankType;

    ImageView img1,img2,img3,img4,img5,img6;

    TextView tv_logout,_nomineeDob,_isipId,_mandateId,_xsipId,_secondapplicationFullName,_secondApplicationPan,_secondApplicantDOB,_secondApplicationOccupation,_secondApplicationGuardian,_clientid;

    Uri file;

    int angel=0;

    ImageView profile_image;

    private static SharedPreferences sharedPreferences = null ;
    public FragmentAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_account, container, false);

        profile_image=(ImageView)view.findViewById(R.id.profile_image);
        _tv_name = view.findViewById(R.id.tv_name);
        _fullName = view.findViewById(R.id.fullName);
        _panDetails = view.findViewById(R.id.panDetails);
        _dobDetails = view.findViewById(R.id.dobDetails);
        _permanentDetails = view.findViewById(R.id.permanentDetails);
        _occupationDetails = view.findViewById(R.id.occupationDetails);
        _bankName = view.findViewById(R.id.bankName);
        _acntNumber = view.findViewById(R.id.acntNumber);
        _ifscCode = view.findViewById(R.id.ifscCode);
        _nomineeName = view.findViewById(R.id.nomineeName);
        _nomineeRelationship = view.findViewById(R.id.nomineeRelationship);
        _nomineeDob = view.findViewById(R.id.nomineeDob);
        _bankType =  view.findViewById(R.id.bankType);
        _isipId = view.findViewById(R.id.isipId);
        _mandateId = view.findViewById(R.id.mandateId);
        _xsipId = view.findViewById(R.id.xsipId);
        _secondapplicationFullName = view.findViewById(R.id.secondapplicationFullName);
        _secondApplicationPan = view.findViewById(R.id.secondApplicationPan);
        _secondApplicantDOB = view.findViewById(R.id.secondApplicantDOB);
        _secondApplicationOccupation = view.findViewById(R.id.secondApplicationOccupation);
        _secondApplicationGuardian = view.findViewById(R.id.secondApplicationGuardian);
        _clientid = view.findViewById(R.id.clientid);

        tv_logout = view.findViewById(R.id.tv_logout);

        _clientid.setText("Wealthclock Id: "+SharedPreferenceManager.getClientCode(getContext()));


        System.out.println("account image path: "+Utility.get_imagePath());
        /*Glide.with(getContext()).load(Utility.get_imagePath()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                System.out.println("onException in account" + e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                System.out.println("onResourceReady:account " + resource);
                return false;
            }
        }).into(profile_image);*/


        _tv_name.setText(SharedPreferenceManager.getUserName(getContext()));
        tv_logout.setOnClickListener(this);


        Glide.with(getActivity()).load(Utility.get_imagePath()).asBitmap().centerCrop().into(new BitmapImageViewTarget(profile_image) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                profile_image.setImageDrawable(circularBitmapDrawable);
            }
        });

        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getAccountDetails(Utility.getEmailaddress(),getContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lay1=(RelativeLayout)view.findViewById(R.id.lay1);
        lay2=(RelativeLayout)view.findViewById(R.id.lay2);
        lay3=(RelativeLayout)view.findViewById(R.id.lay3);
        lay4=(RelativeLayout)view.findViewById(R.id.lay4);
        lay5=(RelativeLayout)view.findViewById(R.id.lay5);
        lay6=(RelativeLayout)view.findViewById(R.id.lay6);
        lay1_details=(LinearLayout)view.findViewById(R.id.lay1_details);
        lay2_details=(LinearLayout)view.findViewById(R.id.lay2_details);
        lay3_details=(LinearLayout)view.findViewById(R.id.lay3_details);
        lay4_details=(LinearLayout)view.findViewById(R.id.lay4_details);
        lay5_details=(LinearLayout)view.findViewById(R.id.lay5_details);
        lay6_details=(LinearLayout)view.findViewById(R.id.lay6_details);


        img1=(ImageView)view.findViewById(R.id.img1);
        img2=(ImageView)view.findViewById(R.id.img2);
        img3=(ImageView)view.findViewById(R.id.img3);
        img4=(ImageView)view.findViewById(R.id.img4);
        img5=(ImageView)view.findViewById(R.id.img5);
        img6=(ImageView)view.findViewById(R.id.img6);
        tv_logout=(TextView)view.findViewById(R.id.tv_logout);

        lay1.setOnClickListener(this);
        lay2.setOnClickListener(this);
        lay3.setOnClickListener(this);
        lay4.setOnClickListener(this);
        lay5.setOnClickListener(this);
        lay6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.lay1:
                ObjectAnimator animator=ObjectAnimator.ofFloat(img1,"rotation",angel,angel+180);
                animator.setDuration(600);
                animator.start();
                angel +=180;
                angel= angel%360;


                if (lay1_details.getVisibility()==View.VISIBLE){
                    lay1_details.setVisibility(View.GONE);

                }
                else {
                    lay1_details.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lay2:
                System.out.println("lay2 click");
                if (lay2_details.getVisibility()==v.VISIBLE){
                    lay2_details.setVisibility(View.GONE);
                }
                else {
                    lay2_details.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.lay3:

                if (lay3_details.getVisibility()==View.VISIBLE){
                    lay3_details.setVisibility(View.GONE);
                }
                else {
                    lay3_details.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lay4:
                if (lay4_details.getVisibility()==View.VISIBLE){
                    lay4_details.setVisibility(View.GONE);
                }
                else {
                    lay4_details.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lay5:
                if (lay5_details.getVisibility()==View.VISIBLE){
                    lay5_details.setVisibility(View.GONE);
                }
                else {
                    lay5_details.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lay6:

                int angel=0;
                ObjectAnimator animator1=ObjectAnimator.ofFloat(img6,"rotation",angel,angel+180);
                animator1.setDuration(600);
                animator1.start();
                angel +=180;
                angel= angel%360;

                if (lay6_details.getVisibility()==View.VISIBLE){
                    lay6_details.setVisibility(View.GONE);
                }
                else {
                    lay6_details.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tv_logout:
                System.out.println("log out press");
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                Utility.deleteCache(getContext());
                startActivity(intent);
                getActivity().finish();

                break;


        }
    }

    private class ServerResultHandler implements ihttpResultHandler{
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }
        /*@Override
        public void onSuccess(Object message, String operation_flag) {

        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            System.out.println("fragment account success");
            if (operation_flag.equalsIgnoreCase("getAccountDetails")) {
                if (message.toString() != null && !message.toString().equalsIgnoreCase("null")) {
                    AccountDetailsModel accountDetailsModel = (AccountDetailsModel) message;

                    _fullName.setText("Full Name: " + accountDetailsModel.getFirstApplicantName());
                    _dobDetails.setText("Date of birth: " + accountDetailsModel.getFirstApplicantDateOfBirth());
                    _panDetails.setText("PAN: " + accountDetailsModel.getFirstApplicanPanNo());
                    _permanentDetails.setText("Permanent Address: " + accountDetailsModel.getPermanentAddress());

                    _bankName.setText("Bank Name: " + accountDetailsModel.getBankName());
                    _acntNumber.setText("Account number: " + accountDetailsModel.getBankAcntNo());
                    _ifscCode.setText("IFSC: " + accountDetailsModel.getBankifsc());
                    _bankType.setText("Bank type: " + accountDetailsModel.getBankAccountType());

                    _nomineeName.setText("Nominee name: " + accountDetailsModel.getNomineeName());
                    _nomineeDob.setText("Nominee DOB: " + accountDetailsModel.getNomineeDob());
                    _nomineeRelationship.setText("Nominee relationship: " + accountDetailsModel.getNomineeRelation());

                    String mandateId = accountDetailsModel.getMandateId();
                    String[] separated = mandateId.split("-");
                    if (separated.length>0) {
                        _xsipId.setText("ISIP Mandate ID: " + separated[0]);
                    }
                    if (separated.length>1) {
                        _isipId.setText("XSIP Mandate ID: " + separated[1]);
                    }



                    if (accountDetailsModel.getSecondApplicantDateOfBirth() != null && !accountDetailsModel.getSecondApplicantDateOfBirth().equalsIgnoreCase("null"))
                    {
                        _secondapplicationFullName.setText("Full Name: " + accountDetailsModel.getSecondApplicantName());
                        _secondApplicationPan.setText("PAN: " + accountDetailsModel.getSecondApplicantPanNo());
                        _secondApplicantDOB.setText("Date of birth: " + accountDetailsModel.getSecondApplicantDateOfBirth());

                    }

                    if (accountDetailsModel.getFirstApplicantOccupationType()!=null && !accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("null")) {
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("01")) {
                            _occupationDetails.setText("Occupation type: " + "BUSINESS");
                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("02")) {
                            _occupationDetails.setText("Occupation type: " + "SERVICE");
                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("03")) {
                            _occupationDetails.setText("Occupation type: " + "PROFESSIONAL");
                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("04")) {
                            _occupationDetails.setText("Occupation type: " + "AGRICULTURE");

                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("05")) {
                            _occupationDetails.setText("Occupation type: " + "RETIRED");

                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("06")) {
                            _occupationDetails.setText("Occupation type: " + "HOUSEWIFE");

                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("07")) {
                            _occupationDetails.setText("Occupation type: " + "STUDENT");

                        }
                        if (accountDetailsModel.getFirstApplicantOccupationType().equalsIgnoreCase("others")) {
                            _occupationDetails.setText("Occupation type: " + "OTHERS");
                        }
                    }



                    if (accountDetailsModel.getSecondApplicantOccupationType()!=null && !accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("null")) {

                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("01")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"BUSINESS");
                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("02")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"SERVICE");
                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("03")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"PROFESSIONAL");
                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("04")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"AGRICULTURE");

                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("05")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"RETIRED");

                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("06")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"HOUSEWIFE");

                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("07")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"STUDENT");

                        }
                        if (accountDetailsModel.getSecondApplicantOccupationType().equalsIgnoreCase("others")) {
                            _secondApplicationOccupation.setText("Occupation type: "+"OTHERS");
                        }
                    }
                }
            }
        }

        @Override
        public void onError(Object message) {
            System.out.println("fragment account error");
        }
    }

}
