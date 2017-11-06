package com.m224.ataxx.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m224.ataxx.R;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class ConfigListAdapter extends RecyclerView.Adapter<ConfigListAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> titleList;
    private List<Integer> imageResList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }

    public ConfigListAdapter(Context mContext, List<String> titleList, List<Integer> imageResList) {
        this.mContext = mContext;
        this.titleList = titleList;
        this.imageResList = imageResList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_config_info, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.title.setText(titleList.get(position));
        holder.thumbnail.setImageResource(imageResList.get(position));

    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}