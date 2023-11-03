package edu.hw4;

import edu.hw4.Errors.TooMuchHeightError;
import edu.hw4.Errors.ValidationError;
import edu.hw4.Errors.WeightIsToSmallError;
import edu.hw4.Validator.AnimalValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimalValidatorTest {

    private final static List<Animal> animalList = new ArrayList<>(
        List.of(
            new Animal("1", Animal.Type.CAT, Animal.Sex.M, 0, -1, -1, true),
            new Animal("2", Animal.Type.CAT, Animal.Sex.M, 10000, 10000, 10000, false),
            new Animal("3", Animal.Type.CAT, Animal.Sex.M, 2, 100000, -4, true)
        )
    );

    private final AnimalValidator animalValidator = new AnimalValidator();

    @ParameterizedTest
    @DisplayName("--AnimalValidator Test")
    @MethodSource("getErrorsForTest")
    void validateTest(Map<String, Set<ValidationError>> errors) {
        HashMap<String, Set<ValidationError>> errorsActual = new HashMap<>();

        for (Animal animal: animalList) {
            errorsActual.put(animal.name(), animalValidator.validate(animal));
        }

        assertThat(errorsActual).isEqualTo(errors);
    }

    private static Stream<HashMap<String, Set<ValidationError>>> getErrorsForTest() {
        return Stream.of(
            new HashMap<>() {{
                put("1", new HashSet<>(List.of(WeightIsToSmallError.getError())));
                put("2", new HashSet<>(List.of(TooMuchHeightError.getError())));
                put("3", new HashSet<>(List.of(WeightIsToSmallError.getError(), TooMuchHeightError.getError())));
            }}
        );
    }
}
