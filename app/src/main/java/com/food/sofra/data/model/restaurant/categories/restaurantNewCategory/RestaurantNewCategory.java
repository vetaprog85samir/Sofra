
package com.food.sofra.data.model.restaurant.categories.restaurantNewCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantNewCategory {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("restaurantNewCategoryData")
    @Expose
    private RestaurantNewCategoryData restaurantNewCategoryData;

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

    public RestaurantNewCategoryData getRestaurantNewCategoryData() {
        return restaurantNewCategoryData;
    }

    public void setRestaurantNewCategoryData(RestaurantNewCategoryData restaurantNewCategoryData) {
        this.restaurantNewCategoryData = restaurantNewCategoryData;
    }

}
