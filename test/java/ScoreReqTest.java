package com.example.decathlon.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreReqTest {

    @Test
    void testScoreReqValues() {
        ScoreReq req = new ScoreReq("Amin", "100m", 12.34);

        assertEquals("Amin", req.name());
        assertEquals("100m", req.event());
        assertEquals(12.34, req.raw());
    }
}