package com.pokemonurpg.image.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.core.service.FileService;
import com.pokemonurpg.core.service.ImageIoService;
import com.pokemonurpg.image.input.ImageInputDto;
import com.pokemonurpg.image.models.ImageFolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {
    private final static RenderedImage IMAGE = mock(RenderedImage.class);
    private final static ImageFolder IMAGE_FOLDER = mock(ImageFolder.class);
    private final static String IMAGE_BASE = "imagebase";
    private final static String EXTENSION = "EXTENSION";
    private final static String NAME = "name";
    private final static String URL = "http://www.google.com";

    private final static String FOLDER_NAME = "folder";
    private final static File FILE = mock(File.class);

    @InjectMocks
    private ImageService imageService;

    @Mock
    private ImageFolderService imageFolderService;

    @Mock
    private FileService fileService;

    @Mock
    private AppConfig appConfig;

    @Mock
    private ImageIoService imageIoService;

    @Test
    public void createSucceeds() throws IOException {
        // Given and ImageInputDto with reasonable FolderName, Name, and Url
        ImageInputDto input = new ImageInputDto();
        input.setFolder(FOLDER_NAME);
        input.setName(NAME);
        input.setUrl(URL);

        // Given we find an image at the given URL
        when(imageIoService.findByUrl(ArgumentMatchers.any())).thenReturn(IMAGE);

        // Given we find the requested Image Folder
        when(imageFolderService.findByName(FOLDER_NAME)).thenReturn(IMAGE_FOLDER);

        // Given the Image Folder has a reasonable Extension
        when(IMAGE_FOLDER.getExtension()).thenReturn(EXTENSION);

        // Given the App Config has a correctly configured Image Base
        when(appConfig.getImageBase()).thenReturn(IMAGE_BASE);

        // Given we are able to create a File object for the intended path
        when(fileService.findByName(IMAGE_BASE + "/" + FOLDER_NAME + "/" + NAME + "." + EXTENSION)).thenReturn(FILE);

        // When I call ImageService.create()
        ImageInputDto output = imageService.create(input);

        // Then the ImageIoService will save the image
        verify(imageIoService, times(1)).write(IMAGE, EXTENSION, FILE);

        // and the function will return the input as the output
        assertEquals(input, output);
    }

    @Test
    public void createFailsToFindImage() throws IOException {
        // Given and ImageInputDto with reasonable Url
        ImageInputDto input = new ImageInputDto();
        input.setUrl(URL);

        // Given we don't find an image at the given URL
        when(imageIoService.findByUrl(ArgumentMatchers.any())).thenThrow(new IOException());

        // When I call ImageService.create()
        ImageInputDto output = imageService.create(input);
    }

    @Test
    public void createFailsOnWrite() throws IOException {
        // Given and ImageInputDto with reasonable FolderName, Name, and Url
        ImageInputDto input = new ImageInputDto();
        input.setFolder(FOLDER_NAME);
        input.setName(NAME);
        input.setUrl(URL);

        // Given we find an image at the given URL
        when(imageIoService.findByUrl(ArgumentMatchers.any())).thenReturn(IMAGE);

        // Given we find the requested Image Folder
        when(imageFolderService.findByName(FOLDER_NAME)).thenReturn(IMAGE_FOLDER);

        // Given the Image Folder has a reasonable Extension
        when(IMAGE_FOLDER.getExtension()).thenReturn(EXTENSION);

        // Given the App Config has a correctly configured Image Base
        when(appConfig.getImageBase()).thenReturn(IMAGE_BASE);

        // Given we are able to create a File object for the intended path
        when(fileService.findByName(IMAGE_BASE + "/" + FOLDER_NAME + "/" + NAME + "." + EXTENSION)).thenReturn(FILE);

        // Given the write fails for an unspecified reason
        doThrow(new IOException()).when(imageIoService).write(IMAGE, EXTENSION, FILE);

        // When I call ImageService.create()
        assertThrows(ResponseStatusException.class, () -> { ImageInputDto output = imageService.create(input); });

        // Then it will throw a Response Status Exception
    }
}