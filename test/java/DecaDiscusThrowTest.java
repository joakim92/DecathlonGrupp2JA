package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DecaDiscusThrowTest {

    @Test
    void testValidDistance() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateField(12.91, 4, 1.1, 50.0)).thenReturn(700);

        DecaDiscusThrow discus = new DecaDiscusThrow() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = discus.calculateResult(50.0);
        assertEquals(700, result, "Poängen ska vara korrekt beräknad för 50.0m");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(50.0);
        when(mockCalc.calculateField(12.91, 4, 1.1, 50.0)).thenReturn(700);

        DecaDiscusThrow discus = new DecaDiscusThrow() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = discus.calculateResult(-1.0); // För lågt → ny input
        assertEquals(700, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(50.0);
        when(mockCalc.calculateField(12.91, 4, 1.1, 50.0)).thenReturn(700);

        DecaDiscusThrow discus = new DecaDiscusThrow() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = discus.calculateResult(100.0); // För högt → ny input
        assertEquals(700, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}