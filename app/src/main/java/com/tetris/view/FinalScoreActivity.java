package com.tetris.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;
import com.tetris.model.Board;
import com.tetris.model.database.ConexionSQLiteHelper;
import com.tetris.utils.UserSettings;

public class FinalScoreActivity extends AppCompatActivity {

    ImageButton button;
    TextView scoreGameoverText;
    ImageButton button2;
    SQLiteDatabase db;
    ConexionSQLiteHelper conn;
    Integer score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_ranking", null, 1);
        updateScore();
        button = findViewById(R.id.restart_exit_button);
        if (score>250){
            ((ImageButton) button).setImageResource(R.drawable.btn_volver);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMenuActivity();
                }
            });
        } else{
            ((ImageButton) button).setImageResource(R.drawable.btn_exit);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });
        }

        button2 = findViewById(R.id.btnranking);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRanking();
            }
        });

        scoreGameoverText = findViewById(R.id.score_gameover);
        scoreGameoverText.setText(String.valueOf(Board.getInstance().getScore()));
    }

    private void updateScore() {
        String username = UserSettings.getUsername();
        UserSettings.setScore(Board.getInstance().getScore());
        score = UserSettings.getScore();
        db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT score FROM ranking WHERE nombre='" + username + "'", null);
        cursor.moveToFirst();
        Integer lastHighScore = cursor.getInt(0);
        if (score > lastHighScore) {
            db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("score", score);
            db.update("ranking", values, "nombre='" + username + "'", null);
            UserSettings.setScore(score);
            Toast.makeText(getApplicationContext(), "New highscore", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Más suerte la proxima, paquete", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openRanking() {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

}
