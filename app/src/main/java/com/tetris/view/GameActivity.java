package com.tetris.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends Activity {

    boolean stopped = false;

    final int BOARD_HEIGHT = 3200;
    final int BOARD_WIDTH = 1600;
    final int PIXEL_SIZE = BOARD_WIDTH / Board.BOARD_COLS;
    final Handler handler = new Handler();

    //Buttons
    public ImageButton despDer;
    public ImageButton despIzq;
    public ImageButton despRotate;

    //Board values
    int speed_test = 50;

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;

    Bitmap leftbitmap; // To show next shape
    Canvas leftcanvas;

    Bitmap fallingbitmap;
    Canvas fallingcanvas;

    ImageView gameLayout;
    ImageView fallingShapeLayout;
    ConstraintLayout scoreLayout;
    ImageView leftLayout;


    public TextView text;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /*TODO: ahora mismo si detecta game over no hace nada mas que dejar de actualizar
            habria que hacer una activity de gameover o algo asi con el score
             */
            if (!Board.getInstance().getGameStatus().equals(Board.GameStatus.GAME_OVER))
                Board.getInstance().update();
            if (!Board.getInstance().getGameStatus().equals(Board.GameStatus.GAME_OVER))
                paintMatrix(); //Paints game board
            else {
                onStop();
            }
            handler.postDelayed(this, speed_test);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Static board
        bitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        gameLayout = findViewById(R.id.game_board);

        //Falling shape
        fallingbitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        fallingcanvas = new Canvas(fallingbitmap);
        fallingShapeLayout = findViewById(R.id.falling_shape);

        //Score
        scoreLayout = findViewById(R.id.top_board);
        text = (TextView) findViewById(R.id.score_text_view);

        //Next shape
        leftbitmap = Bitmap.createBitmap((int) (3 * PIXEL_SIZE * 0.5), (int) (4 * PIXEL_SIZE * 0.5), Bitmap.Config.ARGB_8888);
        leftcanvas = new Canvas(leftbitmap);
        leftLayout = findViewById(R.id.next_shape);

        setUpButtons();

        gameInit();
    }

    private void setUpButtons() {
        //MoveRight button
        despDer = findViewById(R.id.mvRight);
        despDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                if (Board.getInstance().checkMoveRight())
                    Board.getInstance().getFallingShape().moveRight();
            }
        });
        //MoveLeft button
        despIzq = findViewById(R.id.mvLeft);
        despIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                if (Board.getInstance().checkMoveLeft())
                    Board.getInstance().getFallingShape().moveLeft();
            }
        });
        //MoveRotate button
        despRotate = findViewById(R.id.mvRotate);
        despRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                if (Board.getInstance().checkRotate())
                    Board.getInstance().getFallingShape().rotate();
            }
        });
    }

    void gameInit() {
        // TODO: do more stuff like set score to 0 or prepare controls
        if (Board.getInstance().getBlocks().size() > 0)
            Board.getInstance().clear();

        text.setText(String.valueOf(Board.getInstance().getScore()));

        stopped = false;
        Board.getInstance().setGameStatus(Board.GameStatus.INITIATING);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, speed_test);
    }

    public void paintNextShape() {
        for (Block block : Board.getInstance().getNextShape().getBlocks()) {
            Bitmap bitmapBlock =  bitmapTextureSelector(block.getColor());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, (int)(PIXEL_SIZE*0.5), (int)(PIXEL_SIZE*0.5), false);
            canvas.drawBitmap(bitmapBlock, (int) ((block.getX()-4)*PIXEL_SIZE*0.5), (int) ((block.getY()-4)*PIXEL_SIZE*0.5), paint);
        }
    }

    void paintBlockArray(){
        for (Block block : Board.getInstance().getBlocks()) {
            Bitmap bitmapBlock =  bitmapTextureSelector(block.getColor());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
            canvas.drawBitmap(bitmapBlock, block.getX()*PIXEL_SIZE, block.getY()*PIXEL_SIZE, paint);
        }
    }

    void paintFallingShape(){
        for(Block block : Board.getInstance().getFallingShape().getBlocks()){
            Bitmap bitmapBlock =  bitmapTextureSelector(block.getColor());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
            fallingcanvas.drawBitmap(bitmapBlock, block.getX()*PIXEL_SIZE, block.getY()*PIXEL_SIZE, paint);
        }
    }

    void paintMatrix() {
        // Paint the game board background
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        leftcanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // Paint the tetris blocks j = y    i = x
        if(Board.getInstance().getActions().size() > 0) {
            if (Board.getInstance().getActions().get(0) == Board.GameActions.DELETED_LINE) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                paintBlockArray();
            }
            Board.getInstance().getActions().clear();
        }

        //Paint fallingShape Layout
        fallingcanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        paintFallingShape();

        //Update score
        text.setText(String.valueOf(Board.getInstance().getScore()));

        //Paint next shape on left side
        paintNextShape();

        // Display the current painting
        gameLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        fallingShapeLayout.setBackgroundDrawable(new BitmapDrawable(fallingbitmap));
        leftLayout.setBackgroundDrawable(new BitmapDrawable(leftbitmap));
    }

    private Bitmap bitmapTextureSelector(int color) {
        switch (color) {
            case Color.YELLOW:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_yellow);
            case Color.BLUE:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_blue);
            case Color.WHITE:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_white);
            case Color.CYAN:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_cyan);
            case Color.GREEN:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_lime);
            case Color.RED:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_red);
            case Color.MAGENTA:
            default:
                return BitmapFactory.decodeResource(this.getResources(), R.drawable.block_purple);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Board.getInstance().setGameStatus(Board.GameStatus.PAUSED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Board.getInstance().setGameStatus(Board.GameStatus.IN_PROGRESS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Board.getInstance().setGameStatus(Board.GameStatus.PAUSED);

        //handler.removeCallbacks(runnable);

        if (!stopped) {
            stopped = true;
            Intent intent = new Intent(this, FinalScoreActivity.class);
            startActivity(intent);
        }
    }
}
