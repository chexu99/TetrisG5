package com.tetris.view;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tetris.R;
import com.tetris.model.Board;
import com.tetris.model.events.FastShapeEvents;
import com.tetris.model.events.MovementEvents;
import com.tetris.view.layout_painting.BlockedBlocksLayout;
import com.tetris.view.layout_painting.BoardLayout;
import com.tetris.view.layout_painting.FallingShapeLayout;
import com.tetris.view.layout_painting.NextShapeLayout;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends Activity {
    boolean music= true;
    boolean stopped = false;

    protected MediaPlayer mp;

    private static int boardHeight = 800; //Max quality = 6400 -> Laser-mode = 20
    private static int boardWidth = 400; //Max quality = 3200 -> Laser-mode = 10
    private static int pixelSize = boardWidth / Board.BOARD_COLS;
    private final Handler handler = new Handler();

    //Board values
    int speed = 50;

    private static ImageView boardLayout;
    private static ImageView fallingShapeLayout;
    private static ImageView nextShapeLayout;
    private static List<ImageView> customShapeLayouts;
    private static ImageView deadBlocksLayout;

    private BoardLayout boardLay;
    private FallingShapeLayout fallingLay;
    private NextShapeLayout nextLay; //Normal layout
    private NextShapeLayout customLay; //CustomShape layout
    private BlockedBlocksLayout blockedLay;

    private TextView scoreText;
    private TextView crono;

    private boolean playing;
    private boolean homeClicked;




    private ImageButton home,pausebutton;

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
                setCountdown();


            }else{
                onStop();
            }
        }
    };

    public GameActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setUpLayouts();

        setUpButtons();

        setUpMusic();

        gameInit();


    }

    private void setUpLayouts() {
        // Game board
        boardLayout = findViewById(R.id.game_board);
        boardLay = new BoardLayout();

        //Falling shape
        fallingShapeLayout = findViewById(R.id.falling_shape);
        fallingLay = new FallingShapeLayout();

        //Score
        ConstraintLayout scoreLayout = findViewById(R.id.top_board);
        scoreText =  findViewById(R.id.score_text_view);

        //Countdown
        crono= findViewById(R.id.crono);


        //Next shape
        nextShapeLayout = findViewById(R.id.next_shape);
        nextLay = new NextShapeLayout();

        //Custom shape
        customShapeLayouts = new ArrayList<>();
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda1));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda2));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda3));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda4));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda5));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda6));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda7));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda8));
        customShapeLayouts.add((ImageView) findViewById(R.id.minecraft_celda9));
        customLay = new NextShapeLayout();

        //DeadBlocks
        deadBlocksLayout = findViewById(R.id.dead_blocks);
        blockedLay = new BlockedBlocksLayout();

    }



    private void setUpButtons() {
        //MoveRight button
        ImageButton despDer = findViewById(R.id.mvRight);
        despDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                MovementEvents.checkAndMoveRight();
            }
        });
        //MoveLeft button
        ImageButton despIzq = findViewById(R.id.mvLeft);
        despIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                MovementEvents.checkAndMoveLeft();
            }
        });
        //MoveRotate button
        ImageButton despRotate = findViewById(R.id.mvRotate);
        despRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                MovementEvents.checkAndRotate();
            }
        });
        //MoveDown button
        ImageButton despDown = findViewById(R.id.mvDown);
        despDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovementEvents.checkAndMoveDown();
            }
        });
        //Home button
        homeClicked=false;
        home = findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                sethomeClicked(true);
                openMenu();

            }
        });
        //pause music button
        pausebutton = findViewById(R.id.pauseButton);
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (music){
                    mp.pause();
                    music=false;
                }else{
                    mp.start();
                    music=true;
                }
            }
        });

    }
    public void sethomeClicked(boolean b){
        this.homeClicked=b;
    }

    public boolean gethomeClicked(){
        return homeClicked;
    }
    private void setUpMusic(){
        mp = MediaPlayer.create(this,R.raw.musica);
        mp.setLooping(true);
        mp.start();
    }

    private void gameInit() {
        if (!Board.getInstance().getBlocks().isEmpty())
            Board.getInstance().clear();

        scoreText.setText(String.valueOf(Board.getInstance().getScore()));

        stopped = false;
        Board.getInstance().setGameStatus(Board.GameStatus.INITIATING);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, speed);
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }





    public void setCountdown(){
         int limitTime;
         int cTime;
         int time, auxTime;
         limitTime= (int) FastShapeEvents.getFastShapeTimer()/1000;//20
         auxTime= (int) FastShapeEvents.getLastFastShapeUpdate()/1000;
         cTime=(int) SystemClock.uptimeMillis() /1000;
         time= cTime-auxTime;
         if (time>20){
             time=20;
         }
         crono.setText(String.valueOf(limitTime-time));

    }
    //Painting methods
    private void paintGame() {
        for (Board.Actions a : Board.getActionList()) {
            if (a.equals(Board.Actions.COLLISION)) {
                //Update board layout
                boardLay.paintBlockArray(this.getResources());
                //Update score
                scoreText.setText(String.valueOf(Board.getInstance().getScore()));


            }

            if (a.equals(Board.Actions.DEAD_BLOCK)) {
                blockedLay.paintBlockedBlocks(this.getResources());
                Board.getInstance().setSquareGameOver(Board.getInstance().getSquareGameOver() + 2);
            }
            if (a.equals(Board.Actions.RESET_DEAD)) {
                blockedLay.deleteDeadBlocks();
            }
            if (a.equals(Board.Actions.CUSTOM_SHAPE)) {
                customLay.paintCustomShape(this.getResources());
            }
            if (a.equals(Board.Actions.NORMAL_SHAPE)) {
                nextLay.paintNextShape(this.getResources());
            }

        }
        Board.getActionList().clear(); //Clear actions list
        //Paint fallingShape Layout
        fallingLay.paintFallingShape(this.getResources());
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
            mp.pause();
            Intent intent = new Intent(this, FinalScoreActivity.class);
            startActivity(intent);
        }
    }

    public boolean getPlaying(){return playing= true;}
    public static int getBoardHeight() {
        return boardHeight;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    public static int getPixelSize() {
        return pixelSize;
    }

    public static void setBoardHeight(int boardHeight) {
        GameActivity.boardHeight = boardHeight;
    }

    public static void setBoardWidth(int boardWidth) {
        GameActivity.boardWidth = boardWidth;
    }

    public static void setPixelSize(int pixelWidth) {
        pixelSize = pixelWidth / Board.BOARD_COLS;
    }

    public static ImageView getBoardLayout() {
        return boardLayout;
    }

    public static ImageView getFallingShapeLayout() {
        return fallingShapeLayout;
    }

    public static ImageView getNextShapeLayout() {
        return nextShapeLayout;
    }

    public static List<ImageView> getCustomShapeLayouts() {
        return customShapeLayouts;
    }

    public static ImageView getDeadBlocksLayout() {
        return deadBlocksLayout;
    }
}
