package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tetris.R;
import com.tetris.model.Board;

import androidx.appcompat.app.AppCompatActivity;

public class FinalScoreActivity extends AppCompatActivity {

    private Button button;
    public TextView score_gameover_text;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);


        button = findViewById(R.id.restart_button);
        button2=findViewById(R.id.R_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        button2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRanking();
            }
        });

        score_gameover_text = (TextView) findViewById(R.id.score_gameover);
        score_gameover_text.setText(String.valueOf(Board.getInstance().getScore()));
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openRanking() {
        Intent intent = new Intent(this, ranking.class);
        startActivity(intent);
    }

}
