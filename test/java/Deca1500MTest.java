package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Deca1500MTest {

    @Test
    void testValidRunningTime() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        // 4 minuter = 240 sekunder
        when(mockCalc.calculateTrack(0.03768, 480, 18.5, 240.0)).thenReturn(600);

        Deca1500M deca1500M = new Deca1500M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca1500M.calculateResult(240.0); // sekunder
        assertEquals(600, result, "Poängen ska vara korrekt beräknad för 240s");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(240.0);
        when(mockCalc.calculateTrack(0.03768, 480, 18.5, 240.0)).thenReturn(600);

        Deca1500M deca1500M = new Deca1500M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca1500M.calculateResult(1.0); // För lågt → ny input
        assertEquals(600, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(240.0);
        when(mockCalc.calculateTrack(0.03768, 480, 18.5, 240.0)).thenReturn(600);

        Deca1500M deca1500M = new Deca1500M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca1500M.calculateResult(500.0); // För högt → ny input
        assertEquals(600, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}