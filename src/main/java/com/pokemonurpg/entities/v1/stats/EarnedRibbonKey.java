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
public class EarnedRibbonKey implements Serializable {

    @Column(name = "log_url")
    private String logUrl;

    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;
    
    @Column(name = "contest_type_dbid")
    private Integer generationDbid;

    @Column(name = "rank_dbid")
    private Integer rankDbid;

    @Column(name = "attribute_dbid")
    private Integer attributeDbid;
}
