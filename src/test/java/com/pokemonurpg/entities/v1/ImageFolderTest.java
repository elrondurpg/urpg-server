package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderRequest;
import org.junit.Test;

import static org.junit.Assert.*;

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
        ImageFolderRequest input = new ImageFolderRequest();
        input.setDescription(DESCRIPTION);
        input.setExtension(EXTENSION);
        input.setName(NAME);

        ImageFolder imageFolder = new ImageFolder(input);

        assertEquals(NAME.toLowerCase(), imageFolder.getName());
        assertEquals(DESCRIPTION, imageFolder.getDescription());
        assertEquals(EXTENSION, imageFolder.getExtension());
    }

}