package edu.project4.Model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record Color(int r, int g, int b) {

    private final static int BORDER = 256;

    public static Color getRandomColor() {
        Random random = ThreadLocalRandom.current();
        return new Color(random.nextInt(BORDER), random.nextInt(BORDER), random.nextInt(BORDER));
    }
}
