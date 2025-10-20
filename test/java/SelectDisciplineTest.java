package com.example.decathlon.common;

import com.example.decathlon.deca.Deca100M;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;

class SelectDisciplineTest {

    @Test
    void testSelectDeca100M() {
        // Simulera input: "1\n12.0\n"
        String simulatedInput = "1\n12.0\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Mocka Deca100M och InputResult
        Deca100M mockDeca100M = mock(Deca100M.class);
        InputResult mockInputResult = mock(InputResult.class);
        when(mockInputResult.enterResult()).thenReturn(12.0);

        // Skapa en anonym subklass för att injicera mockar
        SelectDiscipline select = new SelectDiscipline() {{
            this.deca100M = mockDeca100M;
            this.inputResult = mockInputResult;
        }};

        select.inputSelection();

        System.setIn(originalIn); // Återställ System.in

        // Verifiera att rätt metod anropades
        verify(mockDeca100M).calculateResult(12.0);
    }
}