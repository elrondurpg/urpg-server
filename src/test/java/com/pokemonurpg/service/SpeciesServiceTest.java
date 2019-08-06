package com.pokemonurpg.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.dto.CosmeticFormDto;
import com.pokemonurpg.dto.SpeciesAttackDto;
import com.pokemonurpg.dto.species.*;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.SpeciesRepository;
import org.junit.Before;
import org.junit.Test;

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
    private AlteredFormMethodService alteredFormMethodService = mock(AlteredFormMethodService.class);
    private EvolutionService evolutionService = mock(EvolutionService.class);
    private MegaEvolutionService megaEvolutionService = mock(MegaEvolutionService.class);

    private Species pikachu = TestObjectFactory.createPikachu();
    private CosmeticForm spikyEaredPikachu = TestObjectFactory.createSpikyEaredPikachu();
    private Species pikachuBelle = TestObjectFactory.createPikachuBelle();
    private Species pichu = TestObjectFactory.createPichu();
    private Species raichu = TestObjectFactory.createRaichu();

    private Species charizard = TestObjectFactory.createCharizard();
    private Species megaCharizardX = TestObjectFactory.createMegaCharizardX();

    private Species nextDex = TestObjectFactory.createNextDex();
    private Species prevDex = TestObjectFactory.createPrevDex();

    private Attack thundershock = TestObjectFactory.createThundershock();
    private Attack thunder = TestObjectFactory.createThunder();
    private Attack voltTackle = TestObjectFactory.createVoltTackle();
    private Attack icicleCrash = TestObjectFactory.createIcicleCrash();

    @Before
    public void initService() {
        speciesService = new SpeciesService(speciesRepository, speciesAttackService, attackService,
                speciesAbilityService, abilityService, alteredFormMethodService, cosmeticFormService,
                evolutionService, megaEvolutionService);
    }

    @Test
    public void serviceReturnsPikachuWithExactName() {
        String name = TestObjectFactory.TEST_SPECIES_NAME;
        when(speciesRepository.findByName(name)).thenReturn(pikachu);

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
        SpeciesPageTabDto dto = speciesService.buildSpeciesPageTabDto(pikachu);
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

        SpeciesDto dto = speciesService.findByDexno(pikachu.getDexno());
        assertEquals(pikachu.getName(), dto.getName());
    }

    @Test
    public void findByNonexistentDexnoReturnsEmptyList() {
        SpeciesDto species = speciesService.findByDexno(-1);
        assertNull(species);
    }

    @Test
    public void buildSpeciesDtoAttachesNextAndPrevDex() {
        when(speciesRepository.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO - 1)).thenReturn(Arrays.asList(prevDex));
        when(speciesRepository.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO + 1)).thenReturn(Arrays.asList(nextDex));

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(pikachu);
        assertNotNull(speciesDto.getNextSpecies());
        assertNotNull(speciesDto.getPrevSpecies());

        SpeciesPageTabDto nextSpecies = speciesDto.getNextSpecies();
        assertEquals(nextSpecies.getDexno(), TestObjectFactory.TEST_SPECIES_DEXNO + 1);

        SpeciesPageTabDto prevSpecies = speciesDto.getPrevSpecies();
        assertEquals(prevSpecies.getDexno(), TestObjectFactory.TEST_SPECIES_DEXNO - 1);
    }

    @Test
    public void findAllByPokedexReturnsPikachuAndPikachuBelle() {
        when(speciesRepository.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO)).thenReturn(Arrays.asList(pikachu, pikachuBelle));

        List<Integer> list = speciesService.findAllSpeciesDbidsByDexno(TestObjectFactory.TEST_SPECIES_DEXNO);
        assertNotNull(list);
        assertEquals(2, list.size());

        assertEquals((Integer) TestObjectFactory.TEST_SPECIES_DBID, list.get(0));
        assertEquals((Integer) TestObjectFactory.TEST_ALTERNATE_FORM_DBID, list.get(1));
    }

    @Test
    public void findAllByNonexistentPokedexReturnsEmptyList() {
        List<Integer> list = speciesService.findAllSpeciesDbidsByDexno(-1);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void buildAlteredFormDtoReturnsPikachuBelle() {
        AlteredFormDto dto = speciesService.buildAlteredFormDto(pikachuBelle);
        assertEquals(pikachuBelle.getDbid(), dto.getDbid());
        assertEquals(pikachuBelle.getName(), dto.getName());
        assertEquals(pikachuBelle.getFormName(), dto.getFormName());
        assertEquals(pikachuBelle.getDisplayName(), dto.getDisplayName());
        assertEquals(pikachuBelle.getType1(), dto.getType1());
        assertEquals(pikachuBelle.getType2(), dto.getType2());
        assertEquals(pikachuBelle.getHp(), dto.getHp());
        assertEquals(pikachuBelle.getAttack(), dto.getAttack());
        assertEquals(pikachuBelle.getDefense(), dto.getDefense());
        assertEquals(pikachuBelle.getSpecialAttack(), dto.getSpecialAttack());
        assertEquals(pikachuBelle.getSpecialDefense(), dto.getSpecialDefense());
        assertEquals(pikachuBelle.getSpeed(), dto.getSpeed());
    }

    @Test
    public void buildAlteredFormFromNull() {
        AlteredFormDto dto = speciesService.buildAlteredFormDto(null);
        assertNull(dto.getName());
    }

    @Test
    public void buildAlteredFormListReturnsPikachuAndPikachuBelle() {
        List<AlteredFormDto> list = speciesService.buildAlteredFormList(Arrays.asList(pikachu, pikachuBelle));
        assertNotNull(list);
        assertEquals(2, list.size());

        AlteredFormDto dto1 = list.get(0);
        assertEquals(TestObjectFactory.TEST_SPECIES_DBID, dto1.getDbid());

        AlteredFormDto dto2 = list.get(1);
        assertEquals(TestObjectFactory.TEST_ALTERNATE_FORM_DBID, dto2.getDbid());
    }

    @Test
    public void buildAlteredFormListFromSingleForm() {
        List<AlteredFormDto> list = speciesService.buildAlteredFormList(Arrays.asList(pikachu));
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void buildAlteredFormListFromNonexistentPokedex() {
        List<AlteredFormDto> list = speciesService.buildAlteredFormList(Collections.emptyList());
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void buildSpeciesDtoAttachesAlteredForm() {
        when(speciesRepository.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO)).thenReturn(Arrays.asList(pikachu, pikachuBelle));

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(pikachu);
        assertNotNull(speciesDto.getAlteredForms());

        List<AlteredFormDto> list = speciesDto.getAlteredForms();
        assertNotNull(list);
        assertEquals(2, list.size());

        AlteredFormDto dto1 = list.get(0);
        assertEquals(TestObjectFactory.TEST_SPECIES_DBID, dto1.getDbid());

        AlteredFormDto dto2 = list.get(1);
        assertEquals(TestObjectFactory.TEST_ALTERNATE_FORM_DBID, dto2.getDbid());
    }

    @Test
    public void buildUniqueMoveListReturnsEmptyListWhenPassedNullList() {
        List<String> uniqueMoves = speciesService.buildUniqueMoveList(null);
        assertNotNull(uniqueMoves);
        assertTrue(uniqueMoves.isEmpty());
    }

    @Test
    public void buildUniqueMoveListReturnsEmptyListWhenPassedEmptyList() {
        List<String> uniqueMoves = speciesService.buildUniqueMoveList(null);
        assertNotNull(uniqueMoves);
        assertTrue(uniqueMoves.isEmpty());
    }

    @Test
    public void buildUniqueMoveListReturnsEmptyListWhenPassedListOfOne() {
        List<String> uniqueMoves = speciesService.buildUniqueMoveList(null);
        assertNotNull(uniqueMoves);
        assertTrue(uniqueMoves.isEmpty());
    }

    public void initAlternateFormMoveLists() {
        SpeciesAttack thundershockRecord = TestObjectFactory.buildSpeciesAttack(thundershock, pikachu);
        SpeciesAttackDto thundershockDto = new SpeciesAttackDto(thundershockRecord);

        SpeciesAttack thundershockRecord2 = TestObjectFactory.buildSpeciesAttack(thundershock, pikachuBelle);
        SpeciesAttackDto thundershockDto2 = new SpeciesAttackDto(thundershockRecord2);

        SpeciesAttack thunderRecord = TestObjectFactory.buildSpeciesAttack(thunder, pikachu);
        thunderRecord.setMethod("LEVEL-UP");
        SpeciesAttackDto thunderDto = new SpeciesAttackDto(thunderRecord);

        SpeciesAttack thunderRecord2 = TestObjectFactory.buildSpeciesAttack(thunder, pikachuBelle);
        thunderRecord2.setMethod("SPECIAL");
        SpeciesAttackDto thunderDto2 = new SpeciesAttackDto(thunderRecord2);

        SpeciesAttack voltTackleRecord = TestObjectFactory.buildSpeciesAttack(voltTackle, pikachu);
        SpeciesAttackDto voltTackleDto = new SpeciesAttackDto(voltTackleRecord);

        SpeciesAttack icicleCrashRecord = TestObjectFactory.buildSpeciesAttack(icicleCrash, pikachuBelle);
        SpeciesAttackDto icicleCrashDto = new SpeciesAttackDto(icicleCrashRecord);

        when(speciesAttackService.findBySpeciesDbid(TestObjectFactory.TEST_SPECIES_DBID)).thenReturn(Arrays.asList(thundershockDto, thunderDto, voltTackleDto));
        when(speciesAttackService.findBySpeciesDbid(TestObjectFactory.TEST_ALTERNATE_FORM_DBID)).thenReturn(Arrays.asList(thundershockDto2, thunderDto2, icicleCrashDto));
    }

    @Test
    public void buildUniqueMoveListReturnsUniqueMoves() {
        initAlternateFormMoveLists();

        AlteredFormDto pikachuDto = new AlteredFormDto(pikachu);
        AlteredFormDto pikachuBelleDto = new AlteredFormDto(pikachuBelle);

        List<String> uniqueMoves = speciesService.buildUniqueMoveList(Arrays.asList(pikachuDto, pikachuBelleDto));
        assertNotNull(uniqueMoves);
        assertTrue(uniqueMoves.contains(TestObjectFactory.TEST_ATTACK_2_NAME));
        assertTrue(uniqueMoves.contains(TestObjectFactory.TEST_ATTACK_3_NAME));
        assertTrue(uniqueMoves.contains(TestObjectFactory.TEST_ATTACK_4_NAME));

        HashMap<String, String> pikachuUniqueAttacks = pikachuDto.getUniqueAttacks();
        assertNotNull(pikachuUniqueAttacks);
        assertFalse(pikachuUniqueAttacks.containsKey(TestObjectFactory.TEST_ATTACK_1_NAME));
        assertTrue(pikachuUniqueAttacks.containsKey(TestObjectFactory.TEST_ATTACK_2_NAME));
        assertEquals("LEVEL-UP", pikachuUniqueAttacks.get(TestObjectFactory.TEST_ATTACK_2_NAME));
        assertTrue(pikachuUniqueAttacks.containsKey(TestObjectFactory.TEST_ATTACK_3_NAME));
        assertNull(pikachuUniqueAttacks.get(TestObjectFactory.TEST_ATTACK_4_NAME));

        HashMap<String, String> pikachuBelleUniqueAttacks = pikachuBelleDto.getUniqueAttacks();
        assertNotNull(pikachuBelleUniqueAttacks);
        assertFalse(pikachuBelleUniqueAttacks.containsKey(TestObjectFactory.TEST_ATTACK_1_NAME));
        assertTrue(pikachuBelleUniqueAttacks.containsKey(TestObjectFactory.TEST_ATTACK_2_NAME));
        assertEquals("SPECIAL", pikachuBelleUniqueAttacks.get(TestObjectFactory.TEST_ATTACK_2_NAME));
        assertNull(pikachuBelleUniqueAttacks.get(TestObjectFactory.TEST_ATTACK_3_NAME));
        assertTrue(pikachuBelleUniqueAttacks.containsKey(TestObjectFactory.TEST_ATTACK_4_NAME));
    }

    @Test
    public void buildCosmeticFormsReturnsEmptyListWhenSpeciesIsNull() {
        List<CosmeticFormDto> list = speciesService.buildCosmeticForms(null, null);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void buildCosmeticFormsReturnsCorrectlyWhenAlteredFormsIsNull() {
        List<CosmeticFormDto> list = new ArrayList<>();
        list.add(new CosmeticFormDto(spikyEaredPikachu));

        when(cosmeticFormService.findBySpeciesDbid(pikachu.getDbid())).thenReturn(list);

        List<CosmeticFormDto> results = speciesService.buildCosmeticForms(pikachu, null);
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(spikyEaredPikachu.getDisplayName(), results.get(0).getDisplayName());
    }

    @Test
    public void buildCosmeticFormsReturnsCorrectlyWhenAlteredFormsIsEmpty() {
        List<CosmeticFormDto> list = new ArrayList<>();
        list.add(new CosmeticFormDto(spikyEaredPikachu));

        when(cosmeticFormService.findBySpeciesDbid(pikachu.getDbid())).thenReturn(list);

        List<CosmeticFormDto> results = speciesService.buildCosmeticForms(pikachu, Collections.emptyList());
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(spikyEaredPikachu.getDisplayName(), results.get(0).getDisplayName());
    }

    @Test
    public void buildCosmeticFormsReturnsCosmeticVariationsOfAlteredForms() {
        List<CosmeticFormDto> list = new ArrayList<>();
        list.add(new CosmeticFormDto(spikyEaredPikachu));

        when(cosmeticFormService.findBySpeciesDbid(pikachuBelle.getDbid())).thenReturn(list);

        List<CosmeticFormDto> results = speciesService.buildCosmeticForms(pikachu, Arrays.asList(new AlteredFormDto(pikachu), new AlteredFormDto(pikachuBelle)));
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(spikyEaredPikachu.getDisplayName(), results.get(0).getDisplayName());
    }

    @Test
    public void buildSpeciesDtoAttachesAlteredFormMethod() {
        when(alteredFormMethodService.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO)).thenReturn(TestObjectFactory.TEST_ALTERNATE_FORM_METHOD);

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(pikachu);
        assertEquals(TestObjectFactory.TEST_ALTERNATE_FORM_METHOD, speciesDto.getAlteredFormMethod());
    }

    @Test
    public void buildEvolutionFamily() {
        TestObjectFactory.buildEvolutionRelation(evolutionService, null, pichu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pichu, pikachu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pikachu, raichu);

        when(speciesRepository.findByDbid(pichu.getDbid())).thenReturn(pichu);
        when(speciesRepository.findByDbid(pikachu.getDbid())).thenReturn(pikachu);
        when(speciesRepository.findByDbid(raichu.getDbid())).thenReturn(raichu);

        List<List<EvolutionFamilyMemberDto>> evolutionFamily = speciesService.buildEvolutionFamily(raichu);
        assertEquals(3, evolutionFamily.size());
        assertEquals(1, evolutionFamily.get(0).size());
        assertEquals(pichu.getName(), evolutionFamily.get(0).get(0).getName());
        assertEquals(1, evolutionFamily.get(1).size());
        assertEquals(pikachu.getName(), evolutionFamily.get(1).get(0).getName());
        assertEquals(1, evolutionFamily.get(2).size());
        assertEquals(raichu.getName(), evolutionFamily.get(2).get(0).getName());
    }

    @Test
    public void getBasicFormReturnsThisIfBasicAlready() {
        when(evolutionService.getPreEvolutionDbid(pichu.getDbid())).thenReturn(-1);
        EvolutionFamilyMemberDto basic = speciesService.findBasicForm(pichu);
        assertEquals(TestObjectFactory.TEST_PRE_EVOLUTION_NAME, basic.getName());
        assertEquals(TestObjectFactory.TEST_PRE_EVOLUTION_DBID, basic.getDbid());
        assertNull(basic.getMethod());
    }

    @Test
    public void getBasicFormReturnsBasicFormOfThirdStage() {
        TestObjectFactory.buildEvolutionRelation(evolutionService, null, pichu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pichu, pikachu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pikachu, raichu);

        when(speciesRepository.findByDbid(pichu.getDbid())).thenReturn(pichu);

        EvolutionFamilyMemberDto basic = speciesService.findBasicForm(raichu);
        assertEquals(TestObjectFactory.TEST_PRE_EVOLUTION_NAME, basic.getName());
        assertEquals(TestObjectFactory.TEST_PRE_EVOLUTION_DBID, basic.getDbid());
        assertNull(basic.getMethod());
    }

    @Test
    public void getBasicFormReturnsNullWhenSpeciesIsNull() {
        EvolutionFamilyMemberDto basic = speciesService.findBasicForm(null);
        assertNull(basic.getName());
        assertNull(basic.getMethod());
    }

    @Test(expected = IllegalStateException.class)
    public void getBasicFormThrowsIllegalStateExceptionWhenBasicFormDoesntExist() {
        TestObjectFactory.buildEvolutionRelation(evolutionService, null, pichu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pichu, pikachu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pikachu, raichu);

        EvolutionFamilyMemberDto basic = speciesService.findBasicForm(raichu);
    }

    @Test
    public void findByDbid() {
        when(speciesRepository.findByDbid(pikachu.getDbid())).thenReturn(pikachu);

        Species result = speciesService.findByDbid(pikachu.getDbid());
        assertNotNull(result);
        assertEquals(result.getDbid(), pikachu.getDbid());
        assertEquals(result.getName(), pikachu.getName());
    }

    @Test
    public void findByNonexistentDbid() {
        Species result = speciesService.findByDbid(-1);
        assertNull(result);
    }

    @Test
    public void buildSpeciesDtoAttachesEvolutionFamily() {
        TestObjectFactory.buildEvolutionRelation(evolutionService, null, pichu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pichu, pikachu);
        TestObjectFactory.buildEvolutionRelation(evolutionService, pikachu, raichu);

        when(speciesRepository.findByDbid(pichu.getDbid())).thenReturn(pichu);
        when(speciesRepository.findByDbid(pikachu.getDbid())).thenReturn(pikachu);
        when(speciesRepository.findByDbid(raichu.getDbid())).thenReturn(raichu);

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(pikachu);
        List<List<EvolutionFamilyMemberDto>> evolutionFamily = speciesDto.getEvolutionFamily();
        assertEquals(3, evolutionFamily.size());
        assertEquals(1, evolutionFamily.get(0).size());
        assertEquals(pichu.getName(), evolutionFamily.get(0).get(0).getName());
        assertEquals(1, evolutionFamily.get(1).size());
        assertEquals(pikachu.getName(), evolutionFamily.get(1).get(0).getName());
        assertEquals(1, evolutionFamily.get(2).size());
        assertEquals(raichu.getName(), evolutionFamily.get(2).get(0).getName());
    }

    @Test
    public void buildSpeciesDtoAttachesMegaEvolutions() {
        when(megaEvolutionService.findByOriginalDbid(TestObjectFactory.TEST_MEGA_EVOLUTION_ORIGINAL_DBID)).thenReturn(Arrays.asList(new MegaEvolutionDto(megaCharizardX, TestObjectFactory.TEST_MEGA_STONE)));

        SpeciesDto speciesDto = speciesService.buildSpeciesDto(charizard);
        List<MegaEvolutionDto> megaEvolutionDtos = speciesDto.getMegaEvolutions();
        assertEquals(1, megaEvolutionDtos.size());
        assertEquals(TestObjectFactory.TEST_MEGA_EVOLUTION_DBID, megaEvolutionDtos.get(0).getDbid());
    }

}