package com.tetris.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tetris.R;
import com.tetris.model.Board;
import com.tetris.model.database.ConexionSQLiteHelper;
import com.tetris.utils.UserSettings;

import androidx.appcompat.app.AppCompatActivity;

public class FinalScoreActivity extends AppCompatActivity {

     Button button;
     TextView score_gameover_text;
     Button button2;
     SQLiteDatabase db;
     ConexionSQLiteHelper conn;
     Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"db_ranking",null,1);
        actualizarscore();
        button = findViewById(R.id.restart_button);
        button2=findViewById(R.id.btnranking);
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

    private void actualizarscore() {
        String username =UserSettings.getUserName();
        UserSettings.setScore(Board.getInstance().getScore());
        score= UserSettings.getScore();
        db=conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT score FROM ranking WHERE nombre='"+username+"'",null);
        cursor.moveToFirst();
        Integer lasthighscore = cursor.getInt(0);
        if (score>lasthighscore) {
            db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("score", score);
            db.update("ranking", values, "nombre='" + username + "'", null);
            UserSettings.setScore(score);
            Toast.makeText(getApplicationContext(),"New highscore",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Más suerte la proxima, paquete",Toast.LENGTH_LONG).show();
        }
        db.close();
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
