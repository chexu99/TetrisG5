package com.tetris.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;
import com.tetris.model.database.ConexionSQLiteHelper;
import com.tetris.utils.UserSettings;

public class InitDBActivity extends AppCompatActivity {
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
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_ranking", null, 1);
        campo_nombre = (EditText) findViewById(R.id.formulario);

    }

    public void onClick(View view) {
        usernameComparator();
        Intent intent = new Intent(InitDBActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void usernameComparator() {
        db = conn.getReadableDatabase();
        UserSettings.username = campo_nombre.getText().toString();
        username = UserSettings.username;
        cursor = db.rawQuery("SELECT * FROM ranking WHERE nombre ='" + username + "'", null);
        if (cursor.moveToFirst()) { // si cursor no apunta a nada entra por el else
            login();
        } else {
            register();
        }
    }

    private void login() {
        Integer lastHighScore;
        puntuacion = cursor.getInt(2);
        Cursor cursor = db.rawQuery("SELECT score FROM ranking WHERE nombre='" + username + "'", null);
        cursor.moveToFirst();
        lastHighScore = cursor.getInt(0);
        UserSettings.score = lastHighScore;
        cursor.close();
        db.close();

        Toast.makeText(getApplicationContext(), "Hola de nuevo " + username, Toast.LENGTH_LONG).show();


    }

    private void register() {
        Integer id;
        puntuacion = 0;
        cursor.close();
        Cursor cursor2 = db.rawQuery("SELECT * FROM ranking", null);
        if (cursor2.moveToLast()) {
            id = cursor2.getInt(0) + 1;
        } else {
            id = 1;
        }
        Toast.makeText(getApplicationContext(), "registrado", Toast.LENGTH_LONG).show();
        db = conn.getWritableDatabase();
        String insert = "INSERT INTO ranking(id,nombre,score) VALUES (" + id + ",'" + username + "'," + puntuacion + ")";
        db.execSQL(insert);
        cursor2.close();
        db.close();
    }
}
