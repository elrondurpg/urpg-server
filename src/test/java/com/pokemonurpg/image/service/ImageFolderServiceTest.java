package com.pokemonurpg.image.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.core.service.FolderService;
import com.pokemonurpg.image.input.ImageFolderInputDto;
import com.pokemonurpg.image.models.ImageFolder;
import com.pokemonurpg.image.repository.ImageFolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageFolderServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static Integer DBID = 2342;
    private final static String NAME = "folder";
    private final static ImageFolder IMAGE_FOLDER = mock(ImageFolder.class);
    private final static ImageFolder NEW_IMAGE_FOLDER = mock(ImageFolder.class);

    @InjectMocks
    private ImageFolderService imageFolderService;

    @Mock
    private FolderService folderService;

    @Mock
    private ImageFolderRepository imageFolderRepository;

    @Mock
    private AppConfig appConfig;

    private ImageFolderInputDto input;

    @BeforeEach
    public void init() {
        input = new ImageFolderInputDto();
        input.setName(NAME);
    }

    @Test
    public void findAllNames() {
        when(imageFolderRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, imageFolderService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        assertEquals(IMAGE_FOLDER, imageFolderService.findByDbid(DBID));
    }

    @Test
    public void findByName() {
        when(imageFolderRepository.findByName(NAME)).thenReturn(IMAGE_FOLDER);
        assertEquals(IMAGE_FOLDER, imageFolderService.findByName(NAME));
    }

    @Test
    public void createReturnsNullWhenImageFolderExists() {
        // Given an Image Folder with the desired name already exists
        when(imageFolderRepository.findByName(NAME)).thenReturn(IMAGE_FOLDER);

        // When I try to create a new Image Folder with that name
        assertThrows(ResponseStatusException.class, () -> imageFolderService.create(input));

        // Then I will receive a ResponseStatusException
    }

    @Test
    public void createSuceedsWhenImageFolderDoesNotExist() {
        // Given no Image Folder with the desired name already exists
        when(imageFolderRepository.findByName(NAME)).thenReturn(null);

        // Note: We do not care if a folder with the given name already exists on the system
        // We only care whether an ImageFolder object with that name is persisted in the DB already

        // When I try to create a new Image Folder with that name
        imageFolderService.create(input);

        // Then the repository will save the new persisted DB object
        verify(imageFolderRepository, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void updateFailsWhenImageFolderNotFound() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(null);
        assertNull(imageFolderService.update(input, DBID));
    }

    @Test
    public void updateFailsWhenDesiredFolderExists() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        when(imageFolderRepository.findByName(NAME)).thenReturn(NEW_IMAGE_FOLDER);
        assertThrows(ResponseStatusException.class, () -> imageFolderService.update(input, DBID));
    }

    @Test
    public void updateFailsWhenFolderServiceCantRename() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        when(folderService.rename(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> imageFolderService.update(input, DBID));
    }

    @Test
    public void updateSucceeds() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        when(folderService.rename(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(true);
        imageFolderService.update(input, DBID);
        verify(IMAGE_FOLDER, times(1)).update(input);
        verify(imageFolderRepository, times(1)).save(IMAGE_FOLDER);
    }

}