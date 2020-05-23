
package com.food.sofra.data.model.general.restaurants.generalListOfRestaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfRestaurant {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalListOfRestaurantPagination")
    @Expose
    private GeneralListOfRestaurantPagination generalListOfRestaurantPagination;

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

    public GeneralListOfRestaurantPagination getData() {
        return generalListOfRestaurantPagination;
    }

    public void setData(GeneralListOfRestaurantPagination generalListOfRestaurantPagination) {
        this.generalListOfRestaurantPagination = generalListOfRestaurantPagination;
    }

}
