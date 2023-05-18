package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourController;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourInputDto;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EliteFourControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static List<String> ELITE_FOURS = new ArrayList<>();

    @InjectMocks
    private EliteFourController eliteFourController;

    @Mock
    private EliteFourService eliteFourService;

    private EliteFour eliteFour = new EliteFour();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNames() {
        when(eliteFourService.findAllNames()).thenReturn(ELITE_FOURS);
        assertEquals(ELITE_FOURS, eliteFourController.findAll());
    }

    @Test
    public void findByName() {
        when(eliteFourService.findByName(NAME)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourController.findByName(NAME));
    }

    @Test
    public void create() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);
        when(eliteFourService.create(input)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourController.create(input));
    }

    @Test
    public void update() {
        EliteFourInputDto input = new EliteFourInputDto();
        input.setName(NAME);
        when(eliteFourService.update(input, DBID)).thenReturn(eliteFour);
        assertEquals(eliteFour, eliteFourController.update(input, DBID));
    }

    @Test
    public void delete() {
        eliteFourController.delete(DBID);
        verify(eliteFourService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(eliteFourService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> eliteFourController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}