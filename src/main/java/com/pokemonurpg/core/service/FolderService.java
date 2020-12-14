package com.pokemonurpg.core.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service
public class FolderService {

    @Resource
    private FileService fileService;

    public boolean create(String path) {
        if (path != null) {
            return fileService.findByName(path).mkdir();
        }
        return false;
    }

    private boolean create(File file) {
        return file.mkdir();
    }

    public boolean rename(String oldPath, String newPath) {
        if (newPath == null || newPath.equals(oldPath)) return true;

        File oldFile = fileService.findByName(oldPath);
        File newFile = fileService.findByName(newPath);

        if (newFile.exists()) return false;

        if (oldFile.exists())
            return oldFile.renameTo(newFile);
        else return create(newFile);
    }
}
