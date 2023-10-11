package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for Task 7")
public class Task7Test {

    @Test
    @DisplayName("--Valid input for rotateLeft")
    void validInputRotateLeftTest() {

        int[][] inputs = {{16, 1}, {17, 2}, {234, 3}};
        int[] answers = {1, 6, 87};

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(answers[i], Task7.rotateLeft(inputs[i][0], inputs[i][1]));
        }
    }

    @Test
    @DisplayName("--Valid input for rotateRight")
    void validInputRotateRightTest() {

        int[][] inputs = {{8, 1}, {123, 1}, {128, 39}};
        int[] answers = {4, 125, 1};

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(answers[i], Task7.rotateRight(inputs[i][0], inputs[i][1]));
        }
    }

    @Test
    @DisplayName("--Invalid input for both functions")
    void invalidInputTest() {

        int[] inputs = {-1, 0, -1899};

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(0, Task7.rotateRight(inputs[i], 3));
        }
    }

}
