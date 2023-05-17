package com.pokemonurpg.image.controller;

import com.pokemonurpg.configuration.v1.images.ImageController;
import com.pokemonurpg.configuration.v1.images.ImageInputDto;
import com.pokemonurpg.configuration.v1.images.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {
    private final static ImageInputDto INPUT = mock(ImageInputDto.class);

    @InjectMocks
    private ImageController imageController;

    @Mock
    private ImageService imageService;

    @Test
    public void testCreate() {
        when(imageService.create(INPUT)).thenReturn(INPUT);
        assertEquals(INPUT, imageController.create(INPUT));
    }

}