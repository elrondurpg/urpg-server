package com.pokemonurpg.species.service;

import com.pokemonurpg.species.input.CosmeticFormInputDto;
import com.pokemonurpg.species.models.CosmeticForm;
import com.pokemonurpg.species.repository.CosmeticFormRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CosmeticFormServiceTest {
    private final static String NAME = "NAME";

    @InjectMocks
    private CosmeticFormService cosmeticFormService;

    @Mock
    private CosmeticFormRepository cosmeticFormRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        CosmeticForm cosmeticForm = new CosmeticForm();

        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(cosmeticFormRepository.findByName(NAME)).thenReturn(cosmeticForm);

        cosmeticFormService.update(input);

        verify(cosmeticFormRepository, times(1)).delete(cosmeticForm);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        CosmeticForm cosmeticForm = mock(CosmeticForm.class);

        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);
        input.setDelete(false);

        when(cosmeticFormRepository.findByName(NAME)).thenReturn(cosmeticForm);

        cosmeticFormService.update(input);

        verify(cosmeticForm, times(1)).update(input);
        verify(cosmeticFormRepository, times(1)).save(cosmeticForm);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        CosmeticFormInputDto input = new CosmeticFormInputDto();
        input.setName(NAME);

        cosmeticFormService.update(input);

        verify(cosmeticFormRepository, times(1)).save(Matchers.any());
    }

}