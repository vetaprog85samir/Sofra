
package com.food.sofra.data.model.restaurant.auth.restaurantResetPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantResetPassword {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantResetPasswordData")
    @Expose
    private RestaurantResetPasswordData restaurantResetPasswordData;

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

    public RestaurantResetPasswordData getRestaurantResetPasswordData() {
        return restaurantResetPasswordData;
    }

    public void setRestaurantResetPasswordData(RestaurantResetPasswordData restaurantResetPasswordData) {
        this.restaurantResetPasswordData = restaurantResetPasswordData;
    }

}
