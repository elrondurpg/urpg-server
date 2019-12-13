package com.pokemonurpg.object;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owned_pokemon")
public class OwnedPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    private Member trainer;

    @OneToOne
    @JoinColumn(name = "species_dbid")
    private Species species;

    @Column
    private String gender;

    @OneToOne
    @JoinColumn(name = "nature")
    private Nature nature;

    @Column
    private int exp;

    @OneToOne
    @JoinColumn(name = "obtained")
    private Obtained obtained;

    @Column(name = "obtained_link")
    private String obtainedLink;

    @Column
    private String nickname;

    @OneToOne
    @JoinColumn(name = "hidden_power_type")
    private Type hiddenPowerType;

    @Column(name = "hidden_power_type_link")
    private String hiddenPowerLink;

    @OneToMany(mappedBy="pokemon")
    private List<OwnedHiddenAbility> ownedHiddenAbilities;

    @OneToMany(mappedBy="pokemon")
    private List<OwnedExtraMove> ownedExtraMoves;

    @OneToMany(mappedBy="pokemon")
    private List<EarnedRibbon> earnedRibbons;

    public OwnedPokemon() {
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        this.trainer = trainer;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Obtained getObtained() {
        return obtained;
    }

    public void setObtained(Obtained obtained) {
        this.obtained = obtained;
    }

    public String getObtainedLink() {
        return obtainedLink;
    }

    public void setObtainedLink(String obtainedLink) {
        this.obtainedLink = obtainedLink;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Type getHiddenPowerType() {
        return hiddenPowerType;
    }

    public void setHiddenPowerType(Type hiddenPowerType) {
        this.hiddenPowerType = hiddenPowerType;
    }

    public String getHiddenPowerLink() {
        return hiddenPowerLink;
    }

    public void setHiddenPowerLink(String hiddenPowerLink) {
        this.hiddenPowerLink = hiddenPowerLink;
    }

    public List<OwnedHiddenAbility> getOwnedHiddenAbilities() {
        return ownedHiddenAbilities;
    }

    public void setOwnedHiddenAbilities(List<OwnedHiddenAbility> ownedHiddenAbilities) {
        this.ownedHiddenAbilities = ownedHiddenAbilities;
    }

    public List<OwnedExtraMove> getOwnedExtraMoves() {
        return ownedExtraMoves;
    }

    public void setOwnedExtraMoves(List<OwnedExtraMove> ownedExtraMoves) {
        this.ownedExtraMoves = ownedExtraMoves;
    }

    public List<EarnedRibbon> getEarnedRibbons() {
        return earnedRibbons;
    }

    public void setEarnedRibbons(List<EarnedRibbon> earnedRibbons) {
        this.earnedRibbons = earnedRibbons;
    }
}
