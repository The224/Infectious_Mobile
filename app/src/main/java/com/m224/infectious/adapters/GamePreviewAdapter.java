package com.m224.infectious.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.infectious.R;
import com.m224.infectious.activities.MainActivity;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class GamePreviewAdapter extends RecyclerView.Adapter<GamePreviewAdapter.Card> {

    private List<String> titleList;
    private List<Integer> imageResList;
    private Context context;

    public class Card extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView preview;

        public Card(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            preview = view.findViewById(R.id.preview);
        }
    }

    public GamePreviewAdapter(Context context, List<String> titleList, List<Integer> imageResList) {;
        this.titleList = titleList;
        this.imageResList = imageResList;
        this.context = context;
    }

    @Override
    public Card onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_game_preview, parent, false);
        return new Card(itemView);
    }

    @Override
    public void onBindViewHolder(final Card holder, final int position) {
        final MainActivity mainActivity = (MainActivity)context;

        holder.title.setText(titleList.get(position));
        holder.preview.setImageResource(imageResList.get(position));

        holder.preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startGameWithConfig(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startGameWithConfig(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}