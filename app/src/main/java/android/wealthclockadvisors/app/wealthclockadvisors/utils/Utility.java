package android.wealthclockadvisors.app.wealthclockadvisors.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utility {

    private static String _imagePath;
    private static String emailaddress;
    private static String _totalChangeFund;
    private static String _currentvalue;
    private static String _totalinvest;
    private static String _totalgain;
    private static String xirr;


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
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/wealthclock";
            System.out.println("fullPath: "+fullPath);
            File dir = new File(fullPath);
            System.out.println("directory: "+dir);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            System.out.println("directory112: "+dir);
            java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
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
        return file;
    }


}
