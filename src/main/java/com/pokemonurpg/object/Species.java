package com.pokemonurpg.object;

import javax.persistence.*;

@Entity
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private Integer dexno;

    @Column
    private String name;

    @Column(name="type1_dbid")
    private Integer type1Dbid;

    @Column(name="type2_dbid")
    private Integer type2Dbid;

    @Column
    private String classification;

    @Column
    private Integer hp;

    @Column
    private Integer attack;

    @Column
    private Integer defense;

    @Column(name = "special_attack")
    private Integer specialAttack;

    @Column(name = "special_defense")
    private Integer specialDefense;

    @Column
    private Integer speed;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column(name = "male_allowed")
    private Boolean maleAllowed;

    @Column(name = "female_allowed")
    private Boolean femaleAllowed;

    @Column
    private String pokemart;

    @Column(name = "story_rank")
    private Integer storyRank;

    @Column(name = "art_rank")
    private Integer artRank;

    @Column(name = "park_location")
    private Integer parkLocation;

    @Column(name = "park_rank")
    private Integer parkRank;

    @Column(name = "contest_credits")
    private String contestCredits;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "form_name")
    private String formName;

    public Species() { }
    public Species(Integer dbid, Integer dexno, String name, Integer type1Dbid, Integer type2Dbid, String classification, Integer hp, Integer attack, Integer defense, Integer specialAttack, Integer specialDefense, Integer speed, Double height, Double weight, Boolean maleAllowed, Boolean femaleAllowed, String pokemart, Integer storyRank, Integer artRank, Integer parkLocation, Integer parkRank, String contestCredits, String displayName, String formName) {
        this.dbid = dbid;
        this.dexno = dexno;
        this.name = name;
        this.type1Dbid = type1Dbid;
        this.type2Dbid = type2Dbid;
        this.classification = classification;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.height = height;
        this.weight = weight;
        this.maleAllowed = maleAllowed;
        this.femaleAllowed = femaleAllowed;
        this.pokemart = pokemart;
        this.storyRank = storyRank;
        this.artRank = artRank;
        this.parkLocation = parkLocation;
        this.parkRank = parkRank;
        this.contestCredits = contestCredits;
        this.displayName = displayName;
        this.formName = formName;
    }

    public void persistValuesFrom(Species species) {
        this.dbid = species.getDbid();

        if (this.getDexno() == null) {
            this.dexno = species.getDexno();
        }

        if (this.getType1Dbid() == null) {
            this.type1Dbid = species.getType1Dbid();
        }

        if (this.getType2Dbid() == null) {
            this.type2Dbid = species.getType2Dbid();
        }

        if (this.getClassification() == null) {
            this.classification = species.getClassification();
        }

        if (this.getHp() == null) {
            this.hp = species.getHp();
        }

        if (this.getAttack() == null) {
            this.attack = species.getAttack();
        }

        if (this.getDefense() == null) {
            this.defense = species.getDefense();
        }

        if (this.getSpecialAttack() == null) {
            this.specialAttack = species.getSpecialAttack();
        }

        if (this.getSpecialDefense() == null) {
            this.specialDefense = species.getSpecialDefense();
        }

        if (this.getSpeed() == null) {
            this.speed = species.getSpeed();
        }

        if (this.getHeight() == null) {
            this.height = species.getHeight();
        }

        if (this.getWeight() == null) {
            this.weight = species.getWeight();
        }

        if (this.getMaleAllowed() == null) {
            this.maleAllowed = species.getMaleAllowed();
        }

        if (this.getFemaleAllowed() == null) {
            this.femaleAllowed = species.getFemaleAllowed();
        }

        if (this.getPokemart() == null) {
            this.pokemart = species.getPokemart();
        }

        if (this.getContestCredits() == null) {
            this.contestCredits = species.getContestCredits();
        }

        if (this.getArtRank() == null) {
            this.artRank = species.getArtRank();
        }

        if (this.getStoryRank() == null) {
            this.storyRank = species.getStoryRank();
        }

        if (this.getParkLocation() == null) {
            this.parkLocation = species.getParkLocation();
        }

        if (this.getParkRank() == null) {
            this.parkRank = species.getParkRank();
        }

        if (this.getDisplayName() == null) {
            this.displayName = species.getDisplayName();
        }

        if (this.getFormName() == null) {
            this.formName = species.getFormName();
        }
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getMaleAllowed() {
        return maleAllowed;
    }

    public void setMaleAllowed(Boolean maleAllowed) {
        this.maleAllowed = maleAllowed;
    }

    public Boolean getFemaleAllowed() {
        return femaleAllowed;
    }

    public void setFemaleAllowed(Boolean femaleAllowed) {
        this.femaleAllowed = femaleAllowed;
    }

    public String getPokemart() {
        return pokemart;
    }

    public void setPokemart(String pokemart) {
        this.pokemart = pokemart;
    }

    public Integer getStoryRank() {
        return storyRank;
    }

    public void setStoryRank(Integer storyRank) {
        this.storyRank = storyRank;
    }

    public Integer getArtRank() {
        return artRank;
    }

    public void setArtRank(Integer artRank) {
        this.artRank = artRank;
    }

    public Integer getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(Integer parkLocation) {
        this.parkLocation = parkLocation;
    }

    public Integer getParkRank() {
        return parkRank;
    }

    public void setParkRank(Integer parkRank) {
        this.parkRank = parkRank;
    }

    public String getContestCredits() {
        return contestCredits;
    }

    public void setContestCredits(String contestCredits) {
        this.contestCredits = contestCredits;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getDexno() {
        return dexno;
    }

    public void setDexno(Integer dexno) {
        this.dexno = dexno;
    }

    public Integer getType1Dbid() {
        return type1Dbid;
    }

    public void setType1Dbid(Integer type1Dbid) {
        this.type1Dbid = type1Dbid;
    }

    public Integer getType2Dbid() {
        return type2Dbid;
    }

    public void setType2Dbid(Integer type2Dbid) {
        this.type2Dbid = type2Dbid;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
