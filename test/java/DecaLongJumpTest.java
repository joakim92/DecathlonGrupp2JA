package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DecaLongJumpTest {

    @Test
    void testValidDistance() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateField(0.14354, 220, 1.4, 750.0)).thenReturn(935);

        DecaLongJump longJump = new DecaLongJump() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = longJump.calculateResult(750.0);
        assertEquals(935, result, "Poängen ska vara korrekt beräknad för 750cm");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(750.0);
        when(mockCalc.calculateField(0.14354, 220, 1.4, 750.0)).thenReturn(935);

        DecaLongJump longJump = new DecaLongJump() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = longJump.calculateResult(100.0); // För lågt → ny input
        assertEquals(935, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(750.0);
        when(mockCalc.calculateField(0.14354, 220, 1.4, 750.0)).thenReturn(935);

        DecaLongJump longJump = new DecaLongJump() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = longJump.calculateResult(1200.0); // För högt → ny input
        assertEquals(935, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}