package com.food.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.food.sofra.R;


public class EmptySpinnerAdapter extends BaseAdapter {

    private Context context;
    //private List<GeneralResponseData> generalResponseDataList = home ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;

    public EmptySpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        //this.generalResponseDataList = generalResponseDataList;
        inflter = (LayoutInflater.from(applicationContext));
    }

   /* public void setAppSettingsData(List<GeneralResponseData> generalResponseDataList, String hint) {
        generalResponseDataList.add(home GeneralResponseData(0, hint));
        this.generalResponseDataList = generalResponseDataList;
    }*/

    @Override
    public int getCount() {
        return 0;
                //generalResponseDataList.size();
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
        view = inflter.inflate(R.layout.fragment_entro, null);
                //custom_spinner_items, null);

        TextView names = (TextView) view.findViewById(R.id.text);

        /*names.setText(generalResponseDataList.get(i).getName());
        selectedId = generalResponseDataList.get(i).getId();*/

        return view;
    }
}