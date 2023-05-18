package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.entities.v1.CosmeticForm;
import com.pokemonurpg.infrastructure.v1.data.jpa.CosmeticFormRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CosmeticFormServiceTest {
    private final static String NAME = "NAME";
    private final static Integer SPECIES_DBID = 123;

    @InjectMocks
    private CosmeticFormService cosmeticFormService;

    @Mock
    private CosmeticFormRepository cosmeticFormRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        CosmeticForm cosmeticForm = new CosmeticForm();

        CosmeticFormRequest input = new CosmeticFormRequest();
        input.setName(NAME);
        input.setDelete(true);

        when(cosmeticFormRepository.findByName(NAME)).thenReturn(cosmeticForm);

        cosmeticFormService.update(input, SPECIES_DBID);

        verify(cosmeticFormRepository, times(1)).delete(cosmeticForm);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        CosmeticForm cosmeticForm = mock(CosmeticForm.class);

        CosmeticFormRequest input = new CosmeticFormRequest();
        input.setName(NAME);
        input.setDelete(false);

        when(cosmeticFormRepository.findByName(NAME)).thenReturn(cosmeticForm);

        cosmeticFormService.update(input, SPECIES_DBID);

        verify(cosmeticForm, times(1)).update(input);
        verify(cosmeticFormRepository, times(1)).save(cosmeticForm);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        CosmeticFormRequest input = new CosmeticFormRequest();
        input.setName(NAME);

        cosmeticFormService.update(input, SPECIES_DBID);
    }

}