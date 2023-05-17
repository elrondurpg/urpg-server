package com.pokemonurpg.controller;

import com.pokemonurpg.URPGServerApplication;
import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.object.Type;
import com.pokemonurpg.repository.SpeciesRepository;
import com.pokemonurpg.service.AttackService;
import com.pokemonurpg.service.SpeciesAttackService;
import com.pokemonurpg.service.SpeciesService;
import com.pokemonurpg.service.TypeService;
import factory.RequestEntityTestFactory;
import factory.SpeciesTestFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

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
        Species pokemon = SpeciesTestFactory.createSpecies();
        when(speciesService.findAll()).thenReturn(Arrays.asList(pokemon, pokemon, pokemon));

        ResponseEntity<List<Species>> response = restTemplate.exchange("/pokemon/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Species>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void getSpeciesByNameTest() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);
        when(speciesService.findByUsernameStartingWith(speciesName.substring(0, 3))).thenReturn(Optional.of(pokemon));

        ResponseEntity<Species> responseWithFullName = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        assertEquals(HttpStatus.OK, responseWithFullName.getStatusCode());
        assertEquals(speciesName, responseWithFullName.getBody().getName());

        ResponseEntity<Species> responseWithPartialName = restTemplate.exchange("/pokemon/" + speciesName.substring(0, 3), HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        assertEquals(HttpStatus.OK, responseWithPartialName.getStatusCode());
        assertEquals(speciesName, responseWithPartialName.getBody().getName());

        ResponseEntity<Species> responseWithNameOfNonexistentPokemon = restTemplate.exchange("/pokemon/NON_EXISTENT_POKEMON", HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        assertEquals(HttpStatus.NOT_FOUND, responseWithNameOfNonexistentPokemon.getStatusCode());
    }

    @Test
    public void createValidSpeciesTest() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/createSpecies", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(speciesService, Mockito.times(1)).save(Mockito.any(Species.class));
    }

    @Test
    public void createSpeciesWithNameEqualToExistingSpeciesShouldFail() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);

        Optional<Species> speciesOptional = Optional.of(pokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/createSpecies", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A Pokemon named " + speciesName + " already exists!", response.getBody());
    }

    @Test
    public void createSpeciesWithInvalidAttributesShouldFail() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        pokemon.setDexno(1000000);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity<String> response = restTemplate.exchange("/pokemon/createSpecies", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Dex No. '" + pokemon.getDexno() + "' is invalid."));
    }

    @Test
    public void updateSpeciesWithValidAttributesTest() {
        String speciesName = "TestName";

        Species existingPokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(existingPokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(speciesName);
        updatedPokemon.setDexno(999);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pokemon " + speciesName + " was updated successfully!", response.getBody());
        verify(speciesService, Mockito.times(1)).save(Mockito.any(Species.class));
    }

    @Test
    public void updateSpeciesWithInvalidDexnoShouldFail() {
        String speciesName = "TestName";

        Species existingPokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(existingPokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(speciesName);
        updatedPokemon.setDexno(10101);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity<String> response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Dex No. '" + updatedPokemon.getDexno() + "' is invalid."));
    }

    @Test
    public void updateSpeciesWithInvalidNameShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + invalidName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateSpeciesWithIncorrectNameInRequestBodyShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(invalidName);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void deleteSpeciesTest() {
        ResponseEntity response = restTemplate.exchange("/pokemon/DOES_NOT_EXIST", HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteNonexistentSpeciesShouldFail() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        when(speciesService.findByUsername(speciesName)).thenReturn(speciesOptional);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pokemon " + speciesName + " was deleted successfully!", response.getBody());
        verify(speciesService, Mockito.times(1)).delete(Mockito.any(Species.class));
    }

    @Test
    public void updateSpeciesWithNewAttackSuccess() {
        Species existingSpecies = mock(Species.class);
        when(existingSpecies.getDbid()).thenReturn(POKEMON_DBID);
        when(speciesService.findByUsername(POKEMON_NAME)).thenReturn(Optional.of(existingSpecies));

        Attack existingAttack = mock(Attack.class);
        when(existingAttack.getDbid()).thenReturn(ATTACK_DBID);
        when(attackService.findByUsername(ATTACK_NAME)).thenReturn(Optional.of(existingAttack));

        Species requestedSpecies = SpeciesTestFactory.createSpecies(POKEMON_NAME, POKEMON_DBID);
        when(typeService.findByDbid(Matchers.any(Integer.class))).thenReturn(Optional.of(new Type("Electric")));

        List<SpeciesAttack> speciesAttacks = new ArrayList<>();
        SpeciesAttack speciesAttack = new SpeciesAttack();
        Attack attack = new Attack();
        attack.setName(ATTACK_NAME);
        speciesAttack.setAttack(attack);
        speciesAttack.setMethod(ATTACK_METHOD);
        speciesAttacks.add(speciesAttack);
        requestedSpecies.setAttacks(speciesAttacks);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(requestedSpecies);
        doCallRealMethod().when(speciesService).save(any(Species.class));

        ResponseEntity response = restTemplate.exchange("/pokemon/" + POKEMON_NAME, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        verify(speciesService, times(1)).save(any(Species.class));
        verify(speciesAttackService, times(1)).save(any(SpeciesAttack.class));
        assertEquals(POKEMON_DBID, speciesAttack.internalGetId().getSpeciesDbid());
        assertEquals(ATTACK_DBID, speciesAttack.internalGetId().getAttackDbid());
    }

     */
}