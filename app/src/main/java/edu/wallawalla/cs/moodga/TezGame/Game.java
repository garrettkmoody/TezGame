package edu.wallawalla.cs.moodga.TezGame;

import android.graphics.Color;
import android.media.Image;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import org.w3c.dom.Text;

public class Game {

    public Integer score = 0;
    public Integer point1 = 0, point2 = 0, point3 = 0;
    public Integer abilityUses = 0;
    public Integer ghostType = 1;
    public Integer ghostColor = 0;
    public Integer day = 0;
    private Button ability;
    private ImageView ghost;
    private TextView guy, guy1, girl;

    Game(Integer type, Integer color, ImageView ghost, TextView guy, TextView guy1, TextView girl, Button ability) {
        ghostType = type;
        this.guy = guy;
        this.guy1 = guy1;
        this.girl = girl;
        this.ghost = ghost;
        this.ability = ability;

        switch(color) {
            case 1:
                ghost.setColorFilter(Color.argb(100, 255, 255, 255));
                break;
            case 2:
                ghost.setColorFilter(Color.argb(100, 255, 0, 0));
                break;
            case 3:
                ghost.setColorFilter(Color.argb(100, 0, 0, 255));
                break;
            default:
                break;
        }

        switch(type) {
            case 1:
                ability.setText("MINDREAD");
                abilityUses = 4;
                break;
            case 2:
                ability.setText("CHARM");
                abilityUses = 1;
                break;
            case 3:
                ability.setText("BRIBE");
                abilityUses = 5;
                break;
            default:
                break;
        }



    }

    public void runDay() {
        Random r = new Random();
        point1 = r.nextInt(5) + 1;
        point2 = r.nextInt(6) + 5;
        point3 = r.nextInt(10) + 10;
        guy.setText("Points: " + point1);
        guy1.setText("Points: " + point2);
        girl.setText("Points: " + point3);

    }


}
