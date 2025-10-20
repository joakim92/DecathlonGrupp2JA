package com.example.decathlon.deca;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DecaPoleVaultTest {

    @Test
    void testValidDistance() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockCalc.calculateField(0.2797, 100, 1.35, 500.0)).thenReturn(820);

        DecaPoleVault poleVault = new DecaPoleVault() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = poleVault.calculateResult(500.0);
        assertEquals(820, result, "Poängen ska vara korrekt beräknad för 500cm");
    }

    @Test
    void testTooLowThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(500.0);
        when(mockCalc.calculateField(0.2797, 100, 1.35, 500.0)).thenReturn(820);

        DecaPoleVault poleVault = new DecaPoleVault() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = poleVault.calculateResult(1.0); // För lågt → ny input
        assertEquals(820, result, "Ska ignorera för lågt värde och ta nästa giltiga");
    }

    @Test
    void testTooHighThenValidInput() {
        CalcTrackAndField mockCalc = mock(CalcTrackAndField.class);
        InputResult mockInput = mock(InputResult.class);

        when(mockInput.enterResult()).thenReturn(500.0);
        when(mockCalc.calculateField(0.2797, 100, 1.35, 500.0)).thenReturn(820);

        DecaPoleVault poleVault = new DecaPoleVault() {{
            this.calc = mockCalc;
            this.inputResult = mockInput;
        }};

        int result = poleVault.calculateResult(1200.0); // För högt → ny input
        assertEquals(820, result, "Ska ignorera för högt värde och ta nästa giltiga");
    }
}