package com.pokemonurpg.core.service;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    private FileService fileService = new FileService();

    @Test
    public void testGetFile() {
        File file = fileService.findByName("any/path/will/do");
        assertNotNull(file);
    }

}