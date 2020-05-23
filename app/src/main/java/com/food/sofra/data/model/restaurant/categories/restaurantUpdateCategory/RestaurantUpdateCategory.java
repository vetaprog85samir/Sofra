
package com.food.sofra.data.model.restaurant.categories.restaurantUpdateCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantUpdateCategory {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantUpdateCategoryData")
    @Expose
    private RestaurantUpdateCategoryData restaurantUpdateCategoryData;

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

    public RestaurantUpdateCategoryData getRestaurantUpdateCategoryData() {
        return restaurantUpdateCategoryData;
    }

    public void setRestaurantUpdateCategoryData(RestaurantUpdateCategoryData restaurantUpdateCategoryData) {
        this.restaurantUpdateCategoryData = restaurantUpdateCategoryData;
    }

}
