package com.homecredit.ph.recyclerviewactivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuperHeroesDto {
    SuperHeroesDto(){}
    @SerializedName("heroes")
    private List<SuperHero> heroes;

    public List<SuperHero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<SuperHero> heroes) {
        this.heroes = heroes;
    }
}
