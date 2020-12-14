package com.pokemonurpg.image.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.core.service.FolderService;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.image.models.ImageFolder;
import com.pokemonurpg.image.repository.ImageFolderRepository;
import com.pokemonurpg.image.input.ImageFolderInputDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageFolderService implements NamedObjectService<ImageFolder> {

    @Resource
    private ImageFolderRepository imageFolderRepository;

    @Resource
    private AppConfig appConfig;

    @Resource
    private FolderService folderService;

    public List<String> findAllNames() {
        return imageFolderRepository.findAllNames();
    }

    public ImageFolder findByDbid(int dbid) {
        return imageFolderRepository.findByDbid(dbid);
    }

    public ImageFolder findByName(String name) {
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
