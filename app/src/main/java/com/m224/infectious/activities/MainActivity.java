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
import com.m224.infectious.adapters.ConfigListAdapter;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ConfigListAdapter adapter;
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

    public void startActivityGrid(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
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

        adapter = new ConfigListAdapter(this, title, image);

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
    }

    private void prepareAlbums() {
        title.add("Test 111");
        image.add(R.drawable.background);
        title.add("Test 222");
        image.add(R.drawable.background);
        title.add("Test 333");
        image.add(R.drawable.ic_arrow_left);
        title.add("Test 444");
        image.add(R.drawable.background);
        title.add("Test 555");
        image.add(R.drawable.background);
        title.add("Test 666");
        image.add(R.drawable.background);
        title.add("Test 777");
        image.add(R.drawable.ic_arrow_right);
        title.add("Test 888");
        image.add(R.drawable.background);
        title.add("Test 999");
        image.add(R.drawable.background);
        title.add("Test 123");
        image.add(R.drawable.background);
        title.add("Test 456");
        image.add(R.drawable.background);
        title.add("Test 789");
        image.add(R.drawable.background);

        adapter.notifyDataSetChanged();
    }


    public void startGameWithConfig(int configId) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("configId", configId);
        startActivity(intent);
        overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }












}
