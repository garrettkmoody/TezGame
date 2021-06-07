package edu.wallawalla.cs.moodga.TezGame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DifficultyFragment.OnDifficultySelectedListener {

    private final int REQUEST_GAME_RESULT = 0;
    private ImageView ghostIV;
    private Integer colorType = 0;
    private Integer classType = 0;
    boolean status = false;
    private Animation standardAnim;


    @Override
    public void onDifficultyClick(int which) {
        SharedPreferences sharedPref = getPreferences(getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        switch(which) {
            case 0:
                editor.putInt("Difficulty", 0);
                editor.apply();
                Toast.makeText(this, "Easy Mode!", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                editor.putInt("Difficulty", 1);
                editor.apply();
                Toast.makeText(this, "Medium Mode!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                editor.putInt("Difficulty", 2);
                editor.apply();
                Toast.makeText(this, "Hard Mode!", Toast.LENGTH_SHORT).show();
                break;
        }
        // Handle difficulty logic

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.startButton);
        Button hunterBT = findViewById(R.id.hunterbt);
        Button scavengerBT = findViewById(R.id.scavengerbt);
        Button assassinBT = findViewById(R.id.assassinbt);
        ghostIV = findViewById(R.id.ghostPic);
        EditText enternameET = findViewById(R.id.etUsername);
        TextView ghostnameTV = findViewById(R.id.ghostNameTV);



        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("classtype", classType);
            intent.putExtra("colortype", colorType);
            startActivity(intent);
        });

        hunterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classType = 1;
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if(fragment != null) {
                    FragmentTransaction trans = fragmentManager.beginTransaction();
                    trans.remove(fragment);
                    trans.commit();
                }
                fragment = new GhostInfoFragment();
                Bundle args = new Bundle();
                args.putInt("classType", 1);
                fragment.setArguments(args);
                fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
            }
        });

        scavengerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classType = 2;
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if(fragment != null) {
                    FragmentTransaction trans = fragmentManager.beginTransaction();
                    trans.remove(fragment);
                    trans.commit();
                }
                fragment = new GhostInfoFragment();
                Bundle args = new Bundle();
                args.putInt("classType", 2);
                fragment.setArguments(args);
                fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
            }
        });

        assassinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classType = 3;
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if(fragment != null) {
                    FragmentTransaction trans = fragmentManager.beginTransaction();
                    trans.remove(fragment);
                    trans.commit();
                }

                fragment = new GhostInfoFragment();
                Bundle args = new Bundle();
                args.putInt("classType", 3);
                fragment.setArguments(args);
                fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();

            }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Determine which menu option was chosen
        if (item.getItemId() == R.id.action_one) {
            ghostIV.setColorFilter(Color.argb(100, 255, 255, 255));
            colorType = 1;
            return true;
        }
        else if (item.getItemId() == R.id.action_two) {
            ghostIV.setColorFilter(Color.argb(100, 255, 0, 0));
            colorType = 2;
            return true;
        }
        else if (item.getItemId() == R.id.action_three) {
            ghostIV.setColorFilter(Color.argb(100, 0, 0, 255));
            colorType = 3;
            return true;
        } else if (item.getItemId() == R.id.action_difficulty) {
            FragmentManager manager = getSupportFragmentManager();
            DifficultyFragment dialog = new DifficultyFragment();
            dialog.show(manager, "difficultyDialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
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