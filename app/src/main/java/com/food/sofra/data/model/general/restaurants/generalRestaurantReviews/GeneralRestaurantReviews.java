
package com.food.sofra.data.model.general.restaurants.generalRestaurantReviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralRestaurantReviews {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private GeneralRestaurantReviewsData data;

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

    public GeneralRestaurantReviewsData getData() {
        return data;
    }

    public void setData(GeneralRestaurantReviewsData generalRestaurantReviewsData) {
        this.data = generalRestaurantReviewsData;
    }

}
