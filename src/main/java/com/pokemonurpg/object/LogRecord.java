package com.pokemonurpg.object;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log_record")
public class LogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @OneToOne
    @JoinColumn(name = "member_dbid")
    private Member member;

    @Column
    private Date timestamp;

    @Column
    private String message;

    public LogRecord() {
    }

    public LogRecord(Member member, String message) {
        setMember(member);
        setMessage(message);
        setTimestamp(new Date());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
