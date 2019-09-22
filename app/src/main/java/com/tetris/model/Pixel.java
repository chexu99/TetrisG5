package com.tetris.model;

public class Pixel {

    protected int x; //X pos in Board
    protected int y; //Y pos in Board

    protected int width; //Width of pixel (1 = normal)
    protected int height; //Height ofpixel (1 = normal)

    //Constructors
    public Pixel() {
    }

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pixel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //Checks if this collides with other
    public boolean collide(Pixel other) {
        return this.contains(other.x, other.y);
    }

    //Checks if a pixel is contained by another
    public boolean contains(int x, int y) {
        return ((x >= this.x) &&
                (x < (this.x + 1)) &&
                (y >= this.y) &&
                (y < (this.y + 1)));
    }

    //Move position relative
    public void moveBy(int extraX, int extraY) {
        this.x += extraX;
        this.y += extraY;
    }

    //Getters & Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
