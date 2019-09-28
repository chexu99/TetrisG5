package com.tetris.view;

import androidx.constraintlayout.widget.ConstraintLayout;

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

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.model.Shape;

public class GameActivity extends Activity {


    int NUM_ROWS = 26;
    int NUM_COLUMNS = 16;
    /*
    public static int BLOCK_WIDTH = 20;
    public static int BLOCK_HEIGHT = 20;
*/
    final int BOARD_HEIGHT = 800;
    final int BOARD_WIDTH = 400;

    final Handler handler = new Handler();

    int speed_test = 500;
    int SPEED_FAST = 50;
    int score;
    boolean gameInProgress, gamePaused, fastSpeedState, currentShapeAlive;


    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    ConstraintLayout constraintLayout;

    Shape currentShape;

    public Button despDer;
    public Button despIzq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        constraintLayout = (ConstraintLayout) findViewById(R.id.game_board);

        //createRegions();

        final int BOARD_HEIGHT = 800;
        final int BOARD_WIDTH = 400;

        final Board board = Board.getInstance();
        board.makeNextShapeFalling();

        currentShape = board.getFallingShape();
        despDer = (Button) findViewById(R.id.mvderecha);
        despDer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View b) {
                currentShape = board.getFallingShape();
                currentShape.moveRight();
            }
        });
        despIzq = (Button) findViewById(R.id.mvizquierda);
        despIzq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View b) {
                currentShape = board.getFallingShape();
                currentShape.moveLeft();
            }
        });

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
        canvas.drawColor(Color.argb(255, 0, 0, 0));

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
            paint.setColor(Color.BLUE);

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
        constraintLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));

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
