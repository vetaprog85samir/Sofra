
package com.food.sofra.data.model.restaurant.offers.restaurantUpdateOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantUpdateOffer {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantUpdateOfferData")
    @Expose
    private RestaurantUpdateOfferPagination data;

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

    public RestaurantUpdateOfferPagination getData() {
        return data;
    }

    public void setData(RestaurantUpdateOfferPagination data) {
        this.data = data;
    }

}
