package com.pokemonurpg.image.controller;

import com.pokemonurpg.image.input.ImageInputDto;
import com.pokemonurpg.image.service.ImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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