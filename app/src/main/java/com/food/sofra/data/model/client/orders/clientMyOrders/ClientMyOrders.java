
package com.food.sofra.data.model.client.orders.clientMyOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientMyOrders {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("clientMyOrdersPagination")
    @Expose
    private ClientMyOrdersPagination clientMyOrdersPagination;

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

    public ClientMyOrdersPagination getClientMyOrdersPagination() {
        return clientMyOrdersPagination;
    }

    public void setClientMyOrdersPagination(ClientMyOrdersPagination clientMyOrdersPagination) {
        this.clientMyOrdersPagination = clientMyOrdersPagination;
    }

}
