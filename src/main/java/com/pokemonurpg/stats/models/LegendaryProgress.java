package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "legendary_progress")
@JsonView(value = { View.MemberView.Summary.class })
public class LegendaryProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories", "championVictories",
            "championTerms", "eliteFourTerms" })
    private Member trainer;

    @ManyToOne
    @JoinColumn(name="section_dbid", nullable=false)
    private Section section;

    @Column
    private Integer value;

    @Column
    private Date date;

    @Column(name = "log_url")
    private String logUrl;

    public LegendaryProgress() {
    }

    public LegendaryProgress(LegendaryProgressInputDto input, Member member, Section section) {
        this.update(input);
        setTrainer(member);
        setSection(section);
    }

    public void update(LegendaryProgressInputDto input) {
        setLogUrl(input.getLogUrl());
        setValue(input.getValue());
        setDate(input.getDate());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        if (logUrl != null) {
            this.logUrl = logUrl;
        }
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        this.trainer = trainer;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        if (value != null) {
            this.value = value;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
        }
    }
}
