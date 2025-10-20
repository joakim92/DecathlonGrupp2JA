package com.example.decathlon.common;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputResultTest {

    @Test
    void testEnterValidResult() {
        String simulatedInput = "12.34\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        InputResult inputResult = new InputResult();
        double result = inputResult.enterResult();

        System.setIn(originalIn); // Återställ System.in

        assertEquals(12.34, result, 0.001, "Ska läsa in ett giltigt decimaltal");
    }

    @Test
    void testEnterInvalidThenValidResult() {
        String simulatedInput = "abc\n15.5\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        InputResult inputResult = new InputResult();
        double result = inputResult.enterResult();

        System.setIn(originalIn); // Återställ System.in

        assertEquals(15.5, result, 0.001, "Ska ignorera ogiltig input och läsa in nästa giltiga");
    }

    @Test
    void testReturnResult() {
        String simulatedInput = "9.99\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        InputResult inputResult = new InputResult();
        inputResult.enterResult();
        double returned = inputResult.returnResult();

        System.setIn(originalIn); // Återställ System.in

        assertEquals(9.99, returned, 0.001, "returnResult ska ge samma värde som enterResult");
    }
}