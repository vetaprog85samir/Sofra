
package com.food.sofra.data.model.restaurant.orders.restaurantAcceptOrder;

import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrderData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantAcceptOrder {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ClientNewOrderData data;

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
        return data;
    }

    public void setData(ClientNewOrderData data) {
        this.data = data;
    }

}
