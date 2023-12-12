package edu.project4.ImageProcessor;

import edu.project4.Model.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
