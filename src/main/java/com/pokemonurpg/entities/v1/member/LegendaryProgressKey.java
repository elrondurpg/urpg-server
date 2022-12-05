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
public class LegendaryProgressKey implements Serializable {

    @Column(name = "log_url")
    private String logUrl;

    @Column(name = "trainer_dbid")
    private Integer trainerDbid;
    
    @Column(name = "section_dbid")
    private Integer sectionDbid;
}
