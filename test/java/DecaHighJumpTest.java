package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DecaHighJumpTest {

    @Test
    void testValidDistance() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateField(0.8465, 75, 1.42, 200.0)).thenReturn(850);

        DecaHighJump highJump = new DecaHighJump() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = highJump.calculateResult(200.0);
        assertEquals(850, result, "Poängen ska vara korrekt beräknad för 200cm");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(200.0);
        when(mockCalc.calculateField(0.8465, 75, 1.42, 200.0)).thenReturn(850);

        DecaHighJump highJump = new DecaHighJump() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = highJump.calculateResult(-10.0); // För lågt → ny input
        assertEquals(850, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(200.0);
        when(mockCalc.calculateField(0.8465, 75, 1.42, 200.0)).thenReturn(850);

        DecaHighJump highJump = new DecaHighJump() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = highJump.calculateResult(400.0); // För högt → ny input
        assertEquals(850, result, "Poängen ska vara korrekt beräknad för 200cm");
    }
}