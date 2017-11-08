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
import android.widget.TextView;

import com.m224.infectious.R;
import com.m224.infectious.adapters.GamePreviewAdapter;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private GamePreviewAdapter adapter;
    private RecyclerView recyclerView;

    private List<String> titles;
    private List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.customActionbar(this, R.layout.actionbar_main);

        mTextMessage = findViewById(R.id.tv_msg);
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
        titles = new ArrayList<>();
        images = new ArrayList<>();

        adapter = new GamePreviewAdapter(this, titles, images);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareListConfigHuman();
    }

    private void prepareListConfigHuman() {
        titles.clear();images.clear();
        titles.add("Config 1");
        images.add(R.drawable.config_1);
        titles.add("Config 2");
        images.add(R.drawable.config_2);
        titles.add("Config 3");
        images.add(R.drawable.config_3);
        titles.add("Config 4");
        images.add(R.drawable.config_4);
        adapter.notifyDataSetChanged();
    }

    private void prepareListConfigComputer() {
        titles.clear();images.clear();
        titles.add("Config 3 Computer");
        images.add(R.drawable.config_3);
        adapter.notifyDataSetChanged();
    }

    private void prepareListConfigOnline() {
        titles.clear();images.clear();
        titles.add("Connect to rival !");
        images.add(R.drawable.config_1);
        adapter.notifyDataSetChanged();
    }


    public void startGameWithConfig(int configId) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("configId", configId);
        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }












}
