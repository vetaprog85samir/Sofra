
package com.food.sofra.data.model.general.offers.generalListOfOffers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfOffers {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalListOfOffersData")
    @Expose
    private GeneralListOfOffersData generalListOfOffersData;

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

    public GeneralListOfOffersData getGeneralListOfOffersData() {
        return generalListOfOffersData;
    }

    public void setGeneralListOfOffersData(GeneralListOfOffersData generalListOfOffersData) {
        this.generalListOfOffersData = generalListOfOffersData;
    }

}
