package com.tetris.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tetris.R;
import com.tetris.model.Board;
import com.tetris.model.events.MovementEvents;
import com.tetris.view.layout_painting.BlockedBlocksLayout;
import com.tetris.view.layout_painting.BoardLayout;
import com.tetris.view.layout_painting.FallingShapeLayout;
import com.tetris.view.layout_painting.NextShapeLayout;

public class GameActivity extends Activity {

    boolean stopped = false;

    private static int boardHeight = 800; //Max quality = 6400 -> Laser-mode = 20
    private static int boardWidth = 400; //Max quality = 3200 -> Laser-mode = 10
    private static int pixelSize = boardWidth / Board.BOARD_COLS;
    private final Handler handler = new Handler();

    //Board values
    int speed = 50;

    private static ImageView boardLayout;
    private static ImageView fallingShapeLayout;
    private static ImageView nextShapeLayout;
    private static ImageView deadBlocksLayout;

    private BoardLayout boardLay;
    private FallingShapeLayout fallingLay;
    private NextShapeLayout nextLay;
    private BlockedBlocksLayout blockedLay;

    private TextView scoreText;

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
        boardLayout = findViewById(R.id.game_board);
        boardLay = new BoardLayout();

        //Falling shape
        fallingShapeLayout = findViewById(R.id.falling_shape);
        fallingLay = new FallingShapeLayout();

        //Score
        ConstraintLayout scoreLayout = findViewById(R.id.top_board);
        scoreText =  findViewById(R.id.score_text_view);

        //Next shape
        nextShapeLayout = findViewById(R.id.next_shape);
        nextLay = new NextShapeLayout();


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
            Board.getActionList().clear(); //Clear actions list
        }
        //Paint fallingShape Layout
        fallingLay.paintFallingShape(this.getResources());
        //Paint next shape on left side
        nextLay.paintNextShape(this.getResources());
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

    public static ImageView getDeadBlocksLayout() {
        return deadBlocksLayout;
    }
}
