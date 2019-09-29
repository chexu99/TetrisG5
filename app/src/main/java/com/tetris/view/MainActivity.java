package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;

import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.new_game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });
    }


    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}


