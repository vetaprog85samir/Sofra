package com.food.sofra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.sofra.R;
import com.food.sofra.data.model.client.notifications.listOfNotifications.ListOfNotificationsData;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

ClientNotificationAdapter extends RecyclerView.Adapter<ClientNotificationAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<ListOfNotificationsData> listOfNotificationsData = new ArrayList<>();
    private Date date;

    public ClientNotificationAdapter(Context context, Activity activity, List<ListOfNotificationsData> listOfNotificationsData) {
        this.context = context;
        this.activity = activity;
        this.listOfNotificationsData = listOfNotificationsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_notification_row_item,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        if(listOfNotificationsData.get(position).getAction().equals("new order")){
            holder.fragmentNotificationRowItemIvPhotoOfNotification.setImageResource(R.drawable.ic_notifications_active_grey);
        }else holder.fragmentNotificationRowItemIvPhotoOfNotification.setImageResource(R.drawable.ic_notifications_none);

        holder.fragmentNotificationRowItemTvNotification.setText(listOfNotificationsData.get(position).getTitle());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        try {
            date = simpleDateFormat.parse(listOfNotificationsData.get(position).getCreatedAt());
        }catch (Exception e){}

        PrettyTime prettyTime = new PrettyTime();
        String time = prettyTime.format(date);
        holder.fragmentNotificationRowItemTvTime.setText(time);

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listOfNotificationsData.size();
    }

    @OnClick(R.id.fragment_notification_row_item_layout_main)
    public void onViewClicked() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.fragment_notification_row_item_iv_photo_of_notification)
        ImageView fragmentNotificationRowItemIvPhotoOfNotification;
        @BindView(R.id.fragment_notification_row_item_tv_notification)
        TextView fragmentNotificationRowItemTvNotification;
        @BindView(R.id.fragment_notification_row_item_iv_timer)
        ImageView fragmentNotificationRowItemIvTimer;
        @BindView(R.id.fragment_notification_row_item_tv_time)
        TextView fragmentNotificationRowItemTvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
