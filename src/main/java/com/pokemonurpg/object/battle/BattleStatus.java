package com.pokemonurpg.object.battle;

import javax.persistence.*;

@Entity
public class BattleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String label;

    @OneToOne
    @JoinColumn(name = "battle_dbid")
    private Battle battle;

    @Column
    private int duration;

    @Column(name = "max_duration")
    private int maxDuration;

    public BattleStatus() {}
    public BattleStatus(String name) {
        this.label = name;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String toString() {
        return "{ \"dbid\": " + dbid + ",\n\"label\": \"" + label + "\"}";
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }
}
