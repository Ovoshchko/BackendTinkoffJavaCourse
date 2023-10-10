package edu.hw1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tests for task 1")
public class Task1Test {

    @Test
    @DisplayName("--Correct input test")
    void correctTimeTest() {

        String[] inputTime = {"34:34", "20:00", "00:00", "656:32"};

        int[] answers = {2074, 1200, 0, 39392};

        for(int i = 0; i<inputTime.length; i++) {
            assertEquals(answers[i], Task1.minutesToSeconds(inputTime[i]));
        }

    }

    @Test
    @DisplayName("--Incorrect input test")
    void incorrectTimeTest() {

        String[] inputTime = {"34:334", "20:500", "-1:00", "65632"};

        for (String s : inputTime) {
            assertEquals(-1, Task1.minutesToSeconds(s));
        }

    }
}
