package com.pokemonurpg.controller;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.SpeciesService;
import factory.RequestEntityTestFactory;
import factory.SpeciesTestFactory;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SpeciesControllerTest
{
    @MockBean
    private SpeciesService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllSpeciesTest() {
        Species pokemon = SpeciesTestFactory.createSpecies();
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(pokemon, pokemon, pokemon));

        ResponseEntity<List<Species>> response = restTemplate.exchange("/pokemon/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Species>>() {});

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(3, response.getBody().size());
    }

    @Test
    public void getSpeciesByNameTest() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);
        Mockito.when(service.findByNameStartingWith(speciesName.substring(0, 3))).thenReturn(Arrays.asList(pokemon, pokemon, pokemon));

        ResponseEntity<Species> responseWithFullName = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        Assert.assertEquals(HttpStatus.OK, responseWithFullName.getStatusCode());
        Assert.assertEquals(speciesName, responseWithFullName.getBody().getName());

        ResponseEntity<Species> responseWithPartialName = restTemplate.exchange("/pokemon/" + speciesName.substring(0, 3), HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        Assert.assertEquals(HttpStatus.OK, responseWithPartialName.getStatusCode());
        Assert.assertEquals(speciesName, responseWithPartialName.getBody().getName());

        ResponseEntity<Species> responseWithNameOfNonexistentPokemon = restTemplate.exchange("/pokemon/NON_EXISTENT_POKEMON", HttpMethod.GET, null, new ParameterizedTypeReference<Species>() {});
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseWithNameOfNonexistentPokemon.getStatusCode());
    }

    @Test
    public void createValidSpeciesTest() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/create", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).save(Mockito.any(Species.class));
    }

    @Test
    public void createSpeciesWithNameEqualToExistingSpeciesShouldFail() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);

        Optional<Species> speciesOptional = Optional.of(pokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/create", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("A Pokemon named " + speciesName + " already exists!", response.getBody());
    }

    @Test
    public void createSpeciesWithInvalidAttributesShouldFail() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        pokemon.setDexno(1000000);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity<String> response = restTemplate.exchange("/pokemon/create", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Dex No. '" + pokemon.getDexno() + "' is invalid."));
    }

    @Test
    public void updateSpeciesWithValidAttributesTest() {
        String speciesName = "TestName";

        Species existingPokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(existingPokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(speciesName);
        updatedPokemon.setDexno(999);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Pokemon " + speciesName + " was updated successfully!", response.getBody());
        Mockito.verify(service, Mockito.times(1)).save(Mockito.any(Species.class));
    }

    @Test
    public void updateSpeciesWithInvalidDexnoShouldFail() {
        String speciesName = "TestName";

        Species existingPokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(existingPokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(speciesName);
        updatedPokemon.setDexno(10101);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity<String> response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertTrue(response.getBody().contains("Dex No. '" + updatedPokemon.getDexno() + "' is invalid."));
    }

    @Test
    public void updateSpeciesWithInvalidNameShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + invalidName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateSpeciesWithIncorrectNameInRequestBodyShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        Species updatedPokemon = SpeciesTestFactory.createSpecies(invalidName);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(updatedPokemon);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void deleteSpeciesTest() {
        ResponseEntity response = restTemplate.exchange("/pokemon/DOES_NOT_EXIST", HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteNonexistentSpeciesShouldFail() {
        String speciesName = "TestName";
        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        ResponseEntity response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.DELETE, null, new ParameterizedTypeReference<String>() {});
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Pokemon " + speciesName + " was deleted successfully!", response.getBody());
        Mockito.verify(service, Mockito.times(1)).delete(Mockito.any(Species.class));
    }
}