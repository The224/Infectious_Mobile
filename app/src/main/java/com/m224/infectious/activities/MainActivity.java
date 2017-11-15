package com.m224.infectious.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.m224.infectious.AllSaveTask;
import com.m224.infectious.R;
import com.m224.infectious.adapters.GameLauncherAdapter;
import com.m224.infectious.domaine.Board;
import com.m224.infectious.utils.ConfigVariable;
import com.m224.infectious.utils.GameType;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameLauncherAdapter adapter;
    private List<Board> saveGames;
    private List<Board> recyclerGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveGames = new ArrayList<>();

        Util.customActionbar(this, R.layout.actionbar_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        findSaveGame();
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        findSaveGame();
    }

    public void findSaveGame() {
        new AllSaveTask(this).execute();
    }

    public void updateSaveGames(List<Board> saveGames) {
        this.saveGames = saveGames;
        putSaveInRecycler(GameType.LOCAL);
    }

    private void initRecyclerView() {
        recyclerGame = new ArrayList<>();

        adapter = new GameLauncherAdapter(this, recyclerGame);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareRecyclerHuman();
    }

    private void prepareRecyclerHuman() {
        recyclerGame.clear();

        putSaveInRecycler(GameType.LOCAL);

        recyclerGame.add(new Board("New Field Game", ConfigVariable.configField, GameType.LOCAL));
        recyclerGame.add(new Board("New Square Game", ConfigVariable.configSquare, GameType.LOCAL));
        recyclerGame.add(new Board("New Block Game", ConfigVariable.configBlock, GameType.LOCAL));
        recyclerGame.add(new Board("New Cross Game", ConfigVariable.configCross, GameType.LOCAL));
        adapter.notifyDataSetChanged();
    }

    private void prepareRecyclerComputer() {
        recyclerGame.clear();

        putSaveInRecycler(GameType.COMPUTER);

        recyclerGame.add(new Board("New Field Game VS computer", ConfigVariable.configField, GameType.COMPUTER));
        adapter.notifyDataSetChanged();
    }

    private void prepareRecyclerOnline() {
        recyclerGame.clear();

        putSaveInRecycler(GameType.ONLINE);

        recyclerGame.add(new Board("Connect to rival !", ConfigVariable.configCross, GameType.LOCAL));
        adapter.notifyDataSetChanged();
    }

    public void putSaveInRecycler(GameType gameType) {
        for (int i = 0; i < saveGames.size(); i++) {
            if (saveGames.get(i).getGameType() == gameType)
                recyclerGame.add(saveGames.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    public void startGameActivity(Board board) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("board", board);

        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }

    /**
     * Listener for 3 game type bottom button
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_human:
                    prepareRecyclerHuman();
                    return true;
                case R.id.navigation_computer:
                    prepareRecyclerComputer();
                    return true;
                case R.id.navigation_online:
                    prepareRecyclerOnline();
                    return true;
            }
            return false;
        }
    };

    public void startInformationActivity(View v) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_start, R.anim.left_end);
    }
}
