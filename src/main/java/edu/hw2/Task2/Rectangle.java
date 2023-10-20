package edu.hw2.Task2;

public class Rectangle {

    private final double height;
    private final double width;

    Rectangle(double height, double width) {
        this.height = Math.abs(height);
        this.width = Math.abs(width);
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(height, this.width);
    }

    public Rectangle setWidth(double width) {
        return new Rectangle(this.height, width);
    }

    double area() {
        return height * width;
    }
}
