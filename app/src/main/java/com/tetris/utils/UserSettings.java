package com.tetris.utils;

import com.tetris.view.GameActivity;

public class UserSettings {

    private UserSettings(){}

    private static int graphicsResolution = 2;

    private static String username;

    private static int score;

    private static int gamma;

    public static void changeResolution() {
        switch (graphicsResolution) {
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
            default:
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

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserSettings.username = username;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        UserSettings.score = score;
    }

    public static int getGamma() {
        return gamma;
    }

    public static void setGamma(int gamma) {
        UserSettings.gamma = gamma;
    }
}
