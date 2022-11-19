package com.pokemonurpg.gym.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.gym.badge.model.Badge;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.stats.models.GymVictory;
import com.pokemonurpg.stats.models.OwnedPokemon;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Gym implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "badge_dbid")
    private Badge badge;

    @OneToOne
    @JoinColumn(name = "type_dbid")
    private Type type;

    @OneToMany(mappedBy="gym")
    @JsonIgnoreProperties({ "gym"})
    private List<GymVictory> victories = new ArrayList<>();

    @ManyToMany(
            targetEntity= OwnedPokemon.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="GYM_POKEMON",
            joinColumns=@JoinColumn(name="GYM_DBID"),
            inverseJoinColumns=@JoinColumn(name="POKEMON_DBID")
    )
    private Set<OwnedPokemon> pokemon = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "term_dbid")
    @JsonIgnoreProperties({"gym" })
    private GymOwnershipTerm currentOwnerRecord;

    public Gym() {
    }

    public Gym (GymInputDto input) {
        this.update(input);
    }

    public void update(GymInputDto input) {
        setName(input.getName());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        if (badge != null) {
            this.badge = badge;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type != null) {
            this.type = type;
        }
    }

    public GymOwnershipTerm getCurrentOwnerRecord() {
        return currentOwnerRecord;
    }

    public void setCurrentOwnerRecord(GymOwnershipTerm currentOwnerRecord) {
        this.currentOwnerRecord = currentOwnerRecord;
    }

    public List<GymVictory> getVictories() {
        return victories;
    }

    public void setVictories(List<GymVictory> victories) {
        this.victories = victories;
    }

    public Set<OwnedPokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<OwnedPokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
