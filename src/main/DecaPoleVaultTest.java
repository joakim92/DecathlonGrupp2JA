
package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class DecaPoleVaultTest {

    // helper: compute expected using the same service the class uses
    private static int expected(double x) {
        return new CalcTrackAndField().calculateField(0.2797, 100, 1.35, x);
    }

    @Test
    void okValue() {
        DecaPoleVault pv = new DecaPoleVault();
        int got = pv.calculateResult(500); // 5.00 m as 500 cm
        assertEquals(expected(500), got);
    }

    @Test
    @Timeout(3)
    void lowThenValid() {
        // Start too low, then user types a valid height
        InputStream oldIn = System.in;
        System.setIn(new ByteArrayInputStream("500\n".getBytes())); // next input when prompted

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            DecaPoleVault pv = new DecaPoleVault();
            int got = pv.calculateResult(1.8); // < 2 -> "Value too low"
            assertTrue(out.toString().contains("Value too low"));
            assertEquals(expected(500), got);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }

    @Test
    @Timeout(3)
    void highThenValid() {
        // Start too high, then user types a valid height
        InputStream oldIn = System.in;
        System.setIn(new ByteArrayInputStream("500\n".getBytes())); // next input when prompted

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            DecaPoleVault pv = new DecaPoleVault();
            int got = pv.calculateResult(2000); // > 1000 -> "Value too high"
            assertTrue(out.toString().contains("Value too high"));
            assertEquals(expected(500), got);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }
}

