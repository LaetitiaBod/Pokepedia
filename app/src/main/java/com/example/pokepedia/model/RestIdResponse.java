package com.example.pokepedia.model;

public class RestIdResponse {

    private Integer id;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer base_experience;
    private Sprite sprites;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public Sprite getSprites() {
        return sprites;
    }
}
