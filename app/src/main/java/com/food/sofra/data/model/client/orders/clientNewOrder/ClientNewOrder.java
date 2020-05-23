
package com.food.sofra.data.model.client.orders.clientNewOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientNewOrder {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("clientNewOrderData")
    @Expose
    private ClientNewOrderData clientNewOrderData;

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

    public ClientNewOrderData getData() {
        return clientNewOrderData;
    }

    public void setClientNewOrderData(ClientNewOrderData clientNewOrderData) {
        this.clientNewOrderData = clientNewOrderData;
    }

}
