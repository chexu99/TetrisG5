package com.tetris.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;

import android.os.Vibrator;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.multidex.MultiDex;

import com.tetris.R;
import com.tetris.model.database.ConexionSQLiteHelper;
import com.tetris.utils.UserSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InitDBActivity extends AppCompatActivity   {

    ConexionSQLiteHelper conn;
    SQLiteDatabase db;
    Integer puntuacion;
    String username;
    EditText campo_nombre;
    Cursor cursor;
    static final int REQUEST_IMAGE_CAPTURE = 20;  
    String absolutePath="";
    Vibrator vibe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        vibe = (Vibrator) InitDBActivity.this.getSystemService(InitDBActivity.VIBRATOR_SERVICE);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "db_ranking", null, 1);
        campo_nombre = findViewById(R.id.formulario);

    }

    public void onClick(View view) {
        vibe.vibrate(40);
        usernameComparator();
    }

    private void usernameComparator() {
        db = conn.getReadableDatabase();
        UserSettings.setUsername(campo_nombre.getText().toString());
        username = UserSettings.getUsername();
        cursor = db.rawQuery("SELECT * FROM ranking WHERE nombre ='" + username + "'", null);
        if (cursor.moveToFirst()) { // si cursor no apunta a nada entra por el else
            login();
            Intent intent = new Intent(InitDBActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            photo();
        }
    }

    private void login() {
        Integer lastHighScore;
        puntuacion = cursor.getInt(2);
        Cursor cursor = db.rawQuery("SELECT score FROM ranking WHERE nombre='" + username + "'", null);
        cursor.moveToFirst();
        lastHighScore = cursor.getInt(0);
        UserSettings.setScore(lastHighScore);
        cursor.close();
        db.close();
        Toast.makeText(getApplicationContext(), "Hola de nuevo "+username, Toast.LENGTH_LONG).show();


    }

    private void register(String img) {
        Integer id;
        puntuacion = 0;
        cursor.close();
        ContentValues values;

        Cursor cursor2 = db.rawQuery("SELECT * FROM ranking", null);
        if (cursor2.moveToLast()) {
            id = cursor2.getInt(0) + 1;
        } else {
            id = 1;
        }
        db = conn.getWritableDatabase();
        try{
            FileInputStream fs = new FileInputStream(img);
            byte[] imgbyte = new byte[fs.available()];
            fs.read(imgbyte);
            values = new ContentValues();
            values.put("id",id);
            values.put("nombre",username);
            values.put("score",puntuacion);
            values.put("img",imgbyte);
            db.insert("ranking",null,values);
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cursor2.close();
        db.close();
    }

    private void photo() {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePhotoIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                photoFile = createPhotoFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(InitDBActivity.this,"com.tetris.provider",photoFile);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                if(takePhotoIntent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    private File createPhotoFile() throws IOException {
        String photoName = new SimpleDateFormat("ddMMyyyy HHmmss").format(new Date());
        File storageFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = File.createTempFile(
                photoName,
                ".jpg",
                storageFile
        );
        absolutePath=photoFile.getAbsolutePath();
        return photoFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            register(absolutePath);
            Toast.makeText(getApplicationContext(), "registrado", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(InitDBActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
