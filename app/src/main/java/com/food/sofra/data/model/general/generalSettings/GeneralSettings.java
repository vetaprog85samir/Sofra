
package com.food.sofra.data.model.general.generalSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralSettings {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("generalSettingsData")
    @Expose
    private GeneralSettingsData generalSettingsData;

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

    public GeneralSettingsData getGeneralSettingsData() {
        return generalSettingsData;
    }

    public void setGeneralSettingsData(GeneralSettingsData generalSettingsData) {
        this.generalSettingsData = generalSettingsData;
    }

}
