package com.pokemonurpg.contest.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContestComboKey implements Serializable {
    @Column(name = "first_attack_dbid")
    private Integer firstAttackDbid;

    @Column(name = "second_attack_dbid")
    private Integer secondAttackDbid;

    @Column(name = "generation_dbid")
    private Integer generationDbid;

    public ContestComboKey() {

    }

    public ContestComboKey(Integer firstAttackDbid, Integer secondAttackDbid, Integer generationDbid) {
        this.firstAttackDbid = firstAttackDbid;
        this.secondAttackDbid = secondAttackDbid;
        this.generationDbid = generationDbid;
    }

    public Integer getFirstAttackDbid() {
        return firstAttackDbid;
    }

    public Integer getSecondAttackDbid() {
        return secondAttackDbid;
    }

    public Integer getGenerationDbid() {
        return generationDbid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstAttackDbid == null) ? 0 : firstAttackDbid.hashCode());
        result = prime * result + ((generationDbid == null) ? 0 : generationDbid.hashCode());
        result = prime * result + ((secondAttackDbid == null) ? 0 : secondAttackDbid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContestComboKey other = (ContestComboKey) obj;
        if (firstAttackDbid == null) {
            if (other.firstAttackDbid != null)
                return false;
        } else if (!firstAttackDbid.equals(other.firstAttackDbid))
            return false;
        if (generationDbid == null) {
            if (other.generationDbid != null)
                return false;
        } else if (!generationDbid.equals(other.generationDbid))
            return false;
        if (secondAttackDbid == null) {
            if (other.secondAttackDbid != null)
                return false;
        } else if (!secondAttackDbid.equals(other.secondAttackDbid))
            return false;
        return true;
    }

    
}
