package com.pokemonurpg.entities.v1;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypeMatchupKey implements Serializable
{
    @Column(name = "attack_type_dbid")
    private
    Integer attackTypeDbid;

    @Column(name = "defend_type_dbid")
    private
    Integer defendTypeDbid;

    public TypeMatchupKey() {

    }

    public TypeMatchupKey(int attackTypeDbid, int defendTypeDbid) {
        this.attackTypeDbid = attackTypeDbid;
        this.defendTypeDbid = defendTypeDbid;
    }

    public Integer getAttackTypeDbid() {
        return attackTypeDbid;
    }

    public Integer getDefendTypeDbid() {
        return defendTypeDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeMatchupKey that = (TypeMatchupKey) o;
        return attackTypeDbid.equals(that.attackTypeDbid) &&
                defendTypeDbid.equals(that.defendTypeDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackTypeDbid, defendTypeDbid);
    }
}
