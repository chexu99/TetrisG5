package com.tetris.utils;

import com.tetris.view.GameActivity;

public class UserSettings {

    public static int colorPalette;

    public static int  graphicsResolution = 2;

    public static int score;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserSettings.userName = userName;
    }

    public static String userName;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        UserSettings.score = score;
    }

    public static void changeResolution() {
        switch (graphicsResolution){
            case 1:
                GameActivity.setBoardHeight(3200);
                GameActivity.setBoardWidth(1600);
                GameActivity.setPixelSize(1600);
                break;
            case 2:
                GameActivity.setBoardHeight(1600);
                GameActivity.setBoardWidth(800);
                GameActivity.setPixelSize(800);
                break;
            case 3:
                GameActivity.setBoardHeight(800);
                GameActivity.setBoardWidth(400);
                GameActivity.setPixelSize(400);
                break;
        }
    }

    public static int getGraphicsResolution() {
        return graphicsResolution;
    }

    public static void setGraphicsResolution(int graphicsResolution) {
        UserSettings.graphicsResolution = graphicsResolution;
    }

}
