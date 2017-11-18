package com.m224.infectious.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.m224.infectious.domaine.Save;
import com.m224.infectious.task.FetchSaveTask;
import com.m224.infectious.R;
import com.m224.infectious.adapters.GameLauncherAdapter;
import com.m224.infectious.domaine.Board;
import com.m224.infectious.utils.GridConfig;
import com.m224.infectious.utils.GameType;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Ptn to the RecyclerAdapter for update
    private GameLauncherAdapter adapter;
    // List of fetch game
    private List<Board> saveBoards;
    // List of user board choice
    private List<Board> recyclerBoards;
    // BottomNav user choice
    private GameType userGameTypeChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.customActionbar(this, R.layout.actionbar_main);

        saveBoards = new ArrayList<>();
        recyclerBoards = new ArrayList<>();
        userGameTypeChoice = GameType.LOCAL;

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navGameTypeListener);

        findSaveGame();
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        findSaveGame();
    }

    // Start FetchSaveTask
    public void findSaveGame() {
        new FetchSaveTask(this).execute();
    }

    // Response of FetchSaveTask
    public void updateSaveGames(List<Board> saveGames) {
        this.saveBoards = saveGames;
        updateRecyclerBoards();

        Log.d("updateSaveGames", "Taille : "+saveGames.size());

        for (Board board : saveGames)
            Log.d("updateSaveGames", board.toString());

    }

    private void initRecyclerView() {
        adapter = new GameLauncherAdapter(this, recyclerBoards);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        updateRecyclerBoards();
    }

    public void updateRecyclerBoards() {
        recyclerBoards.clear();

        putSaveInRecycler();

        if (userGameTypeChoice == GameType.COMPUTER) {
            recyclerBoards.add(new Board("New Field Game VS computer", GridConfig.FIELD, GameType.COMPUTER));
        } else {
            recyclerBoards.add(new Board("New Field Game", GridConfig.FIELD, GameType.LOCAL));
            recyclerBoards.add(new Board("New Square Game", GridConfig.SQUARE, GameType.LOCAL));
            recyclerBoards.add(new Board("New Block Game", GridConfig.BLOCK, GameType.LOCAL));
            recyclerBoards.add(new Board("New Cross Game", GridConfig.CROSS, GameType.LOCAL));
        }

        adapter.notifyDataSetChanged();
    }

    public void putSaveInRecycler() {
        for (int i = 0; i < saveBoards.size(); i++) {
            if (saveBoards.get(i).getGameType() == userGameTypeChoice)
                recyclerBoards.add(saveBoards.get(i));
        }
    }

    /**
     * Listener for game type bottom buttons
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navGameTypeListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_human:
                    userGameTypeChoice = GameType.LOCAL;
                    break;
                case R.id.navigation_computer:
                    userGameTypeChoice = GameType.COMPUTER;
                    break;
                case R.id.navigation_online:
                    // One day ! Sadly, not today ! #Firebase
                    return false;
            }
            updateRecyclerBoards();
            return true;
        }
    };

    public void startGameActivity(Board board) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("board", board);

        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }

    public void startInformationActivity(View v) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_start, R.anim.left_end);
    }
}
