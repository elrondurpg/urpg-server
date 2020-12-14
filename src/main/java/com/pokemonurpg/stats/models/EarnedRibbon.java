package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;

import javax.persistence.*;

@Entity
@Table(name = "earned_ribbon")
@JsonView(value = { View.MemberView.Pokemon.class })
public class EarnedRibbon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @OneToOne
    @JoinColumn(name = "pokemon_dbid")
    private OwnedPokemon pokemon;

    @OneToOne
    @JoinColumn(name = "rank_dbid")
    private ContestRank rank;

    @OneToOne
    @JoinColumn(name = "attribute_dbid")
    private ContestAttribute attribute;

    @Column
    private String url;

    @Column
    private Boolean spent = false;

    public EarnedRibbon() {
    }

    public EarnedRibbon (EarnedRibbonInputDto input, OwnedPokemon pokemon, ContestRank rank, ContestAttribute attribute) {
        this.update(input);
        setPokemon(pokemon);
        setRank(rank);
        setAttribute(attribute);
    }

    public void update(EarnedRibbonInputDto input) {
        setUrl(input.getUrl());
        setSpent(input.getSpent());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url != null) {
            this.url = url;
        }
    }

    public Boolean getSpent() {
        return spent;
    }

    public void setSpent(Boolean spent) {
        if (spent != null) {
            this.spent = spent;
        }
    }
}
