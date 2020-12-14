package com.pokemonurpg.image.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.core.service.FileService;
import com.pokemonurpg.core.service.ImageIoService;
import com.pokemonurpg.image.input.ImageInputDto;
import com.pokemonurpg.image.models.ImageFolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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

    public ImageInputDto create(ImageInputDto input) {
        RenderedImage image = null;
        String extension = null;
        File file = null;
        try {
            image = imageIoService.findByUrl(new URL(input.getUrl()));
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