package com.m224.ataxx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridActivity extends AppCompatActivity {

    private Tile[] tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        tiles = Grid.getConfig1(this);



        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new GridTileAdapter(this, tiles));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ((Tile)v).setState(IGlobalVariable.STATE.BLOCK);

                Toast.makeText(GridActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }






}
