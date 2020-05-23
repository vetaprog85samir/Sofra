
package com.food.sofra.data.model.client.clientAddReview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientAddReview {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("clientAddReviewData")
    @Expose
    private ClientAddReviewData clientAddReviewData;

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

    public ClientAddReviewData getClientAddReviewData() {
        return clientAddReviewData;
    }

    public void setClientAddReviewData(ClientAddReviewData clientAddReviewData) {
        this.clientAddReviewData = clientAddReviewData;
    }

}
