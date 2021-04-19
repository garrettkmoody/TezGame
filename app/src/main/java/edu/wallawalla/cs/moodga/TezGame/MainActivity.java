package edu.wallawalla.cs.moodga.TezGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_GAME_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.startButton);
        EditText enternameET = findViewById(R.id.etUsername);
        TextView ghostnameTV = findViewById(R.id.ghostNameTV);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivityForResult(intent, REQUEST_GAME_RESULT);
        });

        enternameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ghostnameTV.setText("Ghost: " + s);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_GAME_RESULT) {
            Boolean test = false;
            Boolean winOrLoss = data.getBooleanExtra(GameActivity.WIN_OR_LOSE, test);
            if(winOrLoss) {
                Toast.makeText(this, "You won the game!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You lost the game :(", Toast.LENGTH_SHORT).show();
            }
        }
    }
}