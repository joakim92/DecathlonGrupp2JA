package com.example.decathlon.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoringServiceTest {

    ScoringService service = new ScoringService();

    @Test
    void testScoreTrackEventValid() {
        int points = service.score("100m", 11.0); // typisk tid
        assertTrue(points > 0, "Poängen ska vara positiv för 100m");
        assertEquals(861, points, "Förväntat poäng för 11.0s på 100m");
    }

    @Test
    void testScoreFieldEventValid() {
        int points = service.score("longJump", 750); // i cm
        assertTrue(points > 0, "Poängen ska vara positiv för längdhopp");
        assertEquals(950, points, "Förväntat poäng för 750cm längdhopp");
    }

    @Test
    void testScoreUnknownEvent() {
        int points = service.score("bananaJump", 10.0);
        assertEquals(0, points, "Okänd gren ska ge 0 poäng");
    }

    @Test
    void testScoreTrackEventTooSlow() {
        int points = service.score("100m", 25.0); // långsamt = negativ x
        assertEquals(0, points, "För långsam tid ska ge 0 poäng");
    }

    @Test
    void testScoreFieldEventTooShort() {
        int points = service.score("longJump", 100); // under B = negativ x
        assertEquals(0, points, "För kort hopp ska ge 0 poäng");
    }

    @Test
    void testScoreShotPut() {
        int points = service.score("shotPut", 14.0); // typisk kast
        assertEquals(731, points, "Förväntat poäng för 14m kula");
    }

    @Test
    void testScore400m() {
        int points = service.score("400m", 50.0); // typisk tid
        assertEquals(900, points, "Förväntat poäng för 50s på 400m");
    }
}