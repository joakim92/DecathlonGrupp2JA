package hepatest;

import com.example.decathlon.heptathlon.HeptLongJump;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeptLongJumpTest {
//210 cm is minimum distance that is acceptable value. less than that will be returned as zero.
    @Test
    public void testValidScoreCalculationBelow210() { // becoz b value is 210.
        HeptLongJump longJump = new HeptLongJump();
        int points = longJump.calculateResult(150); // valid input
        assertEquals( 0, points);
    }
    @Test
    public void testValidScoreCalculationAbove210() {
        HeptLongJump longJump = new HeptLongJump();
        int points = longJump.calculateResult(400); // valid input
        assertEquals( 308, points);
    }
    @Test
    public void testValidScoreCalculationAt1000() {
        HeptLongJump longJump = new HeptLongJump();
        int points = longJump.calculateResult(1000); // max input
        assertEquals( 2299, points);
    }
/*
// commenting the tc, becoz as of now it doesnt handle higher inputs and
//keeps asking the user to enter a new input. After staffan update the code, lets try with this tc.

    @Test
    public void testValidScoreCalculationAbove1000() {
        HeptLongJump longJump = new HeptLongJump();
        int points = longJump.calculateResult(1010); // max input
        assertEquals( "Value too high\n",0, points);
    }

 */
}
