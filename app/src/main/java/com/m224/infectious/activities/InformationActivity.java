package com.m224.infectious.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.m224.infectious.R;
import com.m224.infectious.utils.Util;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Util.customActionbar(this, R.layout.actionbar_information);



    }

    public void closeInformationActivity(View v) {
        finish();
        this.overridePendingTransition(R.anim.right_start, R.anim.right_end);
    }
}
