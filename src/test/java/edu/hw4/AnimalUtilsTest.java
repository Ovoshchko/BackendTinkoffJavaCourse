package edu.hw4;

import edu.hw4.Validator.AnimalValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class AnimalUtilsTest {

    private final TestObjects testObject = new TestObjects();
    private final List<Animal> animalList = testObject.getAnimalList();
    private final AnimalUtils animalUtils = new AnimalUtils();

    @Nested
    class Task1 {
        @Test
        @DisplayName("--Task1 Test")
        void sortByHeightTest() {
            assertThat(animalUtils.sortByHeight(animalList)).isSortedAccordingTo(Comparator.comparingInt(Animal::height));
        }
    }

    @Nested
    class Task2 {
        @ParameterizedTest
        @DisplayName("--Task2 Test")
        @ValueSource(ints = {1, 4, 10, 20})
        void sortByWeightDescendingTest(int count) {
            assertThat(animalUtils.sortByWeightDescending(animalList, count))
                .isSortedAccordingTo(Comparator.comparingInt(Animal::weight).reversed())
                .hasSize(count);
        }
    }

    @Nested
    class Task3 {
        @Test
        @DisplayName("--Task3 Test")
        void countOfSpeciesTest() {

            Map<Animal.Type, Integer> expected = new HashMap<>();
            expected.put(Animal.Type.SPIDER, 2);
            expected.put(Animal.Type.FISH, 3);
            expected.put(Animal.Type.CAT, 8);
            expected.put(Animal.Type.DOG, 3);
            expected.put(Animal.Type.BIRD, 4);

            assertThat(animalUtils.countOfSpecies(animalList)).isEqualTo(expected);
        }
    }

    @Nested
    class Task4 {
        @Test
        @DisplayName("--Task4 have animals test")
        void hasLongestNameValidTest() throws NoSuchFieldException {
            assertThat(animalUtils.hasLongestName(animalList)).isEqualTo(testObject.getAnimalWithMaxNameLength());
        }

        @Test
        @DisplayName("--Task4 empty animals test")
        void hasLongestNameInvalidTest() {
            assertThrows(NoSuchElementException.class, () -> animalUtils.hasLongestName(new ArrayList<>()));
        }
    }

    @Nested
    class Task5 {
        @Test
        @DisplayName("--Task5 valid Test")
        void hasMoreMaleOrFemaleValidTest() {
            assertThat(animalUtils.hasMoreMaleOrFemale(animalList)).isEqualTo(testObject.getDominatedSex());
        }
    }

    @Nested
    class Task6 {
        @Test
        @DisplayName("--Task6 Test")
        void getHeaviestAnimalsByTypeTest() {
            assertThat(animalUtils.getHeaviestAnimalsByType(animalList))
                .isEqualTo(testObject.getMostWeightedByCategory());
        }
    }

    @Nested
    class Task7 {
        @ParameterizedTest
        @DisplayName("--Task7 Test")
        @ValueSource(ints = {1, 4, 10, 20})
        void kOldestAnimalTest(int k) {
            assertThat(animalUtils.getKOldestAnimal(animalList, k))
                .isEqualTo(testObject.getKOldestAnimal(k));
        }
    }

    @Nested
    class Task8 {
        @ParameterizedTest
        @DisplayName("--Task8 Test")
        @ValueSource(ints = {200, 150, 10, -34})
        void getWeightedUnderHeightTest(int k) {
            assertThat(animalUtils.getWeightedUnderHeight(animalList, k))
                .isEqualTo(testObject.getWeightedUnderHeight(k));
        }
    }

    @Nested
    class Task9 {
        @Test
        @DisplayName("--Task9 Test")
        void sumPawsTest() {
            assertThat(animalUtils.sumPaws(animalList))
                .isEqualTo(testObject.getPawsSum());
        }
    }

    @Nested
    class Task10 {
        @Test
        @DisplayName("--Task10 Test")
        void getAnimalsWithUnequalPawsAndAgeTest() {
            assertThat(animalUtils.getAnimalsWithUnequalPawsAndAge(animalList))
                .isEqualTo(testObject.getAnimalsWithUnequalAgeAndPaws());
        }
    }

    @Nested
    class Task11 {
        @Test
        @DisplayName("--Task11 Test")
        void getAnimalsByteAndUpperHundredHeightTest() {
            assertThat(animalUtils.getAnimalsByteAndUpperHundredHeight(animalList))
                .isEqualTo(testObject.getAnimalsByteAndUpperHundredHeight());
        }
    }

    @Nested
    class Task12 {
        @Test
        @DisplayName("--Task12 Test")
        void countHeightMoreThanWeightTest() {
            assertThat(animalUtils.countHeightLessThanWeight(animalList))
                .isEqualTo(testObject.countHeightLessThanWeight());
        }
    }

    @Nested
    class Task13 {
        @Test
        @DisplayName("--Task13 Test")
        void getAnimalsWithMoreThanOneNameTest() {
            assertThat(animalUtils.getAnimalsWithMoreThanOneName(animalList))
                .isEqualTo(testObject.getAnimalsWithMoreThanOneName());
        }
    }

    @Nested
    class Task14 {
        @ParameterizedTest
        @DisplayName("--Task14 Test")
        @ValueSource(ints = {0, 10, 1000})
        void hasDogHigherThanKTest(int k) {
            assertThat(animalUtils.hasDogHigherThanK(animalList, k))
                .isEqualTo(testObject.hasDogHigherThanK(k));
        }
    }

    @Nested
    class Task15 {
        @ParameterizedTest
        @DisplayName("--Task15 Test")
        @CsvSource(value = {
            "1, 40",
            "10, 50",
            "100, 100"
        })
        void getWeightInAgeBetweenTest(int k, int l) {
            assertThat(animalUtils.getWeightInAgeBetween(animalList, k, l))
                .isEqualTo(testObject.getWeightInAgeBetween(k, l));
        }
    }

    @Nested
    class Task16 {
        @Test
        @DisplayName("--Task16 Test")
        void sortTypeSexNameTest() {
            assertThat(animalUtils.sortTypeSexName(animalList))
                .isEqualTo(testObject.getMultipleSorted());
        }
    }

    @Nested
    class Task17 {
        @Test
        @DisplayName("--Task17 Test")
        void doesSpidersBitesMoreThanDogsTest() {
            assertThat(animalUtils.doesSpidersBitesMoreThanDogs(animalList))
                .isEqualTo(testObject.doesSpidersBitesMoreThanDogs());
        }
    }

    @Nested
    class Task18 {
        @Test
        @DisplayName("--Task18 Test With Two Equal Lists")
        void getWeightedFishInTwoListsTest() {
            List<Animal> firstList = animalList.subList(0, animalList.size() / 2 - 1);
            List<Animal> secondList = animalList.subList(animalList.size() / 2, animalList.size() -1);

            assertThat(animalUtils.getWeightedFishInTwoPlusLists(firstList, secondList))
                .isEqualTo(testObject.findMostWeightedFish());
        }

        @Test
        @DisplayName("--Task18 Test With Several Lists, but only One contains fishes")
        void getWeightedFishInTwoPlusListsTest() {
            List<Animal> firstList = animalList.subList(0, 1);
            List<Animal> secondList = animalList.subList(1, 2);
            List<Animal> thirdList = animalList.subList(2, 4);
            List<Animal> fourthList = animalList.subList(4, animalList.size() -1);

            assertThat(animalUtils.getWeightedFishInTwoPlusLists(firstList, secondList, thirdList, fourthList))
                .isEqualTo(testObject.findMostWeightedFish());
        }
    }

    @Nested
    class Task19 {
        @Test
        @DisplayName("--Task19 Test")
        void getErrorsTest() {
            assertThat(animalUtils.getErrors(animalList))
                .isEqualTo(testObject.getErrors());
        }
    }

    @Nested
    class Task20 {
        @Test
        @DisplayName("--Task20 Test")
        void getErrorsAdvancedTest() {
            assertThat(animalUtils.getErrorsAdvanced(animalList))
                .isEqualTo(testObject.getErrorsAdvanced());
        }
    }
}
