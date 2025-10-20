package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Deca100MTest {

    @Test
    void testValidRunningTime() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateTrack(25.4347, 18, 1.81, 11.0)).thenReturn(861);

        Deca100M deca100M = new Deca100M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca100M.calculateResult(11.0);
        assertEquals(861, result, "Poängen ska vara korrekt beräknad för 11.0s");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(11.0);
        when(mockCalc.calculateTrack(25.4347, 18, 1.81, 11.0)).thenReturn(861);

        Deca100M deca100M = new Deca100M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca100M.calculateResult(4.0); // För lågt → ny input
        assertEquals(861, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(11.0);
        when(mockCalc.calculateTrack(25.4347, 18, 1.81, 11.0)).thenReturn(861);

        Deca100M deca100M = new Deca100M() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = deca100M.calculateResult(20.0); // För högt → ny input
        assertEquals(861, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}