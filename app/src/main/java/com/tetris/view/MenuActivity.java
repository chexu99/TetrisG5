package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tetris.R;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button button;
    private Button graphic;
    private Button ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphic = findViewById(R.id.graphic_button);
        graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGraphicActivity();
            }
        });

        button = findViewById(R.id.new_game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });

        ranking = findViewById(R.id.ranking_button);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRanking();
            }
        });
    }

    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void openGraphicActivity() {
        Intent intent = new Intent(this, GraphicActivity.class);
        startActivity(intent);
    }

    private void openRanking(){
        Intent intent = new Intent(this, ranking.class);
        startActivity(intent);

    }
}

