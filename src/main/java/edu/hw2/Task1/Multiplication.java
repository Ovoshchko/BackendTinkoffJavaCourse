package edu.hw2.Task1;

public record Multiplication(Expr expression1, Expr expression2) implements Expr {

    public double evaluate() {
        return expression1.evaluate() * expression2.evaluate();
    }

}
