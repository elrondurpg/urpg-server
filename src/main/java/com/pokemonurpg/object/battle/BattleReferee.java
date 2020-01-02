package com.pokemonurpg.object.battle;

import com.pokemonurpg.object.trainer.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "battle_referee")
public class BattleReferee {

    @EmbeddedId
    BattleRefereeKey id;

    @ManyToOne
    @JoinColumn(name="referee_dbid", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="battle_dbid", insertable = false, updatable = false)
    private Battle battle;

    @Column(name = "turns_reffed")
    private int turnsReffed;

    public BattleReferee() {
    }

    public BattleRefereeKey getId() {
        return id;
    }

    public void setId(BattleRefereeKey id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public int getTurnsReffed() {
        return turnsReffed;
    }

    public void setTurnsReffed(int turnsReffed) {
        this.turnsReffed = turnsReffed;
    }
}
