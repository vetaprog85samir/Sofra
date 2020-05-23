
package com.food.sofra.data.model.restaurant.notifications.restaurantListOfNotification;

import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrdersData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantListOfNotificationData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_en")
    @Expose
    private String titleEn;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("content_en")
    @Expose
    private String contentEn;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order")
    @Expose
    private ClientMyOrdersData order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ClientMyOrdersData getOrder() {
        return order;
    }

    public void setOrder(ClientMyOrdersData order) {
        this.order = order;
    }

}
