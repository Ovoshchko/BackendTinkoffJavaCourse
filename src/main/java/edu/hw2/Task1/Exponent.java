package edu.hw2.Task1;

public record Exponent(Expr expression, double pow) implements Expr {

    public double evaluate() {
        return Math.pow(expression.evaluate(), pow);
    }

}
