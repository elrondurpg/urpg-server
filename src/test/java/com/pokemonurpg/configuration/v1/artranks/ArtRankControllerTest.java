package com.pokemonurpg.configuration.v1.artranks;

import com.pokemonurpg.entities.v1.ArtRank;
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
public class ArtRankControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ArtRankController artRankController;

    @Mock
    private ArtRankService artRankService;

    private ArtRank artRank = new ArtRank();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(artRankService.findAllNames()).thenReturn(names);
        assertEquals(names, artRankController.findAllNames());
    }

    @Test
    public void findByName() {
        when(artRankService.findByName(NAME)).thenReturn(artRank);
        assertEquals(artRank, artRankController.findByName(NAME));
    }

    @Test
    public void create() {
        ArtRankRequest input = new ArtRankRequest();
        input.setName(NAME);
        when(artRankService.create(input)).thenReturn(artRank);
        assertEquals(artRank, artRankController.create(input));
    }

    @Test
    public void update() {
        ArtRankRequest input = new ArtRankRequest();
        input.setName(NAME);
        when(artRankService.update(input, DBID)).thenReturn(artRank);
        assertEquals(artRank, artRankController.update(input, DBID));
    }

}