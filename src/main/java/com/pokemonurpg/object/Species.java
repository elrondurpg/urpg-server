package com.pokemonurpg.object;

import com.pokemonurpg.dto.species.input.SpeciesInputDto;

import javax.persistence.*;

@Entity
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int dbid;

    @Column
    private int dexno;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "type1_dbid")
    private Type type1;

    @OneToOne
    @JoinColumn(name = "type2_dbid")
    private Type type2;

    @Column
    private String classification;

    @Column
    private int hp;

    @Column
    private int attack;

    @Column
    private int defense;

    @Column(name = "special_attack")
    private int specialAttack;

    @Column(name = "special_defense")
    private int specialDefense;

    @Column
    private int speed;

    @Column
    private double height;

    @Column
    private double weight;

    @Column(name = "male_allowed")
    private boolean maleAllowed;

    @Column(name = "female_allowed")
    private boolean femaleAllowed;

    @Column
    private Integer pokemart;

    @OneToOne
    @JoinColumn(name = "story_rank")
    private StoryRank storyRank;

    @OneToOne
    @JoinColumn(name = "art_rank")
    private ArtRank artRank;

    @OneToOne
    @JoinColumn(name = "park_location")
    private ParkLocation parkLocation;

    @OneToOne
    @JoinColumn(name = "park_rank")
    private ParkRank parkRank;

    @Column(name = "contest_credits")
    private Integer contestCredits;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "form_name")
    private String formName;

    /*
    @JsonManagedReference
    @OneToMany(mappedBy = "species")
    private List<SpeciesAttack> attacks;

    @JsonManagedReference
    @OneToMany(mappedBy = "species")
    private List<SpeciesAbility> abilities;

    @JsonManagedReference
    @OneToMany (mappedBy = "species")
    private List<CosmeticForm> cosmeticForms;

    @Transient
    private SpeciesPageTab next;

    @Transient
    private SpeciesPageTab prev;

    @Transient
    private List<Species> alteredForms;

    @Transient
    private ArrayList<ArrayList<EvolutionFamilyMember>> evolutionFamily;

    @Transient
    private AlteredFormMethod alteredFormMethod;*/

    public Species() { }

    public Species(String name) {
        this.name = name;
    }

    public Species(SpeciesInputDto input) {
        setDexno(input.getDexno());
        setName(input.getName());
        setClassification(input.getClassification());
        setHp(input.getHp());
        setAttack(input.getAttack());
        setDefense(input.getDefense());
        setSpecialAttack(input.getSpecialAttack());
        setSpecialDefense(input.getSpecialDefense());
        setSpeed(input.getSpeed());
        setHeight(input.getHeight());
        setWeight(input.getWeight());
        setMaleAllowed(input.isMaleAllowed());
        setFemaleAllowed(input.isFemaleAllowed());
        if (input.getPokemart() != null) {
            setPokemart(input.getPokemart());
        }
        else setPokemart(-1);
        if (input.getContestCredits() != null) {
            setContestCredits(input.getContestCredits());
        }
        else setContestCredits(-1);
        setDisplayName(input.getDisplayName());
        setFormName(input.getFormName());
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean getMaleAllowed() {
        return maleAllowed;
    }

    public void setMaleAllowed(boolean maleAllowed) {
        this.maleAllowed = maleAllowed;
    }

    public boolean getFemaleAllowed() {
        return femaleAllowed;
    }

    public void setFemaleAllowed(boolean femaleAllowed) {
        this.femaleAllowed = femaleAllowed;
    }

    public Integer getPokemart() {
        return pokemart;
    }

    public void setPokemart(Integer pokemart) {
        this.pokemart = pokemart;
    }

    public StoryRank getStoryRank() {
        return storyRank;
    }

    public void setStoryRank(StoryRank storyRank) {
        this.storyRank = storyRank;
    }

    public ArtRank getArtRank() {
        return artRank;
    }

    public void setArtRank(ArtRank artRank) {
        this.artRank = artRank;
    }

    public ParkLocation getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(ParkLocation parkLocation) {
        this.parkLocation = parkLocation;
    }

    public ParkRank getParkRank() {
        return parkRank;
    }

    public void setParkRank(ParkRank parkRank) {
        this.parkRank = parkRank;
    }

    public Integer getContestCredits() {
        return contestCredits;
    }

    public void setContestCredits(Integer contestCredits) {
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

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDexno() {
        return dexno;
    }

    public void setDexno(int dexno) {
        this.dexno = dexno;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*public AlteredFormMethod getAlteredFormMethod() {
        return alteredFormMethod;
    }

    public void setAlteredFormMethod(AlteredFormMethod alteredFormMethod) {
        this.alteredFormMethod = alteredFormMethod;
    }

    public List<SpeciesAttack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<SpeciesAttack> attacks) {
        this.attacks = attacks;
    }

    public List<SpeciesAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<SpeciesAbility> abilities) {
        this.abilities = abilities;
    }

    public SpeciesPageTab getNext() {
        return next;
    }

    public void setNext(SpeciesPageTab next) {
        this.next = next;
    }

    public SpeciesPageTab getPrev() {
        return prev;
    }

    public void setPrev(SpeciesPageTab prev) {
        this.prev = prev;
    }

    public List<CosmeticForm> getCosmeticForms() {
        return cosmeticForms;
    }

    public void setCosmeticForms(List<CosmeticForm> cosmeticForms) {
        this.cosmeticForms = cosmeticForms;
    }

    public List<Species> getAlteredForms() {
        return alteredForms;
    }

    public void setAlteredForms(List<Species> alteredForms) {
        this.alteredForms = alteredForms;
    }

    public ArrayList<ArrayList<EvolutionFamilyMember>> getEvolvesFrom() {
        return evolutionFamily;
    }

    public void setEvolvesFrom(ArrayList<ArrayList<EvolutionFamilyMember>> evolutionFamily) {
        this.evolutionFamily = evolutionFamily;
    }*/
}
