package com.m224.ataxx.activities;


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

import com.m224.ataxx.R;
import com.m224.ataxx.adapters.ConfigListAdapter;
import com.m224.ataxx.utils.Util;

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

        title = new ArrayList<>();
        image = new ArrayList<>();

        title.add("Test 111");
        image.add(R.drawable.background);


        adapter = new ConfigListAdapter(this, title, image);


        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        // recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);







        prepareAlbums();





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


    private void prepareAlbums() {
        int[] image = new int[]{
                R.drawable.background,
                R.drawable.background,
                R.drawable.background,
                R.drawable.background};

        String[] title = new String[]{
                "test 1",
                "test 2",
                "test 3",
                "test 4"};

        adapter.notifyDataSetChanged();
    }













}
