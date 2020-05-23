
package com.food.sofra.data.model.restaurant.profile.editRestaurantProfileData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditRestaurantProfile {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("editRestaurantProfile")
    @Expose
    private EditRestaurantProfile editRestaurantProfile;

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

    public EditRestaurantProfile getEditRestaurantProfile() {
        return editRestaurantProfile;
    }

    public void setEditRestaurantProfile(EditRestaurantProfile editRestaurantProfile) {
        this.editRestaurantProfile = editRestaurantProfile;
    }

}
