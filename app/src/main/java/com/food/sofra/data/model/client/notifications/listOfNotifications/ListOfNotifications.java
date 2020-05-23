
package com.food.sofra.data.model.client.notifications.listOfNotifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfNotifications {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("listOfNotificationsPagination")
    @Expose
    private ListOfNotificationsPagination listOfNotificationsPagination;

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

    public ListOfNotificationsPagination getListOfNotificationsPagination() {
        return listOfNotificationsPagination;
    }

    public void setListOfNotificationsPagination(ListOfNotificationsPagination listOfNotificationsPagination) {
        this.listOfNotificationsPagination = listOfNotificationsPagination;
    }

}
