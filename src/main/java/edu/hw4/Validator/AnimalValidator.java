package edu.hw4.Validator;

import edu.hw4.Animal;
import edu.hw4.Errors.ValidationError;
import java.util.HashSet;
import java.util.Set;

public class AnimalValidator {

    private final static int MAX_HEIGHT = 100;
    private final static int MIN_WEIGHT = 1;

    public AnimalValidator() {}

    public Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.weight() < MIN_WEIGHT) {
            errors.add(new ValidationError<Integer>("weight", animal.weight()));
        }

        if (animal.height() > MAX_HEIGHT) {
            errors.add(new ValidationError<Integer>("height", animal.height()));
        }

        return errors;
    }
}
