package edu.project4.ImageProcessor;

import edu.project4.Model.Color;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GammaCorrectionTest {

    private ImageProcessor processor;

    @BeforeEach
    void create() {
        processor = new GammaCorrection(2.2);
    }

    @Test
    void process() {
        FractalImage image = new FractalImage(
            new Pixel[][]{
                {new Pixel(new Color(0, 0, 0), 0), new Pixel(new Color(200, 200, 200), 100)},
                {new Pixel(new Color(100, 100, 100), 50), new Pixel(new Color(150, 150, 150), 10)}
            },
            2, 2
        );

        FractalImage answer = new FractalImage(
            new Pixel[][]{
                {new Pixel(new Color(0, 0, 0), 0), new Pixel(new Color(200, 200, 200), 100)},
                {new Pixel(new Color(92, 92, 92), 50), new Pixel(new Color(109, 109, 109), 10)}
            },
            2, 2
        );

        processor.process(image);
        assertArrayEquals(answer.data(), image.data());
    }
}
