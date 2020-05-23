
package com.food.sofra.data.model.restaurant.orders.restaurantShowOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantShowOrder {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantShowOrderData")
    @Expose
    private RestaurantShowOrderData restaurantShowOrderData;

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

    public RestaurantShowOrderData getRestaurantShowOrderData() {
        return restaurantShowOrderData;
    }

    public void setRestaurantShowOrderData(RestaurantShowOrderData restaurantShowOrderData) {
        this.restaurantShowOrderData = restaurantShowOrderData;
    }

}
