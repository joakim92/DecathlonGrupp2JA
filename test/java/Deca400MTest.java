package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Deca400MTest {

    @Test
    void testValidRunningTime() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateTrack(1.53775, 82, 1.81, 50.0)).thenReturn(815);

        Deca400M deca400M = new Deca400M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca400M.calculateResult(50.0);
        assertEquals(815, result, "Poängen ska vara korrekt beräknad för 50.0s");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(50.0);
        when(mockCalc.calculateTrack(1.53775, 82, 1.81, 50.0)).thenReturn(815);

        Deca400M deca400M = new Deca400M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca400M.calculateResult(10.0); // För lågt → ny input
        assertEquals(815, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(50.0);
        when(mockCalc.calculateTrack(1.53775, 82, 1.81, 50.0)).thenReturn(815);

        Deca400M deca400M = new Deca400M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca400M.calculateResult(150.0); // För högt → ny input
        assertEquals(815, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}