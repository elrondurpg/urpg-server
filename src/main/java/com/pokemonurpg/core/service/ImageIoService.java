package com.pokemonurpg.core.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class ImageIoService {

    public RenderedImage findByUrl(URL url) throws IOException {
        return ImageIO.read(url);
    }

    public void write(RenderedImage image, String extension, File file) throws IOException {
        ImageIO.write(image, extension, file);
    }

}
