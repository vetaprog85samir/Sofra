
package com.food.sofra.data.model.restaurant.notifications.restaurantListOfNotification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantListOfNotification {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantListOfNotificationPagination")
    @Expose
    private RestaurantListOfNotificationPagination restaurantListOfNotificationPagination;

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

    public RestaurantListOfNotificationPagination getRestaurantListOfNotificationPagination() {
        return restaurantListOfNotificationPagination;
    }

    public void setRestaurantListOfNotificationPagination(RestaurantListOfNotificationPagination restaurantListOfNotificationPagination) {
        this.restaurantListOfNotificationPagination = restaurantListOfNotificationPagination;
    }

}
