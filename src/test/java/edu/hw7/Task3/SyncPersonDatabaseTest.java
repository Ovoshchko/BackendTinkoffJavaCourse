package edu.hw7.Task3;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class SyncPersonDatabaseTest {

    @ParameterizedTest
    @DisplayName("--Add and Delete Test")
    @MethodSource("provideDatabases")
    void testAddAndDelete(PersonDatabase database) {
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        database.add(person);
        database.delete(1);

        List<Person> foundPeople = database.findByName("John");

        assertTrue(foundPeople.isEmpty());
    }

    @ParameterizedTest
    @DisplayName("--And and update Test")
    @MethodSource("provideDatabases")
    void testAddAndUpdateIndex(PersonDatabase database) {
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        database.add(person);

        person = new Person(1, "John Updated", "123 Main St", "555-1234");
        database.add(person);

        List<Person> foundPeopleByName = database.findByName("John Updated");
        List<Person> foundPeopleByAddress = database.findByAddress("123 Main St");

        assertEquals(1, foundPeopleByName.size());
        assertEquals(person, foundPeopleByName.get(0));
        assertEquals(1, foundPeopleByAddress.size());
        assertEquals(person, foundPeopleByAddress.get(0));
    }

    @ParameterizedTest
    @DisplayName("--Find person by Name Test")
    @MethodSource("provideDatabases")
    void testAddAndFindByName(PersonDatabase database) {
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        database.add(person);

        List<Person> foundPeople = database.findByName("John");

        assertEquals(1, foundPeople.size());
        assertEquals(person, foundPeople.get(0));
    }

    @ParameterizedTest
    @DisplayName("--Find person by Address Test")
    @MethodSource("provideDatabases")
    void testAddAndFindByAddress(PersonDatabase database) {
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        database.add(person);

        List<Person> foundPeople = database.findByAddress("123 Main St");

        assertEquals(1, foundPeople.size());
        assertEquals(person, foundPeople.get(0));
    }

    @ParameterizedTest
    @DisplayName("--Find person by Phone Number Test")
    @MethodSource("provideDatabases")
    void testAddAndFindByPhone(PersonDatabase database) {
        Person person = new Person(1, "John", "123 Main St", "555-1234");

        database.add(person);

        List<Person> foundPeople = database.findByPhone("555-1234");

        assertEquals(1, foundPeople.size());
        assertEquals(person, foundPeople.get(0));
    }

    @ParameterizedTest
    @DisplayName("--Synchronised Test")
    @MethodSource("provideDatabases")
    void testSynchronisedAdmission(PersonDatabase database) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        Person personToTest = new Person(2, "Jo2", "IopPop2", "+468793");

        database.add(new Person(1, "Jo", "IopPop", "+987848763547564"));
        database.add(new Person(3, "Zy", "OPP", "+3547564"));

        service.submit(() -> database.add(personToTest)).isDone();
        Future<List<Person>> personByName = service.submit(() -> database.findByName(personToTest.name()));
        Future<List<Person>> personByAddress = service.submit(() -> database.findByAddress(personToTest.address()));
        Future<List<Person>> personByPhone = service.submit(() -> database.findByPhone(personToTest.phoneNumber()));

        service.close();

        boolean termination = service.awaitTermination(10, TimeUnit.SECONDS);

        service.shutdown();

        assertTrue(termination);
        assertEquals(personToTest, personByName.get().get(0));
        assertEquals(personToTest, personByAddress.get().get(0));
        assertEquals(personToTest, personByPhone.get().get(0));
    }

    @ParameterizedTest
    @DisplayName("--Synchronised Test")
    @MethodSource("provideDatabases")
    void testSynchronisedAdmissionDelete(PersonDatabase database) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Person personToTest = new Person(2, "Jo2", "IopPop2", "+468793");

        database.add(new Person(1, "Jo", "IopPop", "+987848763547564"));
        database.add(new Person(3, "Zy", "OPP", "+3547564"));

        service.submit(() -> database.add(personToTest));
        Future<List<Person>> personByName = service.submit(() -> database.findByName(personToTest.name()));
        Future<List<Person>> personByAddress = service.submit(() -> database.findByAddress(personToTest.address()));
        Future<List<Person>> personByPhone = service.submit(() -> database.findByPhone(personToTest.phoneNumber()));
        service.submit(() -> database.delete(2));

        service.close();

        boolean termination = service.awaitTermination(10, TimeUnit.SECONDS);

        service.close();

        assertTrue(termination);
        assertTrue(personByName.get().isEmpty());
        assertTrue(personByAddress.get().isEmpty());
        assertTrue(personByPhone.get().isEmpty());
    }

    private static Stream<Arguments> provideDatabases() {
        return Stream.of(
            Arguments.of(new SyncPersonDatabase()),
            Arguments.of(new ReadLockPersonDatabase())
        );
    }
}

