package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.model.CaptureMethod;
import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "owned_pokemon")
public class OwnedPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private Integer dbid;

    @ManyToOne
    @JoinColumn(name="trainer_dbid", nullable=false)
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories", "championVictories", "gymVictories",
            "championTerms", "eliteFourTerms" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Member trainer;

    @OneToOne
    @JoinColumn(name = "species_dbid")
    @JsonView(value = { View.MemberView.Summary.class })
    private Species species;

    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private String gender;

    @OneToOne
    @JoinColumn(name = "nature")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Nature nature;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer exp = 0;

    @OneToOne
    @JoinColumn(name = "obtained")
    @JsonView(value = { View.MemberView.Summary.class })
    @JsonIgnoreProperties("dbid")
    private CaptureMethod obtained;

    @Column(name = "obtained_link")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String obtainedLink;

    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private String nickname;

    @OneToOne
    @JoinColumn(name = "hidden_power_type")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Type hiddenPowerType;

    @Column(name = "hidden_power_type_link")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String hiddenPowerLink;

    @ManyToMany(
            targetEntity=Ability.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="OWNED_HIDDEN_ABILITY",
            joinColumns=@JoinColumn(name="POKEMON_DBID"),
            inverseJoinColumns=@JoinColumn(name="ABILITY_DBID")
    )
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Set<Ability> ownedHiddenAbilities;

    @ManyToMany(
            targetEntity=Attack.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="OWNED_EXTRA_MOVE",
            joinColumns=@JoinColumn(name="POKEMON_DBID"),
            inverseJoinColumns=@JoinColumn(name="ATTACK_DBID")
    )
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Set<Attack> ownedExtraMoves;

    @OneToMany(mappedBy="pokemon")
    @JsonIgnoreProperties("pokemon")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private List<EarnedRibbon> earnedRibbons;

    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private Boolean job;

    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private Boolean box;

    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private Boolean uft;

    @Column
    private Boolean rental;

    @OneToMany(mappedBy="pokemon")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Set<WishlistAbility> wishlistAbilities;

    @OneToMany(mappedBy="pokemon")
    @JsonIgnoreProperties({ "pokemon" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Set<WishlistMove> wishlistMoves;

    public OwnedPokemon() {
    }

    public OwnedPokemon(Member member, Species species, String gender) {
        setTrainer(member);
        setSpecies(species);
        setGender(gender);
        setJob(false);
        setBox(false);
        setUft(false);
        setRental(false);
    }

    public OwnedPokemon(OwnedPokemonInputDto input, Member member, Species species) {
        update(input, member);
        setSpecies(species);
        if (job == null) setJob(false);
        if (box == null) setBox(false);
        if (uft == null) setUft(false);
        if (rental == null) setRental(false);
    }

    public void update(OwnedPokemonInputDto input, Member member) {
        setTrainer(member);
        setGender(input.getGender());
        setExp(input.getExp());
        setObtainedLink(input.getObtainedLink());
        setNickname(input.getNickname());
        setHiddenPowerLink(input.getHiddenPowerLink());
        setJob(input.getJob());
        setBox(input.getBox());
        setUft(input.getUft());
        setRental(input.getRental());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public Member getTrainer() {
        return trainer;
    }

    public void setTrainer(Member trainer) {
        if (trainer != null) {
            this.trainer = trainer;
        }
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
        if (gender != null) {
            this.gender = gender;
        }
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        if (nature != null) {
            this.nature = nature;
        }
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        if (exp != null) {
            this.exp = exp;
        }
    }

    public CaptureMethod getObtained() {
        return obtained;
    }

    public void setObtained(CaptureMethod obtained) {
        if (obtained != null) {
            this.obtained = obtained;
        }
    }

    public String getObtainedLink() {
        return obtainedLink;
    }

    public void setObtainedLink(String obtainedLink) {
        if (obtainedLink != null) {
            this.obtainedLink = obtainedLink;
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        if (nickname != null) {
            this.nickname = nickname;
        }
    }

    public Type getHiddenPowerType() {
        return hiddenPowerType;
    }

    public void setHiddenPowerType(Type hiddenPowerType) {
        if (hiddenPowerType != null) {
            this.hiddenPowerType = hiddenPowerType;
        }
    }

    public String getHiddenPowerLink() {
        return hiddenPowerLink;
    }

    public void setHiddenPowerLink(String hiddenPowerLink) {
        if (hiddenPowerLink != null) {
            this.hiddenPowerLink = hiddenPowerLink;
        }
    }

    public Set<Ability> getOwnedHiddenAbilities() {
        return ownedHiddenAbilities;
    }

    public void setOwnedHiddenAbilities(Set<Ability> ownedHiddenAbilities) {
        this.ownedHiddenAbilities = ownedHiddenAbilities;
    }

    public Set<Attack> getOwnedExtraMoves() {
        return ownedExtraMoves;
    }

    public void setOwnedExtraMoves(Set<Attack> ownedExtraMoves) {
        this.ownedExtraMoves = ownedExtraMoves;
    }

    public List<EarnedRibbon> getEarnedRibbons() {
        return earnedRibbons;
    }

    public void setEarnedRibbons(List<EarnedRibbon> earnedRibbons) {
        this.earnedRibbons = earnedRibbons;
    }

    public Boolean getJob() {
        return job;
    }

    public void setJob(Boolean job) {
        if (job != null) {
            this.job = job;
        }
    }

    public Boolean getBox() {
        return box;
    }

    public void setBox(Boolean box) {
        if (box != null) {
            this.box = box;
        }
    }

    public Boolean getUft() {
        return uft;
    }

    public void setUft(Boolean uft) {
        if (uft != null) {
            this.uft = uft;
        }
    }

    public Boolean getRental() {
        return rental;
    }

    public void setRental(Boolean rental) {
        if (rental != null) {
            this.rental = rental;
        }
    }

    public Set<WishlistAbility> getWishlistAbilities() {
        return wishlistAbilities;
    }

    public void setWishlistAbilities(Set<WishlistAbility> wishlistAbilities) {
        this.wishlistAbilities = wishlistAbilities;
    }

    public Set<WishlistMove> getWishlistMoves() {
        return wishlistMoves;
    }

    public void setWishlistMoves(Set<WishlistMove> wishlistMoves) {
        this.wishlistMoves = wishlistMoves;
    }
}
