package com.pokemonurpg.stats.service;

import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.nature.service.NatureService;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.general.service.ObtainedService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.security.service.SessionService;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.OwnedPokemonRepository;
import com.pokemonurpg.stats.validation.OwnedPokemonValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Provider;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnedPokemonServiceTest {
    private final static Integer        DBID            = 432;
    private final static OwnedPokemon   POKEMON         = mock(OwnedPokemon.class);
    private final static SessionService SESSION_SERVICE = mock(SessionService.class);
    private final static Species        SPECIES         = mock(Species.class);
    private final static String         SPECIES_NAME    = "SPECIES_NAME";
    private final static Member         MEMBER          = mock(Member.class);
    private final static String         MEMBER_NAME     = "MEMBER_NAME";
    private final static Nature         NATURE          = mock(Nature.class);
    private final static String         NATURE_NAME     = "NATURE_NAME";
    private final static Obtained       OBTAINED        = mock(Obtained.class);
    private final static String         OBTAINED_NAME   = "OBTAINED_NAME";
    private final static Type           HP_TYPE         = mock(Type.class);
    private final static String         HP_TYPE_NAME    = "HP_TYPE_NAME";

    @InjectMocks
    private OwnedPokemonService ownedPokemonService;

    @Mock
    private OwnedPokemonValidator ownedPokemonValidator;

    @Mock
    private OwnedPokemonRepository ownedPokemonRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private NatureService natureService;

    @Mock
    private ObtainedService obtainedService;

    @Mock
    private TypeService typeService;

    @Mock
    private OwnedExtraMoveService ownedExtraMoveService;

    @Mock
    private OwnedHiddenAbilityService ownedHiddenAbilityService;

    @Mock
    private EarnedRibbonService earnedRibbonService;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findByDbid() {
        OwnedPokemon pokemon = new OwnedPokemon();
        pokemon.setSpecies(SPECIES);

        when(ownedPokemonRepository.findByDbid(DBID)).thenReturn(pokemon);
        when(speciesRepository.findByPreEvolution(SPECIES)).thenReturn(null);

        assertEquals(pokemon, ownedPokemonService.findByDbid(DBID));
    }

    @Test
    public void create() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        input.setSpecies(SPECIES_NAME);
        input.setNature(NATURE_NAME);
        input.setObtained(OBTAINED_NAME);
        input.setHiddenPowerType(HP_TYPE_NAME);

        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(MEMBER);

        EarnedRibbonInputDto ribbon = mock(EarnedRibbonInputDto.class);
        input.setEarnedRibbons(Collections.singletonList(ribbon));

        when(speciesRepository.findByName(SPECIES_NAME)).thenReturn(SPECIES);
        when(ownedPokemonValidator.isValid(SPECIES, input)).thenReturn(true);

        when(natureService.findByName(NATURE_NAME)).thenReturn(NATURE);
        when(obtainedService.findByName(OBTAINED_NAME)).thenReturn(OBTAINED);
        when(typeService.findByName(HP_TYPE_NAME)).thenReturn(HP_TYPE);

        OwnedPokemon pokemon = ownedPokemonService.create(input);
        assertEquals(MEMBER, pokemon.getTrainer());
        assertEquals(SPECIES, pokemon.getSpecies());
        assertEquals(NATURE, pokemon.getNature());
        assertEquals(OBTAINED, pokemon.getObtained());
        assertEquals(HP_TYPE, pokemon.getHiddenPowerType());

        verify(ownedPokemonRepository).save(pokemon);
        verify(ownedExtraMoveService).updateAll(input, pokemon);
        verify(ownedHiddenAbilityService).updateAll(input, pokemon);
        verify(earnedRibbonService).update(ribbon, pokemon);
    }

    @Test
    public void update() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();

        when(ownedPokemonRepository.findByDbid(DBID)).thenReturn(POKEMON);
        when(POKEMON.getSpecies()).thenReturn(SPECIES);
        when(ownedPokemonValidator.isValid(SPECIES, input)).thenReturn(true);

        OwnedPokemon result = ownedPokemonService.update(input, DBID);
        assertEquals(POKEMON, result);
        //verify(POKEMON).update(input);
        verify(ownedPokemonRepository).save(POKEMON);
    }

    @Test
    public void delete() {
        ownedPokemonService.delete(DBID);
        verify(ownedPokemonRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }
}