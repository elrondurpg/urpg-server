package com.pokemonurpg.entities.v1.site;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Flag extends NamedEntity {

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String value;

    @JsonIgnore
    public boolean isTrue() {
        return "TRUE".equalsIgnoreCase(value);
    }
}
