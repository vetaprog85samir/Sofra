
package com.food.sofra.data.model.client.auth.clientLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientLogin {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("clientLoginData")
    @Expose
    private ClientLoginData clientLoginData;

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

    public ClientLoginData getClientLoginData() {
        return clientLoginData;
    }

    public void setClientLoginData(ClientLoginData clientLoginData) {
        this.clientLoginData = clientLoginData;
    }

}
