package com.example.decathlon.common;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputNameTest {

    @Test
    void testAddCompetitorValidName() {
        // Simulera användarinmatning: "Amin\n"
        String simulatedInput = "Amin\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        InputName inputName = new InputName();
        String result = inputName.addCompetitor();

        System.setIn(originalIn); // Återställ System.in

        assertEquals("Amin", result, "Namnet ska vara korrekt inläst");
    }

    @Test
    void testAddCompetitorInvalidThenValid() {
        // Simulera först felaktig input ("123\n"), sen korrekt ("Björn\n")
        String simulatedInput = "123\nBjörn\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        InputName inputName = new InputName();
        String result = inputName.addCompetitor();

        System.setIn(originalIn); // Återställ System.in

        assertEquals("Björn", result, "Ska ignorera ogiltig input och ta nästa giltiga");
    }
}