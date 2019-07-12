package com.pokemonurpg.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.dto.CosmeticFormDto;
import com.pokemonurpg.dto.ResponseDto;
import com.pokemonurpg.dto.species.SpeciesDto;
import com.pokemonurpg.dto.species.SpeciesPageTabDto;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.repository.SpeciesRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SpeciesServiceTest {

    private SpeciesService speciesService;
    private SpeciesRepository speciesRepository = mock(SpeciesRepository.class);
    private SpeciesAttackService speciesAttackService = mock(SpeciesAttackService.class);
    private AttackService attackService = mock(AttackService.class);
    private SpeciesAbilityService speciesAbilityService = mock(SpeciesAbilityService.class);
    private AbilityService abilityService = mock(AbilityService.class);
    private CosmeticFormService cosmeticFormService = mock(CosmeticFormService.class);

    private Species pikachu = TestObjectFactory.createPikachu();
    private CosmeticForm spikyEaredPikachu = TestObjectFactory.createSpikyEaredPikachu();

    private Species nextDex = TestObjectFactory.createNextDex();
    private Species prevDex = TestObjectFactory.createPrevDex();

    @Before
    public void initService() {
        speciesService = new SpeciesService(speciesRepository, speciesAttackService, attackService,
                speciesAbilityService, abilityService, cosmeticFormService);
    }

    @Test
    public void serviceReturnsPikachuWithExactName() {
        String name = TestObjectFactory.TEST_SPECIES_NAME;
        when(speciesRepository.findByName(name)).thenReturn(Optional.of(pikachu));

        SpeciesDto dto = speciesService.findByName(name);
        assertEquals(name, dto.getName());
    }

    @Test
    public void serviceReturnsPikachuWithPartialName() {
        String name = TestObjectFactory.TEST_SPECIES_NAME;
        String namePartial = name.substring(0, 4);
        ArrayList<Species> list = new ArrayList<>();
        list.add(pikachu);
        when(speciesRepository.findByNameStartingWith(namePartial)).thenReturn(list);

        SpeciesDto dto = speciesService.findByName(namePartial);
        assertEquals(name, dto.getName());
    }

    @Test
    public void serviceReturnsNullWithEmptyName() {
        SpeciesDto dto = speciesService.findByName("");
        assertNull(dto);
    }

    @Test
    public void serviceReturnsNullWithNonexistentName() {
        SpeciesDto dto = speciesService.findByName("Mewthree");
        assertNull(dto);
    }

    @Test
    public void buildSpeciesDtoAttachesCosmeticForm() {
        List<CosmeticFormDto> list = new ArrayList<>();
        list.add(new CosmeticFormDto(spikyEaredPikachu));

        when(cosmeticFormService.findBySpeciesDbid(pikachu.getDbid())).thenReturn(list);

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(pikachu);
        assertNotNull(speciesDto.getCosmeticForms());
        assertEquals(1, speciesDto.getCosmeticForms().size());
        assertEquals(spikyEaredPikachu.getDisplayName(), speciesDto.getCosmeticForms().get(0).getDisplayName());
    }

    @Test
    public void getNextDexReturnsOneWhenOriginIsX() {
        int dexno = 1;
        int nextDex = speciesService.getNextDex(dexno);
        assertEquals(2, nextDex);
    }

    @Test
    public void getNextDexReturnsTwoWhenOriginIsOne() {
        int dexno = AppConfig.NUM_SPECIES;
        int nextDex = speciesService.getNextDex(dexno);
        assertEquals(1, nextDex);
    }

    @Test
    public void getNextDexReturnsXWhenOriginIsXMinusOne() {
        int dexno = AppConfig.NUM_SPECIES - 1;
        int nextDex = speciesService.getNextDex(dexno);
        assertEquals(AppConfig.NUM_SPECIES, nextDex);
    }

    @Test
    public void getPrevDexReturnsXWhenOriginIsOne() {
        int dexno = 1;
        int prevDex = speciesService.getPrevDex(dexno);
        assertEquals(AppConfig.NUM_SPECIES, prevDex);
    }

    @Test
    public void getPrevDexReturnsOneWhenOriginIsTwo() {
        int dexno = 2;
        int prevDex = speciesService.getPrevDex(dexno);
        assertEquals(1, prevDex);
    }

    @Test
    public void getPrevDexReturnsXMinusOneWhenOriginIsX() {
        int dexno = AppConfig.NUM_SPECIES;
        int prevDex = speciesService.getPrevDex(dexno);
        assertEquals(AppConfig.NUM_SPECIES - 1, prevDex);
    }

    @Test
    public void buildSpeciesPageTabDtoAssignsCorrectFields() {
        SpeciesDto species = speciesService.buildSpeciesDto(pikachu);
        SpeciesPageTabDto dto = speciesService.buildSpeciesPageTabDto(species);
        assertNotNull(dto);
        assertEquals(dto.getDexno(), pikachu.getDexno());
        assertEquals(dto.getName(), pikachu.getDisplayName());
    }

    @Test
    public void buildSpeciesPageTabDtoReturnsEmptyDtoWhenSpeciesIsNull() {
        SpeciesPageTabDto dto = speciesService.buildSpeciesPageTabDto(null);
        assertNotNull(dto);
        assertEquals(0, dto.getDexno());
        assertNull(dto.getName());
    }

    @Test
    public void findByDexnoReturnsPikachu() {
        when(speciesRepository.findByDexno(pikachu.getDexno())).thenReturn(Arrays.asList(pikachu));

        List<SpeciesDto> responseList = speciesService.findByDexno(pikachu.getDexno());
        assertFalse(responseList.isEmpty());
        assertEquals(1, responseList.size());

        SpeciesDto dto = responseList.get(0);
        assertEquals(pikachu.getName(), dto.getName());
    }

    @Test
    public void findByNonexistentDexnoReturnsEmptyList() {
        List<SpeciesDto> responseList = speciesService.findByDexno(-1);
        assertNotNull(responseList);
        assertTrue(responseList.isEmpty());
    }

    @Test
    public void buildSpeciesDtoAttachesNextAndPrevDex() {
        when(speciesRepository.findByDexno(pikachu.getDexno() - 1)).thenReturn(Arrays.asList(prevDex));
        when(speciesRepository.findByDexno(pikachu.getDexno() + 1)).thenReturn(Arrays.asList(nextDex));

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(pikachu);
        assertNotNull(speciesDto.getNextSpecies());
        assertNotNull(speciesDto.getPrevSpecies());

        SpeciesPageTabDto nextSpecies = speciesDto.getNextSpecies();
        assertEquals(nextSpecies.getDexno(), pikachu.getDexno() + 1);

        SpeciesPageTabDto prevSpecies = speciesDto.getPrevSpecies();
        assertEquals(prevSpecies.getDexno(), pikachu.getDexno() - 1);
    }

}