
package com.food.sofra.data.model.client.auth.clientRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientRegister {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("clientRegisterData")
    @Expose
    private ClientRegisterData clientRegisterData;

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

    public ClientRegisterData getClientRegisterData() {
        return clientRegisterData;
    }

    public void setClientRegisterData(ClientRegisterData clientRegisterData) {
        this.clientRegisterData = clientRegisterData;
    }

}
