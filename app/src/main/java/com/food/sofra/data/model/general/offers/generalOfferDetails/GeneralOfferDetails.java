
package com.food.sofra.data.model.general.offers.generalOfferDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralOfferDetails {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalOfferDetailsData")
    @Expose
    private GeneralOfferDetailsPagination data;

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

    public GeneralOfferDetailsPagination getData() {
        return data;
    }

    public void setData(GeneralOfferDetailsPagination data) {
        this.data = data;
    }

}
