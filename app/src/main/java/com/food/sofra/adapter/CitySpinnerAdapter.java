package com.food.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;

import java.util.ArrayList;
import java.util.List;


public class CitySpinnerAdapter extends BaseAdapter {

    private Context context;
    public List<CityData> cityData = new ArrayList<>();
    private LayoutInflater inflater;
    public int selectedId = 0;
    public String selectId = "";

    public CitySpinnerAdapter(Context applicationContext, List<CityData> cityData) {
        this.context = applicationContext;
        this.cityData = cityData;
        inflater = (LayoutInflater.from(applicationContext));
    }

   public void setData(List<CityData> cityData, String hint) {
        this.cityData = new ArrayList<>();
        this.cityData.add(new CityData(0, hint));
        this.cityData.addAll(cityData);
    }

    @Override
    public int getCount() {
        return cityData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_row_item, null);

        TextView names = view.findViewById(R.id.txt_spinner);

        names.setText(cityData.get(i).getName());
        selectedId = cityData.get(i).getId();

        return view;
    }
}