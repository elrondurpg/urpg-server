package com.pokemonurpg.entities.v1.member;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.LegendaryProgressInputDto;
import com.pokemonurpg.entities.v1.site.Section;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "legendary_progress")
@Getter
@Setter
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
}
