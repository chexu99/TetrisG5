package com.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.tetris.model.Block;
import com.tetris.model.Board;

import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity implements S{
    private ImageView spriteTest;

    private CopyOnWriteArrayList<ImageView> gameViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameViews = new CopyOnWriteArrayList<>();
        spriteTest = findViewById(R.id.test);

        gameViews.add(spriteTest);

        GameScreen gameScreen;
        /*
        for (Block block : Board.getInstance().getBlocks()) {
            switch (block.getColor()) {
                case 1:
                    draw(block.getX(), block.getY(), bloque_rojo.png);
                        //Function draw doesnt exist should draw a square
                        in the given X and Y with the texture defined by its color
                    break;
                case 2:
                default:
                    break;
            }
        }*/

        setContentView(R.layout.activity_main);
    }


}


