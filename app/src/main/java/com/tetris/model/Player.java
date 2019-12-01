package com.tetris.model;

public class Player {
    Integer id;
    String nombre;
    Integer score;
    byte[] img;


    public Player(Integer id, String nombre, Integer score, byte[] img) {
        this.id = id;
        this.nombre = nombre;
        this.score = score;
        this.img=img;
    }

    public Player() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }


}
