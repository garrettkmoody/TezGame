package edu.wallawalla.cs.moodga.TezGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    boolean gameResult;
    public static final String WIN_OR_LOSE = "GAME_WIN_OR_LOSE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button winButton = findViewById(R.id.winButton);
        Button loseButton = findViewById(R.id.loseButton);


        winButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameResult = true;
                sendResult();
            }
        });

        loseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameResult = false;
                sendResult();
            }
        });

    }

    private void sendResult() {
        Intent intent = new Intent();
        intent.putExtra(WIN_OR_LOSE, gameResult);
        setResult(RESULT_OK, intent);
        finish();
    }
}