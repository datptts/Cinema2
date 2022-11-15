package com.example.cinema.prefs;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.cinema.model.User;
import com.example.cinema.util.StringUtil;
import com.google.gson.Gson;

public class DataStoreManager {

    // public static final String PREF_IS_ADMIN = "PREF_IS_ADMIN";
    public static final String PREF_USER_INFOR = "PREF_USER_INFOR";

    private static DataStoreManager instance;
    private MySharedPreferences sharedPreferences;

    /**
     * Call when start application
     */
    public static void init(Context context) {
        instance = new DataStoreManager();
        instance.sharedPreferences = new MySharedPreferences(context);
    }

    public static DataStoreManager getInstance() {
        if (instance != null) {
            return instance;
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }

    public static void setUser(@Nullable User user) {
        String jsonUser = "";
        if (user != null) {
            jsonUser = user.toJSon();
        }
        DataStoreManager.getInstance().sharedPreferences.putStringValue(PREF_USER_INFOR, jsonUser);
    }

    public static User getUser() {
        String jsonUser = DataStoreManager.getInstance().sharedPreferences.getStringValue(PREF_USER_INFOR);
        if (!StringUtil.isEmpty(jsonUser)) {
            return new Gson().fromJson(jsonUser, User.class);
        }
        return new User();
    }

    /*public static void setIsAdmin(boolean isAdmin) {
        DataStoreManager.getInstance().sharedPreferences.putBooleanValue(PREF_IS_ADMIN, isAdmin);
    }

    public static boolean getIsAdmin() {
        return DataStoreManager.getInstance().sharedPreferences.getBooleanValue(PREF_IS_ADMIN);
    }*/
}