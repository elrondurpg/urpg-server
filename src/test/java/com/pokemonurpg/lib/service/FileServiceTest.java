package com.pokemonurpg.lib.service;

import com.pokemonurpg.lib.v1.service.FileService;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileServiceTest {

    private FileService fileService = new FileService();

    @Test
    public void testGetFile() {
        File file = fileService.findByName("any/path/will/do");
        assertNotNull(file);
    }

}