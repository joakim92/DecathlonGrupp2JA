
import com.example.decathlon.heptathlon.HeptShotPut;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HeptShotPutTest {

    @Test
    public void testValidScoreCalculation() {
        HeptShotPut shotPut = new HeptShotPut();
        int points = shotPut.calculateResult(16.50); // valid input

        // Example calculation from formula (expected ≈ 962)
        assertEquals(962, points, "Expected 962 points for 16.50 m heptathlon shot put");
    }

    @Test
    public void testLowInvalidInput() {
        HeptShotPut shotPut = new HeptShotPut();
        int points = shotPut.calculateResult(3.0); // too low (below 5)

        // Should not crash, and score should be >= 0
        assertTrue(points >= 0, "Score should not be negative for invalid low input");
    }

    @Test
    public void testHighInvalidInput() {
        HeptShotPut shotPut = new HeptShotPut();
        int points = shotPut.calculateResult(150.0); // too high (above 100)

        assertTrue(points >= 0, "System should handle too-high input gracefully");
    }
}
