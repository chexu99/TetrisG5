package com.tetris.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tetris.R;
import com.tetris.utils.UserSettings;

public class GammaActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamma);

        ImageButton original = findViewById(R.id.original);
        original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSettings.setGamma(0);
                Toast.makeText(getApplicationContext(), "Paleta original", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton marvel = findViewById(R.id.marvel);
        if (UserSettings.getScore() < 500){
            ((ImageButton) marvel).setImageResource(R.drawable.marvelblocked);
            marvel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Son necesarios 500 puntos", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            ((ImageButton) marvel).setImageResource(R.drawable.marvel_gamma);
            marvel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserSettings.setGamma(1);
                    Toast.makeText(getApplicationContext(), "Paleta de marvel", Toast.LENGTH_LONG).show();
                }
            });
        }

        ImageButton planets = findViewById(R.id.planets);
        if (UserSettings.getScore() < 1000){
            ((ImageButton) planets).setImageResource(R.drawable.planetsblocked);
            planets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Son necesarios 1000 puntos", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            ((ImageButton) planets).setImageResource(R.drawable.planets_gamma);
            planets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserSettings.setGamma(2);
                    Toast.makeText(getApplicationContext(), "Paleta de planetas", Toast.LENGTH_LONG).show();
                }
            });
        }


        ImageButton btn_return = findViewById(R.id.return_back);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });

    }

    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
