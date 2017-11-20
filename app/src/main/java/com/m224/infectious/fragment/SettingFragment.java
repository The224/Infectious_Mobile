package com.m224.infectious.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.infectious.R;

public class SettingFragment extends Fragment {
    private SharedPreferences settings;

    public SettingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        settings = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        createBtnListener(view);
        readPref(view);

        return view;
    }

    public void createBtnListener(final View view) {
        Button btn_update = view.findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePref(view);
            }
        });
    }

    public void readPref(View view) {
        String username = settings.getString("username", "Turtle");
        EditText et_username = view.findViewById(R.id.et_username);
        et_username.setText(username);

        int difficulty = settings.getInt("difficulty", 2);
        SeekBar sb_difficulty = view.findViewById(R.id.sb_difficulty);
        sb_difficulty.setMax(4);
        sb_difficulty.setProgress(difficulty);
    }


    public void updatePref(View view) {
        SharedPreferences.Editor editor = settings.edit();

        EditText et_username = view.findViewById(R.id.et_username);
        editor.putString("username", et_username.getText().toString());

        SeekBar sb_difficulty = view.findViewById(R.id.sb_difficulty);
        editor.putInt("difficulty", sb_difficulty.getProgress());

        editor.apply();
        
        Toast.makeText(getContext(),R.string.toast_confirm, Toast.LENGTH_SHORT).show();
    }


}


















