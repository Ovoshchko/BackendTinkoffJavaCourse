package edu.project4.ImageProcessor;

import edu.project4.Model.Color;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Pixel;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class GammaCorrection implements ImageProcessor {

    private final double gammaCorrected;

    public GammaCorrection(double gamma) {
        this.gammaCorrected = 1.0 / gamma;
    }

    @Override
    public void process(FractalImage image) {
        Pixel[][] pixels = image.data();
        Pixel defaultPixel = new Pixel(new Color(0, 0, 0), 0);
        double max = 0;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] == null) {
                    pixels[i][j] = defaultPixel;
                } else if (pixels[i][j].hitCount() != 0) {
                    max = Math.max(log10(pixels[i][j].hitCount()), max);
                }
            }
        }
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = correctPixel(pixels[i][j], max);
            }
        }

    }

    private Pixel correctPixel(Pixel pixel, double max) {
        double logNorm = log10(pixel.hitCount()) / max;
        return new Pixel(
            new Color(
                (int) (pixel.color().r() * pow(logNorm, gammaCorrected)),
                (int) (pixel.color().g() * pow(logNorm, gammaCorrected)),
                (int) (pixel.color().b() * pow(logNorm, gammaCorrected))
            ),
            pixel.hitCount()
        );
    }
}
