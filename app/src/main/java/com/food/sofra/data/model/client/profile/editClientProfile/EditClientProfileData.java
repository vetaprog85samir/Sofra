
package com.food.sofra.data.model.client.profile.editClientProfile;

import com.food.sofra.data.model.client.auth.clientLogin.ClientUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditClientProfileData {

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
