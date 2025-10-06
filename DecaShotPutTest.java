import com.example.decathlon.deca.DecaShotPut;
import org.junit.Test;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;

public class DecaShotPutTest {

    @Test
    public void testValidScoreCalculation() {
        DecaShotPut shotPut = new DecaShotPut();
        int points = shotPut.calculateResult(16.50); // valid input

        // 16.50 m ≈ 882 points (from formula)
        assertEquals("Expected 882 points for 16.50 m shot put", 882, points);
    }

    @Test
    public void testLowInvalidInput() {
        DecaShotPut shotPut = new DecaShotPut();
        int points = shotPut.calculateResult(-5.0); // triggers validation

        // It might ask for new input in console, but this test ensures no crash
        assertTrue("Score should not be negative for invalid input", points >= 0);
    }

    @Test
    public void testHighInvalidInput() {
        DecaShotPut shotPut = new DecaShotPut();
        int points = shotPut.calculateResult(35.0); // too high

        assertTrue("System should handle too-high input gracefully", points >= 0);
    }
    }

