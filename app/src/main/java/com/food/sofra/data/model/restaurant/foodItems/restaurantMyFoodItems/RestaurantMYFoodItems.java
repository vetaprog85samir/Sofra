
package com.food.sofra.data.model.restaurant.foodItems.restaurantMyFoodItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantMYFoodItems {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantMYFoodItemsData")
    @Expose
    private RestaurantMYFoodItemsData restaurantMYFoodItemsData;

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

    public RestaurantMYFoodItemsData getRestaurantMYFoodItemsData() {
        return restaurantMYFoodItemsData;
    }

    public void setRestaurantMYFoodItemsData(RestaurantMYFoodItemsData restaurantMYFoodItemsData) {
        this.restaurantMYFoodItemsData = restaurantMYFoodItemsData;
    }

}
