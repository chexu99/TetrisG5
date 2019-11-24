package com.tetris.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;
import com.tetris.model.Player;
import com.tetris.model.database.ConexionSQLiteHelper;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {
    ImageButton button;
    ListView LRanking;
    ArrayList<String> listaInfo;
    ArrayList<Player> listaPlayers;
    ConexionSQLiteHelper conn;
    private Vibrator vibe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        vibe = (Vibrator) RankingActivity.this.getSystemService(RankingActivity.VIBRATOR_SERVICE);
        //boton de volver
        button = findViewById(R.id.reStar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreviousActivity();
            }
        });

        LRanking = findViewById(R.id.L_ranking);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_ranking", null, 1);
        checkPlayerList();

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_white_text, R.id.list_content, listaInfo);
        LRanking.setAdapter(adapter);
    }

    private void checkPlayerList() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Player player;
        listaPlayers = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM ranking ORDER BY score DESC", null);

        while (cursor.moveToNext()) {
            player = new Player();
            player.setId(cursor.getInt(0));
            player.setNombre(cursor.getString(1));
            player.setScore(cursor.getInt(2));

            listaPlayers.add(player);
        }
        getPlayerList();
        cursor.close();
    }

    private void getPlayerList() {
        listaInfo = new ArrayList<>();
        for (int i = 0; i < listaPlayers.size(); i++) {
            listaInfo.add(listaPlayers.get(i).getNombre() + " - " + listaPlayers.get(i).getScore());
        }
    }

    private void openPreviousActivity() {
        vibe.vibrate(40);
        finish();
    }
}
