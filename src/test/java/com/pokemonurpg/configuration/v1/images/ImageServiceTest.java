package com.pokemonurpg.configuration.v1.images;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderService;
import com.pokemonurpg.lib.v1.services.FileService;
import com.pokemonurpg.lib.v1.services.ImageIoService;
import com.pokemonurpg.entities.v1.ImageFolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
        when(imageIoService.findByUrl(URL)).thenReturn(IMAGE);

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
        when(imageIoService.findByUrl(URL)).thenThrow(new IOException());

        // When I call ImageService.create()
        ImageInputDto output = imageService.create(input);
    }

    @Test(expected = ResponseStatusException.class)
    public void createFailsOnWrite() throws IOException {
        // Given and ImageInputDto with reasonable FolderName, Name, and Url
        ImageInputDto input = new ImageInputDto();
        input.setFolder(FOLDER_NAME);
        input.setName(NAME);
        input.setUrl(URL);

        // Given we find an image at the given URL
        when(imageIoService.findByUrl(URL)).thenReturn(IMAGE);

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
        ImageInputDto output = imageService.create(input);

        // Then it will throw a Response Status Exception
    }
}