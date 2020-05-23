
package com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfRegionsByCityId {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("regionPagination")
    @Expose
    private RegionPagination regionPagination;

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

    public RegionPagination getRegionPagination() {
        return regionPagination;
    }

    public void setRegionPagination(RegionPagination regionPagination) {
        this.regionPagination = regionPagination;
    }

}
