package com.pokemonurpg.entities.v1.shared;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class NamedEntity extends IndexedEntity {
    @Column
    private String name;
}
