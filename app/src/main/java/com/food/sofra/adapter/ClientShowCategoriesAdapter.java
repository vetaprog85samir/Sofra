package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.general.restaurants.generalListOfCategories.GeneralListOfCategoriesData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

ClientShowCategoriesAdapter extends RecyclerView.Adapter<ClientShowCategoriesAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<GeneralListOfCategoriesData> generalListOfCategoriesData = new ArrayList<>();
    public  int selectedId = 0;

    public ClientShowCategoriesAdapter(Context context, Activity activity, List<GeneralListOfCategoriesData> generalListOfCategoriesData) {
        this.context = context;
        this.activity = activity;
        this.generalListOfCategoriesData = generalListOfCategoriesData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_client_show_categories_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return generalListOfCategoriesData.size();
    }

    @OnClick({R.id.fragment_show_categories_row_item_iv_edit, R.id.fragment_show_categories_row_item_iv_delete, R.id.fragment_show_categories_row_item_layout_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_show_categories_row_item_iv_edit:
                break;
            case R.id.fragment_show_categories_row_item_iv_delete:
                break;
            case R.id.fragment_show_categories_row_item_layout_main:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_show_categories_row_item_iv_category)
        ImageView fragmentShowCategoriesRowItemIvCategory;
        @BindView(R.id.fragment_show_categories_row_item_tv_category)
        TextView fragmentShowCategoriesRowItemTvCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        selectedId = generalListOfCategoriesData.get(position).getId();

        return super.getItemViewType(position);
    }
}
