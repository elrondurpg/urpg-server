package com.pokemonurpg.dto.stats;

import com.pokemonurpg.object.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StatsDto {
    private String name;
    private int money;
    private int wins;
    private int losses;
    private int draws;
    private String joinDate;
    private List<OwnedPokemonBriefDto> pokemon;
    private List<OwnedItemDto> items;
    private List<String> roles = new ArrayList<>();
    private AchievementsDto achievements = new AchievementsDto();;

    private static final DateFormat MM_DD_YYYY = new SimpleDateFormat("MM-dd-yyyy");

    public StatsDto() {
    }

    public StatsDto(Member trainer) {
        if (trainer != null) {
            setName(trainer.getUsername());
            setMoney(trainer.getMoney());
            setWins(trainer.getWins());
            setLosses(trainer.getLosses());
            setDraws(trainer.getDraws());
            if (trainer.getJoinDate() != null) {
                setJoinDate(MM_DD_YYYY.format(trainer.getJoinDate()));
            }

            List<MemberRole> memberRoles = trainer.getRoles();
            if (memberRoles != null) {
                for (MemberRole role : memberRoles) {
                    if (role.getRole() != null) {
                        roles.add(role.getRole().getName());
                    }
                }
            }

            List<EarnedBadge> badges = trainer.getBadges();
            if (badges != null) {
                for (EarnedBadge badge : badges) {
                    BadgeDto dto = new BadgeDto(badge);
                    if ("novice".equalsIgnoreCase(dto.getLeague())) {
                        achievements.getNovice().addBadge(dto);
                    }
                    else if ("advanced".equalsIgnoreCase(dto.getLeague())) {
                        achievements.getAdvanced().addBadge(dto);
                    }
                }
            }

            List<ChampionRecord> championRecords = trainer.getChampionRecords();
            if (championRecords != null) {
                for (ChampionRecord record : championRecords) {
                    ChampionRecordDto dto = new ChampionRecordDto(record);
                    if ("novice".equalsIgnoreCase(dto.getLeague())) {
                        achievements.getNovice().setChampion(dto);
                    }
                    else if ("advanced".equalsIgnoreCase(dto.getLeague())) {
                        achievements.getAdvanced().setChampion(dto);
                    }
                }
            }

            List<LegendaryProgressDto> allProgressDtos = new ArrayList<>();
            List<EarnedLegendary> earnedLegendaries = trainer.getEarnedLegendaries();
            if (earnedLegendaries != null) {
                for (EarnedLegendary legend : earnedLegendaries) {
                    EarnedLegendDto dto = new EarnedLegendDto(legend);
                    achievements.addClaimed(dto);

                    LegendaryProgressDto progressDto = getLegendaryProgressDto(allProgressDtos, legend.getSection());
                    if (progressDto != null) {
                        progressDto.addClaimed(dto.getTier());
                    }
                }
            }

            List<LegendaryProgress> allProgress = trainer.getLegendaryProgress();
            if (allProgress != null) {
                for (LegendaryProgress progress : allProgress) {
                    Section section = progress.getSection();
                    LegendaryProgressDto dto = getLegendaryProgressDto(allProgressDtos, section);
                    if (dto != null) {
                        dto.addProgress(progress);
                    }
                }
            }

            achievements.setUnearnedLegendaries(allProgressDtos);
        }
    }

    private LegendaryProgressDto getLegendaryProgressDto(List<LegendaryProgressDto> allProgressDtos, Section section) {
        if (allProgressDtos != null) {
            if (section != null) {
                String sectionName = section.getName();
                for (LegendaryProgressDto dto : allProgressDtos) {
                    if (dto.getSection().equalsIgnoreCase(dto.getSection())) {
                        return dto;
                    }
                }
                LegendaryProgressDto dto = new LegendaryProgressDto(sectionName);
                allProgressDtos.add(dto);
                return dto;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public List<OwnedPokemonBriefDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<OwnedPokemonBriefDto> pokemon) {
        this.pokemon = pokemon;
    }

    public List<OwnedItemDto> getItems() {
        return items;
    }

    public void setItems(List<OwnedItemDto> items) {
        this.items = items;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public AchievementsDto getAchievements() {
        return achievements;
    }

    public void setAchievements(AchievementsDto achievements) {
        this.achievements = achievements;
    }
}
