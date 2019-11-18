package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tetris.R;
import com.tetris.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class MinecraftActivity extends AppCompatActivity {


    private int numColor = 2;
    private int maxCeldas = 6;
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
    private ImageButton bCancelar;
    private ImageButton bCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minecraft);
        vibe = (Vibrator) MinecraftActivity.this.getSystemService(MinecraftActivity.VIBRATOR_SERVICE);

        setUpButtonsCeldas();

        setUpButtonsColors();

        setUpButtonsFinal();
    }

    private void setUpButtonsFinal(){
        bCancelar = findViewById(R.id.mine_cancel);
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(40);
                finish();
            }
        });

        bCreate = findViewById(R.id.mine_newshape);
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: CREATE NEW SHAPE AND SHOW THE SHAPE
            }
        });
    }

    private void setUpButtonsCeldas() {
        bCelda1 = findViewById(R.id.celda1);
        bCelda1.setTag(0);
        bCelda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda1);
            }
        });
        celdas.add(bCelda1);

        bCelda2 = findViewById(R.id.celda2);
        bCelda2.setTag(0);
        bCelda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda2);
            }
        });
        celdas.add(bCelda2);

        bCelda3 = findViewById(R.id.celda3);
        bCelda3.setTag(0);
        bCelda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda3);
            }
        });
        celdas.add(bCelda3);

        bCelda4 = findViewById(R.id.celda4);
        bCelda4.setTag(0);
        bCelda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda4);
            }
        });
        celdas.add(bCelda4);

        bCelda5 = findViewById(R.id.celda5);
        bCelda5.setTag(0);
        bCelda5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda5);
            }
        });
        celdas.add(bCelda5);

        bCelda6 = findViewById(R.id.celda6);
        bCelda6.setTag(0);
        bCelda6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda6);
            }
        });
        celdas.add(bCelda6);

        bCelda7 = findViewById(R.id.celda7);
        bCelda7.setTag(0);
        bCelda7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda7);
            }
        });
        celdas.add(bCelda7);

        bCelda8 = findViewById(R.id.celda8);
        bCelda8.setTag(0);
        bCelda8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda8);
            }
        });
        celdas.add(bCelda8);

        bCelda9 = findViewById(R.id.celda9);
        bCelda9.setTag(0);
        bCelda9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda9);
            }
        });
        celdas.add(bCelda9);
    }

    private void setUpButtonsColors() {
        ImageButton bColor1 = findViewById(R.id.color1);
        bColor1.setImageResource(Colors.colorSelectorID(2));
        bColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) { colorEvent(2);
            }
        });

        ImageButton bColor2 = findViewById(R.id.color2);
        bColor2.setImageResource(Colors.colorSelectorID(5));
        bColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(5);
            }
        });

        ImageButton bColor3 = findViewById(R.id.color3);
        bColor3.setImageResource(Colors.colorSelectorID(6));
        bColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(6);
            }
        });

        ImageButton bColor4 = findViewById(R.id.color4);
        bColor4.setImageResource(Colors.colorSelectorID(0));
        bColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(0);
            }
        });

        ImageButton bColor5 = findViewById(R.id.color5);
        bColor5.setImageResource(Colors.colorSelectorID(3));
        bColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(3);
            }
        });

        ImageButton bColor6 = findViewById(R.id.color6);
        bColor6.setImageResource(Colors.colorSelectorID(4));
        bColor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(4);
            }
        });

        ImageButton bColor7 = findViewById(R.id.color7);
        bColor7.setImageResource(Colors.colorSelectorID(1));
        bColor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(1);
            }
        });

        ImageButton bColor8 = findViewById(R.id.color8);
        bColor8.setImageResource(Colors.colorSelectorID(7));
        bColor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(7);
            }
        });
    }

    private void celdaEvent(ImageButton celda){
        int drawable = (Integer) celda.getTag();
        if ((maxCeldas == 0) && (drawable == 0)){
            Toast.makeText(getApplicationContext(), "Debe coger un máximo de 6 bloques", Toast.LENGTH_LONG).show();
        } else {
            chooseColor(celda);
        }
        vibe.vibrate(40);
    }

    private void colorEvent(int num){
        numColor = num;
        updateColor();
        vibe.vibrate(40);
    }

    private void chooseColor(ImageButton celda){
        int drawable = (Integer) celda.getTag();
        if (drawable == 0){
            setMaxCeldas(getMaxCeldas()-1);
            celda.setTag(Colors.colorSelectorID(getNumColor()));
            celda.setImageResource(Colors.colorSelectorID(getNumColor()));
        }else{
            setMaxCeldas(getMaxCeldas()+1);
            celda.setTag(0);
            celda.setImageResource(android.R.color.transparent);
        }
    }

    private void updateColor(){
        for (ImageButton c :celdas) {
            int drawable = (Integer) c.getTag();
            if (drawable != 0){
                c.setTag(Colors.colorSelectorID(getNumColor()));
                c.setImageResource(Colors.colorSelectorID(getNumColor()));
            }
        }
    }

    private void createCustomShape(){
        //TODO: hay que mergear con master o importarlo de algun modo


    }


    public int getMaxCeldas() {
        return maxCeldas;
    }

    public void setMaxCeldas(int maxCeldas) {
        this.maxCeldas = maxCeldas;
    }

    public int getNumColor() {
        return numColor;
    }

    public void setNumColor(int numColor) {
        this.numColor = numColor;
    }
}
