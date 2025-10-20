package com.example.decathlon.dto;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StandingDtoTest {

    @Test
    void testStandingDtoValues() {
        Map<String, Integer> scores = Map.of(
                "100m", 861,
                "longJump", 935,
                "shotPut", 728
        );

        StandingDto dto = new StandingDto("Amin", scores, 2524);

        assertEquals("Amin", dto.name());
        assertEquals(2524, dto.total());
        assertEquals(861, dto.scores().get("100m"));
        assertEquals(3, dto.scores().size());
    }

    @Test
    void testEmptyScores() {
        StandingDto dto = new StandingDto("Ghost", Map.of(), 0);

        assertEquals("Ghost", dto.name());
        assertEquals(0, dto.total());
        assertTrue(dto.scores().isEmpty());
    }
}