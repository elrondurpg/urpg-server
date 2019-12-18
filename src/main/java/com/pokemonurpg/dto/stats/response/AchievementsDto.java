package com.pokemonurpg.dto.stats.response;

import java.util.ArrayList;
import java.util.List;

public class AchievementsDto {
    private GymLeagueDto novice = new GymLeagueDto();
    private GymLeagueDto advanced = new GymLeagueDto();
    private List<EarnedLegendDto> claimedLegendaries = new ArrayList<>();
    private List<LegendaryProgressDto> unearnedLegendaries = new ArrayList<>();

    public GymLeagueDto getNovice() {
        return novice;
    }

    public void setNovice(GymLeagueDto novice) {
        this.novice = novice;
    }

    public GymLeagueDto getAdvanced() {
        return advanced;
    }

    public void setAdvanced(GymLeagueDto advanced) {
        this.advanced = advanced;
    }

    public List<EarnedLegendDto> getClaimedLegendaries() {
        return claimedLegendaries;
    }

    public void setClaimedLegendaries(List<EarnedLegendDto> claimedLegendaries) {
        this.claimedLegendaries = claimedLegendaries;
    }

    public void addClaimed(EarnedLegendDto legend) {
        claimedLegendaries.add(legend);
    }

    public List<LegendaryProgressDto> getUnearnedLegendaries() {
        return unearnedLegendaries;
    }

    public void setUnearnedLegendaries(List<LegendaryProgressDto> unearnedLegendaries) {
        this.unearnedLegendaries = unearnedLegendaries;
    }

    public void addUnearned(LegendaryProgressDto progress) {
        unearnedLegendaries.add(progress);
    }
}
