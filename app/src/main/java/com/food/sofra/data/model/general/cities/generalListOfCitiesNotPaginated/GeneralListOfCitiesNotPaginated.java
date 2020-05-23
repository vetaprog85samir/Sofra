
package com.food.sofra.data.model.general.cities.generalListOfCitiesNotPaginated;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfCitiesNotPaginated {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<GeneralListOfCitiesNotPaginatedData> data = null;

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

    public List<GeneralListOfCitiesNotPaginatedData> getData() {
        return data;
    }

    public void setData(List<GeneralListOfCitiesNotPaginatedData> data) {
        this.data = data;
    }

}
