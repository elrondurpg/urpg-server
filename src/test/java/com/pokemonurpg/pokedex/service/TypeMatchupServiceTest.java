package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.entities.v1.TypeMatchup;
import com.pokemonurpg.pokedex.v1.TypeMatchupDto;
import com.pokemonurpg.infrastructure.v1.data.jpa.TypeMatchupRepository;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.Type;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.pokedex.v1.TypeMatchupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TypeMatchupServiceTest {
    private final static String TYPE1_NAME = "TYPE1";
    private final static String TYPE2_NAME = "TYPE2";
    private final static Integer TYPE1_DBID = 3243;
    private final static Integer TYPE2_DBID = 2432;
    private final static Double MULT1V1 = 3.0;
    private final static Double MULT1V2 = 2.0;
    private final static Double MULT2V1 = 1.5;
    private final static Double MULT2V2 = 0.5;

    @InjectMocks
    private TypeMatchupService typeMatchupService;

    @Mock
    private TypeMatchupRepository typeMatchupRepository;

    @Mock
    private TypeService typeService;

    private Type type1;
    private Type type2;

    @Before
    public void setUpMatchups() {
        type1 = new Type();
        type1.setName(TYPE1_NAME);
        type1.setDbid(TYPE1_DBID);

        type2 = new Type();
        type2.setName(TYPE2_NAME);
        type2.setDbid(TYPE2_DBID);

        // Given a list of types stored in the DB
        when(typeService.findAll()).thenReturn(Arrays.asList(type1, type2));

        // Given TypeMatchup records for each combination of types
        TypeMatchup matchup1vs1 = new TypeMatchup();
        matchup1vs1.setMultiplier(MULT1V1);
        when(typeMatchupRepository.findByAttackTypeAndDefendType(type1, type1)).thenReturn(matchup1vs1);

        TypeMatchup matchup1vs2 = new TypeMatchup();
        matchup1vs2.setMultiplier(MULT1V2);
        when(typeMatchupRepository.findByAttackTypeAndDefendType(type1, type2)).thenReturn(matchup1vs2);

        TypeMatchup matchup2vs1 = new TypeMatchup();
        matchup2vs1.setMultiplier(MULT2V1);
        when(typeMatchupRepository.findByAttackTypeAndDefendType(type2, type1)).thenReturn(matchup2vs1);

        TypeMatchup matchup2vs2 = new TypeMatchup();
        matchup2vs2.setMultiplier(MULT2V2);
        when(typeMatchupRepository.findByAttackTypeAndDefendType(type2, type2)).thenReturn(matchup2vs2);
    }

    @Test
    public void testTwoTypes() {
        // Given a Species with Type1 "type1" and Type2 "type2"
        Species species = new Species();
        species.setType1(type1);
        species.setType2(type2);

        // when I call typeMatchupService.findBySpecies(species);
        List<TypeMatchupDto> matchups = typeMatchupService.findBySpecies(species);

        // Then I will receive a list of two TypeMatchupDtos, where each typeMatchupDto has the correct multiplier
        assertEquals(2, matchups.size());
        assertEquals(MULT1V1 * MULT1V2, matchups.get(0).getMultiplier(), 0);
        assertEquals(MULT2V1 * MULT2V2, matchups.get(1).getMultiplier(), 0);
    }

    @Test
    public void testOneType() {
        Species species = new Species();
        species.setType1(type1);

        // when I call typeMatchupService.findBySpecies(species);
        List<TypeMatchupDto> matchups = typeMatchupService.findBySpecies(species);

        // Then I will receive a list of two TypeMatchupDtos, where each typeMatchupDto has the correct multiplier
        assertEquals(2, matchups.size());
        assertEquals(MULT1V1, matchups.get(0).getMultiplier(), 0);
        assertEquals(MULT2V1, matchups.get(1).getMultiplier(), 0);
    }

}