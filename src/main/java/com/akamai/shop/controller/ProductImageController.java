package com.akamai.shop.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@RestController
public class ProductImageController {
    @GetMapping(path = "api/images/{name}/image.png", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage getProductImage(@PathVariable String name) throws IOException {
        final BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/static/t-shirt.jpg")));

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(16f));
        g.setColor(Color.BLACK);
        g.drawString(name, 160, 170);
        g.dispose();

        return image;
    }
}
