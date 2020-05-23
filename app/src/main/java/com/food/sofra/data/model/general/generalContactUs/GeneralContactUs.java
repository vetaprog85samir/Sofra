
package com.food.sofra.data.model.general.generalContactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralContactUs {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalContactUsData")
    @Expose
    private GeneralContactUsData generalContactUsData;

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

    public GeneralContactUsData getGeneralContactUsData() {
        return generalContactUsData;
    }

    public void setGeneralContactUsData(GeneralContactUsData generalContactUsData) {
        this.generalContactUsData = generalContactUsData;
    }

}
