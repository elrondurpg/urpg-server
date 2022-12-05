package com.pokemonurpg.entities.v1.attack;

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
import com.pokemonurpg.configuration.v1.attack.attack.input.ContestComboInputDto;
import com.pokemonurpg.entities.v1.contest.ContestType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contest_combo")
@Getter
@Setter
public class ContestCombo {

    @EmbeddedId
    ContestComboKey id;

    @ManyToOne
    @MapsId("first_attack_dbid")
    @JoinColumn(name = "first_attack_dbid")
    private Attack firstAttack;

    @ManyToOne
    @MapsId("second_attack_dbid")
    @JoinColumn(name = "second_attack_dbid")
    private Attack secondAttack;

    @OneToOne
    @MapsId("generation_dbid")
    @JoinColumn(name = "generation_dbid")
    @JsonView(value = { AttackViews.Full.class })
    private ContestType generation;

    @Column
    private Boolean overpowered;

    public ContestCombo() {

    }

    public ContestCombo(ContestComboInputDto input, Attack firstAttack, Attack secondAttack, ContestType generation) {
        this.update(input);
        this.id = new ContestComboKey(firstAttack.getDbid(), secondAttack.getDbid(), generation.getDbid());
        setFirstAttack(firstAttack);
        setSecondAttack(secondAttack);
        setGeneration(generation);
        if (overpowered == null) overpowered = false;
    }

    public void update(ContestComboInputDto input) {
        setOverpowered(input.getOverpowered());
    }

    
}
