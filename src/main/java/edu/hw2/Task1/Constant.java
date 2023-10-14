package edu.hw2.Task1;

public record Constant(double number) implements Expr {

    public double evaluate() {
        return number;
    }

}
