package com.m224.infectious.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m224.infectious.R;
import com.m224.infectious.activities.MainActivity;
import com.m224.infectious.domaine.Board;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class GameLauncherAdapter extends RecyclerView.Adapter<GameLauncherAdapter.Card> {

    private final List<Board> boards;
    private Context context;

    public class Card extends RecyclerView.ViewHolder {
        public TextView title;

        public Card(View view) {
            super(view);
            title = view.findViewById(R.id.title);
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