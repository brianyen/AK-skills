package com.example.quiz.model;

import jakarta.persistence.*;

@Entity
public class GameCharacter {

    @Id
    public String id;
    public String name;
    public String portrait;

    protected GameCharacter() {}

    public GameCharacter(String id, String name, String portrait) {
        this.id = id;
        this.name = name;
        this.portrait = id;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPortrait() { return portrait; }
}