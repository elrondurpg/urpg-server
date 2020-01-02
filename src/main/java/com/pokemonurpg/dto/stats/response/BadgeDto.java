package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.trainer.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BadgeDto {
    private String gym;
    private String name;
    private String leader;
    private String url;
    private String date;
    private String league;

    private static final DateFormat MM_DD_YYYY = new SimpleDateFormat("MM-dd-yyyy");

    public BadgeDto(EarnedBadge earnedBadge) {
        if (earnedBadge != null) {
            Gym gym = earnedBadge.getGym();
            if (gym != null) {
                setGym(gym.getName());

                Badge badge = gym.getBadge();
                if (badge != null) {
                    setName(badge.getName());
                }

                Member owner = gym.getOwner();
                if (owner != null) {
                    setLeader(owner.getUsername());
                }

                setUrl(earnedBadge.getLogUrl());

                Date date = earnedBadge.getDate();
                if (date != null) {
                    setDate(MM_DD_YYYY.format(date));
                }

                GymLeague league = gym.getLeague();
                if (league != null) {
                    setLeague(league.getName());
                }
            }
        }
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String getLeague() {
        return league;
    }

    void setLeague(String league) {
        this.league = league;
    }
}
