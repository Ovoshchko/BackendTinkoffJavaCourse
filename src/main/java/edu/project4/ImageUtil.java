package edu.project4;

import edu.project4.Model.Color;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;

public final class ImageUtil {

    private final static String FILE_NAME = "FractalFlame-";
    private final static String FOLDERS_SEPARATOR = "/";
    private final static String DOT = ".";
    public static final int RED_SHIFT = 16;
    public static final int GREEN_SHIFT = 8;

    private ImageUtil() {}

    public static void save(FractalImage image, Path path, ImageFormat format) {
        BufferedImage imageToSave = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        saveToImage(image.data(), image.width(), image.height(), imageToSave);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");
            String formattedDateTime = LocalDateTime.now().format(formatter);
            String fileName = path.toString() + FOLDERS_SEPARATOR
                + FILE_NAME + formattedDateTime + DOT + format.getExtension();

            Path file = Files.createFile(Path.of(fileName));
            ImageIO.write(imageToSave, format.getExtension(), new File(file.toString()));
         } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveToImage(Pixel[][] pixels, int width, int height, BufferedImage image) {
        int rgb;
        Color color;
        Color defaultColor = new Color(0, 0, 0);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (pixels[y][x] == null) {
                    color = defaultColor;
                } else {
                    color = pixels[y][x].color();
                }
                rgb = (color.r() << RED_SHIFT) | (color.g() << GREEN_SHIFT) | (color.b());
                image.setRGB(x, y, rgb);
            }
        }
    }
}
