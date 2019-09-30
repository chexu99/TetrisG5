package com.tetris.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;

public class GameActivity extends Activity {

    final int BOARD_HEIGHT = 800;
    final int BOARD_WIDTH = 400;
    final Handler handler = new Handler();

    //Buttons
    public Button despDer;
    public Button despIzq;
    public Button despRotate;

    //Board values
    int NUM_ROWS = 26;
    int NUM_COLUMNS = 16;
    int speed_test = 100;
    int score;

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    ConstraintLayout constraintLayout;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /*TODO: ahora mismo si detecta game over no hace nada mas que dejar de actualizar
            habria que hacer una activity de gameover o algo asi con el score
             */
            Board.getInstance().update();
            if (!Board.getInstance().getGameStatus().equals(Board.GameStatus.GAME_OVER))
                paintMatrix(); //Paints game board
            else
                onStop();
            handler.postDelayed(this, speed_test);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        constraintLayout = findViewById(R.id.game_board);

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
                if (Board.getInstance().checkMoveLeft()&& Board.getInstance().checkMoveRight())
                    Board.getInstance().getFallingShape().rotateRight();
            }
        });
    }

    void gameInit() {
        // TODO: do more stuff like set score to 0 or prepare controls
        Board.getInstance().setGameStatus(Board.GameStatus.INITIATING);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, speed_test);
    }

    void paintMatrix() {
        // Paint the game board background
        canvas.drawColor(Color.BLACK);

        // Paint the grid on the game board
        paint.setColor(Color.WHITE);
        for (int i = 0; i <= (NUM_ROWS - 6); ++i) {
            canvas.drawLine(0, i * (BOARD_HEIGHT / (NUM_ROWS - 6)), BOARD_WIDTH,
                    i * (BOARD_HEIGHT / (NUM_ROWS - 6)), paint);
        }
        for (int i = 0; i <= (NUM_COLUMNS - 6); ++i) {
            canvas.drawLine(i * (BOARD_WIDTH / (NUM_COLUMNS - 6)), 0,
                    i * (BOARD_WIDTH / (NUM_COLUMNS - 6)), BOARD_HEIGHT, paint);
        }


        // Paint the tetris blocks j = y    i = x
        for (Block block : Board.getInstance().getBlocks()) {
            paint.setColor(block.getColor());

            /*   //En el futuro cambiar por imagen
            Bitmap bitmapBlock = BitmapFactory.decodeResource(this.getResources(), R.drawable.bloque_test);
            bitmapBlock = Bitmap.createScaledBitmap(bitmap, block.getWidth() * 20, block.getHeight() * 20, false);
            canvas.drawBitmap(bitmap, block.getX(), block.getY(), paint);
             */
            canvas.drawRect((block.getX()) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                    (block.getY()) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                    (block.getX() + 1) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                    (block.getY() + 1) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                    paint);

        }
        // Paint borders to the tetris blocks
        for (int i = 3; i < NUM_ROWS - 3; ++i) {
            for (int j = 3; j < NUM_COLUMNS - 3; ++j) {
                paint.setColor(Color.BLACK);
                canvas.drawLine((j - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        (j - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i + 1 - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        paint);
                canvas.drawLine((j - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        (j + 1 - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        paint);
                canvas.drawLine((j + 1 - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        (j + 1 - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i + 1 - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        paint);
                canvas.drawLine((j - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i + 1 - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        (j + 1 - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                        (i + 1 - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                        paint);

            }
        }

        // Display the current painting
        constraintLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));

        // Update the score textview
        TextView textView = findViewById(R.id.game_score_textview);
        textView.setText("Score: " + score);


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
    }
}
