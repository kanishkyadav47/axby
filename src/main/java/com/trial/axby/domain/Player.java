package com.trial.axby.domain;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    Integer id;

    private Color color;

    @Column(name = "name")
    String name;

    boolean isRegistered = false;

    protected Player(){}

    public Player(int id, Color color, String name) {
        this.color = color;
        this.name = name;
        this.id = id;
    }


    @Override
    public String toString() {
        return String.format(
                "Player[id=%d, Name ='%s']",
                id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void insert(int inStack){

    }

}
