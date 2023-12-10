package edu.project4.Tranformation;

public enum TransformationList {
    Cylinder(new CylinderTransformation()),
    Diamond(new DiamondTransformation()),
    Disc(new DiscTransformation()),
    Exponential(new ExponentialTransformation()),
    Handkerchief(new HandkerchiefTransformation()),
    Heart(new HeartTransformation()),
    HorseShoe(new HorseShoeTransformation()),
    Linear(new LinearTransformation()),
    Polar(new PolarTransformation()),
    Sinusoidal(new SinusoidalTransformation()),
    Spherical(new SphericalTransformation()),
    Swirl(new SwirlTransformation());

    private final Transformation transformation;

    TransformationList(Transformation transformation) {
        this.transformation = transformation;
    }

    public Transformation getTransformation() {
        return transformation;
    }
}
