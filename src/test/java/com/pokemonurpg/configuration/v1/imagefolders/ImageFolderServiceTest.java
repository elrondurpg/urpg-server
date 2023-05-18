package com.pokemonurpg.configuration.v1.imagefolders;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.lib.v1.services.FolderService;
import com.pokemonurpg.entities.v1.ImageFolder;
import com.pokemonurpg.infrastructure.v1.data.jpa.ImageFolderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageFolderServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static Integer DBID = 2342;
    private final static String NAME = "folder";
    private final static ImageFolder IMAGE_FOLDER = mock(ImageFolder.class);
    private final static ImageFolder NEW_IMAGE_FOLDER = mock(ImageFolder.class);
    private static final String IMAGE_BASE = "IMAGE_BASE";
    private static final String OLD_NAME = "OLD_NAME";

    @InjectMocks
    private ImageFolderService imageFolderService;

    @Mock
    private FolderService folderService;

    @Mock
    private ImageFolderRepository imageFolderRepository;

    @Mock
    private AppConfig appConfig;

    private ImageFolderInputDto input;

    @Captor
    ArgumentCaptor<ImageFolder> imageFolderArgumentCaptor;

    @Before
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

    @Test(expected = ResponseStatusException.class)
    public void createReturnsNullWhenImageFolderExists() {
        // Given an Image Folder with the desired name already exists
        when(imageFolderRepository.findByName(NAME)).thenReturn(IMAGE_FOLDER);

        // When I try to create a new Image Folder with that name
        imageFolderService.create(input);

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
        verify(imageFolderRepository, times(1)).save(imageFolderArgumentCaptor.capture());
        assertNotNull(imageFolderArgumentCaptor.getValue());
    }

    @Test
    public void updateFailsWhenImageFolderNotFound() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(null);
        assertNull(imageFolderService.update(input, DBID));
    }

    @Test(expected = ResponseStatusException.class)
    public void updateFailsWhenDesiredFolderExists() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        when(imageFolderRepository.findByName(NAME)).thenReturn(NEW_IMAGE_FOLDER);
        imageFolderService.update(input, DBID);
    }

    @Test(expected = ResponseStatusException.class)
    public void updateFailsWhenFolderServiceCantRename() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        when(IMAGE_FOLDER.getName()).thenReturn(OLD_NAME);
        when(appConfig.getImageBase()).thenReturn(IMAGE_BASE);
        when(folderService.rename(IMAGE_BASE + "/" + OLD_NAME, IMAGE_BASE + "/" + NAME)).thenReturn(false);
        imageFolderService.update(input, DBID);
    }

    @Test
    public void updateSucceeds() {
        when(imageFolderRepository.findByDbid(DBID)).thenReturn(IMAGE_FOLDER);
        when(IMAGE_FOLDER.getName()).thenReturn(OLD_NAME);
        when(appConfig.getImageBase()).thenReturn(IMAGE_BASE);
        when(folderService.rename(IMAGE_BASE + "/" + OLD_NAME, IMAGE_BASE + "/" + NAME)).thenReturn(true);
        imageFolderService.update(input, DBID);
        verify(IMAGE_FOLDER, times(1)).update(input);
        verify(imageFolderRepository, times(1)).save(IMAGE_FOLDER);
    }

}