package com.lightgeneration.kid_locker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.models.AppInfo;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public class AppAdapter extends ArrayAdapter<AppInfo> {
    private ArrayList<AppInfo> appInfos;
    private LayoutInflater inflater;

    public AppAdapter(Context context, int resource, ArrayList<AppInfo> objects) {
        super(context, resource, objects);
        appInfos = objects;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_app, parent, false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.lock = (ImageView) convertView.findViewById(R.id.img_lock);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        final ViewHolder finalHolder = holder;

        if (appInfos.get(position).isClick()) {
            finalHolder.lock.setImageResource(R.drawable.padlock);
        } else {
            finalHolder.lock.setImageResource(R.drawable.padlock_unlock);
        }

        holder.img.setImageDrawable(appInfos.get(position).getIcon());
        holder.tv.setText(appInfos.get(position).getTitle());
        return convertView;
    }

    public class ViewHolder {
        private ImageView img, lock;
        private TextView tv;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }
}
