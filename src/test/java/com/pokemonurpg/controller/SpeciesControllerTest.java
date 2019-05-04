package com.pokemonurpg.controller;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.SpeciesService;
import factory.RequestEntityTestFactory;
import factory.SpeciesTestFactory;
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
    public void updateSpeciesWithInvalidNameShouldFail() {
        String speciesName = "TestName";
        String invalidName = "Test";

        Species pokemon = SpeciesTestFactory.createSpecies(speciesName);
        Optional<Species> speciesOptional = Optional.of(pokemon);
        Mockito.when(service.findByName(speciesName)).thenReturn(speciesOptional);

        HttpEntity<Species> requestEntity = RequestEntityTestFactory.createSpeciesRequestEntity(pokemon);

        ResponseEntity<Species> response = restTemplate.exchange("/pokemon/" + invalidName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Species>() {});
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

        ResponseEntity<Species> response = restTemplate.exchange("/pokemon/" + speciesName, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<Species>() {});
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
}