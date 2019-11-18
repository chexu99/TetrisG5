package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
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
    private ImageButton btn_minecraft;
    private ImageButton btn_salir;
    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vibe = (Vibrator) MenuActivity.this.getSystemService(MenuActivity.VIBRATOR_SERVICE);

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

        btn_minecraft= findViewById(R.id.minecraft);
        btn_minecraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMinecrafActivity();
            }
        });

        btn_salir = findViewById(R.id.salir);
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitActivity();
            }
        });
    }

    private void exitActivity(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void openMinecrafActivity() {
        vibe.vibrate(40);
        Intent intent = new Intent(this, MinecraftActivity.class);
        startActivity(intent);
    }

    private void openGameActivity() {
        vibe.vibrate(40);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void openGraphicActivity() {
        vibe.vibrate(40);
        Intent intent = new Intent(this, GraphicActivity.class);
        startActivity(intent);
    }

    private void openRanking(){
        vibe.vibrate(40);
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    private void openGammas(){
        vibe.vibrate(40);
        Intent intent = new Intent(this, GammaActivity.class);
        startActivity(intent);
    }
}


