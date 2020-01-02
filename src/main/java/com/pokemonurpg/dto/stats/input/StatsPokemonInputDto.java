package com.pokemonurpg.dto.stats.input;

public class StatsPokemonInputDto {
    private Integer dbid;
    private Integer exp;

    public StatsPokemonInputDto() {
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
}
