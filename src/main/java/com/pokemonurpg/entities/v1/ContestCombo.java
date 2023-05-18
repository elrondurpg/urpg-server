package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.configuration.v1.attacks.ContestComboRequest;

import javax.persistence.*;

@Entity
@Table(name = "contest_combo")
public class ContestCombo {

    @EmbeddedId
    ContestComboKey id;

    @ManyToOne
    @MapsId("first_attack_dbid")
    @JoinColumn(name = "first_attack_dbid")
    @JsonIgnoreProperties({ "dbid", "type", "description", "power", "accuracy", "pp",
            "category", "target", "contact", "snatch", "substitute", "sheerForce",
            "magicCoat", "rseContestMoveType", "rseContestAttribute", "orasContestMoveType",
            "orasContestAttribute", "dppContestMoveType", "dppContestAttribute", "advContestMoveType",
            "advContestAttribute", "tm", "pokemon", "contestCombos" })
    private Attack firstAttack;

    @ManyToOne
    @MapsId("second_attack_dbid")
    @JoinColumn(name = "second_attack_dbid")
    @JsonIgnoreProperties({ "dbid", "type", "description", "power", "accuracy", "pp",
            "category", "target", "contact", "snatch", "substitute", "sheerForce",
            "magicCoat", "rseContestMoveType", "rseContestAttribute", "orasContestMoveType",
            "orasContestAttribute", "dppContestMoveType", "dppContestAttribute", "advContestMoveType",
            "advContestAttribute", "tm", "pokemon", "contestCombos" })
    private Attack secondAttack;

    @OneToOne
    @MapsId("generation_dbid")
    @JoinColumn(name = "generation_dbid")
    private ContestGeneration generation;

    @Column
    private Boolean overpowered;

    public ContestCombo() {

    }

    public ContestCombo(ContestComboRequest input, Attack firstAttack, Attack secondAttack, ContestGeneration generation) {
        this.update(input);
        this.id = new ContestComboKey(firstAttack.getDbid(), secondAttack.getDbid(), generation.getDbid());
        setFirstAttack(firstAttack);
        setSecondAttack(secondAttack);
        setGeneration(generation);
        if (overpowered == null) overpowered = false;
    }

    public void update(ContestComboRequest input) {
        setOverpowered(input.getOverpowered());
    }

    public Attack getFirstAttack() {
        return firstAttack;
    }

    public void setFirstAttack(Attack firstAttack) {
        this.firstAttack = firstAttack;
    }

    public Attack getSecondAttack() {
        return secondAttack;
    }

    public void setSecondAttack(Attack secondAttack) {
        this.secondAttack = secondAttack;
    }

    public Boolean getOverpowered() {
        return overpowered;
    }

    public void setOverpowered(Boolean overpowered) {
        if (overpowered != null) {
            this.overpowered = overpowered;
        }
    }

    public ContestGeneration getGeneration() {
        return generation;
    }

    public void setGeneration(ContestGeneration generation) {
        this.generation = generation;
    }

    
}
