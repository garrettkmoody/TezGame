package edu.wallawalla.cs.moodga.TezGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GestureDetectorCompat;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    boolean gameResult;
    private Integer highscore;
    private GestureDetectorCompat mDetector;
    private ImageView movableGhost;
    private ImageView guyIV;
    private ImageView guy1IV;
    private ImageView girlIV, ghostIV;
    private boolean abilityInUse = false;
    private Game game;
    private boolean running = false;
    private TextView guyTV, guy1TV, girlTV, scoreTV;
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
                highscore = 0;
            } else {
                colortype = extras.getInt("colortype");
                classtype = extras.getInt("classtype");
                highscore = extras.getInt("highscore");
            }
        } else {
            colortype = (Integer) savedInstanceState.getSerializable("colortype");
            classtype = (Integer) savedInstanceState.getSerializable("classtype");
            highscore = (Integer) savedInstanceState.getSerializable("highscore");
        }




        guyIV = findViewById(R.id.person);
        guy1IV = findViewById(R.id.person2);
        girlIV = findViewById(R.id.person3);
        startBT = findViewById(R.id.btStart);
        guyTV = findViewById(R.id.tvGuy);
        guy1TV = findViewById(R.id.tvGuy2);
        girlTV = findViewById(R.id.tvGirl);
        ghostIV = findViewById(R.id.imageView6);
        scoreTV = findViewById(R.id.scoreTV);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        ghostIV.startAnimation(aniSlide);
        Animation aniSlide2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bouncepeople);
        guyIV.startAnimation(aniSlide2);
        guy1IV.startAnimation(aniSlide2);
        girlIV.startAnimation(aniSlide2);
        abilityBT = findViewById(R.id.buttonAbility);

        abilityBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running) {
                    return;
                }
                abilityInUse = true;
            }
        });

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
                if(!running) {
                    return;
                }
                if(abilityInUse) {
                    useAbility(1);
                    abilityInUse = false;
                } else {
                    if(game.percent1) {
                        Toast.makeText(GameActivity.this, "Friended!", Toast.LENGTH_SHORT).show();
                        game.score += game.point1;
                    } else {
                        Toast.makeText(GameActivity.this, "They didn't want to be your friend", Toast.LENGTH_SHORT).show();
                    }
                    game.runDay();
                    if(game.day == 10) {
                        sendResult();
                        return;
                    }
                    (new Handler()).postDelayed(GameActivity.this::newDay, 1000);
                }
                scoreTV.setText("Score: " + game.score);

            }
        });

        guy1IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running) {
                    return;
                }
                if(abilityInUse) {
                    useAbility(2);
                    abilityInUse = false;
                } else {
                    if(game.percent2) {
                        Toast.makeText(GameActivity.this, "Friended!", Toast.LENGTH_SHORT).show();
                        game.score += game.point2;
                    } else {
                        Toast.makeText(GameActivity.this, "They didn't want to be your friend", Toast.LENGTH_SHORT).show();
                    }
                    game.runDay();
                    if(game.day == 10) {
                        sendResult();
                        return;
                    }
                    (new Handler()).postDelayed(GameActivity.this::newDay, 1000);
                }
                scoreTV.setText("Score: " + game.score);

            }
        });

        girlIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running) {
                    return;
                }
                if(abilityInUse) {
                    useAbility(3);
                    //Toast.makeText(GameActivity.this, "ITs TIME", Toast.LENGTH_LONG).show();
                    abilityInUse = false;
                } else {
                    if(game.percent3) {
                        Toast.makeText(GameActivity.this, "Friended!", Toast.LENGTH_SHORT).show();
                        game.score += game.point3;
                    } else {
                        Toast.makeText(GameActivity.this, "They didn't want to be your friend", Toast.LENGTH_SHORT).show();
                    }
                    game.runDay();
                    if(game.day == 10) {
                        sendResult();
                        return;
                    }
                    (new Handler()).postDelayed(GameActivity.this::newDay, 1000);
                }
                scoreTV.setText("Score: " + game.score);

            }
        });
    }


    private void sendResult() {
        if(game.score >= 50) {
            gameResult = true;
        } else {
            gameResult = false;
        }
        if(game.score > highscore) {
            MainActivity.setDefaults("highscore", game.score, this);
        }
        Intent intent = new Intent();
        intent.putExtra(WIN_OR_LOSE, gameResult);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void useAbility(Integer person) {
        if(game.abilityUses <= 0) {
            Toast.makeText(this, "No more abilities!", Toast.LENGTH_SHORT).show();
            return;
        }
        game.abilityUses--;
        switch (person) {
            case 1:
                switch (game.ghostType) {
                    case 1:
                        if(game.percent1) {
                            Toast.makeText(this, "This person wants to be your friend!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "This person does not want to be your friend :(", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        Toast.makeText(this, "Charmed!", Toast.LENGTH_SHORT).show();
                        game.score += game.point1;
                        game.runDay();
                        if(game.day == 10) {
                            sendResult();
                            break;
                        }
                        (new Handler()).postDelayed(this::newDay, 1000);
                        break;
                    case 3:
                        Toast.makeText(this, "Bribed! Odds of friendship increased by 20 percent!", Toast.LENGTH_SHORT).show();
                        game.d -= .20;
                        game.runNumbers();
                        break;
                }
                break;
            case 2:
                switch (game.ghostType) {
                    case 1:
                        if(game.percent2) {
                            Toast.makeText(this, "This person wants to be your friend!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "This person does not want to be your friend :(", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        Toast.makeText(this, "Charmed!", Toast.LENGTH_SHORT).show();
                        game.score += game.point2;
                        game.runDay();
                        if(game.day == 10) {
                            sendResult();
                            break;
                        }
                        (new Handler()).postDelayed(this::newDay, 1000);
                        break;
                    case 3:
                        Toast.makeText(this, "Bribed! Odds of friendship increased by 20 percent!", Toast.LENGTH_SHORT).show();
                        game.d -= .20;
                        game.runNumbers();
                        break;
                }
                break;
            case 3:
                switch (game.ghostType) {
                    case 1:
                        if(game.percent3) {
                            Toast.makeText(this, "This person wants to be your friend!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "This person does not want to be your friend :(", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        Toast.makeText(this, "Charmed!", Toast.LENGTH_SHORT).show();
                        game.score += game.point3;
                        game.runDay();
                        if(game.day == 10) {
                            sendResult();
                            break;
                        }
                        (new Handler()).postDelayed(this::newDay, 1000);
                        break;
                    case 3:
                        Toast.makeText(this, "Bribed! Odds of friendship increased by 20 percent!", Toast.LENGTH_SHORT).show();
                        game.d -= .20;
                        game.runNumbers();
                        break;
                }
                break;
        }

    }


    private void newDay() {
        game.day++;
        Intent intent = new Intent(GameActivity.this, NightActivity.class);
        intent.putExtra("day", game.day);
        startActivity(intent);
    }


}