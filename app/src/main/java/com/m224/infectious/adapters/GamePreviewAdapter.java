package com.m224.infectious.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m224.infectious.R;
import com.m224.infectious.activities.MainActivity;
import com.m224.infectious.domaine.CardConfig;
import com.m224.infectious.utils.GameType;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class GamePreviewAdapter extends RecyclerView.Adapter<GamePreviewAdapter.Card> {

    private List<CardConfig> cardConfigs;
    private Context context;

    public class Card extends RecyclerView.ViewHolder {
        public TextView title;
        public GameType gameType;
        public int configId;

        public Card(View view) {
            super(view);
            title = view.findViewById(R.id.title);
        }
    }

    public GamePreviewAdapter(Context context, List<CardConfig> cardConfigs) {
        this.cardConfigs = cardConfigs;
        this.context = context;
    }

    @Override
    public Card onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_config_preview, parent, false);
        return new Card(itemView);
    }

    @Override
    public void onBindViewHolder(final Card holder, final int position) {
        final MainActivity mainActivity = (MainActivity)context;

        holder.title.setText(cardConfigs.get(position).getTitle());
        holder.gameType = cardConfigs.get(position).getGameType();
        holder.configId = cardConfigs.get(position).getConfigId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startGameWithConfig(holder.configId, holder.gameType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardConfigs.size();
    }
}