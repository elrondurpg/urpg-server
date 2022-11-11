package com.pokemonurpg.configuration.v1.pokemon.species.input;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.pokemonurpg.configuration.v1.pokemon.species.model.CosmeticForm;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAttack;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.test.RandomNumberGenerator;
import com.pokemonurpg.test.RandomStringGenerator;

public class SpeciesInputTestDto extends SpeciesInputDto {
    public static Type TYPE1 = new Type();
    public static Type TYPE2 = new Type();
    public static StoryRank STORY_RANK = new StoryRank();
    public static ArtRank ART_RANK = new ArtRank();
    public static ParkLocation PARK_LOCATION = new ParkLocation();
    public static ParkRank PARK_RANK = new ParkRank();
    public static Species PRE_EVOLUTION = new Species();
    public static Species PRE_MEGA = new Species();
    public static List<SpeciesAttack> ATTACKS = Collections.emptyList();
    public static List<SpeciesAbility> ABILITIES = Collections.emptyList();
    public static Set<CosmeticForm> COSMETIC_FORMS = Collections.emptySet();

    public SpeciesInputTestDto() {
        setDexno(RandomNumberGenerator.generate());
        setName(RandomStringGenerator.generate());
        setClassification(RandomStringGenerator.generate());
        setHp(RandomNumberGenerator.generate());
        setAttack(RandomNumberGenerator.generate());
        setDefense(RandomNumberGenerator.generate());
        setSpecialAttack(RandomNumberGenerator.generate());
        setSpecialDefense(RandomNumberGenerator.generate());
        setSpeed(RandomNumberGenerator.generate());
        setHeight(RandomNumberGenerator.generateDouble());
        setWeight(RandomNumberGenerator.generateDouble());
        setMaleAllowed(true);
        setFemaleAllowed(true);
        setPokemart(RandomNumberGenerator.generate());
        setContestCredits(RandomNumberGenerator.generate());
        setDisplayName(RandomStringGenerator.generate());
        setFormName(RandomStringGenerator.generate());
        setLegendaryTier(RandomNumberGenerator.generate());
        setAlteredFormMethod(RandomStringGenerator.generate());
        setEvolutionMethod(RandomStringGenerator.generate());
        setEvolutionExpRequirement(RandomNumberGenerator.generate());
        setMegaStone(RandomStringGenerator.generate());
        setMegaSuffix(RandomStringGenerator.generate());
        setType1(RandomStringGenerator.generate());
        setType2(RandomStringGenerator.generate());
        setStoryRank(RandomStringGenerator.generate());
        setParkLocation(RandomStringGenerator.generate());
        setParkRank(RandomStringGenerator.generate());
        setArtRank(RandomStringGenerator.generate());
        setPreEvolution(RandomStringGenerator.generate());
        setPreMega(RandomStringGenerator.generate());
        setAttacks(Collections.singletonList(new SpeciesAttackInputTestDto()));
        setAbilities(Collections.singletonList(new SpeciesAbilityInputTestDto()));
        setCosmeticForms(Collections.singletonList(new CosmeticFormInputTestDto()));
        setBattleOnly(true);
    }
}
