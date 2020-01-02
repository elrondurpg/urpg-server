package com.pokemonurpg.dto.battle;

public class BattleLeadPokemonDto {
    private String species;
    private Integer sequenceNo;
    private Integer position;

    public BattleLeadPokemonDto() {
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
