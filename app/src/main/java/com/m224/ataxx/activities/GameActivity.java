package com.m224.ataxx.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.ataxx.controllers.GameController;
import com.m224.ataxx.adapters.TileAdapter;
import com.m224.ataxx.R;

public class GameActivity extends AppCompatActivity {

    private static GameController gameControllerc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gameControllerc = new GameController(this);
        gameControllerc.setConfig1();

        handleGrid();
    }

    private void handleGrid() {
        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new TileAdapter(this, gameControllerc));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                gameControllerc.makeMove(position);

                TextView tv_player_1 = (TextView) findViewById(R.id.tv_player_1);
                TextView tv_player_2 = (TextView) findViewById(R.id.tv_player_2);

                tv_player_1.setText(""+gameControllerc.getScorePlayer1());
                tv_player_2.setText(""+gameControllerc.getScorePlayer2());

                //Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
