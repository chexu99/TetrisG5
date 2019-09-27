package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;

import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity{
    private ImageView spriteTest;

    private CopyOnWriteArrayList<ImageView> gameViews;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.new_game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });
        /*gameViews = new CopyOnWriteArrayList<>();
        spriteTest = findViewById(R.id.test);

        gameViews.add(spriteTest);
         */
    }


    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}


