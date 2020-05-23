
package com.food.sofra.data.model.client.auth.clientLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientLoginData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("clientUser")
    @Expose
    private ClientUser clientUser;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public ClientUser getClientUser() {
        return clientUser;
    }

    public void setClientUser(ClientUser clientUser) {
        this.clientUser = clientUser;
    }

}
