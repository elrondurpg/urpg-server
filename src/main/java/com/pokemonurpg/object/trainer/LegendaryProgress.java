package com.pokemonurpg.object.trainer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "legendary_progress")
public class LegendaryProgress {

    @Id
    @Column(name = "log_url")
    private String logUrl;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    private Member trainer;

    @ManyToOne
    @JoinColumn(name="section_dbid", nullable=false)
    private Section section;

    @Column
    private int value;

    @Column
    private Date date;

    public LegendaryProgress() {
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
