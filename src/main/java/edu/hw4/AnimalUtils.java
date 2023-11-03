package edu.hw4;

import edu.hw4.Errors.ValidationError;
import edu.hw4.Validator.AnimalValidator;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalUtils {

    private final static int LOWEST_HEIGHT = 100;

    private final static int MIN_WORDS_IN_NAME = 2;
    private final AnimalValidator animalValidator = new AnimalValidator();

    /**
     * Сортирует животных по возрастанию показателя роста
     *
     * @param animals Список животных
     * @return Отсортированный список животных
     */
    public List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    /***
     * Сортирует животных по уменьшению показателя веса и возвращает первые k из них
     *
     * @param animals Список животных
     * @param count Количество животных, которое надо вернуть
     * @return Отсортированный список животных
     */
    public List<Animal> sortByWeightDescending(List<Animal> animals, int count) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(count)
            .toList();
    }

    /***
     * Возвращает Map и количество особей каждого вида
     *
     * @param animals Список животных
     * @return Map, где ключ - вид, значение - количество особей
     */
    public Map<Animal.Type, Integer> countOfSpecies(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    /***
     * Ищет животное с наибольшей длиной имени
     *
     * @param animals Список животных
     * @return Животное с наибольшей длиной названия
     */
    public Animal hasLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElseThrow(NoSuchElementException::new);
    }

    /***
     * Возвращает пол наиболее представленный среди животных
     *
     * @param animals Список животных
     * @return Наиболее представленный пол
     */
    public Animal.Sex hasMoreMaleOrFemale(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /***
     * Ищет самое тяжелое животное в каждом типе
     *
     * @param animals Список животных
     * @return Map, где ключ - тип животного, значение - самое тяжелое животное класса
     */
    public Map<Animal.Type, Animal> getHeaviestAnimalsByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                Optional::get
            )));
    }

    /***
     * Возвращает k-е старое животное
     *
     * @param animals Список животных
     * @param k Номер требуемого животного
     * @return Жиовтное, k-e по возрасту, или 0 в случае ошибки
     */
    public Animal getKOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    /***
     * Возвращает самое тяжелое животное ниже определенного роста
     *
     * @param animals Список животных
     * @param k Предел роста
     * @return Optional(Жиовтное) с мкксимальным весом и ниже k если есть такое,
     *         Optional.empty если нет такого животного
     */
    public Optional<Animal> getWeightedUnderHeight(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    /***
     * Суммирует количество лап всех животных из списка
     *
     * @param animals Список животных
     * @return Сумму лап всех животных из списка
     */
    public Integer sumPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws).sum();
    }

    /***
     * Собирает животных, у которых не совпадает количество лап и возраст
     *
     * @param animals Список животных
     * @return List с животными, у которых не совпадает количество лап и возраст
     */
    public List<Animal> getAnimalsWithUnequalPawsAndAge(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    /***
     * Собирает животных выше 100, которые могут кусаться
     *
     * @param animals Список животных
     * @return List с животными выше 100, которые могут укусить
     */
    public List<Animal> getAnimalsByteAndUpperHundredHeight(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > LOWEST_HEIGHT)
            .toList();
    }

    /***
     * Считает количество животных, у которых вес больше их роста
     *
     * @param animals Список животных
     * @return Количество животных, у которых вес больше их роста
     */
    public Integer countHeightLessThanWeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.height() < animal.weight())
            .mapToInt(animal -> 1).sum();
    }

    /***
     * Собирает животных с именем, состоящим из двух или более слов
     *
     * @param animals Список животных
     * @return List с животными, чьи имена состоят из 2+ слов
     */
    public List<Animal> getAnimalsWithMoreThanOneName(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" +").length >= MIN_WORDS_IN_NAME)
            .toList();
    }

    /***
     * Ищет собаку, которая выше k см
     *
     * @param animals Список животных
     * @return true, если в списке есть собака выше k см, false, если такого нет
     */
    public boolean hasDogHigherThanK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .anyMatch(animal -> animal.height() > k);
    }

    public Map<Animal.Type, Integer> getWeightInAgeBetween(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() > k && animal.age() < l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /***
     * Сортирует список сначала по типу, потом по полу, потом по имени
     *
     * @param animals Список животных
     * @return Отсортированный List
     */
    public List<Animal> sortTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    /***
     * Считает, кусаются ли пауки чаще, чем собаки
     *
     * @param animals Список животных
     * @return true - пауки чаще, false - соьаки чаще
     */
    public boolean doesSpidersBitesMoreThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER || animal.type() == Animal.Type.DOG)
            .mapToInt(animal ->
                animal.type() == Animal.Type.DOG
                    ? 1
                    : -1).sum() > 0;
    }

    /***
     * Ищет самую тяжелую рыбу в 2-х или более списках
     *
     * @param lists Списки жвиотных
     * @return Самую тяжелую рыбу
     */
    public Animal getWeightedFishInTwoPlusLists(List<Animal>... lists) {
        return Stream.of(lists)
            .flatMap(Collection::stream)
            .toList()
            .stream()
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    /***
     * Валидирует животных из списка и собирает ошибки
     *
     * @param animals Список животных
     * @return Map, ключ - название, значение - ошибки
     */
    public Map<String, Set<ValidationError>> getErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                animalValidator::validate
            ))
            .entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /***
     * Собирает ошибки и переводит их в вид - животное - невалидные поля
     * @param animals Список животных
     * @return Map, ключ - название животного, значение - сообщения с ошибками
     */
    public Map<String, String> getErrorsAdvanced(List<Animal> animals) {
        Map<String, Set<ValidationError>> errors = getErrors(animals);

        return errors.entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    error -> error.getValue().stream()
                                .map(Throwable::getMessage)
                                .collect(Collectors.joining("; "))
                )
            );
    }

}
