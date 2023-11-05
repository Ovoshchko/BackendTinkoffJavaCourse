package edu.hw4;

import edu.hw4.Errors.ValidationError;
import edu.hw4.Validator.AnimalValidator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/*
    После задания 15 меня смутило мое решение)
    И возник вопрос: тесты должны писаться так же, как и код: чтобы изменение в наполнении листа не ломало программу
    или они пишутся конкретно и не меняются, потому можно захардкодить как в случае с AnimalValidatorTest?
 */
public class TestObjects {

    private final List<Animal> animalList = new ArrayList<>(List.of(
        new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 12, 8, true),
        new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 18, 15, true),
        new Animal("Sparrow", Animal.Type.BIRD, Animal.Sex.F, 1, 6, 0, false),
        new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
        new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.M, 2, 2, 0, false),
        new Animal("Horse", Animal.Type.CAT, Animal.Sex.F, 7, 60, 1000, false),
        new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 5, 10, 1, false),
        new Animal("Tiger", Animal.Type.CAT, Animal.Sex.M, 6, 30, 500, true),
        new Animal("Kangaroo", Animal.Type.DOG, Animal.Sex.F, 4, 50, 100, false),
        new Animal("Hamster", Animal.Type.CAT, Animal.Sex.M, 2, 4, 0, false),
        new Animal("Elephant", Animal.Type.DOG, Animal.Sex.M, 15, 120, 6000, false),
        new Animal("Dolphin", Animal.Type.FISH, Animal.Sex.F, 10, 10, 800, false),
        new Animal("Eagle", Animal.Type.BIRD, Animal.Sex.M, 5, 3, 5, false),
        new Animal("Li on", Animal.Type.CAT, Animal.Sex.M, 8, 42, 400, true),
        new Animal("Rabbit", Animal.Type.CAT, Animal.Sex.F, 2, 8, 3, false),
        new Animal("Penguin", Animal.Type.BIRD, Animal.Sex.M, 3, 2, 1, false),
        new Animal("Snake", Animal.Type.SPIDER, Animal.Sex.M, 4, 4, 2, true),
        new Animal("Guppy", Animal.Type.FISH, Animal.Sex.F, 1, 1, 1, false),
        new Animal("Cheetah", Animal.Type.CAT, Animal.Sex.M, 6, 28, 100, true),
        new Animal("Fer ret", Animal.Type.CAT, Animal.Sex.F, 2, 12, 2, false)
    ));

    public Animal getAnimalWithMaxNameLength() {
        Animal animalWithLongestName = new Animal("", Animal.Type.CAT, Animal.Sex.M, 10, 10, 10, false);

        for (Animal animal: animalList) {
            if (animal.name().length() > animalWithLongestName.name().length()) {
                animalWithLongestName = animal;
            }
        }

        return animalWithLongestName;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
    public Animal.Sex getDominatedSex() {
        return Animal.Sex.M;
    }

    public Map<Animal.Type, Animal> getMostWeightedByCategory() {
        Map<Animal.Type, Animal> animals = new HashMap<>();

        for (Animal animal: animalList) {
            if (animals.containsKey(animal.type())) {
                if (animals.get(animal.type()).weight() < animal.weight()) {
                    animals.put(animal.type(), animal);
                }
            } else {
                animals.put(animal.type(), animal);
            }

        }

        return animals;
    }

    public Animal getKOldestAnimal(int k) {
        List<Animal> animalsOldest = new ArrayList<>(animalList);
        animalsOldest.sort(Comparator.comparingInt(Animal::age).reversed());
        return animalsOldest.get(k-1);
    }

    public Optional<Animal> getWeightedUnderHeight(int k) {
        Animal animalWeighted = null;

        for (Animal animal: animalList) {
            if (animal.height() < k) {
                if ((animalWeighted == null) || (animalWeighted.weight() < animal.weight())) {
                    animalWeighted = animal;
                }
            }
        }

        return Optional.ofNullable(animalWeighted);
    }

    public Integer getPawsSum() {
        int sum = 0;

        for (Animal animal: animalList) {
            sum += animal.type().getPaws();
        }

        return sum;
    }

    public List<Animal> getAnimalsWithUnequalAgeAndPaws() {
        List<Animal> animals = new ArrayList<>();

        for (Animal animal: animalList) {
            if (animal.age() != animal.type().getPaws()) {
                animals.add(animal);
            }
        }

        return animals;
    }

    public List<Animal> getAnimalsByteAndUpperHundredHeight() {
        List<Animal> animals = new ArrayList<>();

        for (Animal animal: animalList) {
            if ((animal.height() > 100) && (animal.bites())) {
                animals.add(animal);
            }
        }

        return animals;
    }

    public Integer countHeightLessThanWeight() {
        List<Animal> animals = new ArrayList<>();

        for (Animal animal: animalList) {
            if (animal.height() < animal.weight()) {
                animals.add(animal);
            }
        }

        return animals.size();
    }

    public List<Animal> getAnimalsWithMoreThanOneName() {
        List<Animal> animals = new ArrayList<>();

        for (Animal animal: animalList) {
            if (animal.name().split(" +").length >= 2) {
                animals.add(animal);
            }
        }

        return animals;
    }

    public boolean hasDogHigherThanK(int k) {
        for (Animal animal: animalList) {
            if ((animal.type() == Animal.Type.DOG) && (animal.height() > k)) {
                return true;
            }
        }

        return false;
    }

    public Map<Animal.Type, Integer> getWeightInAgeBetween(int k, int l) {
        Map<Animal.Type, Integer> animals = new HashMap<>();

        for (Animal animal: animalList) {
            if ((animal.age() > k) && (animal.age() < l)) {
                if (animals.containsKey(animal.type())) {
                    animals.put(animal.type(), animals.get(animal.type()) + animal.weight());
                } else {
                    animals.put(animal.type(), animal.weight());
                }
            }
        }

        return animals;
    }

    public List<Animal> getMultipleSorted() {
        List<Animal> animals = new ArrayList<>(animalList);
        animals.sort(
            Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)
        );

        return animals;
    }

    public boolean doesSpidersBitesMoreThanDogs() {
        Map<Animal.Type, Integer> counts = new HashMap<>() {{
            put(Animal.Type.SPIDER, 0);
            put(Animal.Type.DOG, 0);
        }};

        for (Animal animal: animalList) {
            if (((animal.type() == Animal.Type.SPIDER) || (animal.type() == Animal.Type.DOG)) && (animal.bites())) {
                counts.put(animal.type(), counts.get(animal.type()) + 1);
            }
        }

        return counts.get(Animal.Type.DOG) <= counts.get(Animal.Type.SPIDER);
    }

    public Animal findMostWeightedFish() {
        Animal weightedFish = null;

        for (Animal animal: animalList) {
            if (animal.type() == Animal.Type.FISH) {
                if (weightedFish != null) {
                    if (weightedFish.weight() < animal.weight()) {
                        weightedFish = animal;
                    }
                } else {
                    weightedFish = animal;
                }
            }
        }

        return weightedFish;
    }

    public Map<String, Set<ValidationError>> getErrors() {
        Map<String, Set<ValidationError>> errors = new HashMap<>();
        AnimalValidator animalValidator = new AnimalValidator();
        Set<ValidationError> currentErrors;

        for (Animal animal: animalList) {
            currentErrors = animalValidator.validate(animal);
            if (!currentErrors.isEmpty()) {
                errors.put(animal.name(), currentErrors);
            }
        }

        return errors;
    }

    public Map<String, String> getErrorsAdvanced() {
        Map<String, String> errors = new HashMap<>();
        AnimalValidator animalValidator = new AnimalValidator();
        Set<ValidationError> currentErrors;
        StringBuilder currentString;

        for (Animal animal: animalList) {
            currentString = new StringBuilder();
            currentErrors = animalValidator.validate(animal);
            if (!currentErrors.isEmpty()) {
                for (ValidationError validationError: currentErrors) {
                    currentString.append(validationError.fieldName()).append("; ");
                }
                currentString.replace(currentString.length() - 2, currentString.length(), "");
                errors.put(animal.name(), currentString.toString());
            }
        }

        return errors;
    }
}
