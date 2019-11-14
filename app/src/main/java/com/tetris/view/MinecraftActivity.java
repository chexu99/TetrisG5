package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

import com.tetris.R;
import com.tetris.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class MinecraftActivity extends AppCompatActivity {


    private int numColor = 2;
    private ImageButton bCelda1;
    private ImageButton bCelda2;
    private ImageButton bCelda3;
    private ImageButton bCelda4;
    private ImageButton bCelda5;
    private ImageButton bCelda6;
    private ImageButton bCelda7;
    private ImageButton bCelda8;
    private ImageButton bCelda9;
    private List<ImageButton> celdas = new ArrayList<>();
    private Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecraft);
        vibe = (Vibrator) MinecraftActivity.this.getSystemService(MinecraftActivity.VIBRATOR_SERVICE);

        setUpButtonsCeldas();

        setUpButtonsColors();
    }

    private void setUpButtonsCeldas() {
        bCelda1 = findViewById(R.id.celda1);
        bCelda1.setTag(R.drawable.celda_crafting);
        bCelda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda1);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda1);

        bCelda2 = findViewById(R.id.celda2);
        bCelda2.setTag(R.drawable.celda_crafting);
        bCelda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda2);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda2);

        bCelda3 = findViewById(R.id.celda3);
        bCelda3.setTag(R.drawable.celda_crafting);
        bCelda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda3);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda3);

        bCelda4 = findViewById(R.id.celda4);
        bCelda4.setTag(R.drawable.celda_crafting);
        bCelda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda4);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda4);

        bCelda5 = findViewById(R.id.celda5);
        bCelda5.setTag(R.drawable.celda_crafting);
        bCelda5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda5);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda5);

        bCelda6 = findViewById(R.id.celda6);
        bCelda6.setTag(R.drawable.celda_crafting);
        bCelda6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda6);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda6);

        bCelda7 = findViewById(R.id.celda7);
        bCelda7.setTag(R.drawable.celda_crafting);
        bCelda7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda7);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda7);

        bCelda8 = findViewById(R.id.celda8);
        bCelda8.setTag(R.drawable.celda_crafting);
        bCelda8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda8);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda8);

        bCelda9 = findViewById(R.id.celda9);
        bCelda9.setTag(R.drawable.celda_crafting);
        bCelda9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                chooseColor(bCelda9);
                vibe.vibrate(40);
            }
        });
        celdas.add(bCelda9);
    }

    private void setUpButtonsColors() {
        ImageButton bColor1 = findViewById(R.id.color1);
        ((ImageButton) bColor1).setImageResource(Colors.colorSelectorID(2));
        bColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(2);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor2 = findViewById(R.id.color2);
        ((ImageButton) bColor2).setImageResource(Colors.colorSelectorID(5));
        bColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(5);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor3 = findViewById(R.id.color3);
        ((ImageButton) bColor3).setImageResource(Colors.colorSelectorID(6));
        bColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(6);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor4 = findViewById(R.id.color4);
        ((ImageButton) bColor4).setImageResource(Colors.colorSelectorID(0));
        bColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(0);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor5 = findViewById(R.id.color5);
        ((ImageButton) bColor5).setImageResource(Colors.colorSelectorID(3));
        bColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(3);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor6 = findViewById(R.id.color6);
        ((ImageButton) bColor6).setImageResource(Colors.colorSelectorID(4));
        bColor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(4);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor7 = findViewById(R.id.color7);
        ((ImageButton) bColor7).setImageResource(Colors.colorSelectorID(1));
        bColor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(1);
                updateColor();
                vibe.vibrate(40);
            }
        });

        ImageButton bColor8 = findViewById(R.id.color8);
        ((ImageButton) bColor8).setImageResource(Colors.colorSelectorID(7));
        bColor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                setNumColor(7);
                updateColor();
                vibe.vibrate(40);
            }
        });
    }

    private void chooseColor(ImageButton celda){
        int drawable = (Integer) celda.getTag();
        if (drawable==R.drawable.celda_crafting){
            celda.setTag(Colors.colorSelectorID(getNumColor()));
            ((ImageButton) celda).setImageResource(Colors.colorSelectorID(getNumColor()));
        }else{
            celda.setTag(R.drawable.celda_crafting);
            ((ImageButton) celda).setImageResource(R.drawable.celda_crafting);
        }
    }

    private void updateColor(){
        for (ImageButton c :celdas) {
            int drawable = (Integer) c.getTag();
            if (drawable!=R.drawable.celda_crafting){
                c.setTag(Colors.colorSelectorID(getNumColor()));
                ((ImageButton) c).setImageResource(Colors.colorSelectorID(getNumColor()));
            }
        }
    }

    public int getNumColor() {
        return numColor;
    }

    public void setNumColor(int numColor) {
        this.numColor = numColor;
    }
}
