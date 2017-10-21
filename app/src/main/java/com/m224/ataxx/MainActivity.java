package com.m224.ataxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// TODO : change App icon

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startActivityGrid(View view) {
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }

















}
