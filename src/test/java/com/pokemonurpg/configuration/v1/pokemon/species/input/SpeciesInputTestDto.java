package com.pokemonurpg.configuration.v1.pokemon.species.input;

import static org.junit.jupiter.api.Assertions.*;

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

    public void assertValid(Species species) {
        assertEquals(getHeight(), species.getHeight());
        assertEquals(getWeight(), species.getWeight());
        assertEquals(getMaleAllowed(), species.getMaleAllowed());
        assertEquals(getFemaleAllowed(), species.getFemaleAllowed());
        assertEquals(getPokemart(), species.getPokemart());
        assertEquals(getContestCredits(), species.getContestCredits());
        assertEquals(getDisplayName(), species.getDisplayName());
        assertEquals(getFormName(), species.getFormName());
        assertEquals(getName(), species.getName());
        assertEquals(getHp(), species.getHp());
        assertEquals(getAttack(), species.getAttack());
        assertEquals(getDefense(), species.getDefense());
        assertEquals(getSpecialAttack(), species.getSpecialAttack());
        assertEquals(getSpecialDefense(), species.getSpecialDefense());
        assertEquals(getSpeed(), species.getSpeed());
        assertEquals(getDexno(), species.getDexno());
        assertEquals(getClassification(), species.getClassification());
        assertEquals(getLegendaryTier(), species.getLegendaryTier());
        assertEquals(getAlteredFormMethod(), species.getAlteredFormMethod());
        assertEquals(getEvolutionMethod(), species.getEvolutionMethod());
        assertEquals(getEvolutionExpRequirement(), species.getEvolutionExpRequirement());
        assertEquals(getMegaStone(), species.getMegaStone());
        assertEquals(getMegaSuffix(), species.getMegaSuffix());
        assertEquals(TYPE1, species.getType1());
        assertEquals(TYPE2, species.getType2());
        assertEquals(STORY_RANK, species.getStoryRank());
        assertEquals(ART_RANK, species.getArtRank());
        assertEquals(PARK_LOCATION, species.getParkLocation());
        assertEquals(PARK_RANK, species.getParkRank());
        assertEquals(PRE_EVOLUTION, species.getPreEvolution());
        assertEquals(PRE_MEGA, species.getPreMega());

        List<SpeciesAbility> abilities = species.getAbilities();
        assertNotNull(abilities);
        this.getAbilities().forEach(ability -> {
            assertTrue(abilities.stream().anyMatch(foundAbility -> ability.getName().equals(foundAbility.getAbility().getName())));
        });

        List<SpeciesAttack> attacks = species.getAttacks();
        assertNotNull(attacks);
        this.getAbilities().forEach(attack -> {
            assertTrue(attacks.stream().anyMatch(foundAttack -> attack.getName().equals(foundAttack.getAttack().getName())));
        });

        Set<CosmeticForm> forms = species.getCosmeticForms();
        assertNotNull(forms);
        this.getAbilities().forEach(form -> {
            assertTrue(forms.stream().anyMatch(foundForm -> form.getName().equals(foundForm.getName())));
        });
    }
}
