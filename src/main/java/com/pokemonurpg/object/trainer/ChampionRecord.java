package com.pokemonurpg.object.trainer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "champion_record")
public class ChampionRecord {

    @EmbeddedId
    ChampionRecordKey id;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="league_dbid", insertable = false, updatable = false)
    private GymLeague league;

    @OneToOne
    @JoinColumn(name = "opponent_dbid")
    private Member opponent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "log_url")
    private String logUrl;

    public ChampionRecord() {
    }

    public ChampionRecordKey getId() {
        return id;
    }

    public void setId(ChampionRecordKey id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public GymLeague getLeague() {
        return league;
    }

    public void setLeague(GymLeague league) {
        this.league = league;
    }

    public Member getOpponent() {
        return opponent;
    }

    public void setOpponent(Member opponent) {
        this.opponent = opponent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }
}
