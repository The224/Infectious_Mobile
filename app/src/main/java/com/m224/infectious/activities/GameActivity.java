package com.m224.infectious.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.m224.infectious.domaine.Board;
import com.m224.infectious.domaine.GridImageView;
import com.m224.infectious.services.AIService;
import com.m224.infectious.services.GameService;
import com.m224.infectious.adapters.GridImageAdapter;
import com.m224.infectious.R;
import com.m224.infectious.sql.SaveTable;
import com.m224.infectious.task.AIComputeTask;
import com.m224.infectious.utils.AIDifficulty;
import com.m224.infectious.utils.GameType;
import com.m224.infectious.utils.GridConfig;
import com.m224.infectious.utils.State;
import com.m224.infectious.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private GameService gameService;
    private TextView tv_player_1, tv_player_2, tv_turn;
    private List<GridImageView> gridImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Util.customActionbar(this, R.layout.actionbar_grid);

        tv_player_1 = findViewById(R.id.tv_player_1);
        tv_player_2 = findViewById(R.id.tv_player_2);
        tv_turn = findViewById(R.id.tv_turn);

        Bundle extras = getIntent().getExtras();
        gameService = new GameService((Board) extras.getSerializable("board"));

        if (gameService.getBoard().getGameType() == GameType.COMPUTER) {
            tv_player_2.setTextColor(getResources().getColor(R.color.playerComputer));
            new AIComputeTask(this, getDifficultyUser()).execute();
        }
        
        initGridView();
        refreshInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_setting, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        quickSaveGame();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.game_menu_restart:
                menuRestartDialog();
                return true;
            case R.id.game_menu_save:
                menuSaveDialog();
                return true;
            case R.id.game_menu_delete:
                menuDeleteDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void closeGameActivity(View v) {
        finish();
        this.overridePendingTransition(R.anim.left_start, R.anim.left_end);
    }

    private void initGridView() {
        for (int i = 0; i < GridConfig.MAX_TILE; i++) {
            if (gameService.getBoard().getGameType() == GameType.COMPUTER &&
                    gameService.getStateAt(i) == State.PLAYER2) // Other Player is a AI !
                gridImages.add(new GridImageView(this, State.PLAYERAI, i));
            else
                gridImages.add(new GridImageView(this, gameService.getStateAt(i), i));
        }

        GridView gridview = findViewById(R.id.grid_view);
        gridview.setAdapter(new GridImageAdapter(this, gridImages));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                gameService.move(position);
                refreshInterface();
                // Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show(); eventually
            }
        });
    }

    public void aiMove(Pair<Integer,Integer> result) {
        gameService.aiMove(result);
        refreshInterface();
    }

    public AIDifficulty getDifficultyUser() {
        SharedPreferences settings = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int difficulty = settings.getInt("difficulty", 2);

        if (difficulty == 0)
            return AIDifficulty.EASY;
        else if (difficulty == 1)
            return AIDifficulty.MEDIUM;
        else if (difficulty == 2)
            return AIDifficulty.HARD;
        else
            return AIDifficulty.HARDCORE;
    }

    /* *Menu Dialog* */
    private void menuSaveDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_save_game);
        final EditText editText = dialog.findViewById(R.id.et_save_name);

        Button dialogButton = dialog.findViewById(R.id.btn_save);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().equals(""))
                    saveGame(editText.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void menuRestartDialog() {
        AlertDialog.Builder alertRestartDialogBuilder =
                new AlertDialog.Builder(this, R.style.DialogTheme);

        alertRestartDialogBuilder
                .setTitle(R.string.restart_title)
                .setCancelable(true)
                .setPositiveButton(R.string.restart_yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                gameService.restart();
                                refreshInterface();
                            }
                        })
                .setNegativeButton(R.string.restart_no,null)
                .create()
                .show();
    }

    private void menuDeleteDialog() {
        AlertDialog.Builder alertDeleteDialogBuilder =
                new AlertDialog.Builder(this, R.style.DialogTheme);

        alertDeleteDialogBuilder
                .setTitle(R.string.delete_title)
                .setCancelable(true)
                .setPositiveButton(R.string.delete_yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // A voir
                                closeGameActivity(null);
                            }
                        })
                .setNegativeButton(R.string.delete_no,null)
                .create()
                .show();
    }
    /* ************* */

    /* *SQL Speaker* */
    private void quickSaveGame() {
        gameService.getBoard().setTitle(getResources().getString(R.string.quick_save));
        String jsonBoard = Util.boardToJSONString(gameService.getBoard());

        SaveTable saveTable = new SaveTable(this);

        saveTable.open();
        saveTable.insertQuickSave(jsonBoard);
        saveTable.close();
    }
    private void saveGame(String name) {
        gameService.getBoard().setTitle(name);
        String jsonBoard = Util.boardToJSONString(gameService.getBoard());




        SaveTable saveTable = new SaveTable(this);

        saveTable.open();
        saveTable.insertSave(jsonBoard);
        saveTable.close();
    }
    /* ************* */

    /* *UI Refresher* */
    private void refreshInterface() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshGrid();
                refreshScore();
                refreshTurn();
            }
        });
    }
    private void refreshGrid() {
        for (int i = 0; i < GridConfig.MAX_TILE; i++) {

            if (gameService.getBoard().getGameType() == GameType.COMPUTER &&
                    gameService.getStateAt(i) == State.PLAYER2) {
                gridImages.get(i).setState(State.PLAYERAI);
            }
            else
                gridImages.get(i).setState(gameService.getStateAt(i));

            gridImages.get(i).setSelected(gameService.isSelectAt(i));
        }
    }
    private void refreshScore() {
        tv_player_1.setText(""+ gameService.getScorePlayer1());
        tv_player_2.setText(""+ gameService.getScorePlayer2());
    }
    private void refreshTurn() {
        if (gameService.isTurnPlayerOne()) {
            tv_turn.setText(getResources().getString(R.string.turn_player_one));
            tv_turn.setTextColor(getResources().getColor(R.color.player1));
        } else {
            if (gameService.getBoard().getGameType() == GameType.COMPUTER) {
                tv_turn.setTextColor(getResources().getColor(R.color.playerComputer));
                tv_turn.setText(getResources().getString(R.string.turn_player_computer));
            }
            else {
                tv_turn.setTextColor(getResources().getColor(R.color.player2));
                tv_turn.setText(getResources().getString(R.string.turn_player_two));
            }
        }
    }
    /* ************** */

    public GameService getGameService() {
        return gameService;
    }
}


















