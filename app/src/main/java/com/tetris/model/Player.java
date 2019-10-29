package com.tetris.model;

public class Player {
    Integer id;
    String nombre;
    Integer score;

    public Player(Integer id, String nombre, Integer score) {
        this.id = id;
        this.nombre = nombre;
        this.score = score;
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
}
