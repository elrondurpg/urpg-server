package com.pokemonurpg.dto.stats.input;

public class StatsPokemonInputDto {
    private Integer dbid;
    private Integer exp;
    private String hiddenPowerType;
    private String hiddenPowerLink;

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

    public String getHiddenPowerType() {
        return hiddenPowerType;
    }

    public void setHiddenPowerType(String hiddenPowerType) {
        this.hiddenPowerType = hiddenPowerType;
    }

    public String getHiddenPowerLink() {
        return hiddenPowerLink;
    }

    public void setHiddenPowerLink(String hiddenPowerLink) {
        this.hiddenPowerLink = hiddenPowerLink;
    }
}
