package com.pokemonurpg.configuration.v1.site.image.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.site.image.input.ImageFolderInputDto;
import com.pokemonurpg.core.service.FolderService;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.entities.v1.site.ImageFolder;
import com.pokemonurpg.entities.v1.site.ImageFolderRepository;

@Service
public class ImageFolderService {

    @Resource
    private ImageFolderRepository imageFolderRepository;

    @Resource
    private AppConfig appConfig;

    @Resource
    private FolderService folderService;

    public ImageFolder findByDbid(int dbid) {
        return imageFolderRepository.findByDbid(dbid);
    }

    public ImageFolder findByName(String name) {
        return findByNameExact(name);
    }

    public ImageFolder findByNameExact(String name) {
        return imageFolderRepository.findByName(name);
    }

    public ImageFolder create(ImageFolderInputDto input) {
        ImageFolder imageFolder = new ImageFolder(input);

        String imageBase = appConfig.getImageBase() + "/";
        String newName = input.getName();

        if (imageFolderRepository.findByName(newName) == null) {
            folderService.create(imageBase + newName);
            return imageFolderRepository.save(imageFolder);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image folder " + input.getName() + " already exists!");
    }

    public ImageFolder update(ImageFolderInputDto input, int dbid) {
        ImageFolder imageFolder = imageFolderRepository.findByDbid(dbid);

        if (imageFolder != null) {
            String imageBase = appConfig.getImageBase() + "/";
            String oldPath =  imageBase + imageFolder.getName();
            String newName = input.getName();
            String newPath = imageBase + newName;
            ImageFolder conflictingFolder = imageFolderRepository.findByName(newName);
            if (conflictingFolder == null && folderService.rename(oldPath, newPath)) {
                imageFolder.update(input);
                return imageFolderRepository.save(imageFolder);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image folder " + newName + " already exists!");
        }
        return null;
    }
}
