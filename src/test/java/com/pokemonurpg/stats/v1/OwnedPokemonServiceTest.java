package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Nature;
import com.pokemonurpg.entities.v1.CaptureMethod;
import com.pokemonurpg.configuration.v1.natures.NatureService;
import com.pokemonurpg.configuration.v1.capturemethods.CaptureMethodService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.v1.SessionService;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.infrastructure.v1.data.jpa.OwnedPokemonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Provider;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OwnedPokemonServiceTest {
    private final static Integer        DBID            = 432;
    private final static OwnedPokemon   POKEMON         = mock(OwnedPokemon.class);
    private final static SessionService SESSION_SERVICE = mock(SessionService.class);
    private final static Pokemon SPECIES = mock(Pokemon.class);
    private final static String         SPECIES_NAME    = "SPECIES_NAME";
    private final static Member         MEMBER          = mock(Member.class);
    private final static String         MEMBER_NAME     = "MEMBER_NAME";
    private final static Nature         NATURE          = mock(Nature.class);
    private final static String         NATURE_NAME     = "NATURE_NAME";
    private final static CaptureMethod CAPTURE_METHOD = mock(CaptureMethod.class);
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
    private PokemonService pokemonService;

    @Mock
    private NatureService natureService;

    @Mock
    private CaptureMethodService captureMethodService;

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

    @Mock
    private WishlistAbilityService wishlistAbilityService;

    @Mock
    private WishlistMoveService wishlistMoveService;

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findByDbid() {
        OwnedPokemon pokemon = new OwnedPokemon();
        pokemon.setSpecies(SPECIES);

        when(ownedPokemonRepository.findByDbid(DBID)).thenReturn(pokemon);

        assertEquals(pokemon, ownedPokemonService.findByDbid(DBID));
    }

    @Test
    public void create() {
        OwnedPokemonRequest input = new OwnedPokemonRequest();
        input.setSpecies(SPECIES_NAME);
        input.setNature(NATURE_NAME);
        input.setObtained(OBTAINED_NAME);
        input.setHiddenPowerType(HP_TYPE_NAME);

        when(sessionServiceProvider.get()).thenReturn(SESSION_SERVICE);
        when(SESSION_SERVICE.getAuthenticatedMember()).thenReturn(MEMBER);

        EarnedRibbonRequest ribbon = mock(EarnedRibbonRequest.class);
        input.setEarnedRibbons(Collections.singletonList(ribbon));

        when(pokemonService.findByName(SPECIES_NAME)).thenReturn(SPECIES);
        when(ownedPokemonValidator.isValid(SPECIES, input)).thenReturn(true);

        when(natureService.findByName(NATURE_NAME)).thenReturn(NATURE);
        when(captureMethodService.findByName(OBTAINED_NAME)).thenReturn(CAPTURE_METHOD);
        when(typeService.findByName(HP_TYPE_NAME)).thenReturn(HP_TYPE);

        OwnedPokemon pokemon = ownedPokemonService.create(input);
        assertEquals(MEMBER, pokemon.getTrainer());
        assertEquals(SPECIES, pokemon.getSpecies());
        assertEquals(NATURE, pokemon.getNature());
        assertEquals(CAPTURE_METHOD, pokemon.getObtained());
        assertEquals(HP_TYPE, pokemon.getHiddenPowerType());

        verify(ownedExtraMoveService).updateAll(input, pokemon);
        verify(ownedHiddenAbilityService).updateAll(input, pokemon);
        verify(earnedRibbonService).update(ribbon, pokemon);
    }

    @Test
    public void update() {
        OwnedPokemonRequest input = new OwnedPokemonRequest();

        when(ownedPokemonRepository.findByDbid(DBID)).thenReturn(POKEMON);
        when(POKEMON.getSpecies()).thenReturn(SPECIES);
        when(ownedPokemonValidator.isValid(SPECIES, input)).thenReturn(true);

        OwnedPokemon result = ownedPokemonService.update(input, DBID);
        assertEquals(POKEMON, result);
    }

    @Test
    public void delete() {
        ownedPokemonService.delete(DBID);
        verify(ownedPokemonRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }
}