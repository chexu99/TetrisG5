package com.tetris.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.model.Shape;

import java.util.List;

public class GameActivity extends Activity {

    int NUM_ROWS = 26;
    int NUM_COLUMNS = 16;
    final int BOARD_HEIGHT = 800;
    final int BOARD_WIDTH = 400;

    final Handler handler = new Handler();

    int speed_test = 500;
    int SPEED_FAST = 50;
    String difficulty, speed;
    int score;
    boolean gameInProgress, gamePaused, fastSpeedState, currentShapeAlive;

    final int dx[] = {-1, 0, 1, 0};
    final int dy[] = {0, 1, 0, -1};

    //private GestureDetectorCompat gestureDetector;


    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    LinearLayout linearLayout;

    Shape currentShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        linearLayout = (LinearLayout) findViewById(R.id.game_board);

        gameInit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (gameInProgress) {
            gamePaused = true;
            PaintMatrix();
        }
    }


    void PaintMatrix() {

        // Paint the game board background
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT, paint);

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
        for(Block block : Board.getInstance().getBlocks()){
                    paint.setColor(Color.BLUE);
                    canvas.drawRect((block.getY() - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                            (block.getX() - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                            (block.getY() + 1 - 3) * (BOARD_WIDTH / (NUM_COLUMNS - 6)),
                            (block.getX() + 1 - 3) * (BOARD_HEIGHT / (NUM_ROWS - 6)),
                            paint);

        }
        /* //optional?
        // Paint borders to the tetris blocks
        for (int i = 3; i < NUM_ROWS - 3; ++i) {
            for (int j = 3; j < NUM_COLUMNS - 3; ++j) {
                if (gameMatrix[i][j].getState() == 1) {
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
        }
        */

        if (!gameInProgress) {
            TextView textView = (TextView) findViewById(R.id.game_over_textview);
            textView.setVisibility(View.VISIBLE);
            TextView textView2 = (TextView) findViewById(R.id.game_over_textview2);
            textView2.setVisibility(View.VISIBLE);
        } else if (gamePaused) {
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(60);
            canvas.drawText("GAME PAUSED", (float) (BOARD_WIDTH / 2.0), (float) (BOARD_HEIGHT / 2.0), paint);
        }
        // Display the current painting
        linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));

        // Update the score textview
        TextView textView = (TextView) findViewById(R.id.game_score_textview);
        textView.setText("Score: " + score);


    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /*
            if (!gameInProgress) {
                return;
            }
            if (gamePaused) {
                PaintMatrix();
                return;
            }
             */
            Board.getInstance().update(); //Moves falling shape and checks collision

            PaintMatrix(); // TODO: doesnt really paint it, idk why fix it pls

            handler.postDelayed(this, speed_test);


        }
    };

    void gameInit() {
        // TODO: do more stuff like set score to 0 or prepare controls
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, speed_test);
    }
}
