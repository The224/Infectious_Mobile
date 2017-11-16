package com.m224.infectious.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.m224.infectious.domaine.Board;
import com.m224.infectious.domaine.GridImageView;
import com.m224.infectious.services.GameService;
import com.m224.infectious.adapters.GridItemImageAdapter;
import com.m224.infectious.R;
import com.m224.infectious.sql.SaveBoardTable;
import com.m224.infectious.utils.ConfigVariable;
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
        setTitle("");

        tv_player_1 = findViewById(R.id.tv_player_1);
        tv_player_2 = findViewById(R.id.tv_player_2);
        tv_turn = findViewById(R.id.tv_turn);

        Bundle extras = getIntent().getExtras();

        gameService = new GameService((Board) extras.getSerializable("board"));

        for (int i = 0; i < ConfigVariable.MAX_TILE; i++) {
            gridImages.add(new GridImageView(this, gameService.getStateAt(i), i));
        }

        handleGrid();
        refreshInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.game_menu_restart:
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
                return true;
            case R.id.game_menu_save:
                showSaveDialog();
                return true;
            case R.id.game_menu_delete:

                AlertDialog.Builder alertDeleteDialogBuilder =
                        new AlertDialog.Builder(this, R.style.DialogTheme);

                alertDeleteDialogBuilder
                        .setTitle(R.string.delete_title)
                        .setCancelable(true)
                        .setPositiveButton(R.string.delete_yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        // A voir
                                        onBackClick(null);
                                    }
                                })
                        .setNegativeButton(R.string.delete_no,null)
                        .create()
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSaveDialog() {
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


    public void onBackClick(View v) {
        finish();
        this.overridePendingTransition(R.anim.left_start, R.anim.left_end);
    }

    private void handleGrid() {
        GridView gridview = findViewById(R.id.grid_view);
        gridview.setAdapter(new GridItemImageAdapter(this, gridImages));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                gameService.move(position);
                refreshInterface();
                Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* *Refresher* */
    private void refreshInterface() {
        refreshGrid();
        refreshScore();
        refreshTurn();
    }
    private void refreshGrid() {
        for (int i = 0; i < ConfigVariable.MAX_TILE; i++) {
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
            tv_turn.setText(getResources().getString(R.string.turn_player_two));
            tv_turn.setTextColor(getResources().getColor(R.color.player2));
        }
    }
    /* *********** */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        quickSaveGame();
    }

    public void quickSaveGame() {
        gameService.getBoard().setTitle(getResources().getString(R.string.quick_save));
        String jsonBoard = Util.boardToJSONString(gameService.getBoard());

        SaveBoardTable saveBoardTable = new SaveBoardTable(this);

        saveBoardTable.open();
        saveBoardTable.insertQuickSave(jsonBoard);
        saveBoardTable.close();
    }

    public void saveGame(String name) {
        gameService.getBoard().setTitle(name);
        String jsonBoard = Util.boardToJSONString(gameService.getBoard());

        SaveBoardTable saveBoardTable = new SaveBoardTable(this);

        saveBoardTable.open();
        saveBoardTable.insertSave(jsonBoard);
        saveBoardTable.close();
    }

}


















