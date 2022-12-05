package com.pokemonurpg.entities.v1.stats;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.entities.v1.contest.ContestRank;
import com.pokemonurpg.entities.v1.contest.ContestType;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "earned_ribbon")
@JsonView(value = { View.MemberView.Pokemon.class })
@Getter
@Setter
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
}
