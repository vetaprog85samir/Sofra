package com.food.sofra.data.local.sharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.food.sofra.data.model.client.auth.clientLogin.ClientLoginData;
import com.food.sofra.data.model.client.auth.clientRegister.ClientRegisterData;
import com.food.sofra.data.model.restaurant.auth.restaurantLogin.RestaurantLoginData;
import com.google.gson.Gson;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences = null;
    public static String userType = "userType";
    public static String userTypeClient = "client";
    public static String userTypeRestaurant = "restaurant";
    public static String clientData = "clientData";
    public static String restaurantData = "restaurantData";
    public static ClientLoginData clientUserLoginData = null;
    public static ClientRegisterData clientRegisterData = null;
    public static RestaurantLoginData restaurantUserData = null;


    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Sofra", Context.MODE_PRIVATE);
        }
    }

    public static void saveData(Activity activity, String dataKey, String dataValue) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(dataKey, dataValue);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String dataKey, boolean dataValue) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(dataKey, dataValue);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String dataKey, Object dataValue) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(dataValue);
            editor.putString(dataKey, StringData);
            editor.commit();
        }
    }

    public static void saveClientData(Activity activity, ClientLoginData clientData) {
        saveData(activity, SharedPreferencesManger.clientData, clientData);
    }

    public static void saveClientRegisterData(Activity activity, ClientRegisterData clientData) {
        saveData(activity, SharedPreferencesManger.clientData, clientData);
    }

    public static void saveRestaurantData(Activity activity, RestaurantLoginData restaurantData) {
        saveData(activity, SharedPreferencesManger.restaurantData, restaurantData);
    }

    public static void saveUserType(Activity activity, String userType) {
        saveData(activity, SharedPreferencesManger.userType, userType);
    }

    public static String loadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean loadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static ClientLoginData loadClientLoginData(Activity activity, String data_Key) {

        Gson gson = new Gson();
        clientUserLoginData = gson.fromJson(loadData(activity, data_Key), ClientLoginData.class);

        return clientUserLoginData;
    }

    public static ClientRegisterData loadClientRegisterData(Activity activity, String data_Key) {

        Gson gson = new Gson();
        clientRegisterData = gson.fromJson(loadData(activity, data_Key), ClientRegisterData.class);

        return clientRegisterData;
    }
    public static RestaurantLoginData loadRestaurantData(Activity activity, String data_Key) {

        Gson gson = new Gson();
        restaurantUserData = gson.fromJson(loadData(activity, data_Key), RestaurantLoginData.class);

        return restaurantUserData;
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

}
