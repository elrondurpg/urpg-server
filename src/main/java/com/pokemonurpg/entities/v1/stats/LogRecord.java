package com.pokemonurpg.entities.v1.stats;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.shared.IndexedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "log_record")
@Getter
@Setter
public class LogRecord extends IndexedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @OneToOne
    @JoinColumn(name = "member_dbid")
    private Member member;

    @OneToOne
    @JoinColumn(name = "creator_dbid")
    private Member creator;

    @Column
    private Date timestamp;

    @Column
    private String message;

    public LogRecord() {
    }

    public LogRecord(Member member, Member creator, String message) {
        setMember(member);
        setCreator(creator);
        setMessage(message);
        setTimestamp(new Date());
    }
}
