package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.tetris.R;
import com.tetris.model.Player;
import com.tetris.model.database.ConexionSQLiteHelper;

import java.util.ArrayList;

public class ranking extends AppCompatActivity {
     Button button;
     ListView LRanking;
     ArrayList<String> Listainfo;
     ArrayList<Player> Listaplayers;
     ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //boton de volver
        button = findViewById(R.id.reStar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        LRanking=(ListView) findViewById(R.id.L_ranking);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"db_ranking",null,1);
        consultarListaPlayers();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Listainfo);
        LRanking.setAdapter(adapter);
    }

    private void consultarListaPlayers() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Player player = null;
        Listaplayers=new ArrayList<Player>();
        Cursor cursor =db.rawQuery("SELECT * FROM ranking ORDER BY score DESC", null);

        while (cursor.moveToNext()){
            player =new Player();
            player.setId(cursor.getInt(0));
            player.setNombre(cursor.getString(1));
            player.setScore(cursor.getInt(2));

            Listaplayers.add(player);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        Listainfo= new ArrayList<String>();
        for (int i=0;i<Listaplayers.size();i++){
            Listainfo.add(Listaplayers.get(i).getNombre()+ " - "+Listaplayers.get(i).getScore());
        }
    }



    public void openMainActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
