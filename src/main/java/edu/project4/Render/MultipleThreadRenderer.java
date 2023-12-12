package edu.project4.Render;

import edu.project4.Model.ColoredAffineTransformation;
import edu.project4.Model.FractalImage;
import edu.project4.Model.Rectangle;
import edu.project4.Tranformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ParameterNumber")
public class MultipleThreadRenderer implements Renderer {
    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private final ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

    public MultipleThreadRenderer() {}

    @Override
    public FractalImage render(FractalImage canvas, Rectangle world, List<ColoredAffineTransformation> affines,
        List<Transformation> transformations, int samples, short iterPerSample, int symmetry, long seed) {

        for (int i = 0; i < THREAD_COUNT; i++) {
            service.submit(() -> new OneThreadRender(
                canvas, world, affines, transformations, samples / THREAD_COUNT, iterPerSample, symmetry, seed
            ).run());
        }

        try {
            service.shutdown();
            service.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        service.close();

        return canvas;
    }

    private static class OneThreadRender implements Runnable {

        private final static int STEPS_BEFORE = -20;

        private final FractalImage canvas;
        private final Rectangle world;
        private final List<ColoredAffineTransformation> affines;
        private final List<Transformation> transformations;
        private final int samples;
        private final short iterPerSample;
        private final int symmetry;
        private final long seed;

        private OneThreadRender(FractalImage canvas, Rectangle world, List<ColoredAffineTransformation> affines,
            List<Transformation> transformations, int samples, short iterPerSample, int symmetry, long seed) {
            this.canvas = canvas;
            this.world = world;
            this.affines = affines;
            this.transformations = transformations;
            this.samples = samples;
            this.iterPerSample = iterPerSample;
            this.symmetry = symmetry;
            this.seed = seed;
        }

        @Override
        public void run() {
            new OneThreadRenderer().render(
                canvas,
                world,
                affines,
                transformations,
                samples,
                iterPerSample,
                symmetry,
                seed
            );
        }

    }
}
