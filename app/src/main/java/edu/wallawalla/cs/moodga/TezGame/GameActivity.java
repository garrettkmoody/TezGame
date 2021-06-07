package edu.wallawalla.cs.moodga.TezGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    boolean gameResult;
    private GestureDetectorCompat mDetector;
    private ImageView movableGhost;
    private ImageView guyIV;
    private ImageView guy1IV;
    private ImageView girlIV, ghostIV;
    private Game game;
    private boolean running = false;
    private TextView guyTV, guy1TV, girlTV;
    private Button abilityBT, startBT;
    public static final String WIN_OR_LOSE = "GAME_WIN_OR_LOSE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Integer colortype, classtype;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                colortype = 0;
                classtype = 0;
            } else {
                colortype = extras.getInt("colortype");
                classtype = extras.getInt("classtype");
            }
        } else {
            colortype = (Integer) savedInstanceState.getSerializable("colortype");
            classtype = (Integer) savedInstanceState.getSerializable("classtype");
        }


        guyIV = findViewById(R.id.person);
        guy1IV = findViewById(R.id.person2);
        girlIV = findViewById(R.id.person3);
        startBT = findViewById(R.id.btStart);
        guyTV = findViewById(R.id.tvGuy);
        guy1TV = findViewById(R.id.tvGuy2);
        girlTV = findViewById(R.id.tvGirl);
        ghostIV = findViewById(R.id.imageView6);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        ghostIV.startAnimation(aniSlide);
        Animation aniSlide2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bouncepeople);
        guyIV.startAnimation(aniSlide2);
        guy1IV.startAnimation(aniSlide2);
        girlIV.startAnimation(aniSlide2);
        abilityBT = findViewById(R.id.buttonAbility);


        game = new Game(classtype, colortype, ghostIV, guyTV, guy1TV, girlTV, abilityBT);
        startBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBT.setVisibility(View.INVISIBLE);
                game.runDay();
                running = true;
            }
        });

        guyIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        guy1IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        girlIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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