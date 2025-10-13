package hepatest;

import com.example.decathlon.heptathlon.HeptJavelinThrow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeptJavelinThrowTest {
  // Range: 0 - 110
    // A=15.9803; B=3.8 ;C=1.04
    // as per formulae b  must ve a min valid value is 3.8m
    // = INT(A(P — B)C)
    @Test
    public void testValidScoreCalculation() {
        HeptJavelinThrow javelinThrow = new HeptJavelinThrow();
        int points = javelinThrow.calculateResult(3.8); // min valid input-3,8 in meters
        assertEquals( 0, points);
    }

    @Test
    public void testLowInvalidInput() {
        HeptJavelinThrow javelinThrow = new HeptJavelinThrow();
        int points = javelinThrow.calculateResult(3.0); // too low (below 3,8)
        assertEquals( 0, points);
        //assertTrue("Value too low", points >= 3.8);
    }

    @Test
    public void testHighValidInputAt110() {
        HeptJavelinThrow javelinThrow = new HeptJavelinThrow();
        int points = javelinThrow.calculateResult(110); // max value
        assertEquals( 2045, points);
    }
}
