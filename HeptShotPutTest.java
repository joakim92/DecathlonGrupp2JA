package hepatest;

import com.example.decathlon.heptathlon.HeptShotPut;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeptShotPutTest {
  // Range: 0 - 30
    @Test
    public void testValidScoreCalculation() {
        HeptShotPut shotPut = new HeptShotPut();
        int points = shotPut.calculateResult(16.50); // valid input
        // Example calculation from formula (expected ≈ 962)
        assertEquals("Expected 962 points for 16.50 m heptathlon shot put", 962, points);
    }

    @Test
    public void testLowInvalidInput() {
        HeptShotPut shotPut = new HeptShotPut();
        int points = shotPut.calculateResult(3.0); // too low (below 5)

        // Should not crash, and score should be >= 0
        assertTrue("Score should not be negative for invalid low input", points >= 0);
    }

    @Test
    public void testHighInvalidInput() {
        HeptShotPut shotPut = new HeptShotPut();
        int points = shotPut.calculateResult(150.0); // too high (above 100)

        assertTrue("System should handle too-high input gracefully", points >= 0);
    }
}
