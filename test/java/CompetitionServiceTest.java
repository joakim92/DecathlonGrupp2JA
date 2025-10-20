package com.example.decathlon.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetitionServiceTest {

    private ScoringService scoring;
    private CompetitionService service;

    @BeforeEach
    void setUp() {
        scoring = mock(ScoringService.class);
        service = new CompetitionService(scoring);
    }

    @Test
    void testAddAndScoreCompetitor() {
        when(scoring.score("100m", 11.0)).thenReturn(861);

        service.addCompetitor("Amin");
        int points = service.score("Amin", "100m", 11.0);

        assertEquals(861, points);
        List<Map<String, Object>> standings = service.standings();
        assertEquals(1, standings.size());
        assertEquals("Amin", standings.get(0).get("name"));
        assertEquals(861, standings.get(0).get("total"));
    }

    @Test
    void testScoreCreatesCompetitorIfMissing() {
        when(scoring.score("400m", 50.0)).thenReturn(900);

        int points = service.score("Björn", "400m", 50.0);

        assertEquals(900, points);
        assertEquals(1, service.standings().size());
        assertEquals("Björn", service.standings().get(0).get("name"));
    }

    @Test
    void testExportCsv() {
        when(scoring.score("100m", 11.0)).thenReturn(861);
        when(scoring.score("400m", 50.0)).thenReturn(900);

        service.score("Amin", "100m", 11.0);
        service.score("Amin", "400m", 50.0);
        service.score("Björn", "400m", 50.0);

        String csv = service.exportCsv();
        assertTrue(csv.contains("Name,100m,400m,Total"));
        assertTrue(csv.contains("Amin,861,900,1761"));
        assertTrue(csv.contains("Björn,,900,900"));
    }
}