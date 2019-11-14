package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;

public class MenuActivity extends AppCompatActivity {

    private ImageButton btn_new_game;
    private ImageButton btn_graphic;
    private ImageButton btn_ranking;
    private ImageButton btn_gamma;
    private Button prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_new_game = findViewById(R.id.new_game_button);
        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });

        btn_graphic = findViewById(R.id.graphic_button);
        btn_graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGraphicActivity();
            }
        });

        btn_ranking = findViewById(R.id.ranking_button);
        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRanking();
            }
        });

        btn_gamma = findViewById(R.id.gamma_button);
        btn_gamma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGammas();
            }
        });

        prueba = findViewById(R.id.prueba);
        prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMinecrafActivity();
            }
        });
    }

    public void openMinecrafActivity() {
        Intent intent = new Intent(this, MinecraftActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    private void openGammas(){
        Intent intent = new Intent(this, GammaActivity.class);
        startActivity(intent);
    }
}


