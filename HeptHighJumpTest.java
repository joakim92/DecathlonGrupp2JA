package hepatest;

import com.example.decathlon.heptathlon.HeptHightJump;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeptHighJumpTest {
  // Range: 0 - 110
    // A= 1.84523; B=75 ;C= 1.348
    // as per formulae b  must ve a min valid value is 75m
    // = INT(A(P — B)C)
    @Test
    public void testValidScoreCalculation() {
        HeptHightJump hightJump = new HeptHightJump();
        int points = hightJump.calculateResult(75.7); // min valid input
        assertEquals( 1, points);
    }

    @Test
    public void testLowInvalidInput() {
        HeptHightJump hightJump = new HeptHightJump();
        int points = hightJump.calculateResult(70); // too low
        assertEquals( 0, points);
        //assertTrue("Value too low", points >= 3.8);
    }

    @Test
    public void testHighValidInputAt270() {
        HeptHightJump hightJump = new HeptHightJump();
        int points = hightJump.calculateResult(270); // max value
        assertEquals( 2254, points);
    }
}
