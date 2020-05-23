
package com.food.sofra.data.model.restaurant.auth.restaurantLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantLogin {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantLoginData")
    @Expose
    private RestaurantLoginData restaurantLoginData;

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

    public RestaurantLoginData getRestaurantLoginData() {
        return restaurantLoginData;
    }

    public void setRestaurantLoginData(RestaurantLoginData restaurantLoginData) {
        this.restaurantLoginData = restaurantLoginData;
    }

}
