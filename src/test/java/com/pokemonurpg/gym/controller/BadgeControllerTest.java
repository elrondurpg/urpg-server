package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.input.BadgeInputDto;
import com.pokemonurpg.gym.service.BadgeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BadgeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private BadgeController badgeController;

    @Mock
    private BadgeService badgeService;

    private Badge badge = new Badge();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(badgeService.findAllNames()).thenReturn(names);
        assertEquals(names, badgeController.findAllNames());
    }

    @Test
    public void findByName() {
        when(badgeService.findByName(NAME)).thenReturn(badge);
        assertEquals(badge, badgeController.findByName(NAME));
    }

    @Test
    public void create() {
        BadgeInputDto input = new BadgeInputDto();
        input.setName(NAME);
        when(badgeService.create(input)).thenReturn(badge);
        assertEquals(badge, badgeController.create(input));
    }

    @Test
    public void update() {
        BadgeInputDto input = new BadgeInputDto();
        input.setName(NAME);
        when(badgeService.update(input, DBID)).thenReturn(badge);
        assertEquals(badge, badgeController.update(input, DBID));
    }

    @Test
    public void delete() {
        badgeController.delete(DBID);
        verify(badgeService, times(1)).delete(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(badgeService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> badgeController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

}