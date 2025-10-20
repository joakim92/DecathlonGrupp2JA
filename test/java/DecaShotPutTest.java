import com.example.decathlon.deca.DecaShotPut;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;

public class DecaShotPutTest {

    @Test
    public void testValidScoreCalculation() {
        DecaShotPut shotPut = new DecaShotPut();
        int points = shotPut.calculateResult(16.50); // valid input

        // 16.50 m ≈ 882 points (from formula)
        assertEquals(882, points, "Expected 882 points for 16.50 m shot put");
    }

    @Test
    public void testLowInvalidInput() {
        DecaShotPut shotPut = new DecaShotPut();
        int points = shotPut.calculateResult(-5.0); // triggers validation

        // It might ask for new input in console, but this test ensures no crash
        assertTrue(points >= 0, "Score should not be negative for invalid input");
    }

    @Test
    public void testHighInvalidInput() {
        DecaShotPut shotPut = new DecaShotPut();
        int points = shotPut.calculateResult(35.0); // too high

        assertTrue(points >= 0, "System should handle too-high input gracefully");
    }
    }

