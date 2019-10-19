package com.esit.floristry.storageUtility;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreference {

    private static final String PREFS_FILE_NAME = "AppPreferences";
    private static final String IS_lOG_IN = "islogin";
    private static final String USER_TYPE = "user_type";
    private static final String USER_NAME = "user_name";


    // for Log in status check

    public static boolean getIslogIn(final Context con) {
        return con.getSharedPreferences(AppSharedPreference.PREFS_FILE_NAME,
                Context.MODE_PRIVATE).getBoolean(AppSharedPreference.IS_lOG_IN, false);
    }

    public static void setIslogIn(final Context con, final boolean islogin) {
        final SharedPreferences prefs = con.getSharedPreferences(
                AppSharedPreference.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(AppSharedPreference.IS_lOG_IN, islogin);
        editor.commit();
    }

    // get and set userType
    public static String getUserType(final Context con) {
        return con.getSharedPreferences(AppSharedPreference.PREFS_FILE_NAME,
                Context.MODE_PRIVATE).getString(AppSharedPreference.USER_TYPE, "0");
    }

    public static void setUserType(final Context con, final String userType) {
        final SharedPreferences prefs = con.getSharedPreferences(
                AppSharedPreference.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(AppSharedPreference.USER_TYPE, userType);
        editor.commit();
    }

    // get set for user Name
    public static String getUserName(final Context con) {
        return con.getSharedPreferences(AppSharedPreference.PREFS_FILE_NAME,
                Context.MODE_PRIVATE).getString(AppSharedPreference.USER_NAME, "0");
    }

    public static void setUserName(final Context con, final String userName) {
        final SharedPreferences prefs = con.getSharedPreferences(
                AppSharedPreference.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(AppSharedPreference.USER_NAME, userName);
        editor.commit();
    }

}
