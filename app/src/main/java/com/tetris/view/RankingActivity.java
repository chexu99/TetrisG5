package com.tetris.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tetris.R;
import com.tetris.model.Player;
import com.tetris.model.database.ConexionSQLiteHelper;
import com.tetris.utils.RecyclerViewAdaptador;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    ImageButton button;
    ListView LRanking;
    ArrayList<String> listaInfo;
    ConexionSQLiteHelper conn;
    private RecyclerView recyclerViewUser;
    private RecyclerViewAdaptador userAdapter;
    private Vibrator vibe;
    public  boolean idioma = MenuActivity.idiom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        vibe = (Vibrator) RankingActivity.this.getSystemService(RankingActivity.VIBRATOR_SERVICE);

        //boton de volver
        button = findViewById(R.id.reStar);
        if(MenuActivity.idiom) {
            button.setImageResource(R.drawable.btn_volver);
        }else{
            button.setImageResource(R.drawable.btn_return);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPreviousActivity();
            }
        });

        recyclerViewUser=(RecyclerView)findViewById(R.id.recyclerUser);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));


        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_ranking", null, 1);

        userAdapter = new RecyclerViewAdaptador(checkPlayerList());
        recyclerViewUser.setAdapter(userAdapter);
    }

    public List<Player> checkPlayerList() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Player player;
        ArrayList<Player> listaPlayers = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM ranking ORDER BY score DESC", null);

        while (cursor.moveToNext()) {
            player = new Player();
            player.setId(cursor.getInt(0));
            player.setNombre(cursor.getString(1));
            player.setScore(cursor.getInt(2));
            player.setImg(cursor.getBlob(3));

            listaPlayers.add(player);
        }
        return listaPlayers;
    }


    private void openPreviousActivity() {
        vibe.vibrate(40);
        finish();
    }
}
