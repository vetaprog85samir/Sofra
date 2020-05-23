
package com.food.sofra.data.model.restaurant.offers.restaurantMyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantMyOffer {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantMyOfferPagination")
    @Expose
    private RestaurantMyOfferPagination restaurantMyOfferPagination;

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

    public RestaurantMyOfferPagination getRestaurantMyOfferPagination() {
        return restaurantMyOfferPagination;
    }

    public void setRestaurantMyOfferPagination(RestaurantMyOfferPagination restaurantMyOfferPagination) {
        this.restaurantMyOfferPagination = restaurantMyOfferPagination;
    }

}
