package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard.FragmentAccount;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

import wealthclockadvisors.app.wealthclockadvisors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTicketRegistration extends Fragment {
    private EditText _subEditText,_descEditText;
    private Button _submitButton,_uploadButton;
    private final int GALLERY_PHOTO = 102;
    private File file;
    ViewGroup viewGroup;
    private String imagePath;
    private ImageView _previewImage;
    public FragmentTicketRegistration() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_ticket_registration, container, false);
        _subEditText = view.findViewById(R.id.subEditText);
        _descEditText = view.findViewById(R.id.descEditText);
        _submitButton = view.findViewById(R.id.submitButton);
        _uploadButton = view.findViewById(R.id.uploadButton);
        _previewImage = view.findViewById(R.id.previewImage);
         viewGroup = view.findViewById(android.R.id.content);


        _submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerResultHandler serverResultHandler = new ServerResultHandler();
                serverResultHandler.setContext(getContext());
                UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
                String finalstring  = SharedPreferenceManager.getUserId(getContext())+"|"+_subEditText.getText().toString().trim()+"|"+_descEditText.getText().toString().trim()+"|"+SharedPreferenceManager.getUserName(getContext())+"|"+SharedPreferenceManager.getUserEmail(getContext());
                UserHandler.getInstance().tickeregistration(finalstring,file,getContext());

                AndroidNetworking.upload("https://www.wealthclockadvisors.com/API/api/Ticket")
                        .addMultipartFile("file",file)
                        .addMultipartParameter("Details",finalstring)
                        .setTag("uploadTe")
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
                        /*if (_iHttpResultHandler != null)
                                _iHttpResultHandler.onSuccess("","","", " ","","","tickeregistration");
                        System.out.println();*/
                                 showCustomDialog();
                                Toast.makeText(getContext(), "Image Successfully Updated", Toast.LENGTH_LONG).show();
                                //System.out.println("success in api from multimedia"+response);
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                       /* if (_iHttpResultHandler != null)
                            _iHttpResultHandler.onError(error.getMessage());*/
                                Toast.makeText(getContext(), "Some error has occurred.Please try again.", Toast.LENGTH_LONG).show();
                                System.out.println("faliuer in api from multimedia"+error.getErrorDetail()+error);
                            }
                        });
            }
        });

        _uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), GALLERY_PHOTO);
                }
                else {
                   /* if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED )
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 46);
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 46);
                        }
                        return;
                    }*/
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 46);
                }
            }
        });
        return view;
    }

    private class ServerResultHandler implements ihttpResultHandler {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {

        showCustomDialog();

        }

        @Override
        public void onError(Object message) {

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
            File file_last;
            //file_last = Utility.getImageUri(getContext(), bitmapResult);
            file_last = Utility.getImageUri(getContext(), bitmapResult);
            String path = file_last.getPath();
            file = Utility.getImage(getContext(),imagePath,bitmapResult);
            System.out.println("file in resize image:- "+file);
            //SharedPreferenceManager.setImagePath(getContext(),path);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("onRequestPermissionsResultgalley:  " + grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 46 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            System.out.println("onRequestPermissionsResultgalley2:  " + grantResults);
            Intent cameraIntent = new Intent(Intent.ACTION_PICK);
            cameraIntent.setType("image/*");
            cameraIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(cameraIntent, GALLERY_PHOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case GALLERY_PHOTO :{
                try {
                    System.out.println("permission grantewd "+requestCode+ "vale:- "+resultCode+ "nv:- "+data);
                    try {
                        final Uri imageUri = data.getData();

                        final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                        imagePath = Utility.getPath(getContext(),imageUri);
                        ResizeImage resizeImage = new ResizeImage();
                        resizeImage.execute(imageStream);

                        Glide.with(getContext()).load(imagePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(_previewImage) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                                circularBitmapDrawable.setCircular(false);
                                _previewImage.setImageDrawable(circularBitmapDrawable);
                            }
                        });

                        System.out.println("permission grante gallery photo in ticket registration:-  "+Utility.getPath(getContext(),imageUri));
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
         if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED )
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 47);
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 47);
            }
            return;
        }
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup

        //ViewGroup viewGroup = View.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);

        Button buttonOk=dialogView.findViewById(R.id.buttonOk);
        //Now we need an AlertDialog.Builder object

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it

        final AlertDialog alertDialog = builder.create();

        alertDialog.setCancelable(false);

        alertDialog.show();

        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStackImmediate();
                alertDialog.dismiss();
                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



                FragmentDashboard fragmentDashboard = new FragmentDashboard();

                fragmentTransaction.replace(R.id.frag, fragmentDashboard, "fragmentDashboard");

                fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();

                alertDialog.dismiss();*/

            }

        });

    }

}
