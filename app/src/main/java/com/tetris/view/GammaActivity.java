package com.tetris.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.tetris.view.MenuActivity;
import com.tetris.R;
import com.tetris.utils.UserSettings;

public class GammaActivity extends Activity {

    private Vibrator vibe;
    public  boolean idiom=MenuActivity.idiom ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamma);

        vibe = (Vibrator) GammaActivity.this.getSystemService(GammaActivity.VIBRATOR_SERVICE);

        ImageButton original = findViewById(R.id.original);
        original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSettings.setGamma(0);
                vibe.vibrate(40);
                if(idiom) {
                    Toast.makeText(getApplicationContext(), "Paleta original", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Original palette", Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageButton marvel = findViewById(R.id.marvel);
        if (UserSettings.getScore() < 500){
            marvel.setImageResource(R.drawable.marvelblocked);
            marvel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(idiom) {
                        Toast.makeText(getApplicationContext(), "Son necesarios 500 puntos", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "You need 500 points", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            marvel.setImageResource(R.drawable.marvel_gamma);
            marvel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserSettings.setGamma(1);
                    vibe.vibrate(40);
                    if(idiom) {
                        Toast.makeText(getApplicationContext(), "Paleta de marvel", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Marvel palette", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        ImageButton planets = findViewById(R.id.planets);
        if (UserSettings.getScore() < 1000){
            planets.setImageResource(R.drawable.planetsblocked);
            planets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(idiom) {
                        Toast.makeText(getApplicationContext(), "Son necesarios 1000 puntos", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "You need 1000 points", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            planets.setImageResource(R.drawable.planets_gamma);
            planets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserSettings.setGamma(2);
                    vibe.vibrate(40);
                    if(idiom) {
                        Toast.makeText(getApplicationContext(), "Paleta de planetas", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Planets palette", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


        ImageButton btn_return = findViewById(R.id.return_back);
        if(!idiom){
            btn_return.setImageResource(R.drawable.btn_return);
        }

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });

    }

    private void openMenuActivity() {
        vibe.vibrate(40);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
