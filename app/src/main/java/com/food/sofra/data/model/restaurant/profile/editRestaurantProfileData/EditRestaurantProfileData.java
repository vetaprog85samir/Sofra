
package com.food.sofra.data.model.restaurant.profile.editRestaurantProfileData;

import com.food.sofra.data.model.client.auth.clientLogin.ClientUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditRestaurantProfileData {

    @SerializedName("user")
    @Expose
    private ClientUser user;

    public ClientUser getUser() {
        return user;
    }

    public void setUser(ClientUser user) {
        this.user = user;
    }

}
