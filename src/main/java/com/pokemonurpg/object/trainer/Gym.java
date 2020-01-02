package com.pokemonurpg.object.trainer;

import javax.persistence.*;

@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    private Member owner;

    @OneToOne
    @JoinColumn(name = "league_dbid")
    private GymLeague league;

    @OneToOne
    @JoinColumn(name = "badge_dbid")
    private Badge badge;

    public Gym() {
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public GymLeague getLeague() {
        return league;
    }

    public void setLeague(GymLeague league) {
        this.league = league;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
