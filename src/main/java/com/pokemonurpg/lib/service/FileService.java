package com.pokemonurpg.lib.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
    public File findByName(String path) {
        return new File(path);
    }
}
