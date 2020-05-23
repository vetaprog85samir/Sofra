
package com.food.sofra.data.model.restaurant.orders.restaurantMyOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantMyOrders {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantMyOrdersPagination")
    @Expose
    private RestaurantMyOrdersPagination restaurantMyOrdersPagination;

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

    public RestaurantMyOrdersPagination getRestaurantMyOrdersPagination() {
        return restaurantMyOrdersPagination;
    }

    public void setRestaurantMyOrdersPagination(RestaurantMyOrdersPagination restaurantMyOrdersPagination) {
        this.restaurantMyOrdersPagination = restaurantMyOrdersPagination;
    }

}
