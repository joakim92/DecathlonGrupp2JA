package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DecaJavelinThrowTest {

    @Test
    void testValidDistance() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateField(10.14, 7, 1.08, 60.0)).thenReturn(750);

        DecaJavelinThrow javelin = new DecaJavelinThrow() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = javelin.calculateResult(60.0);
        assertEquals(750, result, "Poängen ska vara korrekt beräknad för 60.0m");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(60.0);
        when(mockCalc.calculateField(10.14, 7, 1.08, 60.0)).thenReturn(750);

        DecaJavelinThrow javelin = new DecaJavelinThrow() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = javelin.calculateResult(-5.0); // För lågt → ny input
        assertEquals(750, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(60.0);
        when(mockCalc.calculateField(10.14, 7, 1.08, 60.0)).thenReturn(750);

        DecaJavelinThrow javelin = new DecaJavelinThrow() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = javelin.calculateResult(150.0); // För högt → ny input
        assertEquals(750, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}