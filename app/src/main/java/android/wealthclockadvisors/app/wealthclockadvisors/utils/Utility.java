package android.wealthclockadvisors.app.wealthclockadvisors.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.DocumentsContract.isDocumentUri;

public class Utility {

    private static String _imagePath;
    private static String emailaddress;
    private static String _totalChangeFund;
    private static String _currentvalue;
    private static String _totalinvest;
    private static String _totalgain;
    private static String xirr;
    private static double result=0.0;

    public static double getResult() {
        return result;
    }

    public static void setResult(double result) {
        Utility.result = result;
    }

    public static boolean isInternetOn(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                ////////System.out.println("getConnectivityStatus():-   " + "Type_wifi");
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                ////////System.out.println("getConnectivityStatus():-   " + "Type_mobole");
                return true;
            }
        }

        return false;
    }

    public static String get_imagePath() {
        return _imagePath;
    }

    public static void set_imagePath(String _imagePath) {
        Utility._imagePath = _imagePath;
    }

    public static String getEmailaddress() {
        return emailaddress;
    }

    public static void setEmailaddress(String emailaddress) {
        Utility.emailaddress = emailaddress;
    }

    public static String get_totalChangeFund() {
        return _totalChangeFund;
    }

    public static void set_totalChangeFund(String _totalChangeFund) {
        Utility._totalChangeFund = _totalChangeFund;
    }

    public static String get_currentvalue() {
        return _currentvalue;
    }

    public static void set_currentvalue(String _currentvalue) {
        Utility._currentvalue = _currentvalue;
    }

    public static String get_totalinvest() {
        return _totalinvest;
    }

    public static void set_totalinvest(String _totalinvest) {
        Utility._totalinvest = _totalinvest;
    }

    public static String get_totalgain() {
        return _totalgain;
    }

    public static void set_totalgain(String _totalgain) {
        Utility._totalgain = _totalgain;
    }

    public static String getXirr() {
        return xirr;
    }

    public static void setXirr(String xirr) {
        Utility.xirr = xirr;
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }


    public static File getImageUri(Context inContext, Bitmap finalBitmap)
    {
        File file = null;

        try
        {
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/sujoy";
            System.out.println("fullPath: "+fullPath);
            File dir = new File(fullPath);
            System.out.println("directory: "+dir);
            if (!dir.exists())
            {
                dir.mkdirs();
            }

            dir.mkdirs();
            System.out.println("directory112: "+dir);
            java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            //Bitmap targetBitmap;

            file = new File(fullPath, File.separator + "Sujoy" + System.currentTimeMillis()+".jpg");
            if(file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
            fo.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        /*return Uri.fromFile(file);*/

        System.out.println("returen file:- "+file);
        return file;
    }

    public static File getImage(Context inContext,String imagePath ,Bitmap finalBitmap)
    {
        File file = null;

        try
        {
            //String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + imagePath;
            //System.out.println("fullPath: "+fullPath);
            File dir = new File(imagePath);
            System.out.println("directory in getImage: "+dir);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            System.out.println("directory | getImage: "+dir);
            java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            //Bitmap targetBitmap;

            file = new File(imagePath);
            if(file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.close();
            fo.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        /*return Uri.fromFile(file);*/
        return file;
    }


    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

}
