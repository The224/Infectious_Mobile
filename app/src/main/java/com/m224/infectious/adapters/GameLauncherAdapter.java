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
import com.m224.infectious.domaine.Board;
import com.m224.infectious.utils.GridConfig;

import java.util.Arrays;
import java.util.List;

public class GameLauncherAdapter extends RecyclerView.Adapter<GameLauncherAdapter.Card> {

    private final List<Board> boards;
    private Context context;

    protected class Card extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        // TODO : Mettre des icons et meilleur card

        private Card(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
        }
    }

    public GameLauncherAdapter(Context context, List<Board> boards) {
        this.boards = boards;
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

        holder.title.setText(boards.get(position).getTitle());

        if (Arrays.equals(boards.get(position).getBlockTile(), GridConfig.SQUARE))
            holder.image.setImageResource(R.drawable.config_2);
        else if (Arrays.equals(boards.get(position).getBlockTile(), GridConfig.BLOCK))
            holder.image.setImageResource(R.drawable.config_3);
        else if (Arrays.equals(boards.get(position).getBlockTile(), GridConfig.CROSS))
            holder.image.setImageResource(R.drawable.config_4);
        else
            holder.image.setImageResource(R.drawable.config_1);



            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startGameActivity(boards.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }
}