package com.pokemonurpg.entities.v1.member;

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
@AllArgsConstructor
@EqualsAndHashCode
public class EliteFourVictoryKey implements Serializable {

    @Column(name = "challenger_dbid")
    private Integer challengerDbid;

    @Column(name = "defender_dbid")
    private Integer defenderDbid;
}
