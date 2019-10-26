package com.tetris.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.utils.Colors;
import com.tetris.utils.EasterEggs;

import java.util.HashMap;
import java.util.Random;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends Activity {

    boolean stopped = false;

    public static int BOARD_HEIGHT = 800; //Max quality = 6400 -> Laser-mode = 20
    public static int BOARD_WIDTH = 400; //Max quality = 3200 -> Laser-mode = 10
    public static int PIXEL_SIZE = BOARD_WIDTH / Board.BOARD_COLS;
    final Handler handler = new Handler();

    //Buttons
    public ImageButton despDer;
    public ImageButton despIzq;
    public ImageButton despRotate;
    public Button despDown;

    //Board values
    int speed = 50;

    Paint paint;

    Bitmap boardBitmap;
    Canvas boardCanvas;

    Bitmap nextShapeBitmap;
    Canvas nextShapeCanvas;

    Bitmap fallingShapeBitmap;
    Canvas fallingShapeCanvas;

    Bitmap deadBlocksBitmap;
    Canvas deadBlocksCanvas;

    ImageView boardLayout;
    ImageView fallingShapeLayout;
    ConstraintLayout scoreLayout;
    ImageView nextShapeLayout;
    ImageView deadBlocksLayout;

    TextView scoreText;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!Board.getInstance().getGameStatus().equals(Board.GameStatus.GAME_OVER)) {
                Board.getInstance().update(); //Updates the board
            }
            if (!Board.getInstance().getGameStatus().equals(Board.GameStatus.GAME_OVER)) {
                paintGame(); //Paints game board
                handler.removeCallbacks(this);
                handler.postDelayed(this, speed);
            } else {
                onStop();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setUpLayouts();

        setUpButtons();

        gameInit();
    }

    private void setUpLayouts() {
        // Game board
        boardBitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        boardCanvas = new Canvas(boardBitmap);
        boardCanvas.drawColor(Color.TRANSPARENT);
        boardLayout = findViewById(R.id.game_board);
        boardLayout.setBackgroundDrawable(new BitmapDrawable(boardBitmap));

        //Falling shape
        fallingShapeBitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        fallingShapeCanvas = new Canvas(fallingShapeBitmap);
        fallingShapeLayout = findViewById(R.id.falling_shape);
        fallingShapeLayout.setBackgroundDrawable(new BitmapDrawable(fallingShapeBitmap));

        //Score
        scoreLayout = findViewById(R.id.top_board);
        scoreText = (TextView) findViewById(R.id.score_text_view);

        //Next shape
        nextShapeBitmap = Bitmap.createBitmap((int) (3 * PIXEL_SIZE * 0.5), (int) (4 * PIXEL_SIZE * 0.5), Bitmap.Config.ARGB_8888);
        nextShapeCanvas = new Canvas(nextShapeBitmap);
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        nextShapeLayout = findViewById(R.id.next_shape);

        //DeadBlocks
        deadBlocksBitmap = Bitmap.createBitmap(BOARD_WIDTH, BOARD_HEIGHT, Bitmap.Config.ARGB_8888);
        deadBlocksCanvas = new Canvas(deadBlocksBitmap);
        deadBlocksLayout = findViewById(R.id.dead_blocks);
        deadBlocksLayout.setBackgroundDrawable(new BitmapDrawable(deadBlocksBitmap));

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
        //MoveDown button
        despDown = findViewById(R.id.mvDown);
        despDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.getInstance().checkMoveDown();
            }
        });
    }

    private void gameInit() {
        // TODO: do more stuff like set score to 0 or prepare controls
        if (Board.getInstance().getBlocks().size() > 0)
            Board.getInstance().clear();

        scoreText.setText(String.valueOf(Board.getInstance().getScore()));

        stopped = false;
        Board.getInstance().setGameStatus(Board.GameStatus.INITIATING);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, speed);
    }

    //Painting methods
    private void paintGame() {
        for (Board.Actions a : Board.getInstance().getActions()) {
            if (a.equals(Board.Actions.COLLISION)) {
                //Update board layout
                paintBlockArray();

                //Update score
                scoreText.setText(String.valueOf(Board.getInstance().getScore()));

                if (Board.getInstance().getScore() >= 99999) {
                    scoreText.setText(EasterEggs.easterEgg2());
                }

                //Paint next shape on left side
                paintNextShape();

            }
            if (a.equals(Board.Actions.DEAD_BLOCK)) {
                paintDeadBlocks();
                Board.getInstance().setSquareGameOver(Board.getInstance().getSquareGameOver() + 2);
            }
            if (a.equals(Board.Actions.RESET_DEAD)) {
                deleteDeadBlocks();
            }
        }
        Board.getInstance().getActions().clear(); //Clear actions list
        //Paint fallingShape Layout
        paintFallingShape();

    }

    private void deleteDeadBlocks(){
        deadBlocksCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
  
    private void paintDeadBlocks() {
        Bitmap bitmapBlock = Colors.blockedTexture(this.getResources());
        for (int i = 0; i < Board.BOARD_COLS; i++) {
            for (int j = Board.getInstance().getDeadBlockY(); j < Board.getInstance().getDeadBlockY() + 2; j++) {
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
                deadBlocksCanvas.drawBitmap(bitmapBlock, i * PIXEL_SIZE, j * PIXEL_SIZE, paint);
            }
        }
        deadBlocksLayout.setBackgroundDrawable(new BitmapDrawable(deadBlocksBitmap));
    }

    private void paintNextShape() {
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        int color = Board.getInstance().getNextShape().getBlocks()[0].getColor(); //Color of first block
        BitmapDrawable bitmapShape = Colors.nextShapeTextureSelector(this.getResources(), color);

        nextShapeLayout.setBackgroundDrawable(bitmapShape);
    }


    private void paintBlockArray() {
        boardCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Bitmap bitmapBlock;


        for (Block block : Board.getInstance().getBlocks()) {
            bitmapBlock =  Colors.blockTextureSelector(this.getResources(),block.getColorNow());

            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
            boardCanvas.drawBitmap(bitmapBlock, block.getX() * PIXEL_SIZE, block.getY() * PIXEL_SIZE, paint);
        }

        boardLayout.setBackgroundDrawable(new BitmapDrawable(boardBitmap));
    }

    private void paintFallingShape() {
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Bitmap bitmapBlock;

        for(Block block : Board.getInstance().getFallingShape().getBlocks()){
            bitmapBlock =  Colors.blockTextureSelector(this.getResources(),block.getColorNow());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
            fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX() * PIXEL_SIZE, block.getY() * PIXEL_SIZE, paint);
        }

        if (Board.getInstance().getFastShape() != null) {
            for (Block block : Board.getInstance().getFastShape().getBlocks()) {
                bitmapBlock = Colors.blockTextureSelector(this.getResources(), block.getColorNow());
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
                fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX() * PIXEL_SIZE, block.getY() * PIXEL_SIZE, paint);
            }
        }

        fallingShapeLayout.setBackgroundDrawable(new BitmapDrawable(fallingShapeBitmap));
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

        if (!stopped) {
            stopped = true;
            Intent intent = new Intent(this, FinalScoreActivity.class);
            startActivity(intent);
        }
    }


    public static void setBoardHeight(int boardHeight) {
        BOARD_HEIGHT = boardHeight;
    }

    public static void setBoardWidth(int boardWidth) {
        BOARD_WIDTH = boardWidth;
    }

    public static void setPixelSize(int pixelWidth) {
        PIXEL_SIZE = pixelWidth / Board.BOARD_COLS;
    }
}
