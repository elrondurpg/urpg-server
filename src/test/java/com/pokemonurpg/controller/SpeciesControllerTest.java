package com.pokemonurpg.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpeciesControllerTest
{

    @Test
    public void justForKicks() {

    }
    /*
    public static final String POKEMON_NAME="Pikachu";
    public static final Integer POKEMON_DBID = 808;

    public static final String ATTACK_NAME="Fire Blast";
    public static final Integer ATTACK_DBID = 777;
    public static final String ATTACK_METHOD="LEVEL-UP";

    @MockBean
    private SpeciesService speciesService;

    @MockBean
    private SpeciesRepository speciesRepository;

    @MockBean
    private AttackService attackService;

    @MockBean
    private SpeciesAttackService speciesAttackService;

    @MockBean
    private TypeService typeService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllSpeciesTest() {
        Species species = SpeciesTestFactory.createSpecies();
        when(speciesService.findAllNames()).thenReturn(Arrays.asList(species, species, species));

        ResponseEntity<List<Species>> response = restTemplate.exchange("/species/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Species>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void getSpeciesByNameTest() {
        String speciesName = "TestName";
        Species species = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(species);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);
        when(speciesService.findByUsernameStartingWith(speciesName.substring(0, 3))).thenReturn(Optional.of(species));

        ResponseEntity<Species> responseWithFullName = restTemplate.exchange("/species/" + speciesName, HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        assertEquals(HttpStatus.OK, responseWithFullName.getStatusCode());
        assertEquals(speciesName, responseWithFullName.getBody().getName());

        ResponseEntity<Species> responseWithPartialName = restTemplate.exchange("/species/" + speciesName.substring(0, 3), HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        assertEquals(HttpStatus.OK, responseWithPartialName.getStatusCode());
        assertEquals(speciesName, responseWithPartialName.getBody().getName());

        ResponseEntity<Species> responseWithNameOfNonexistentPokemon = restTemplate.exchange("/species/NON_EXISTENT_POKEMON", HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        assertEquals(HttpStatus.NOT_FOUND, responseWithNameOfNonexistentPokemon.getStatusCode());
    }

    @Test
    public void createValidSpeciesTest() {
        String speciesName = "TestName";
        Species species = SpeciesTestFactory.createSpecies(speciesName);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(species);

        ResponseEntity response = restTemplate.exchange("/species/createSpecies", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(speciesService, Mockito.times(1)).save(Mockito.any(Species.class));
    }

    @Test
    public void createSpeciesWithNameEqualToExistingSpeciesShouldFail() {
        String speciesName = "TestName";
        Species species = SpeciesTestFactory.createSpecies(speciesName);

        Optional<Species> speciesOptional = Optional.of(species);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(species);

        ResponseEntity response = restTemplate.exchange("/species/createSpecies", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A Pokemon named " + speciesName + " already exists!", response.getBody());
    }

    @Test
    public void createSpeciesWithInvalidAttributesShouldFail() {
        String speciesName = "TestName";
        Species species = SpeciesTestFactory.createSpecies(speciesName);
        species.setDexno(1000000);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(species);

        ResponseEntity<String> response = restTemplate.exchange("/species/createSpecies", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Dex No. '" + species.getDexno() + "' is invalid."));
    }

    @Test
    public void updateSpeciesWithValidAttributesTest() {
        String speciesName = "TestName";

        Species existingPokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(existingPokemon);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(speciesName);
        updatedPokemon.setDexno(999);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity response = restTemplate.exchange("/species/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pokemon " + speciesName + " was updated successfully!", response.getBody());
        verify(speciesService, Mockito.times(1)).save(Mockito.any(Species.class));
    }

    @Test
    public void updateSpeciesWithInvalidDexnoShouldFail() {
        String speciesName = "TestName";

        Species existingPokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(existingPokemon);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(speciesName);
        updatedPokemon.setDexno(10101);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity<String> response = restTemplate.exchange("/species/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Dex No. '" + updatedPokemon.getDexno() + "' is invalid."));
    }

    @Test
    public void updateSpeciesWithInvalidNameShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species species = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(species);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(species);

        ResponseEntity response = restTemplate.exchange("/species/" + invalidName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateSpeciesWithIncorrectNameInRequestBodyShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species species = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(species);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(invalidName);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity response = restTemplate.exchange("/species/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void deleteSpeciesTest() {
        ResponseEntity response = restTemplate.exchange("/species/DOES_NOT_EXIST", HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteNonexistentSpeciesShouldFail() {
        String speciesName = "TestName";
        Species species = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(species);
        when(speciesService.findByNameExact(speciesName)).thenReturn(speciesOptional);

        ResponseEntity response = restTemplate.exchange("/species/" + speciesName, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pokemon " + speciesName + " was deleted successfully!", response.getBody());
        verify(speciesService, Mockito.times(1)).delete(Mockito.any(Species.class));
    }

    @Test
    public void updateSpeciesWithNewAttackSuccess() {
        Species existingSpecies = mock(Species.class);
        when(existingSpecies.getDbid()).thenReturn(POKEMON_DBID);
        when(speciesService.findByNameExact(POKEMON_NAME)).thenReturn(Optional.of(existingSpecies));

        Attack existingAttack = mock(Attack.class);
        when(existingAttack.getDbid()).thenReturn(ATTACK_DBID);
        when(attackService.findByNameExact(ATTACK_NAME)).thenReturn(Optional.of(existingAttack));

        Species requestedSpecies = SpeciesTestFactory.createSpecies(POKEMON_NAME, POKEMON_DBID);
        when(typeService.findByDbid(Matchers.any(Integer.class))).thenReturn(Optional.of(new Type("Electric")));

        List<SpeciesAttack> speciesAttacks = new ArrayList<>();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        speciesAttack.setAbility(attack);
        speciesAttack.setMethod(ATTACK_METHOD);
        speciesAttacks.add(speciesAttack);
        requestedSpecies.setAttacks(speciesAttacks);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(requestedSpecies);
        doCallRealMethod().when(speciesService).save(any(Species.class));

        ResponseEntity response = restTemplate.exchange("/species/" + POKEMON_NAME, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        verify(speciesService, times(1)).save(any(Species.class));
        verify(speciesAttackService, times(1)).save(any(SpeciesAttack.class));
        assertEquals(POKEMON_DBID, speciesAttack.internalGetId().getSpeciesDbid());
        assertEquals(ATTACK_DBID, speciesAttack.internalGetId().getAttackDbid());
    }

     */
}