package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;

import javax.persistence.*;

@Entity
@Table(name = "earned_ribbon")
@JsonView(value = { View.MemberView.Pokemon.class })
public class EarnedRibbon {

    @EmbeddedId
    EarnedRibbonKey id;

    @OneToOne
    @MapsId("pokemon_dbid")
    @JoinColumn(name = "pokemon_dbid")
    private OwnedPokemon pokemon;

    @OneToOne
    @MapsId("rank_dbid")
    @JoinColumn(name = "rank_dbid")
    private ContestRank rank;

    @OneToOne
    @MapsId("attribute_dbid")
    @JoinColumn(name = "attribute_dbid")
    private ContestAttribute attribute;

    @OneToOne
    @MapsId("generation_dbid")
    @JoinColumn(name = "contest_type_dbid")
    private ContestType generation;

    @Column
    private Integer spent;

    @Column
    private Integer quantity;

    public EarnedRibbon() {
    }

    public EarnedRibbon (EarnedRibbonInputDto input, OwnedPokemon pokemon, ContestType generation, ContestRank rank, ContestAttribute attribute) {
        this.update(input);
        this.id = new EarnedRibbonKey(input.getLogUrl(), pokemon.getDbid(), generation.getDbid(), rank.getDbid(), attribute.getDbid());
        setPokemon(pokemon);
        setGeneration(generation);
        setRank(rank);
        setAttribute(attribute);
        if (spent == null) spent = 0;
        if (quantity == null) quantity = 0;
    }

    public void update(EarnedRibbonInputDto input) {
        setSpent(input.getSpent());
        setQuantity(input.getQuantity());
    }

    public String getLogUrl() {
        return id.getLogUrl();
    }

    public OwnedPokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(OwnedPokemon pokemon) {
        this.pokemon = pokemon;
    }

    public ContestRank getRank() {
        return rank;
    }

    public void setRank(ContestRank rank) {
        this.rank = rank;
    }

    public ContestAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ContestAttribute attribute) {
        this.attribute = attribute;
    }

    public Integer getSpent() {
        return spent;
    }

    public void setSpent(Integer spent) {
        if (spent != null) {
            this.spent = spent;
        }
    }

    public ContestType getGeneration() {
        return generation;
    }

    public void setGeneration(ContestType generation) {
        this.generation = generation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity != null) {
            this.quantity = quantity;
        }
    }

    
}
