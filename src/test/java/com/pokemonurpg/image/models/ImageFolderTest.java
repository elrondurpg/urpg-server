package com.pokemonurpg.image.models;

import com.pokemonurpg.image.input.ImageFolderInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImageFolderTest {
    private final static String NAME = "FOLDER";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static String EXTENSION = "EXTENSION";
    private final static Integer DBID = 32432;

    @Test
    public void testPojo() {
        ImageFolder imageFolder = new ImageFolder();
        imageFolder.setDescription(DESCRIPTION);
        imageFolder.setExtension(EXTENSION);
        imageFolder.setDbid(DBID);
        imageFolder.setName(NAME);

        assertEquals(NAME, imageFolder.getName());
        assertEquals(DESCRIPTION, imageFolder.getDescription());
        assertEquals(EXTENSION, imageFolder.getExtension());
        assertEquals(DBID, imageFolder.getDbid());
    }

    @Test
    public void testConstructor() {
        ImageFolderInputDto input = new ImageFolderInputDto();
        input.setDescription(DESCRIPTION);
        input.setExtension(EXTENSION);
        input.setName(NAME);

        ImageFolder imageFolder = new ImageFolder(input);

        assertEquals(NAME.toLowerCase(), imageFolder.getName());
        assertEquals(DESCRIPTION, imageFolder.getDescription());
        assertEquals(EXTENSION, imageFolder.getExtension());
    }

}