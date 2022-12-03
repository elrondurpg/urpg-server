package com.pokemonurpg.entities.v3.attack;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contest_combo")
@Getter
@Setter
public class ContestComboEntity {

    @EmbeddedId
    ContestComboKeyEntity id;

    @ManyToOne
    @MapsId("first_attack_dbid")
    @JoinColumn(name = "first_attack_dbid")
    private AttackEntity firstAttack;

    @ManyToOne
    @MapsId("second_attack_dbid")
    @JoinColumn(name = "second_attack_dbid")
    private AttackEntity secondAttack;

    @OneToOne
    @MapsId("generation_dbid")
    @JoinColumn(name = "generation_dbid")
    @JsonView(value = { AttackViews.Full.class })
    private ContestType generation;

    @Column
    private Boolean overpowered;

    /*
    public ContestComboEntity() {

    }

    public ContestComboEntity(ContestComboInputDto input, Attack firstAttack, Attack secondAttack, ContestType generation) {
        this.update(input);
        this.id = new ContestComboKey(firstAttack.getDbid(), secondAttack.getDbid(), generation.getDbid());
        setFirstAttack(firstAttack);
        setSecondAttack(secondAttack);
        setGeneration(generation);
        if (overpowered == null) overpowered = false;
    }

    public void update(ContestComboInputDto input) {
        setOverpowered(input.getOverpowered());
    }*/

    
}
