package com.pokemonurpg.dto.stats.input;

import com.pokemonurpg.dto.stats.response.OwnedItemDto;

import java.util.List;

public class StatsInputDto {
    private Integer money;
    private String name;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private List<OwnedItemDto> items;

    public StatsInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public List<OwnedItemDto> getItems() {
        return items;
    }

    public void setItems(List<OwnedItemDto> items) {
        this.items = items;
    }
}
