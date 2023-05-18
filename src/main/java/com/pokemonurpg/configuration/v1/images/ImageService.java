package com.pokemonurpg.configuration.v1.images;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderService;
import com.pokemonurpg.entities.v1.ImageFolder;
import com.pokemonurpg.lib.v1.services.FileService;
import com.pokemonurpg.lib.v1.services.ImageIoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    @Resource
    private ImageIoService imageIoService;

    @Resource
    private FileService fileService;

    @Resource
    private ImageFolderService imageFolderService;

    @Resource
    private AppConfig appConfig;

    public ImageRequest create(ImageRequest input) {
        RenderedImage image = null;
        String extension = null;
        File file = null;
        try {
            image = imageIoService.findByUrl(input.getUrl());
            ImageFolder folder = imageFolderService.findByName(input.getFolder());

            String imageBase = appConfig.getImageBase() + "/";
            String folderName = input.getFolder() + "/";
            String fileName = input.getName();
            extension = folder.getExtension();

            file = fileService.findByName( imageBase + folderName + fileName + "." + extension);
        } catch (IOException e) {
            return null;
        }

        try {
            imageIoService.write(image, extension, file);
            return input;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encountered an error while trying to save new file.");
        }
    }

}
