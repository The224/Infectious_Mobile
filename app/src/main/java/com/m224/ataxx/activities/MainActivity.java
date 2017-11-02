package com.m224.ataxx.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.m224.ataxx.R;
import com.m224.ataxx.utils.Util;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.customActionbar(this, R.layout.actionbar_main);

        mTextMessage = (TextView) findViewById(R.id.tv_msg);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    /*

    view pager, un contre un, un contre ordinateur, multijoueur
    avec list d'item image de preview

    */
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




}
