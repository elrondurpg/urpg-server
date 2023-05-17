package com.pokemonurpg.object;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "earned_badge")
public class EarnedBadge {

    @EmbeddedId
    EarnedBadgeKey id;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="gym_dbid", insertable = false, updatable = false)
    private Gym gym;

    @Column
    private Date date;

    @Column(name = "log_url")
    private String logUrl;

    public EarnedBadge() {
    }

    public EarnedBadgeKey getId() {
        return id;
    }

    public void setId(EarnedBadgeKey id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }
}
