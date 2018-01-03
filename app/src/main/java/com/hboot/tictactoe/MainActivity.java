package com.hboot.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;
    Game game;
    boolean user1Flag = true;
    AlertDialog.Builder restartBuilder;

    int count = 0;
    boolean gameOver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = Game.getInstance();
        restartBuilder = new AlertDialog.Builder(MainActivity.this);

        restartBuilder.setMessage(R.string.restart_message).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startNewGame();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

            }
        });


        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);

        block1.setOnClickListener(this);
        block2.setOnClickListener(this);
        block3.setOnClickListener(this);
        block4.setOnClickListener(this);
        block5.setOnClickListener(this);
        block6.setOnClickListener(this);
        block7.setOnClickListener(this);
        block8.setOnClickListener(this);
        block9.setOnClickListener(this);
        restart.setOnClickListener(this);

    }

    private void startNewGame() {
        game.reset();
        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");
        result.setText("");
        user1Flag = true;
        count = 0;
        gameOver = false;
    }

    private void updateUI(int x, int y, Button block) {
        boolean won;
        if(game.valueAt(x,y) == Game.EMPTY && !gameOver) {
            if (user1Flag) {
                block.setText(R.string.player_1_move);
                game.mark(x, y, Game.NOUGHT);
                won = game.hasWon(x, y, Game.NOUGHT);
            } else {
                block.setText(R.string.player_2_move);
                game.mark(x, y, Game.CROSS);
                won = game.hasWon(x, y, Game.CROSS);
            }
            count++;
            if (won) {
                result.setText(user1Flag ? R.string.player_1_wins : R.string.player_2_wins);
                gameOver = true;
            }
            if(!won && count >= 9){
                result.setText(R.string.draw);
                gameOver = true;
            }
            user1Flag = !user1Flag;
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_block1:
                updateUI(0, 0, block1);
                break;
            case R.id.bt_block2:
                updateUI(0, 1, block2);
                break;
            case R.id.bt_block3:
                updateUI(0, 2, block3);
                break;
            case R.id.bt_block4:
                updateUI(1, 0, block4);
                break;
            case R.id.bt_block5:
                updateUI(1, 1, block5);
                break;
            case R.id.bt_block6:
                updateUI(1, 2, block6);
                break;
            case R.id.bt_block7:
                updateUI(2, 0, block7);
                break;
            case R.id.bt_block8:
                updateUI(2, 1, block8);
                break;
            case R.id.bt_block9:
                updateUI(2, 2, block9);
                break;
            case R.id.bt_restart_game:
                restartBuilder.create().show();
                break;
        }
    }
}
