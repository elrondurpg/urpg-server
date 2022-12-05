package com.pokemonurpg.entities.v1.pokemon;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class TypeMatchupKey implements Serializable
{
    @Column(name = "attack_type_dbid")
    private
    Integer attackTypeDbid;

    @Column(name = "defend_type_dbid")
    private
    Integer defendTypeDbid;
}
