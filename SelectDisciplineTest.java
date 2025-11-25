package com.example.decathlon.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SelectDisciplineTest {

    @Test
    void menu() {
        SelectDiscipline s = new SelectDiscipline();

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));

        s.printDisciplines();

        System.setOut(old);
        String out = buf.toString();
        assertTrue(out.contains("1. Decathlon 100 meters."));
        assertTrue(out.contains("17. Heptathlon Javelin Throw."));
    }

    @Test
    @Timeout(2)
    void choose() {
        SelectDiscipline s = new SelectDiscipline();

        // Always return a VALID result so validation doesn't loop
        s.inputResult = new InputResult() {
            @Override public double enterResult() { return 50.0; } // 400m in 50s
        };

        s.disciplineSelected = 2; // 400m
        assertDoesNotThrow(s::makeSelection);
    }
}
// test