package edu.project4;

import edu.project4.Model.Color;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class ImageUtilTest {

    private final static Path PATH = Path.of("src/test/resources/edu/project4/ImageUtil");

    @BeforeEach
    void create() {
        try {
            Files.createDirectories(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void delete() {
        try {
            deleteContents(new File(PATH.toString()));
            Files.delete(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void save() {
        FractalImage image = new FractalImage(
            new Pixel[][]{
                {new Pixel(new Color(0, 0, 0), 0), new Pixel(new Color(200, 200, 200), 100)},
                {new Pixel(new Color(100, 100, 100), 50), new Pixel(new Color(150, 150, 150), 10)}
            },
            2, 2
        );

        ImageUtil.save(image, PATH, ImageFormat.PNG);

        File[] files = new File(PATH.toString()).listFiles();

        assertTrue(files.length > 0);
    }

    private static void deleteContents(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                file.delete();
            }
        }
    }
}
