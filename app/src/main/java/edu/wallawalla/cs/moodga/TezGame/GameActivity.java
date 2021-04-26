package edu.wallawalla.cs.moodga.TezGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GameActivity extends AppCompatActivity {
    boolean gameResult;
    private GestureDetectorCompat mDetector;
    private ImageView movableGhost;
    public static final String WIN_OR_LOSE = "GAME_WIN_OR_LOSE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        movableGhost = findViewById(R.id.movingGhost);
        Button winButton = findViewById(R.id.winButton);
        Button loseButton = findViewById(R.id.loseButton);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void sendResult() {
        Intent intent = new Intent();
        intent.putExtra(WIN_OR_LOSE, gameResult);
        setResult(RESULT_OK, intent);
        finish();
    }

    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            movableGhost.setX(e.getRawX());
            movableGhost.setY(e.getRawY() - 50);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

    }

}