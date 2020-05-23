
package com.food.sofra.data.model.general.restaurants.generalRestaurantDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralRestaurantDetails {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalRestaurantDetailsData")
    @Expose
    private GeneralRestaurantDetailsData generalRestaurantDetailsData;

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

    public GeneralRestaurantDetailsData getGeneralRestaurantDetailsData() {
        return generalRestaurantDetailsData;
    }

    public void setGeneralRestaurantDetailsData(GeneralRestaurantDetailsData generalRestaurantDetailsData) {
        this.generalRestaurantDetailsData = generalRestaurantDetailsData;
    }

}
