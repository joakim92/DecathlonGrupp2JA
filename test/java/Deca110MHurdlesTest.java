package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Deca110MHurdlesTest {

    @Test
    void testValidRunningTime() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateTrack(5.74352, 28.5, 1.92, 15.0)).thenReturn(900);

        Deca110MHurdles hurdles = new Deca110MHurdles() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = hurdles.calculateResult(15.0);
        assertEquals(900, result, "Poängen ska vara korrekt beräknad för 15.0s");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(15.0);
        when(mockCalc.calculateTrack(5.74352, 28.5, 1.92, 15.0)).thenReturn(900);

        Deca110MHurdles hurdles = new Deca110MHurdles() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = hurdles.calculateResult(9.0); // För lågt → ny input
        assertEquals(900, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(15.0);
        when(mockCalc.calculateTrack(5.74352, 28.5, 1.92, 15.0)).thenReturn(900);

        Deca110MHurdles hurdles = new Deca110MHurdles() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = hurdles.calculateResult(30.0); // För högt → ny input
        assertEquals(900, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}