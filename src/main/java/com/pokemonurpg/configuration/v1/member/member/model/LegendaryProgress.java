package com.pokemonurpg.configuration.v1.member.member.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.LegendaryProgressInputDto;
import com.pokemonurpg.configuration.v1.site.section.model.Section;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "legendary_progress")
public class LegendaryProgress {

    @EmbeddedId
    LegendaryProgressKey id;

    @ManyToOne
    @MapsId("trainer_dbid")
    @JoinColumn(name="trainer_dbid", nullable=false)
    private Member trainer;

    @ManyToOne
    @MapsId("section_dbid")
    @JoinColumn(name="section_dbid", nullable=false)
    @JsonView(value = { MemberViews.Full.class })
    private Section section;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Integer value;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Date date;

    public LegendaryProgress() {
    }

    public LegendaryProgress(LegendaryProgressInputDto input, Member member, Section section) {
        this.update(input);
        this.id = new LegendaryProgressKey(input.getLogUrl(), member.getDbid(), section.getDbid());
        setTrainer(member);
        setSection(section);
    }

    public void update(LegendaryProgressInputDto input) {
        setValue(input.getValue());
        setDate(input.getDate());
    }

    public String getLogUrl() {
        return id.getLogUrl();
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
