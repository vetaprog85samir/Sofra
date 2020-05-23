
package com.food.sofra.data.model.restaurant.orders.restaurantCommission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantCommission {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantCommissionData")
    @Expose
    private RestaurantCommissionData restaurantCommissionData;

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

    public RestaurantCommissionData getRestaurantCommissionData() {
        return restaurantCommissionData;
    }

    public void setRestaurantCommissionData(RestaurantCommissionData restaurantCommissionData) {
        this.restaurantCommissionData = restaurantCommissionData;
    }

}
