package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.response.EvolutionFamilyMemberDto;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.pokemon.Species;
import com.pokemonurpg.repository.EvolutionRepository;
import com.pokemonurpg.repository.SpeciesRepository;
import com.pokemonurpg.service.pokemon.EvolutionService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EvolutionServiceTest {

    private EvolutionService evolutionService;
    private EvolutionRepository evolutionRepository = mock(EvolutionRepository.class);
    private SpeciesRepository speciesRepository = mock(SpeciesRepository.class);

    private Species pikachu = TestObjectFactory.createPikachu();
    private Species pichu = TestObjectFactory.createPichu();
    private Species raichu = TestObjectFactory.createRaichu();

    @Before
    public void init() {
        evolutionService = new EvolutionService(evolutionRepository, speciesRepository);
    }

    @Test
    public void getPreEvolution() {
        TestObjectFactory.buildEvolutionRelation(evolutionRepository, pichu, pikachu);

        int prevoDbid = evolutionService.getPreEvolutionDbid(pikachu.getDbid());
        assertEquals(pichu.getDbid(), prevoDbid);
    }

    @Test
    public void getPreEvolutionForNonEvolvedPokemon() {
        int prevoDbid = evolutionService.getPreEvolutionDbid(-1);
        assertEquals(-1, prevoDbid);
    }

    @Test
    public void findEvolutionsForPichu() {
        TestObjectFactory.buildEvolutionRelation(evolutionRepository, pichu, pikachu);

        when(speciesRepository.findByDbid(pikachu.getDbid())).thenReturn(pikachu);

        List<EvolutionFamilyMemberDto> evolutions = evolutionService.findEvolutionsByPreEvolutionDbid(pichu.getDbid());
        assertEquals(1, evolutions.size());
        assertEquals(pikachu.getName(), evolutions.get(0).getName());
    }

    @Test
    public void findEvolutionsReturnsEmptyListForPokemonThatDoesntEvolve() {
        List<EvolutionFamilyMemberDto> evolutions = evolutionService.findEvolutionsByPreEvolutionDbid(raichu.getDbid());
        assertEquals(0, evolutions.size());
    }
}