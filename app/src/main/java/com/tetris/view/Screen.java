package com.tetris.view;

public class Screen {

    private Rectangle leftRegion;
    private Rectangle rightRegion;
    private Rectangle workingRegion;
    private Rectangle commandRegion;

    private Rectangle gameoverScreenBounds;
    private Rectangle gameScreenBounds;
    private Rectangle pauseButtonBounds;
    private Rectangle leftButtonBounds;
    private Rectangle rightButtonBounds;
    private Rectangle rotateButtonBounds;
    private Rectangle downButtonBounds;
    private Rectangle xButtonBounds;
    private Rectangle pauseMenuBounds;
    private Rectangle readyMenuBounds;
    private Rectangle homeMenuBounds;

    /*public GameScreen() {

        leftRegion = new Rectangle(0, 0, 60, 400);
        workingRegion = new Rectangle(60, 20, 200, 400);
        rightRegion = new Rectangle(260, 0, 60, 400);
        commandRegion = new Rectangle(0, 400, 320, 80);

        gameoverScreenBounds=new Rectangle(0, 0, 320, 480);
        gameScreenBounds=new Rectangle(0, 0, 320, 480);
        pauseButtonBounds=new Rectangle(5, 20, 50, 50);
        leftButtonBounds=new Rectangle(30, 425, 50, 50);
        rightButtonBounds=new Rectangle(240, 425, 50, 50);
        rotateButtonBounds=new Rectangle(100, 425, 50, 50);
        downButtonBounds=new Rectangle(170, 425, 50, 50);
        pauseMenuBounds=new Rectangle(100, 100, 160, 48);
        readyMenuBounds=new Rectangle(65, 100, 188, 70);
        homeMenuBounds=new Rectangle(80, 148, 160, 48);
        xButtonBounds=new Rectangle(128, 200, 50, 50);
    }*/

    /*
    public void update(float deltaTime) {
        Log.i(LOG_TAG, "update -- begin");
        List<TouchEvent> touchEvents = Gdx.input.getTouchEvents();
        Gdx.input.getKeyEvents();
        states.get(DroidsWorld.getInstance().getState()).update(touchEvents, deltaTime);
    }


    public void draw(float deltaTime) {
        Log.i(LOG_TAG, "draw -- begin");
        // draw the background
        Gdx.graphics.drawPixmap(Assets.gamescreen, gameScreenBounds.getX(), gameScreenBounds.getY());
        // render the game world.
        renderer.draw();
        // draw buttons
        Gdx.graphics.drawPixmap(Assets.buttons, leftButtonBounds.getX(), leftButtonBounds.getY(), 50, 50,
                leftButtonBounds.getWidth()+1, leftButtonBounds.getHeight()+1);  // left button
        Gdx.graphics.drawPixmap(Assets.buttons, rightButtonBounds.getX(), rightButtonBounds.getY(), 0, 50,
                rightButtonBounds.getWidth()+1, rightButtonBounds.getHeight()+1); // right button
        Gdx.graphics.drawPixmap(Assets.buttons, rotateButtonBounds.getX(), rotateButtonBounds.getY(), 50, 150,
                rotateButtonBounds.getWidth()+1, rotateButtonBounds.getHeight()+1); // rotate button
        Gdx.graphics.drawPixmap(Assets.buttons, downButtonBounds.getX(), downButtonBounds.getY(), 0, 150,
                downButtonBounds.getWidth()+1, downButtonBounds.getHeight()+1); // down button

        // draw the goal, score and level.
        TextStyle style = new TextStyle();
        style.setColor(0xffffffff);
        style.setTextSize(10);
        style.setAlign(TextStyle.Align.CENTER);
        Gdx.graphics.drawText("" + DroidsWorld.getInstance().getLevel(), 30 + leftRegion.getX(), 165 + leftRegion.getY(), style);
        Gdx.graphics.drawText("" + DroidsWorld.getInstance().getGoal(), 30 + leftRegion.getX(), 265 + leftRegion.getY(), style);
        Gdx.graphics.drawText("" + DroidsWorld.getInstance().getScore(), 30 + rightRegion.getX(), 265 + rightRegion.getY(), style);

        // draw the state specific element
        states.get(DroidsWorld.getInstance().getState()).draw();
    }*/
}
