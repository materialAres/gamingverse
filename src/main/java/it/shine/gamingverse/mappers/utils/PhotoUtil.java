package it.shine.gamingverse.mappers.utils;

import it.shine.gamingverse.dtos.PhotoDto;
import it.shine.gamingverse.entities.AbstractPhoto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class PhotoUtil {

    public static void setPhoto(PhotoDto photoDto, AbstractPhoto abstractPhoto) {
        byte[] decodedBytes = Base64.getDecoder().decode(photoDto.getContent());

        try (InputStream in = new ByteArrayInputStream(decodedBytes)) {
            BufferedImage photo = ImageIO.read(in);

            ByteArrayOutputStream photoByte = new ByteArrayOutputStream();

            try {
                ImageIO.write(photo, "png", photoByte);
            } catch (IOException e) {
                System.out.println("There was a problem encoding the image");
            }

            abstractPhoto.setContent(photoByte.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
