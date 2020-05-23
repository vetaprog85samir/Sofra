package com.food.sofra.utils;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.food.sofra.adapter.CitySpinnerAdapter;
import com.food.sofra.adapter.RegionSpinnerAdapter;
import com.food.sofra.data.model.general.cities.generalListOfCities.GeneralListOfCities;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.GeneralListOfRegionsByCityId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequest {

    public static void getSpinnerData(Activity activity, Spinner spinner, CitySpinnerAdapter adapter, String hint,
                                      Call<GeneralListOfCities> method){
        method.enqueue(new Callback<GeneralListOfCities>() {
            @Override
            public void onResponse(Call<GeneralListOfCities> call, Response<GeneralListOfCities> response) {
                try {
                    if(response.body().getStatus()==1){
                        adapter.setData(response.body().getCityPagination().getData(),hint);
                        spinner.setAdapter(adapter);
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GeneralListOfCities> call, Throwable t) {
            }
        });
    }
    //----------------------------------------------------------------------------------------------

    public static void getSpinnerData(Call<GeneralListOfCities> call, Spinner spinner, CitySpinnerAdapter adapter,
                                      Integer selectedId, String hint, AdapterView.OnItemSelectedListener listener) {
        call.enqueue(new Callback<GeneralListOfCities>() {
            @Override
            public void onResponse(Call<GeneralListOfCities> call, Response<GeneralListOfCities> response) {
                try {
                    adapter.setData(response.body().getCityPagination().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.cityData.size(); i++) {
                        if (adapter.cityData.get(i).getId() == selectedId) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                    spinner.setOnItemSelectedListener(listener);
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GeneralListOfCities> call, Throwable t) {
            }
        });
    }
//___________________________________________________________________________________________________________________

    public static void getSpinnerData(Call<GeneralListOfRegionsByCityId> call, Spinner spinner, RegionSpinnerAdapter adapter,
                                      Integer selectedId, String hint, AdapterView.OnItemSelectedListener listener) {
        call.enqueue(new Callback<GeneralListOfRegionsByCityId>() {
            @Override
            public void onResponse(Call<GeneralListOfRegionsByCityId> call, Response<GeneralListOfRegionsByCityId> response) {
                try {
                    adapter.setData(response.body().getRegionPagination().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.regionData.size(); i++) {
                        if (adapter.regionData.get(i).getId() == selectedId) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                    spinner.setOnItemSelectedListener(listener);
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GeneralListOfRegionsByCityId> call, Throwable t) {
            }
        });
    }
//___________________________________________________________________________________________________________________

    public static void getSpinnerData(Call<GeneralListOfCities> call, Spinner spinner, CitySpinnerAdapter adapter,
                                      Integer selectedId, String hint) {
        call.enqueue(new Callback<GeneralListOfCities>() {
            @Override
            public void onResponse(Call<GeneralListOfCities> call, Response<GeneralListOfCities> response) {
                try {
                    adapter.setData(response.body().getCityPagination().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.cityData.size(); i++) {
                        if (adapter.cityData.get(i).getId() == selectedId) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GeneralListOfCities> call, Throwable t) {
            }
        });
    }
    //----------------------------------------------------------------------------------------------

}
