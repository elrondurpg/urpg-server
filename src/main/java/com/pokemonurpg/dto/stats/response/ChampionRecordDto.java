package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.ChampionRecord;
import com.pokemonurpg.object.Member;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChampionRecordDto {
    private String startDate;
    private String endDate;
    private String url;
    private String opponent;
    private String league;

    private static final DateFormat MM_DD_YYYY = new SimpleDateFormat("MM-dd-yyyy");

    public ChampionRecordDto (ChampionRecord record) {
        if (record != null) {
            Date startDate = record.getStartDate();
            if (startDate != null) {
                setStartDate(MM_DD_YYYY.format(startDate));
            }

            Date endDate = record.getEndDate();
            if (endDate != null) {
                setEndDate(MM_DD_YYYY.format(endDate));
            }

            setUrl(record.getLogUrl());

            Member opponent = record.getOpponent();
            if (opponent != null) {
                setOpponent(opponent.getUsername());
            }
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    String getLeague() {
        return league;
    }

    void setLeague(String league) {
        this.league = league;
    }
}
