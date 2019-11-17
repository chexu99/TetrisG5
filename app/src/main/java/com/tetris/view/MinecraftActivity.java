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
                celdaEvent(bCelda1);
            }
        });
        celdas.add(bCelda1);

        bCelda2 = findViewById(R.id.celda2);
        bCelda2.setTag(R.drawable.celda_crafting);
        bCelda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda2);
            }
        });
        celdas.add(bCelda2);

        bCelda3 = findViewById(R.id.celda3);
        bCelda3.setTag(R.drawable.celda_crafting);
        bCelda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda3);
            }
        });
        celdas.add(bCelda3);

        bCelda4 = findViewById(R.id.celda4);
        bCelda4.setTag(R.drawable.celda_crafting);
        bCelda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda4);
            }
        });
        celdas.add(bCelda4);

        bCelda5 = findViewById(R.id.celda5);
        bCelda5.setTag(R.drawable.celda_crafting);
        bCelda5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda5);
            }
        });
        celdas.add(bCelda5);

        bCelda6 = findViewById(R.id.celda6);
        bCelda6.setTag(R.drawable.celda_crafting);
        bCelda6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda6);
            }
        });
        celdas.add(bCelda6);

        bCelda7 = findViewById(R.id.celda7);
        bCelda7.setTag(R.drawable.celda_crafting);
        bCelda7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda7);
            }
        });
        celdas.add(bCelda7);

        bCelda8 = findViewById(R.id.celda8);
        bCelda8.setTag(R.drawable.celda_crafting);
        bCelda8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                celdaEvent(bCelda8);
            }
        });
        celdas.add(bCelda8);

        bCelda9 = findViewById(R.id.celda9);
        bCelda9.setTag(R.drawable.celda_crafting);
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
        bColor1.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 2), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) { colorEvent(2);
            }
        });

        ImageButton bColor2 = findViewById(R.id.color2);
        bColor2.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 5), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(5);
            }
        });

        ImageButton bColor3 = findViewById(R.id.color3);
        bColor3.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 6), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(6);
            }
        });

        ImageButton bColor4 = findViewById(R.id.color4);
        bColor4.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 0), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(0);
            }
        });

        ImageButton bColor5 = findViewById(R.id.color5);
        bColor5.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 3), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(3);
            }
        });

        ImageButton bColor6 = findViewById(R.id.color6);
        bColor6.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 4), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(4);
            }
        });

        ImageButton bColor7 = findViewById(R.id.color7);
        bColor7.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 1), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(1);
            }
        });

        ImageButton bColor8 = findViewById(R.id.color8);
        bColor8.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), 7), (int) (3.5 * GameActivity.getPixelSize()),
                (int) (3.5 * GameActivity.getPixelSize()), false));
        bColor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                colorEvent(7);
            }
        });
    }

    private void celdaEvent(ImageButton celda){
        int drawable = (Integer) celda.getTag();
        if ((maxCeldas == 0) && (drawable == R.drawable.celda_crafting)){
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
        if (drawable == R.drawable.celda_crafting){
            setMaxCeldas(getMaxCeldas()-1);
            celda.setTag(Colors.colorSelectorID(numColor));
            celda.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), numColor), (int) (4.5 * GameActivity.getPixelSize()),
                    (int) (4.5 * GameActivity.getPixelSize()), false));
        }else{
            setMaxCeldas(getMaxCeldas()+1);
            celda.setTag(R.drawable.celda_crafting);
            celda.setImageResource(android.R.color.transparent);
        }
    }

    private void updateColor(){
        for (ImageButton c :celdas) {
            int drawable = (Integer) c.getTag();
            if (drawable != R.drawable.celda_crafting){
                c.setTag(Colors.colorSelectorID(getNumColor()));
                c.setImageBitmap(Bitmap.createScaledBitmap(Colors.blockTextureSelector(this.getResources(), numColor), (int) (4.5 * GameActivity.getPixelSize()),
                        (int) (4.5 * GameActivity.getPixelSize()), false));
                //c.setImageResource(Colors.colorSelectorID(getNumColor()));
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
