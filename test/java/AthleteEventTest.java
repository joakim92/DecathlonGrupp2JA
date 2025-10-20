import com.example.decathlon.Shoba.AthleteEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AthleteEventTest {
    @Test
    public void testGetName() {

       AthleteEvent athlete = new AthleteEvent("John", 850);
        assertEquals("John", athlete.getName());
    }

}
