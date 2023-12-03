package edu.hw8.Task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void hasNextPassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator(2, 0, 4);
        for (int i = 0; i < 4; i++) {
            assertTrue(passwordGenerator.hasNextPassword());
            passwordGenerator.generatePassword();
        }
        assertFalse(passwordGenerator.hasNextPassword());
    }

    @Test
    void generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator(3, 0, 4);
        for (int i = 0; i < 4; i++) {
            assertEquals(3, passwordGenerator.generatePassword().length());
        }
    }
}
