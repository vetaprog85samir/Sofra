
package com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfRestaurantItems {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalListOfRestaurantItemsPagination")
    @Expose
    private GeneralListOfRestaurantItemsPagination generalListOfRestaurantItemsPagination;

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

    public GeneralListOfRestaurantItemsPagination getData() {
        return generalListOfRestaurantItemsPagination;
    }

    public void setData(GeneralListOfRestaurantItemsPagination generalListOfRestaurantItemsPagination) {
        this.generalListOfRestaurantItemsPagination = generalListOfRestaurantItemsPagination;
    }

}
