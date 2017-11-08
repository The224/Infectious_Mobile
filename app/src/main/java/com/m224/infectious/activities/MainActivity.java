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

    private List<String> title;
    private List<Integer> image;

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
                    mTextMessage.setText(R.string.nav_human);
                    return true;
                case R.id.navigation_computer:
                    mTextMessage.setText(R.string.nav_computer);
                    return true;
                case R.id.navigation_online:
                    mTextMessage.setText(R.string.nav_online);
                    return true;
            }
            return false;
        }
    };


    private void initRecyclerView() {
        title = new ArrayList<>();
        image = new ArrayList<>();

        adapter = new GamePreviewAdapter(this, title, image);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareListConfig();
    }

    private void prepareListConfig() {
        title.add("Config 1");
        image.add(R.drawable.config_1);
        title.add("Config 2");
        image.add(R.drawable.config_2);
        title.add("Config 3");
        image.add(R.drawable.config_3);
        title.add("Config 4");
        image.add(R.drawable.config_4);

        adapter.notifyDataSetChanged();
    }


    public void startGameWithConfig(int configId) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("configId", configId);
        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }












}
