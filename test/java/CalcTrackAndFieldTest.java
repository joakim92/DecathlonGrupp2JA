import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.decathlon.common.CalcTrackAndField;

public class CalcTrackAndFieldTest {

    @Test
    void testCalculateField() {
        CalcTrackAndField calc = new CalcTrackAndField();
        int points = calc.calculateField(51.39, 1.5, 1.05, 14.0);
        assertEquals(731, points, "Förväntat poäng för 14.0m kula");
    }

    @Test
    void testCalculateTrack() {
        CalcTrackAndField calc = new CalcTrackAndField();
        int points = calc.calculateTrack(25.4347, 18, 1.81, 11.0);
        assertEquals(861, points, "Förväntat poäng för 11.0s på 100m");
    }
}