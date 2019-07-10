package com.pokemonurpg.object;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pokemonurpg.AppConfig;
import com.pokemonurpg.object.partials.EvolutionFamilyMember;
import com.pokemonurpg.object.partials.SpeciesPageTab;
import com.pokemonurpg.service.AlteredFormMethodService;
import com.pokemonurpg.service.EvolutionService;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @OneToOne
    @JoinColumn(name = "type1_dbid")
    private Type type1;

    @OneToOne
    @JoinColumn(name = "type2_dbid")
    private Type type2;

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
    private int contestCredits;

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
    /*public Species(Integer dbid, Integer dexno, String name, Type type1, Type type2, String classification, Integer hp, Integer attack, Integer defense, Integer specialAttack, Integer specialDefense, Integer speed, Double height, Double weight, Boolean maleAllowed, Boolean femaleAllowed, int pokemart, StoryRank storyRank, ArtRank artRank, ParkLocation parkLocation, ParkRank parkRank, int contestCredits, String displayName, String formName) {
        this.dbid = dbid;
        this.dexno = dexno;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
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

    // TODO remove all references to this function
    public void cloneValuesFrom(Species species) {
        this.dbid = species.getDbid();

        if (this.getDexno() == null) {
            this.dexno = species.getDexno();
        }

        if (this.getType1() == null) {
            this.type1 = species.getType1();
        }

        if (this.getType2() == null) {
            this.type2 = species.getType2();
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


        if (this.getDisplayName() == null) {
            this.displayName = species.getDisplayName();
        }

        if (this.getFormName() == null) {
            this.formName = species.getFormName();
        }
    }

    public Species cloneWithoutTransientFields() {
        Species clone = new Species();
        clone.cloneValuesFrom(this);
        clone.setName(this.getName());
        clone.setAbilities(this.getAbilities());
        clone.setAttacks(this.getAttacks());
        return clone;
    }*/

    /*public void addTransientFields(SpeciesService speciesService, AlteredFormMethodService alteredFormMethodService, EvolutionService evolutionService) {
        next = createNextTab(speciesService);
        prev = createPrevTab(speciesService);
        alteredForms = createAlteredForms(speciesService);
        alteredFormMethod = createAlteredFormMethod(alteredFormMethodService);
        evolutionFamily = createEvolutionFamily(speciesService, evolutionService);
    }

    public SpeciesPageTab createNextTab(SpeciesService service) {
        int mod = AppConfig.NUM_SPECIES;
        int dexBase0 = dexno - 1;

        int nextDex = (dexBase0 + 1) % mod + 1;
        Optional<Species> nextSpecies = service.findByDexno(nextDex);
        if (nextSpecies.isPresent())
            return new SpeciesPageTab(nextSpecies.get());

        return null;
    }

    public SpeciesPageTab createPrevTab(SpeciesService service) {
        int mod = AppConfig.NUM_SPECIES;
        int dexBase0 = dexno - 1;

        int prevDex = (dexBase0 + mod - 1) % mod + 1;
        Optional<Species> prevSpecies = service.findByDexno(prevDex);
        if (prevSpecies.isPresent())
            return new SpeciesPageTab(prevSpecies.get());

        return null;
    }

    public List<Species> createAlteredForms(SpeciesService service) {
        ArrayList<Species> alteredForms = new ArrayList<>();
        List<Species> alteredFormsTemp = service.findAllByDexno(dexno);

        if (alteredFormsTemp.size() > 1) {
            for (Species form : alteredFormsTemp) {
                if (!Objects.equals(form.getDbid(), dbid)) {
                    alteredForms.add(form);
                }
                else {
                    alteredForms.add(this.cloneWithoutTransientFields());
                }
            }
        }
        return alteredForms;
    }

    public AlteredFormMethod createAlteredFormMethod(AlteredFormMethodService service) {
        Optional<AlteredFormMethod> alteredFormMethodOptional = service.findByDexno(dexno);
        if (alteredFormMethodOptional.isPresent())
            return alteredFormMethodOptional.get();
        return null;
    }

    public ArrayList<ArrayList<EvolutionFamilyMember>> createEvolutionFamily(SpeciesService speciesService, EvolutionService evolutionService) {
        ArrayList<ArrayList<EvolutionFamilyMember>> evolutionFamily = new ArrayList<>();

        // Get any Pokemon that evolve into this, then get any Pokemon that evolve into THAT
        Species basic = this;
        Species prevo = this;
        int tempDbid = dbid;
        do {
            prevo = getPrevo(tempDbid, speciesService, evolutionService);
            if (prevo != null) {
                basic = prevo;
            }
        } while (prevo != null);


        return evolutionFamily;
    }

    public Species getPrevo(Integer dbid, SpeciesService speciesService, EvolutionService evolutionService) {
        EvolutionKey prevoKey = new EvolutionKey();
        prevoKey.setEvolutionDbid(dbid);
        Evolution prevoRecord = new Evolution();
        prevoRecord.setId(prevoKey);
        List<Evolution> prevos = evolutionService.findAll(Example.of(prevoRecord));
        if (prevos.size() == 1) {
            Integer prevoDbid = prevos.get(0).getId().getPreEvolutionDbid();
            Optional<Species> prevo = speciesService.findByDbid(prevoDbid);
            if (prevo.isPresent()) {
                return prevo.get();
            }
            else throw new IllegalStateException("Species with DBID = " + dbid + " has prevo with DBID = " + prevoDbid + " that doesn't correspond to an existing Pokemon.");
        }
        else if (prevos.size() == 0) {
            return null;
        }
        else throw new IllegalStateException("Database contains " + prevos.size() + " Species that evolve into species with DBID = " + dbid);
    }*/

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

    public int getPokemart() {
        return pokemart;
    }

    public void setPokemart(int pokemart) {
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

    public int getContestCredits() {
        return contestCredits;
    }

    public void setContestCredits(int contestCredits) {
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

    public ArrayList<ArrayList<EvolutionFamilyMember>> getEvolutionFamily() {
        return evolutionFamily;
    }

    public void setEvolutionFamily(ArrayList<ArrayList<EvolutionFamilyMember>> evolutionFamily) {
        this.evolutionFamily = evolutionFamily;
    }*/
}
