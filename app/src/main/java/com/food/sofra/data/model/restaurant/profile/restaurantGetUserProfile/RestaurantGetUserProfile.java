
package com.food.sofra.data.model.restaurant.profile.restaurantGetUserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantGetUserProfile {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantGetUserProfileData")
    @Expose
    private RestaurantGetUserProfileData restaurantGetUserProfileData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RestaurantGetUserProfileData getRestaurantGetUserProfileData() {
        return restaurantGetUserProfileData;
    }

    public void setRestaurantGetUserProfileData(RestaurantGetUserProfileData restaurantGetUserProfileData) {
        this.restaurantGetUserProfileData = restaurantGetUserProfileData;
    }

}
