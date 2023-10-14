package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test for Task 8")
public class Task8Test {

    @Test
    @DisplayName("--Valid true input")
    void validTrueInputTest() throws Exception{

        int[][] board = {{0, 0, 0, 1, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 1, 0, 0, 0, 1, 0, 0},
                         {0, 0, 0, 0, 1, 0, 1, 0},
                         {0, 1, 0, 0, 0, 1, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 1, 0, 0, 0, 0, 0, 1},
                         {0, 0, 0, 0, 1, 0, 0, 0}};

        assertTrue(Task8.knightBoardCapture(board));
    }

    @Test
    @DisplayName("--Valid false input")
    void validFalseInputTest() throws Exception{

        int[][] board = {{0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}};

        assertFalse(Task8.knightBoardCapture(board));
    }

    @Test
    @DisplayName("--Invalid input")
    void invalidInputTest() throws Exception{

        int[][] board = {{0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}};

        Throwable thrown = assertThrows(Exception.class, () -> {
            Task8.knightBoardCapture(board);
        });
        assertNotNull(thrown.getMessage());
    }
}
