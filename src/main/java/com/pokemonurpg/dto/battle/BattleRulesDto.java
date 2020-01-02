package com.pokemonurpg.dto.battle;

import java.util.List;

public class BattleRulesDto {
    private String battleGeneration;
    private String battleType;
    private String sendType;
    private String teamType;
    private Integer teamSize;
    private Boolean ohkoClause;
    private Boolean sleepClause;
    private Boolean freezeClause;
    private Boolean accuracyClause;
    private Boolean evasionClause;
    private Boolean speciesClause;
    private Boolean itemsAllowed;
    private Boolean itemClause;
    private Boolean megaClause;
    private Boolean zmoveClause;
    private Boolean dynamaxClause;
    private Boolean legendClause;
    private String permWeather;
    private String permTerrain;
    private Boolean basicsClause;
    private Boolean randomClause;
    private Boolean inversionClause;
    private Boolean skyClause;
    private Boolean gameboyClause;
    private Boolean wonderLauncherClause;
    private List<BattleTrainerDto> trainers;
    private String referee;

    public BattleRulesDto() {
    }

    public String getBattleGeneration() {
        return battleGeneration;
    }

    public void setBattleGeneration(String battleGeneration) {
        this.battleGeneration = battleGeneration;
    }

    public String getBattleType() {
        return battleType;
    }

    public void setBattleType(String battleType) {
        this.battleType = battleType;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public Boolean getOhkoClause() {
        return ohkoClause;
    }

    public void setOhkoClause(Boolean ohkoClause) {
        this.ohkoClause = ohkoClause;
    }

    public Boolean getSleepClause() {
        return sleepClause;
    }

    public void setSleepClause(Boolean sleepClause) {
        this.sleepClause = sleepClause;
    }

    public Boolean getFreezeClause() {
        return freezeClause;
    }

    public void setFreezeClause(Boolean freezeClause) {
        this.freezeClause = freezeClause;
    }

    public Boolean getAccuracyClause() {
        return accuracyClause;
    }

    public void setAccuracyClause(Boolean accuracyClause) {
        this.accuracyClause = accuracyClause;
    }

    public Boolean getEvasionClause() {
        return evasionClause;
    }

    public void setEvasionClause(Boolean evasionClause) {
        this.evasionClause = evasionClause;
    }

    public Boolean getSpeciesClause() {
        return speciesClause;
    }

    public void setSpeciesClause(Boolean speciesClause) {
        this.speciesClause = speciesClause;
    }

    public Boolean getItemsAllowed() {
        return itemsAllowed;
    }

    public void setItemsAllowed(Boolean itemsAllowed) {
        this.itemsAllowed = itemsAllowed;
    }

    public Boolean getItemClause() {
        return itemClause;
    }

    public void setItemClause(Boolean itemClause) {
        this.itemClause = itemClause;
    }

    public Boolean getMegaClause() {
        return megaClause;
    }

    public void setMegaClause(Boolean megaClause) {
        this.megaClause = megaClause;
    }

    public Boolean getZmoveClause() {
        return zmoveClause;
    }

    public void setZmoveClause(Boolean zmoveClause) {
        this.zmoveClause = zmoveClause;
    }

    public Boolean getDynamaxClause() {
        return dynamaxClause;
    }

    public void setDynamaxClause(Boolean dynamaxClause) {
        this.dynamaxClause = dynamaxClause;
    }

    public Boolean getLegendClause() {
        return legendClause;
    }

    public void setLegendClause(Boolean legendClause) {
        this.legendClause = legendClause;
    }

    public String getPermWeather() {
        return permWeather;
    }

    public void setPermWeather(String permWeather) {
        this.permWeather = permWeather;
    }

    public String getPermTerrain() {
        return permTerrain;
    }

    public void setPermTerrain(String permTerrain) {
        this.permTerrain = permTerrain;
    }

    public Boolean getBasicsClause() {
        return basicsClause;
    }

    public void setBasicsClause(Boolean basicsClause) {
        this.basicsClause = basicsClause;
    }

    public Boolean getRandomClause() {
        return randomClause;
    }

    public void setRandomClause(Boolean randomClause) {
        this.randomClause = randomClause;
    }

    public Boolean getInversionClause() {
        return inversionClause;
    }

    public void setInversionClause(Boolean inversionClause) {
        this.inversionClause = inversionClause;
    }

    public Boolean getSkyClause() {
        return skyClause;
    }

    public void setSkyClause(Boolean skyClause) {
        this.skyClause = skyClause;
    }

    public Boolean getGameboyClause() {
        return gameboyClause;
    }

    public void setGameboyClause(Boolean gameboyClause) {
        this.gameboyClause = gameboyClause;
    }

    public Boolean getWonderLauncherClause() {
        return wonderLauncherClause;
    }

    public void setWonderLauncherClause(Boolean wonderLauncherClause) {
        this.wonderLauncherClause = wonderLauncherClause;
    }

    public List<BattleTrainerDto> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<BattleTrainerDto> trainers) {
        this.trainers = trainers;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }
}
