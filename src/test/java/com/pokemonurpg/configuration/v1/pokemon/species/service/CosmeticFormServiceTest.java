package com.pokemonurpg.configuration.v1.pokemon.species.service;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.CosmeticForm;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.CosmeticFormRepository;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CosmeticFormServiceTest {
    private final static String NAME = "NAME";
    private final static Integer SPECIES_DBID = 123;
    private final static Species SPECIES = new Species();

    @InjectMocks
    private CosmeticFormService cosmeticFormService;

    @Mock
    private CosmeticFormRepository cosmeticFormRepository;

    @BeforeEach
    public void setupSpecies() {
        SPECIES.setDbid(SPECIES_DBID);
    }

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        CosmeticForm cosmeticForm = new CosmeticForm();

        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(cosmeticFormRepository.findByName(NAME)).thenReturn(cosmeticForm);

        cosmeticFormService.update(SPECIES, input);

        verify(cosmeticFormRepository, times(1)).delete(cosmeticForm);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        CosmeticForm cosmeticForm = mock(CosmeticForm.class);

        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);
        input.setDelete(false);

        when(cosmeticFormRepository.findByName(NAME)).thenReturn(cosmeticForm);

        cosmeticFormService.update(SPECIES, input);

        verify(cosmeticForm, times(1)).update(input);
        verify(cosmeticFormRepository, times(1)).save(cosmeticForm);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);

        cosmeticFormService.update(SPECIES, input);

        verify(cosmeticFormRepository, times(1)).save(ArgumentMatchers.any());
    }

}