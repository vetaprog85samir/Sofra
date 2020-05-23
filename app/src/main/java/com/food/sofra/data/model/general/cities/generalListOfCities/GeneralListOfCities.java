
package com.food.sofra.data.model.general.cities.generalListOfCities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfCities {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("cityPagination")
    @Expose
    private CityPagination cityPagination;

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

    public CityPagination getCityPagination() {
        return cityPagination;
    }

    public void setCityPagination(CityPagination cityPagination) {
        this.cityPagination = cityPagination;
    }

}
