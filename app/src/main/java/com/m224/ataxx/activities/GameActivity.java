package com.m224.ataxx.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.ataxx.services.GameService;
import com.m224.ataxx.adapters.TileAdapter;
import com.m224.ataxx.R;

public class GameActivity extends AppCompatActivity {

    private GameService gameService;
    private TextView tv_player_1;
    private TextView tv_player_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        tv_player_1 = (TextView) findViewById(R.id.tv_player_1);
        tv_player_2 = (TextView) findViewById(R.id.tv_player_2);

        gameService = new GameService(this);
        gameService.setConfigOne();

        handleGrid();
        refreshScore();
    }

    private void handleGrid() {
        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new TileAdapter(this, gameService));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                gameService.makeMoveOld(position);

                refreshScore();

                Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void refreshScore() {
        tv_player_1.setText(""+ gameService.getScorePlayer1());
        tv_player_2.setText(""+ gameService.getScorePlayer2());
    }








}
