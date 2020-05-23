
package com.food.sofra.data.model.general.restaurants.generalListOfRestaurantWithFilter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfRestaurantWithFilter {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Data")
    @Expose
    private GeneralListOfRestaurantWithFilterPagination Data;

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

    public GeneralListOfRestaurantWithFilterPagination getData() {
        return Data;
    }

    public void setData(GeneralListOfRestaurantWithFilterPagination data) {
        this.Data = data;
    }

}
