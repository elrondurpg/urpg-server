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
@EqualsAndHashCode
@AllArgsConstructor
public class OwnedItemKey implements Serializable {

    @Column(name = "trainer_dbid")
    private Integer trainerDbid;

    @Column(name = "item_dbid")
    private Integer itemDbid;
}
