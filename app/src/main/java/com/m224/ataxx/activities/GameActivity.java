package com.m224.ataxx.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.ataxx.domaine.TileImageView;
import com.m224.ataxx.services.GameService;
import com.m224.ataxx.adapters.TileAdapter;
import com.m224.ataxx.R;
import com.m224.ataxx.utils.IGlobalVariable;
import com.m224.ataxx.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private GameService gameService = new GameService();
    private TextView tv_player_1, tv_player_2, tv_turn;
    private List<TileImageView> gridImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Util.customActionbar(this, R.layout.actionbar_grid);
        setTitle("");

        tv_player_1 = findViewById(R.id.tv_player_1);
        tv_player_2 = findViewById(R.id.tv_player_2);
        tv_turn = findViewById(R.id.tv_turn);

        gameService.setConfig(1);

        for (int i = 0; i < IGlobalVariable.MAX_TILE; i++) {
            gridImages.add(new TileImageView(this, gameService.getStateAt(i), i));
        }

        handleGrid();
        refreshInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_dashboard:
                gameService.setConfig(1);
                refreshInterface();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackClick(View v) {
        finish();
        this.overridePendingTransition(R.anim.left_start, R.anim.left_end);
    }

    private void handleGrid() {
        GridView gridview = findViewById(R.id.grid_view);
        gridview.setAdapter(new TileAdapter(this, gridImages));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                gameService.move(position);
                refreshInterface();
                Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* *Refresher* */
    private void refreshInterface() {
        refreshGrid();
        refreshScore();
        refreshTurn();
    }
    private void refreshGrid() {
        for (int i = 0; i < IGlobalVariable.MAX_TILE; i++) {
            gridImages.get(i).setState(gameService.getStateAt(i));
            gridImages.get(i).setSelected(gameService.isSelectAt(i));
        }
    }
    private void refreshScore() {
        tv_player_1.setText(""+ gameService.getScorePlayer1());
        tv_player_2.setText(""+ gameService.getScorePlayer2());
    }
    private void refreshTurn() {
        if (gameService.isTurnPlayerOne()) {
            tv_turn.setText(getResources().getString(R.string.turn_player_one));
            tv_turn.setTextColor(getResources().getColor(R.color.player1));
        } else {
            tv_turn.setText(getResources().getString(R.string.turn_player_two));
            tv_turn.setTextColor(getResources().getColor(R.color.player2));
        }
    }
    /* *********** */




}
