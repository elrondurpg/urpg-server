package com.pokemonurpg.dto.stats.response;

import java.util.ArrayList;
import java.util.List;

public class GymLeagueDto {
    private List<BadgeDto> badges = new ArrayList<>();
    private ChampionRecordDto champion;

    public List<BadgeDto> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeDto> badges) {
        this.badges = badges;
    }

    public void addBadge(BadgeDto dto) {
        badges.add(dto);
    }

    public ChampionRecordDto getChampion() {
        return champion;
    }

    public void setChampion(ChampionRecordDto champion) {
        this.champion = champion;
    }
}
