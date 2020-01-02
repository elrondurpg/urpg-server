package com.pokemonurpg.object.battle;

import com.pokemonurpg.dto.battle.BattleRulesDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long dbid;

    @Column
    private String state;

    @OneToOne
    @JoinColumn(name = "battle_generation")
    private BattleGeneration battleGeneration;

    @OneToOne
    @JoinColumn(name = "battle_type")
    private BattleType battleType;

    @OneToOne
    @JoinColumn(name = "send_type")
    private SendType sendType;

    @OneToOne
    @JoinColumn(name = "team_type")
    private TeamType teamType;

    @OneToOne
    @JoinColumn(name = "perm_weather")
    private Weather permWeather;

    @OneToOne
    @JoinColumn(name = "perm_terrain")
    private Terrain permTerrain;

    @Column(name = "team_size")
    private int teamSize;

    @Column(name = "ohko_clause")
    private boolean ohkoClause;

    @Column(name = "sleep_clause")
    private boolean sleepClause;

    @Column(name = "freeze_clause")
    private boolean freezeClause;

    @Column(name = "accuracy_clause")
    private boolean accuracyClause;

    @Column(name = "evasion_clause")
    private boolean evasionClause;

    @Column(name = "species_clause")
    private boolean speciesClause;

    @Column(name = "items_allowed")
    private boolean itemsAllowed;

    @Column(name = "item_clause")
    private boolean itemClause;

    @Column(name = "mega_clause")
    private boolean megaClause;

    @Column(name = "zmove_clause")
    private boolean zmoveClause;

    @Column(name = "dynamax_clause")
    private boolean dynamaxClause;

    @Column(name = "legend_clause")
    private boolean legendClause;

    @Column(name = "basics_clause")
    private boolean basicsClause;

    @Column(name = "random_clause")
    private boolean randomClause;

    @Column(name = "inversion_clause")
    private boolean inversionClause;

    @Column(name = "sky_clause")
    private boolean skyClause;

    @Column(name = "gameboy_clause")
    private boolean gameboyClause;

    @Column(name = "wonder_launcher_clause")
    private boolean wonderLauncherClause;

    @OneToMany(mappedBy="battle")
    private List<BattleStatus> statuses;

    @OneToMany(mappedBy="battle")
    private List<BattleTrainer> trainers;

    public Battle() {
    }

    public long getDbid() {
        return dbid;
    }

    public void setDbid(long dbid) {
        this.dbid = dbid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BattleGeneration getBattleGeneration() {
        return battleGeneration;
    }

    public void setBattleGeneration(BattleGeneration battleGeneration) {
        this.battleGeneration = battleGeneration;
    }

    public BattleType getBattleType() {
        return battleType;
    }

    public void setBattleType(BattleType battleType) {
        this.battleType = battleType;
    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public void setTeamType(TeamType teamType) {
        this.teamType = teamType;
    }

    public Weather getPermWeather() {
        return permWeather;
    }

    public void setPermWeather(Weather permWeather) {
        this.permWeather = permWeather;
    }

    public Terrain getPermTerrain() {
        return permTerrain;
    }

    public void setPermTerrain(Terrain permTerrain) {
        this.permTerrain = permTerrain;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public boolean isOhkoClause() {
        return ohkoClause;
    }

    public void setOhkoClause(boolean ohkoClause) {
        this.ohkoClause = ohkoClause;
    }

    public boolean isSleepClause() {
        return sleepClause;
    }

    public void setSleepClause(boolean sleepClause) {
        this.sleepClause = sleepClause;
    }

    public boolean isFreezeClause() {
        return freezeClause;
    }

    public void setFreezeClause(boolean freezeClause) {
        this.freezeClause = freezeClause;
    }

    public boolean isAccuracyClause() {
        return accuracyClause;
    }

    public void setAccuracyClause(boolean accuracyClause) {
        this.accuracyClause = accuracyClause;
    }

    public boolean isEvasionClause() {
        return evasionClause;
    }

    public void setEvasionClause(boolean evasionClause) {
        this.evasionClause = evasionClause;
    }

    public boolean isSpeciesClause() {
        return speciesClause;
    }

    public void setSpeciesClause(boolean speciesClause) {
        this.speciesClause = speciesClause;
    }

    public boolean isItemsAllowed() {
        return itemsAllowed;
    }

    public void setItemsAllowed(boolean itemsAllowed) {
        this.itemsAllowed = itemsAllowed;
    }

    public boolean isItemClause() {
        return itemClause;
    }

    public void setItemClause(boolean itemClause) {
        this.itemClause = itemClause;
    }

    public boolean isMegaClause() {
        return megaClause;
    }

    public void setMegaClause(boolean megaClause) {
        this.megaClause = megaClause;
    }

    public boolean isZmoveClause() {
        return zmoveClause;
    }

    public void setZmoveClause(boolean zmoveClause) {
        this.zmoveClause = zmoveClause;
    }

    public boolean isDynamaxClause() {
        return dynamaxClause;
    }

    public void setDynamaxClause(boolean dynamaxClause) {
        this.dynamaxClause = dynamaxClause;
    }

    public boolean isLegendClause() {
        return legendClause;
    }

    public void setLegendClause(boolean legendClause) {
        this.legendClause = legendClause;
    }

    public boolean isBasicsClause() {
        return basicsClause;
    }

    public void setBasicsClause(boolean basicsClause) {
        this.basicsClause = basicsClause;
    }

    public boolean isRandomClause() {
        return randomClause;
    }

    public void setRandomClause(boolean randomClause) {
        this.randomClause = randomClause;
    }

    public boolean isInversionClause() {
        return inversionClause;
    }

    public void setInversionClause(boolean inversionClause) {
        this.inversionClause = inversionClause;
    }

    public boolean isSkyClause() {
        return skyClause;
    }

    public void setSkyClause(boolean skyClause) {
        this.skyClause = skyClause;
    }

    public boolean isGameboyClause() {
        return gameboyClause;
    }

    public void setGameboyClause(boolean gameboyClause) {
        this.gameboyClause = gameboyClause;
    }

    public boolean isWonderLauncherClause() {
        return wonderLauncherClause;
    }

    public void setWonderLauncherClause(boolean wonderLauncherClause) {
        this.wonderLauncherClause = wonderLauncherClause;
    }

    public List<BattleStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<BattleStatus> statuses) {
        this.statuses = statuses;
    }

    public List<BattleTrainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<BattleTrainer> trainers) {
        this.trainers = trainers;
    }
}
