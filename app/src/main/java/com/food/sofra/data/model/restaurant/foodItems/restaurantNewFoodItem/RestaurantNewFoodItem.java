
package com.food.sofra.data.model.restaurant.foodItems.restaurantNewFoodItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantNewFoodItem {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantNewFoodItemData")
    @Expose
    private RestaurantNewFoodItemData restaurantNewFoodItemData;

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

    public RestaurantNewFoodItemData getRestaurantNewFoodItemData() {
        return restaurantNewFoodItemData;
    }

    public void setRestaurantNewFoodItemData(RestaurantNewFoodItemData restaurantNewFoodItemData) {
        this.restaurantNewFoodItemData = restaurantNewFoodItemData;
    }

}
