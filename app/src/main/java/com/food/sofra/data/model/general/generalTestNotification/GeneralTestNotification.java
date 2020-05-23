
package com.food.sofra.data.model.general.generalTestNotification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralTestNotification {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("send")
    @Expose
    private Object send;

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

    public Object getSend() {
        return send;
    }

    public void setSend(Object send) {
        this.send = send;
    }

}
