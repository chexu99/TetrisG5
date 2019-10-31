package com.tetris.view;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.tetris.R;
import com.tetris.utils.UserSettings;

public class GammaActivity extends Activity {

    public ImageButton original;
    public ImageButton marvel;
    public ImageButton planets;
    private UserSettings user;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new UserSettings();
        original = findViewById(R.id.original);
        original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setCurrentGamma("o");
            }
        });
        marvel = findViewById(R.id.marvel);
        marvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setCurrentGamma("m");
            }
        });

        planets = findViewById(R.id.planets);
        planets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setCurrentGamma("p");
            }
        });
    }
}
