
package com.food.sofra.data.model.restaurant.orders.restaurantGetCommission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantGetCommission {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantGetCommissionData")
    @Expose
    private RestaurantGetCommissionData restaurantGetCommissionData;

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

    public RestaurantGetCommissionData getRestaurantGetCommissionData() {
        return restaurantGetCommissionData;
    }

    public void setRestaurantGetCommissionData(RestaurantGetCommissionData restaurantGetCommissionData) {
        this.restaurantGetCommissionData = restaurantGetCommissionData;
    }

}
