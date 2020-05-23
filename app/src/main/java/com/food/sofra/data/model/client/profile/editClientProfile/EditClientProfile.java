
package com.food.sofra.data.model.client.profile.editClientProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditClientProfile {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("editClientProfileData")
    @Expose
    private EditClientProfileData editClientProfileData;

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

    public EditClientProfileData getEditClientProfileData() {
        return editClientProfileData;
    }

    public void setEditClientProfileData(EditClientProfileData editClientProfileData) {
        this.editClientProfileData = editClientProfileData;
    }

}
