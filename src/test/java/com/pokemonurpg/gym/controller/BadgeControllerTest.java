package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.input.BadgeInputDto;
import com.pokemonurpg.gym.service.BadgeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BadgeControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private BadgeController badgeController;

    @Mock
    private BadgeService badgeService;

    private Badge badge = new Badge();

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

}