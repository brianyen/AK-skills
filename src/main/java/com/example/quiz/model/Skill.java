package com.example.quiz.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Id
    private String id;
    private String name;

    @Column(length = 2000)
    private String description;

    private String type;
    private String activation;

    private String rangeExtension;
    private String icon;

    private Integer initialSp;
    private Integer spCost;
    private Integer duration;

    @ManyToMany
    @JoinTable(
            name = "skill_character",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<GameCharacter> characters = new HashSet<>();

    protected Skill() {}

    public Skill(
            String id,
            String name,
            String description,
            String type,
            String activation,
            String rangeExtension,
            String icon,
            Integer initialSp,
            Integer spCost,
            Integer duration
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.activation = activation;
        this.rangeExtension = rangeExtension;
        this.icon = icon;
        this.initialSp = initialSp;
        this.spCost = spCost;
        this.duration = duration;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getIcon() { return icon; }
    public Set<GameCharacter> getCharacters() { return characters; }
}