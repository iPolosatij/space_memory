package space.digitallab.memory_game;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

import static space.digitallab.memory_game.R.drawable.moon_alien;
import static space.digitallab.memory_game.R.drawable.moon_chees_planet;
import static space.digitallab.memory_game.R.drawable.moon_darck_planet;
import static space.digitallab.memory_game.R.drawable.moon_earth;
import static space.digitallab.memory_game.R.drawable.moon_europa;
import static space.digitallab.memory_game.R.drawable.moon_jupiter;
import static space.digitallab.memory_game.R.drawable.moon_mars;
import static space.digitallab.memory_game.R.drawable.moon_rocket;
import static space.digitallab.memory_game.R.drawable.moon_saturn;
import static space.digitallab.memory_game.R.drawable.moon_sputnik;
import static space.digitallab.memory_game.R.drawable.no_color_back;


public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> elementsList = new ArrayList<>();
    TextView text;
    ToggleButton info = null;
    ToggleButton info1 = null;
    int restart = 0;
    int counter = 0;
    int level = 0;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        level = getIntent().getIntExtra("level", 0);
        switch (level){
            case (0):
                counter = 120;
                break;
            case (1):
                counter = 100;
                break;
            case (2):
                counter = 80;
                break;
            case (3):
                counter = 70;
                break;
            case (4):
                counter = 60;
                break;
            case (5):
                counter = 50;
                break;
            case (6):
                counter = 40;
                break;
        }
        text.setText("Level - " + level + "      steps - " + counter);
        createGame();
    }
    public  void createGame(){

        elementsList.add(moon_alien);
        elementsList.add(moon_chees_planet);
        elementsList.add(moon_darck_planet);
        elementsList.add(moon_earth);
        elementsList.add(moon_europa);
        elementsList.add(moon_jupiter);
        elementsList.add(moon_mars);
        elementsList.add(moon_rocket);
        elementsList.add(moon_saturn);
        elementsList.add(moon_sputnik);
        elementsList.add(moon_alien);
        elementsList.add(moon_chees_planet);
        elementsList.add(moon_darck_planet);
        elementsList.add(moon_earth);
        elementsList.add(moon_europa);
        elementsList.add(moon_jupiter);
        elementsList.add(moon_mars);
        elementsList.add(moon_rocket);
        elementsList.add(moon_saturn);
        elementsList.add(moon_sputnik);
        Collections.shuffle(elementsList);

        int BOOKSHELF_ROWS = 5;
        int BOOKSHELF_COLUMNS = 4;

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        int count = 0;

        for (int i = 0; i < BOOKSHELF_ROWS; i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));


            for (int j = 0; j < BOOKSHELF_COLUMNS; j++) {
                ToggleButton toggleButton = new ToggleButton(this);
                toggleButton.setBackgroundResource(elementsList.get(count));
                toggleButton.setTextOn("" + elementsList.get(count));
                toggleButton.setTextColor(0000);
                toggleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ToggleButton t = (ToggleButton) view;
                        logic(t);
                    }
                });
                tableRow.addView(toggleButton, j);
                count++;
            }

            tableLayout.addView(tableRow, i);
        }
    }
    public void logic(ToggleButton toggleButton){

        if(counter > 0) {
            counter--;
            text.setText("Level - " + level + " steps - " + counter);
        }else{
            level = 0;
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("level", level);
            startActivity(intent);
        }

        if (info == null && info1 == null) {
            info = toggleButton;
            toggleButton.setClickable(false);
        } else if(info1 != null){
            toggleButton.setClickable(false);
            info.setChecked(false);
            info1.setChecked(false);
            info.setClickable(true);
            info1.setClickable(true);
            info = toggleButton;
            info1 = null;
        }else{
            if (info.getTextOn().toString().equals(toggleButton.getTextOn().toString())) {

                toggleButton.setClickable(false);
                info.setClickable(false);
                toggleButton.setBackgroundColor(0000);
                info.setBackgroundColor(0000);
                restart++;
                info = null;
                if(restart == 10){
                    if(level < 6) level++;
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("level", level);
                    startActivity(intent);

                }
            } else {
                info.setChecked(true);
                info.setClickable(false);
                info1 = toggleButton;
                info1.setClickable(false);
                info1.setChecked(true);
            }
        }
    }
}