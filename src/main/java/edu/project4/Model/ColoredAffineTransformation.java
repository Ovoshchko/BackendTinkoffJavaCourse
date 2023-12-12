package edu.project4.Model;

import edu.project4.Tranformation.Affines.AffineTransformation;
import edu.project4.Tranformation.Affines.PreTransformation;

public record ColoredAffineTransformation(PreTransformation transformation, Color color) {

    public static ColoredAffineTransformation getNewColoredAffineTransformation() {
        return new ColoredAffineTransformation(AffineTransformation.getRandomTransformation(), Color.getRandomColor());
    }
}
