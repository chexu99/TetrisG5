package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tetris.R;
import com.tetris.model.database.ConexionSQLiteHelper;
import com.tetris.utils.UserSettings;

public class start extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    SQLiteDatabase db;
    Integer puntuacion;
    String username;
    EditText campo_nombre;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"db_ranking",null,1);
        campo_nombre = (EditText) findViewById(R.id.formulario);

    }
    public void onClick(View view){
        Comparador();
        Intent intent = new Intent(start.this,MenuActivity.class);
        startActivity(intent);
    }

    private void Comparador() {
        db = conn.getReadableDatabase();
        username = campo_nombre.getText().toString();
        cursor = db.rawQuery("SELECT * FROM ranking WHERE nombre ='"+username+"'",null);
        if (cursor.moveToFirst()){ // si cursor no apunta a nada entra por el else
            login();
        }else {
            register();
        }

    }

    private void login() {
        puntuacion= cursor.getInt(2);
        cursor.close();
        db.close();
    }
    private void register(){
        Integer id;
        puntuacion=0;
        cursor.close();
        Cursor cursor2= db.rawQuery("SELECT * FROM ranking",null);
        if (cursor2.moveToLast()) {
            id = cursor2.getInt(0) + 1;
        }else{
            id=1;
        }
        Toast.makeText(getApplicationContext(),"registrado",Toast.LENGTH_LONG).show();
        db= conn.getWritableDatabase();
        String insert = "INSERT INTO ranking(id,nombre,score) VALUES ("+id+",'"+username+"',"+puntuacion+")";
        db.execSQL(insert);
        cursor2.close();
        db.close();
    }
}
