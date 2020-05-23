
package com.food.sofra.data.model.general.cities.generalListOfRegionsByCityIdNotPaginated;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfRegionsByCityIdNotPaginated {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<GeneralListOfRegionsByCityIdNotPaginatedData> data = null;

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

    public List<GeneralListOfRegionsByCityIdNotPaginatedData> getData() {
        return data;
    }

    public void setData(List<GeneralListOfRegionsByCityIdNotPaginatedData> data) {
        this.data = data;
    }

}
