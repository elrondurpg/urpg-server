package com.pokemonurpg.object.trainer;

import com.pokemonurpg.object.pokemon.ContestAttribute;
import com.pokemonurpg.object.pokemon.ContestRank;

import javax.persistence.*;

@Entity
@Table(name = "earned_ribbon")
public class EarnedRibbon {

    @EmbeddedId
    EarnedRibbonKey id;

    @ManyToOne
    @MapsId("pokemon_dbid")
    @JoinColumn(name="pokemon_dbid", insertable = false, updatable = false)
    private OwnedPokemon pokemon;

    @ManyToOne
    @MapsId("rank_dbid")
    @JoinColumn(name="rank_dbid", insertable = false, updatable = false)
    private ContestRank rank;

    @ManyToOne
    @MapsId("attribute_dbid")
    @JoinColumn(name="attribute_dbid", insertable = false, updatable = false)
    private ContestAttribute attribute;

    @Column
    private String url;

    public EarnedRibbon() {
    }

    public EarnedRibbonKey getId() {
        return id;
    }

    public void setId(EarnedRibbonKey id) {
        this.id = id;
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
        this.url = url;
    }
}
