package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

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
    private final int CAPTURE_PHOTO = 101;
    private final int GALLERY_PHOTO = 102;
    File file_last;

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
        capture_image = view.findViewById(R.id.capture_image);

        tv_logout = view.findViewById(R.id.tv_logout);
        tv_logout.setVisibility(View.GONE);

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

        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("on click in profile image");
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setCancelable(false);
                dialog.setTitle("Image Upload");
                dialog.setMessage("Please Select any one where you want to upload");
                dialog.setPositiveButton("Media", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".

                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), GALLERY_PHOTO);


                    }
                }).setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAPTURE_PHOTO);

                    }

                });
                final AlertDialog alert = dialog.create();
                alert.show();
            }

        });
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

        tv_logout.setEnabled(false);

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 46);
                System.out.println("on start in cooking process in if block: ");
            }
            return;
        }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case CAPTURE_PHOTO: {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Bundle extras = data.getExtras();
                        Bitmap finalBitmap = (Bitmap) extras.get("data");
                        file_last = Utility.getImageUri(getContext(), finalBitmap);
                        String path = file_last.getPath();

                        SharedPreferenceManager.setImagePath(getContext(),path);

                        System.out.println("hsdhuh: -"+path + finalBitmap);
                        Uri uri = Uri.fromFile(file_last);

                        Glide.with(getActivity()).load(path).asBitmap().centerCrop().into(new BitmapImageViewTarget(profile_image) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                profile_image.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                        System.out.println("file check in api:- "+file);
                        AndroidNetworking.upload("https://www.wealthclockadvisors.com/API/API/FileUpload")
                                .addMultipartFile("file",file_last)
                                .addMultipartParameter("UserId",SharedPreferenceManager.getUserId(getContext()))
                                .setTag("uploadTest")
                                .setPriority(Priority.HIGH)
                                .build()
                                .setUploadProgressListener(new UploadProgressListener() {
                                    @Override
                                    public void onProgress(long bytesUploaded, long totalBytes) {
                                        // do anything with progress
                                    }
                                })
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // do anything with response

                                        Toast.makeText(getContext(), "Image Successfully Updated", Toast.LENGTH_LONG).show();
                                    }
                                    @Override
                                    public void onError(ANError error) {
                                        // handle error
                                        Toast.makeText(getContext(), "Some error has occurred.Please try again.", Toast.LENGTH_LONG).show();
                                        //System.out.println("faliuer in api"+error.getErrorDetail());
                                    }
                                });

                        System.out.println("onActivityResult | CookingProcessInitialFragment: " + finalBitmap + file + "ggdhf:- "+path+file);
                        //image.setImageBitmap(MeMeUtility.getRoundedShape(finalBitmap));
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            case GALLERY_PHOTO :{
                try {
                    System.out.println("permission grantewd "+requestCode+ "vale:- "+resultCode+ "nv:- "+data);
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);



                        ResizeImage resizeImage = new ResizeImage();
                        resizeImage.execute(imageStream);


                      /*  AndroidNetworking.upload("https://www.wealthclockadvisors.com/API/API/FileUpload")
                                .addMultipartFile("file",imageStream)
                                .setTag("uploadTest")
                                .setPriority(Priority.HIGH)
                                .build()
                                .setUploadProgressListener(new UploadProgressListener() {
                                    @Override
                                    public void onProgress(long bytesUploaded, long totalBytes) {
                                        // do anything with progress
                                    }
                                })
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // do anything with response

                                        System.out.println("success in api");
                                    }
                                    @Override
                                    public void onError(ANError error) {
                                        // handle error

                                        System.out.println("faliuer in api"+error.getErrorDetail());
                                    }
                                });*/

                        System.out.println("permission grantew gallery photo:-  "+imageStream+imageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {

                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 45);
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 46);
            }
            return;
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 45 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            System.out.println("onRequestPermissionsResultcamera:  " + grantResults);
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAPTURE_PHOTO);

        }

        if (requestCode == 46 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            System.out.println("onRequestPermissionsResultgalley:  " + grantResults);
            Intent cameraIntent = new Intent(Intent.ACTION_PICK);
            startActivityForResult(cameraIntent, GALLERY_PHOTO);

        }
    }

    public class ResizeImage extends AsyncTask<InputStream, Bitmap, Bitmap> {
        @Override
        protected Bitmap doInBackground(InputStream... values) {
            InputStream imageStream = values[0];
            Bitmap bitmap = null;

            try {
                // First decode with inJustDecodeBounds=true to check dimensions
                //final BitmapFactory.Options options = new BitmapFactory.Options();
                //options.inSampleSize = 2;

                bitmap = BitmapFactory.decodeStream(imageStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ////System.out.println("-------------------------------------------------------------");
            ////System.out.println("bitmap.getWidth(): " + bitmap.getWidth());
            ////System.out.println("bitmap.getHeight(): " + bitmap.getHeight());
            long totalPixel = bitmap.getHeight() * bitmap.getWidth();
            ////System.out.println("totalPixel: " + totalPixel);

            if (totalPixel > 240000) {
                Bitmap targetBitmap = null;
                try {
                    double factor = Math.sqrt(totalPixel / 240000);
                    int targetHeight = (int) (bitmap.getHeight() / factor);
                    int targetWidth = (int) (bitmap.getWidth() / factor);

                    targetBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);
                } catch (Exception e) {
                    ////System.out.println("bitmap catch: " + e.getMessage());
                }

                return targetBitmap;
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmapResult) {
            super.onPostExecute(bitmapResult);

            file_last = Utility.getImageUri(getContext(), bitmapResult);
            file_last = Utility.getImageUri(getContext(), bitmapResult);
            String path = file_last.getPath();
            SharedPreferenceManager.setImagePath(getContext(),path);
            Glide.with(getActivity()).load(path).asBitmap().centerCrop().into(new BitmapImageViewTarget(profile_image) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    profile_image.setImageDrawable(circularBitmapDrawable);
                }
            });

            System.out.println("file check in api in multimedia:- "+SharedPreferenceManager.getUserId(getContext()));
            AndroidNetworking.upload("https://www.wealthclockadvisors.com/API/API/FileUpload")
                    .addMultipartFile("file",file_last)
                    .addMultipartParameter("userid",SharedPreferenceManager.getUserId(getContext()))
                    .setTag("uploadTest")
                    .setPriority(Priority.HIGH)
                    .build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            // do anything with progress
                        }
                    })
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response
                            Toast.makeText(getContext(), "Image Successfully Updated", Toast.LENGTH_LONG).show();
                            //System.out.println("success in api from multimedia"+response);
                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                            Toast.makeText(getContext(), "Some error has occurred.Please try again.", Toast.LENGTH_LONG).show();
                            //System.out.println("faliuer in api from multimedia"+error.getErrorDetail());
                        }
                    });

            ////System.out.println("onPostExecute | bitmap: " + bitmapResult);
            //finalBitmap = bitmapResult;
            //image.setImageBitmap(MeMeUtility.getRoundedShape(finalBitmap));
            //startActivityForCropImage(bitmapResult);
        }
    }
}
