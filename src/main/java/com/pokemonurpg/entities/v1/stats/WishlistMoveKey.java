package com.pokemonurpg.entities.v1.stats;

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
public class WishlistMoveKey implements Serializable {

    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;

    @Column(name = "move_dbid")
    private Integer moveDbid;
}

