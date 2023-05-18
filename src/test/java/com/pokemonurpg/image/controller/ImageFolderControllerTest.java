package com.pokemonurpg.image.controller;

import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderController;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderInputDto;
import com.pokemonurpg.entities.v1.ImageFolder;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImageFolderControllerTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "name";
    private final static ImageFolder IMAGE_FOLDER = mock(ImageFolder.class);
    private final static ImageFolderInputDto INPUT = mock(ImageFolderInputDto.class);
    private final static Integer DBID = 32432;

    @InjectMocks
    private ImageFolderController imageFolderController;

    @Mock
    private ImageFolderService imageFolderService;

    @Test
    public void findAllNames() {
        when(imageFolderService.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, imageFolderController.findAllNames());
    }

    @Test
    public void findByName() {
        when(imageFolderService.findByName(NAME)).thenReturn(IMAGE_FOLDER);
        assertEquals(IMAGE_FOLDER, imageFolderController.findByName(NAME));
    }

    @Test
    public void create() {
        when(imageFolderService.create(INPUT)).thenReturn(IMAGE_FOLDER);
        assertEquals(IMAGE_FOLDER, imageFolderController.create(INPUT));
    }

    @Test
    public void update() {
        when(imageFolderService.update(INPUT, DBID)).thenReturn(IMAGE_FOLDER);
        assertEquals(IMAGE_FOLDER, imageFolderController.update(INPUT, DBID));
    }

}