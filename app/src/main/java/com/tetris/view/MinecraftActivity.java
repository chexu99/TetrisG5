package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.tetris.R;

public class MinecraftActivity extends AppCompatActivity {


    public int numColor = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecraft);

        setUpButtonsCeldas();

        setUpButtonsColors();
    }

    private void setUpButtonsCeldas() {
        final ImageButton bCelda1 = findViewById(R.id.celda1);
        bCelda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {

            }
        });
        ImageButton bCelda2 = findViewById(R.id.celda2);
        bCelda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda3 = findViewById(R.id.celda3);
        bCelda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda4 = findViewById(R.id.celda4);
        bCelda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda5 = findViewById(R.id.celda5);
        bCelda5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda6 = findViewById(R.id.celda6);
        bCelda6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda7 = findViewById(R.id.celda7);
        bCelda7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda8 = findViewById(R.id.celda8);
        bCelda8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
        ImageButton bCelda9 = findViewById(R.id.celda9);
        bCelda9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
            }
        });
    }

    private void setUpButtonsColors() {
        ImageButton bColor1 = findViewById(R.id.color1);
        bColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(2);
            }
        });

        ImageButton bColor2 = findViewById(R.id.color2);
        bColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(5);
            }
        });

        ImageButton bColor3 = findViewById(R.id.color3);
        bColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(6);
            }
        });
        ImageButton bColor4 = findViewById(R.id.color4);
        bColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(0);
            }
        });
        ImageButton bColor5 = findViewById(R.id.color5);
        bColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(3);
            }
        });
        ImageButton bColor6 = findViewById(R.id.color6);
        bColor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(4);
            }
        });
        ImageButton bColor7 = findViewById(R.id.color7);
        bColor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(1);
            }
        });
        ImageButton bColor8 = findViewById(R.id.color8);
        bColor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(7);
            }
        });
    }


    public int getNumColor() {
        return numColor;
    }

    public void setNumColor(int numColor) {
        this.numColor = numColor;
    }
}
