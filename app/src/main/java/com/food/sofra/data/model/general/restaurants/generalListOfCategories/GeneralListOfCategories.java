
package com.food.sofra.data.model.general.restaurants.generalListOfCategories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralListOfCategories {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private GeneralListOfCategoriesPagination data = null;

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

    public GeneralListOfCategoriesPagination getData() {
        return data;
    }

    public void setData(GeneralListOfCategoriesPagination data) {
        this.data = data;
    }

}
