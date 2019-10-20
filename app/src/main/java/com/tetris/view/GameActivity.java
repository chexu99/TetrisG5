package com.tetris.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tetris.R;
import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.utils.Colors;
import com.tetris.utils.EasterEggs;

public class GameActivity extends Activity {

    boolean stopped = false;
/*
    public void setBOARD_HEIGHT(int BOARD_HEIGHT) {
        this.BOARD_HEIGHT = BOARD_HEIGHT;
    }

    public void setBOARD_WIDTH(int BOARD_WIDTH) {
        this.BOARD_WIDTH = BOARD_WIDTH;
    }
*/
    final int BOARD_HEIGHT=800; //Max quality = 6400 -> Laser-mode = 20
    final int BOARD_WIDTH=400; //Max quality = 3200 -> Laser-mode = 10
    final int PIXEL_SIZE = BOARD_WIDTH / Board.BOARD_COLS;
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
            }
            else {
                onStop();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
/*
        Bundle data = this.getIntent().getExtras();
        if (data != null){
            int height = data.getInt("board_height");
            int width = data.getInt("board_width");
            setBOARD_HEIGHT(height);
            setBOARD_WIDTH(width);
        }else{
            setBOARD_HEIGHT(800);
            setBOARD_WIDTH(400);
        }
*/
        setUpLayouts();

        setUpButtons();

        gameInit();
    }

    private void setUpLayouts(){
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

        if(Board.getInstance().isNeedsUpdate()) {
            //Update board layout
            paintBlockArray();

            //Update score
            scoreText.setText(String.valueOf(Board.getInstance().getScore()));

            if(Board.getInstance().getScore() >= 99999){
                scoreText.setText(EasterEggs.easterEgg2());
            }

            //Paint next shape on left side
            paintNextShape();

            if (Board.getInstance().isDeadBlocksUpdate()){
                paintDeadBlocks();
                Board.getInstance().setDeadBlocksUpdate(false);
                Board.getInstance().setSquareGameOver(Board.getInstance().getSquareGameOver()+2);
            }
        }else {
            //Paint fallingShape Layout
            paintFallingShape();

        }

    }

    private void paintDeadBlocks(){
        Bitmap bitmapBlock =  Colors.blockedTexture(this.getResources());
        for (int i = 0; i<Board.BOARD_COLS;i++){
            for (int j = Board.getInstance().getDeadBlockY(); j<Board.getInstance().getDeadBlockY()+2;j++){
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
                deadBlocksCanvas.drawBitmap(bitmapBlock, i*PIXEL_SIZE, j*PIXEL_SIZE, paint);
            }
        }
        deadBlocksLayout.setBackgroundDrawable(new BitmapDrawable(deadBlocksBitmap));
    }

    private void paintNextShape() {
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        int color = Board.getInstance().getNextShape().getBlocks()[0].getColor(); //Color of first block
        Bitmap bitmapBlock =  Colors.nextShapeTextureSelector(this.getResources(), color);
        if(color == 5) //If the piece is red (4x1)
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, (int) (PIXEL_SIZE), (int) (PIXEL_SIZE*1.0), false); //110 -> 64 = 1.71875
        else
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
        bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
        nextShapeCanvas.drawBitmap(bitmapBlock, 0, 30, paint);

        nextShapeLayout.setBackgroundDrawable(new BitmapDrawable(nextShapeBitmap));
    }

    private void chooseColorBlocks(){
        if (Board.getInstance().getNumberDeleteLines()==4){

        } else if (Board.getInstance().getNumberDeleteLines()==1){

        } else {

        }
    }

    private void paintBlockArray(){
        boardCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Bitmap bitmapBlock;
        for (Block block : Board.getInstance().getBlocks()) {
            if (Board.getInstance().getNumberDeleteLines()>0){
                bitmapBlock = Colors.blockTextureSelector(this.getResources(), Board.getInstance().getColorLastFallingShape());
            } else {
                bitmapBlock = Colors.blockTextureSelector(this.getResources(), block.getColor());
            }
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
            boardCanvas.drawBitmap(bitmapBlock, block.getX()*PIXEL_SIZE, block.getY()*PIXEL_SIZE, paint);
        }
        Board.getInstance().setNumberDeleteLines(0);
        Board.getInstance().setNeedsUpdate(false);
        boardLayout.setBackgroundDrawable(new BitmapDrawable(boardBitmap));
    }

    private void paintFallingShape(){
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        for(Block block : Board.getInstance().getFallingShape().getBlocks()){
            Bitmap bitmapBlock =  Colors.blockTextureSelector(this.getResources(), block.getColor());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, PIXEL_SIZE, PIXEL_SIZE, false);
            fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX()*PIXEL_SIZE, block.getY()*PIXEL_SIZE, paint);
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

        //handler.removeCallbacks(runnable);

        if (!stopped) {
            stopped = true;
            Intent intent = new Intent(this, FinalScoreActivity.class);
            startActivity(intent);
        }
    }
}
