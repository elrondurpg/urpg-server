package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "earned_badge")
@JsonView(value = { View.MemberView.Summary.class })
public class EarnedBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @ManyToOne
    @JoinColumn(name="trainer_dbid")
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories" })
    private Member member;

    @Column(name = "trainer_name")
    private String trainerName;

    @OneToOne
    @JoinColumn(name = "type_dbid")
    private Type type;

    @Column(name = "league_dbid")
    private Integer leagueDbid;

    @ManyToOne
    @MapsId("gym_dbid")
    @JoinColumn(name="gym_dbid")
    @JsonIgnoreProperties("winners")
    private Gym gym;

    @Column
    private Date date;

    @Column(name = "log_url")
    private String logUrl;

    public EarnedBadge() {
    }

    public EarnedBadge(EarnedBadgeInputDto input, Member member, Gym gym) {
        this.update(input);
        //this.id = new EarnedBadgeKey(member.getDbid(), gym.getDbid());
        setMember(member);
        setGym(gym);
    }

    public void update(EarnedBadgeInputDto input) {
        setDate(input.getDate());
        setLogUrl(input.getLogUrl());
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
        if (date != null) {
            this.date = date;
        }
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        if (logUrl != null) {
            this.logUrl = logUrl;
        }
    }
}
