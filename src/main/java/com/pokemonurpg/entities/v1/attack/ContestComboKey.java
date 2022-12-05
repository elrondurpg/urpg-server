package com.pokemonurpg.entities.v1.attack;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ContestComboKey implements Serializable {
    @Column(name = "first_attack_dbid")
    private Integer firstAttackDbid;

    @Column(name = "second_attack_dbid")
    private Integer secondAttackDbid;

    @Column(name = "generation_dbid")
    private Integer generationDbid;    
}
