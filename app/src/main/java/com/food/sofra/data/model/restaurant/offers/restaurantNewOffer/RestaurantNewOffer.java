
package com.food.sofra.data.model.restaurant.offers.restaurantNewOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantNewOffer {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantNewOfferData")
    @Expose
    private RestaurantNewOfferData restaurantNewOfferData;

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

    public RestaurantNewOfferData getRestaurantNewOfferData() {
        return restaurantNewOfferData;
    }

    public void setRestaurantNewOfferData(RestaurantNewOfferData restaurantNewOfferData) {
        this.restaurantNewOfferData = restaurantNewOfferData;
    }

}
