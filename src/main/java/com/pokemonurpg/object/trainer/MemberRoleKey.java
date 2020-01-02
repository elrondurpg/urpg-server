package com.pokemonurpg.object.trainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MemberRoleKey implements Serializable {

    @Column(name = "member_dbid")
    private Integer memberDbid;

    @Column(name = "role_dbid")
    private Integer roleDbid;

    public MemberRoleKey() {
    }

    public MemberRoleKey(Integer memberDbid, Integer roleDbid) {
        this.memberDbid = memberDbid;
        this.roleDbid = roleDbid;
    }

    public Integer getMemberDbid() {
        return memberDbid;
    }

    public void setMemberDbid(Integer memberDbid) {
        this.memberDbid = memberDbid;
    }

    public Integer getRoleDbid() {
        return roleDbid;
    }

    public void setRoleDbid(Integer roleDbid) {
        this.roleDbid = roleDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberRoleKey that = (MemberRoleKey) o;
        return Objects.equals(memberDbid, that.memberDbid) &&
                Objects.equals(roleDbid, that.roleDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberDbid, roleDbid);
    }
}
