package edu.hw3.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactParserTest {

    private final ContactsParser contactsParser = new ContactsParser();

    @ParameterizedTest
    @DisplayName("--Valid list input with Names and Surnames test")
    @MethodSource("getValidNameSurnameInput")
    void validNameSurnameInputTest(String[] contacts, String order, Contact[] answer) {
        assertArrayEquals(answer, contactsParser.parseContacts(contacts, order));
    }

    @ParameterizedTest
    @DisplayName("--Valid list without Surnames test")
    @MethodSource("getValidNameInput")
    void validNameInputTest(String[] contacts, String order, Contact[] answer) {
        assertArrayEquals(answer, contactsParser.parseContacts(contacts, order));
    }

    @ParameterizedTest
    @DisplayName("--Invalid list input")
    @MethodSource("getInvalidArrayInput")
    void inputArrayInputTest(String[] contacts, String order) {
        assertThrows(NullPointerException.class, () -> contactsParser.parseContacts(contacts, order));
    }

    @ParameterizedTest
    @DisplayName("--Invalid order input")
    @MethodSource("getInvalidOrderInput")
    void invalidOrderInputTest(String[] input, String order) {

    }

    private static Stream<Arguments> getValidNameSurnameInput() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC",
                new Contact[]{
                    new Contact( "Aquinas", "Thomas"),
                    new Contact("Descartes", "Rene"),
                    new Contact("Hume", "David"),
                    new Contact("Locke", "John")
                }
            ),

            Arguments.of(
                new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                new Contact[]{
                    new Contact("Gauss", "Carl"),
                    new Contact("Euler", "Leonhard"),
                    new Contact("Erdos", "Paul")
                }
            )
        );
    }

    private static Stream<Arguments> getValidNameInput() {
        return Stream.of(
            Arguments.of(
                new String[] {"John", "Thomas", "David Hume", "Rene Descartes"},
                "ASC",
                new Contact[]{
                    new Contact("Descartes", "Rene"),
                    new Contact("Hume", "David"),
                    new Contact("", "John"),
                    new Contact("", "Thomas")
                }
            ),

            Arguments.of(
                new String[]{"Paul", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                new Contact[]{
                    new Contact("", "Paul"),
                    new Contact("Gauss", "Carl"),
                    new Contact("Euler", "Leonhard")
                }
            ),

            Arguments.of(
                new String[]{},
                "ASC",
                new Contact[]{}
            )
        );
    }

    private static Stream<Arguments> getInvalidArrayInput() {
        return Stream.of(
            Arguments.of(null, "ASC")
        );
    }

    private static Stream<Arguments> getInvalidOrderInput() {
        return Stream.of(
            Arguments.of(
                new String[] {"John", "Thomas", "David Hume", "Rene Descartes"},
                "ASc",
                new Contact[]{
                    new Contact("Descartes", "Rene"),
                    new Contact("Hume", "David"),
                    new Contact("", "John"),
                    new Contact("", "Thomas")
                }
            ),

            Arguments.of(
                new String[]{"Paul", "Leonhard Euler", "Carl Gauss"},
                "desc",
                new Contact[]{
                    new Contact("", "Paul"),
                    new Contact("Gauss", "Carl"),
                    new Contact("Euler", "Leonhard")
                }
            ),

            Arguments.of(
                new String[]{},
                "----4",
                new Contact[]{}
            )
        );
    }
}
