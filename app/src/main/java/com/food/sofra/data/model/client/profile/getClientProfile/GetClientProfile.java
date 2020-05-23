
package com.food.sofra.data.model.client.profile.getClientProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetClientProfile {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("getClientProfileData")
    @Expose
    private GetClientProfileData getClientProfileData;

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

    public GetClientProfileData getGetClientProfileData() {
        return getClientProfileData;
    }

    public void setGetClientProfileData(GetClientProfileData getClientProfileData) {
        this.getClientProfileData = getClientProfileData;
    }

}
