
package com.food.sofra.data.model.client.profile.getClientProfile;

import com.food.sofra.data.model.client.auth.clientLogin.ClientUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetClientProfileData {

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
