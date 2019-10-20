package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;
import com.tetris.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GraphicActivity extends AppCompatActivity {

    private RadioGroup myRadioGroup;
    private Button confirm;
    public int board_height;
    public int board_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        myRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.high_radioButton){
                    board_height =3200;
                    board_width =1600;
                    Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.medium_radioButton){
                    board_height =1600;
                    board_width =800;
                    Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                }else if (checkedId == R.id.low_radioButton){
                    board_height =800;
                    board_width =400;
                    Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
                }
            }
        });

        confirm = findViewById(R.id.ok_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("variable_board_height", board_height);
        intent.putExtra("variable_board_width", board_width);
        startActivity(intent);
    }
}
