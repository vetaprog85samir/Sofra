
package com.food.sofra.data.model.restaurant.profile.restaurantChangeState;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantChangeState {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantChangeStateData")
    @Expose
    private RestaurantChangeStateData restaurantChangeStateData;

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

    public RestaurantChangeStateData getRestaurantChangeStateData() {
        return restaurantChangeStateData;
    }

    public void setRestaurantChangeStateData(RestaurantChangeStateData restaurantChangeStateData) {
        this.restaurantChangeStateData = restaurantChangeStateData;
    }

}
