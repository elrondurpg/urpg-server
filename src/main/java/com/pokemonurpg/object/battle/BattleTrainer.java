package com.pokemonurpg.object.battle;

import com.pokemonurpg.object.trainer.Member;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "battle_trainer")
public class BattleTrainer {

    @EmbeddedId
    BattleTrainerKey id;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="battle_dbid", insertable = false, updatable = false)
    private Battle battle;

    @Column
    private int team;

    public BattleTrainer() {
    }

    public BattleTrainerKey getId() {
        return id;
    }

    public void setId(BattleTrainerKey id) {
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

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }
}
