package com.tetris.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.tetris.R;
import com.tetris.model.Shape;
import com.tetris.utils.UserSettings;

public class GraphicActivity extends AppCompatActivity {

    private RadioGroup myRadioGroup;
    private ImageButton confirm;
    private int optionDefault;
    private RadioButton highButton;
    private RadioButton mediumButton;
    private RadioButton lowButton;
    private Vibrator vibe;
    private Switch hardMode;
    private static boolean isOn = false;
    public  boolean idiom = MenuActivity.idiom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        vibe = (Vibrator) GraphicActivity.this.getSystemService(GraphicActivity.VIBRATOR_SERVICE);

        highButton = findViewById(R.id.high_radioButton);
        if(idiom){
            highButton.setText("AlTO");
        }else {
            highButton.setText("HIGH");
        }

        mediumButton = findViewById(R.id.medium_radioButton);
        if(idiom){
            mediumButton.setText("MEDIO");
        } else{
            mediumButton.setText("MEDIUM");
        }
        lowButton = findViewById(R.id.low_radioButton);
        if(idiom){
            lowButton.setText("BAJO");
        }
        else{
            lowButton.setText("LOW");

        }
        hardMode = findViewById(R.id.hardmode_switch);
        if(idiom){
            hardMode.setText("Modo dificil");
        }
        else{
            hardMode.setText("Difficult Mode");
        }
        settingDefault();

        myRadioGroup = findViewById(R.id.radioGroup);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.high_radioButton:
                        UserSettings.setGraphicsResolution(1);
                        break;
                    case R.id.medium_radioButton:
                        UserSettings.setGraphicsResolution(2);
                        break;
                    case R.id.low_radioButton:
                    default:
                        UserSettings.setGraphicsResolution(3);
                        break;
                }
            }
        });

        hardMode.setChecked(isOn);
        hardMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener (){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
            boolean isChecked) {
                isOn = isChecked;
                if(isChecked)
                    Shape.updateInterval = 100;
                else
                    Shape.updateInterval = 300;
            }
        });

        confirm = findViewById(R.id.ok_button);
        if(idiom){
            confirm.setImageResource(R.drawable.btn_volver);
        }else{
            confirm.setImageResource(R.drawable.btn_return);
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSettings.changeResolution();
                openMenuActivity();
            }
        });
    }

    private void settingDefault() {
        optionDefault = UserSettings.getGraphicsResolution();
        switch (optionDefault){
            case 1:
                highButton.setChecked(true);
                break;
            case 2:
                mediumButton.setChecked(true);
                break;
            case 3:
            default:
                lowButton.setChecked(true);
                break;
        }
    }

    private void changeDifficulty(){

    }

    public void openMenuActivity() {
        vibe.vibrate(40);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

}
