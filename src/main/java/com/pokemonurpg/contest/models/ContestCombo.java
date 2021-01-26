package com.pokemonurpg.contest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.contest.input.ContestComboInputDto;

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

    @Column
    private Boolean overpowered;

    public ContestCombo() {

    }

    public ContestCombo(ContestComboInputDto input, Attack firstAttack, Attack secondAttack) {
        this.update(input);
        this.id = new ContestComboKey(firstAttack.getDbid(), secondAttack.getDbid(), input.getContestType());
        setFirstAttack(firstAttack);
        setSecondAttack(secondAttack);
    }

    public void update(ContestComboInputDto input) {
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

    public String getContestType() {
        return id.getContestType();
    }
}
