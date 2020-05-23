
package com.food.sofra.data.model.client.clientAddReview;

import com.food.sofra.data.model.client.auth.clientLogin.ClientUser;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurant.GeneralListOfRestaurantData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("client_id")
    @Expose
    private Integer clientId;
    @SerializedName("client")
    @Expose
    private ClientUser client;
    @SerializedName("restaurant")
    @Expose
    private GeneralListOfRestaurantData restaurant;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public ClientUser getClient() {
        return client;
    }

    public void setClient(ClientUser client) {
        this.client = client;
    }

    public GeneralListOfRestaurantData getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(GeneralListOfRestaurantData restaurant) {
        this.restaurant = restaurant;
    }

}
