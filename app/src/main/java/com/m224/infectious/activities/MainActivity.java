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

import com.m224.infectious.R;
import com.m224.infectious.adapters.GamePreviewAdapter;
import com.m224.infectious.domaine.Config;
import com.m224.infectious.utils.GameType;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GamePreviewAdapter adapter;
    private RecyclerView recyclerView;

    private List<Config> configs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.customActionbar(this, R.layout.actionbar_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initRecyclerView();
    }

    public void startActivityInformation(View v) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_start, R.anim.left_end);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_human:
                    prepareListConfigHuman();
                    return true;
                case R.id.navigation_computer:
                    prepareListConfigComputer();
                    return true;
                case R.id.navigation_online:
                    prepareListConfigOnline();
                    return true;
            }
            return false;
        }
    };

    private void initRecyclerView() {
        configs = new ArrayList<>();

        adapter = new GamePreviewAdapter(this, configs);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareListConfigHuman();
    }

    private void prepareListConfigHuman() {
        configs.clear();
        configs.add(new Config("Config 1", GameType.LOCAL, 0));
        configs.add(new Config("Config 2", GameType.LOCAL, 1));
        configs.add(new Config("Config 3", GameType.LOCAL, 2));
        configs.add(new Config("Config 4", GameType.LOCAL, 3));
        adapter.notifyDataSetChanged();
    }

    private void prepareListConfigComputer() {
        configs.clear();
        configs.add(new Config("Config 3 computer", GameType.COMPUTER, 2));
        adapter.notifyDataSetChanged();
    }

    private void prepareListConfigOnline() {
        configs.clear();
        configs.add(new Config("Connect to rival !", GameType.ONLINE, 1));
        adapter.notifyDataSetChanged();
    }

    public void startGameWithConfig(Config config) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("config", config);

        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }



}
