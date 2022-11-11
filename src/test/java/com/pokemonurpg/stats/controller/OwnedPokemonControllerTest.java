package com.pokemonurpg.stats.controller;

import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnedPokemonControllerTest {
    private final static Integer DBID = 3432;
    private final static OwnedPokemon POKEMON = mock(OwnedPokemon.class);

    @InjectMocks
    private OwnedPokemonController ownedPokemonController;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findByDbid() {
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(POKEMON);
        assertEquals(POKEMON, ownedPokemonController.findByDbid(DBID));
    }

    @Test
    public void create() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        when(ownedPokemonService.create(input)).thenReturn(POKEMON);
        assertEquals(POKEMON, ownedPokemonController.create(input));
    }

    @Test
    public void update() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        when(ownedPokemonService.update(input, DBID)).thenReturn(POKEMON);
        assertEquals(POKEMON, ownedPokemonController.update(input, DBID));
    }

    @Test
    public void delete() {
        ownedPokemonController.delete(DBID);
        verify(ownedPokemonService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(ownedPokemonService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> ownedPokemonController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
}