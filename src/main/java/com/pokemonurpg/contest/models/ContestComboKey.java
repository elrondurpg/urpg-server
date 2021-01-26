package com.pokemonurpg.contest.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContestComboKey implements Serializable {
    @Column(name = "first_attack_dbid")
    private Integer firstAttackDbid;

    @Column(name = "second_attack_dbid")
    private Integer secondAttackDbid;

    @Column(name = "contest_type")
    private String contestType;

    public ContestComboKey() {

    }

    public ContestComboKey(Integer firstAttackDbid, Integer secondAttackDbid, String contestType) {
        this.firstAttackDbid = firstAttackDbid;
        this.secondAttackDbid = secondAttackDbid;
        this.contestType = contestType;
    }

    public Integer getFirstAttackDbid() {
        return firstAttackDbid;
    }

    public Integer getSecondAttackDbid() {
        return secondAttackDbid;
    }

    public String getContestType() {
        return contestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContestComboKey)) return false;
        ContestComboKey that = (ContestComboKey) o;
        return Objects.equals(getFirstAttackDbid(), that.getFirstAttackDbid()) &&
                Objects.equals(getSecondAttackDbid(), that.getSecondAttackDbid()) &&
                Objects.equals(getContestType(), that.getContestType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstAttackDbid(), getSecondAttackDbid(), getContestType());
    }
}
