package com.pokemonurpg.entities.v1.pokemon;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "type_matchup")
@Getter
@Setter
public class TypeMatchup {
    @EmbeddedId
    private
    TypeMatchupKey id;

    @ManyToOne
    @MapsId("attack_type_dbid")
    @JoinColumn(name = "attack_type_dbid")
    private Type attackType;

    @ManyToOne
    @MapsId("defend_type_dbid")
    @JoinColumn(name = "defend_type_dbid")
    private Type defendType;

    @Column
    private
    Double multiplier;
}
