package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;
import com.tetris.model.Board;

public class FinalScoreActivity extends AppCompatActivity {

    private Button button;
    public TextView score_gameover_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        /*   for sprint 3
        button = findViewById(R.id.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
         */
        score_gameover_text = (TextView) findViewById(R.id.score_gameover);
        score_gameover_text.setText(String.valueOf(Board.getInstance().getScore()));
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
