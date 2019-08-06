package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypeMatchupKey implements Serializable
{
    @Column(name = "attack_type_dbid")
    int attackTypeDbid;

    @Column(name = "defend_type_dbid")
    int defendTypeDbid;

    public TypeMatchupKey() {

    }

    public TypeMatchupKey(int attackTypeDbid, int defendTypeDbid) {
        this.attackTypeDbid = attackTypeDbid;
        this.defendTypeDbid = defendTypeDbid;
    }

    public int getAttackTypeDbid() {
        return attackTypeDbid;
    }

    public void setAttackTypeDbid(int attackTypeDbid) {
        this.attackTypeDbid = attackTypeDbid;
    }

    public int getDefendTypeDbid() {
        return defendTypeDbid;
    }

    public void setDefendTypeDbid(int defendTypeDbid) {
        this.defendTypeDbid = defendTypeDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeMatchupKey that = (TypeMatchupKey) o;
        return attackTypeDbid == that.attackTypeDbid &&
                defendTypeDbid == that.defendTypeDbid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackTypeDbid, defendTypeDbid);
    }
}
