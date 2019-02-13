package android.wealthclockadvisors.app.wealthclockadvisors.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.constant.AppConstant;

public class SharedPreferenceManager {
    private static SharedPreferences sharedPreferences = null ;

    public static void setClientCode(Context context, String clientcode)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.CLIENT_CODE,clientcode);
        editor.apply();
    }

    public static String getClientCode(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.CLIENT_CODE,"");
    }

    public static void setUserName(Context context, String userName)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.USER_NAME,userName);
        editor.apply();
    }

    public static String getUserName(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.USER_NAME,"");
    }

    public static void setImagePath(Context context, String path)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.IMAGE_PATH,path);
        editor.apply();
    }

    public static String getImagePath(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.IMAGE_PATH,"");
    }


    public static void setUserEmail(Context context, String email)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.ENAIL,email);
        editor.apply();
    }

    public static String getUserEmail(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.ENAIL,"");
    }


    public static void setUserPassword(Context context, String pwd)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.PASSWORD,pwd);
        editor.apply();
    }

    public static String getUserPassword(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.PASSWORD,"");
    }


    public static void setPanNo(Context context, String panno)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.PAN_NO,panno);
        editor.apply();
    }

    public static String getPanno(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.PAN_NO,"");
    }

    public static void setBankIfsceCode(Context context, String ifsccode)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.IFSC_CODE,ifsccode);
        editor.apply();
    }

    public static String getBankIfscCode(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.IFSC_CODE,"");
    }


    public static void setBankAccountNumber(Context context, String accntnumber)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.ACCOUNT_NUMBER,accntnumber);
        editor.apply();
    }

    public static String getBankAccountNumber(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.ACCOUNT_NUMBER,"");
    }

    public static void setBankName(Context context, String bankName)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.BANK_NAME,bankName);
        editor.apply();
    }

    public static String getBankName(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.BANK_NAME,"");
    }


    public static void setIsISIPActive(Context context, String IsISIPActive)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.IsISIPActive,IsISIPActive);
        editor.apply();
    }

    public static String getIsISIPActive(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.IsISIPActive,"");
    }


    public static void setIsXSIPActive(Context context, String IsXSIPActive)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.IsXSIPActive,IsXSIPActive);
        editor.apply();
    }

    public static String getIsXSIPActive(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.IsISIPActive,"");
    }

    public static void setManadateRegId(Context context, String IsXSIPActive)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstant.MANDATEREGID,IsXSIPActive);
        editor.apply();
    }

    public static String getsetManadateRegId(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(AppConstant.MANDATEREGID,"");
    }

}
