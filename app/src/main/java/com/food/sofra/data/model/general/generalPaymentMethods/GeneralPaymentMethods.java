
package com.food.sofra.data.model.general.generalPaymentMethods;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralPaymentMethods {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<GeneralPaymentMethodsData> data = null;

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

    public List<GeneralPaymentMethodsData> getData() {
        return data;
    }

    public void setData(List<GeneralPaymentMethodsData> data) {
        this.data = data;
    }

}
