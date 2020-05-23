package com.food.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.general.cities.generalListOfCities.CityData;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.RegionData;

import java.util.ArrayList;
import java.util.List;


public class RegionSpinnerAdapter extends BaseAdapter {

    private Context context;
    public List<RegionData> regionData = new ArrayList<>();
    private LayoutInflater inflater;
    public int selectedId = 0;

    public RegionSpinnerAdapter(Context applicationContext, List<RegionData> regionData) {
        this.context = applicationContext;
        this.regionData = regionData;
        inflater = (LayoutInflater.from(applicationContext));
    }

   public void setData(List<RegionData> regionData, String hint) {
        this.regionData = new ArrayList<>();
        this.regionData.add(new RegionData(0, hint));
        this.regionData.addAll(regionData);
    }

    @Override
    public int getCount() {
        return regionData.size();
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

        names.setText(regionData.get(i).getName());
        selectedId = regionData.get(i).getId();

        return view;
    }
}