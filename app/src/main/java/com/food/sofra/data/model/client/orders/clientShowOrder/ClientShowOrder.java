
package com.food.sofra.data.model.client.orders.clientShowOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientShowOrder {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("clientShowOrderData")
    @Expose
    private ClientShowOrderData clientShowOrderData;

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

    public ClientShowOrderData getClientShowOrderData() {
        return clientShowOrderData;
    }

    public void setClientShowOrderData(ClientShowOrderData clientShowOrderData) {
        this.clientShowOrderData = clientShowOrderData;
    }

}
