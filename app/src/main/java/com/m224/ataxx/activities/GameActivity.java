package com.m224.ataxx.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.ataxx.services.GameService;
import com.m224.ataxx.adapters.TileAdapter;
import com.m224.ataxx.R;
import com.m224.ataxx.utils.Util;

public class GameActivity extends AppCompatActivity {

    private GameService gameService;
    private TextView tv_player_1, tv_player_2, tv_turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Util.customActionbar(this, R.layout.actionbar_grid);
        setTitle("");

        tv_player_1 = (TextView) findViewById(R.id.tv_player_1);
        tv_player_2 = (TextView) findViewById(R.id.tv_player_2);
        tv_turn = (TextView) findViewById(R.id.tv_turn);

        gameService = new GameService(this);
        gameService.setConfigOne();

        handleGrid();
        refreshScore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_dashboard:
                gameService.setConfigOne();
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
        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new TileAdapter(this, gameService));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                gameService.move(position);

                refreshScore();
                refreshTurn();

                Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
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




}
