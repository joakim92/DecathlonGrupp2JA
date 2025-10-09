
package com.example.decathlon.core;

import com.example.decathlon.core.CompetitionService;
import com.example.decathlon.core.ScoringService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class CompetitionServiceTest {

    @Test
    void scoringAddsCompetitorAndCalculatesPoints() {
        ScoringService scoring = new ScoringService();     // use the real service
        CompetitionService svc = new CompetitionService(scoring);

        // add a competitor and score an event
        svc.addCompetitor("Alice");
        int points = svc.score("Alice", "100m", 12.0);     // uses real scoring formula
        Assertions.assertTrue(points > 0, "Points should be calculated");

        List<Map<String, Object>> standings = svc.standings();
        Assertions.assertEquals(1, standings.size());
        Assertions.assertEquals("Alice", standings.get(0).get("name"));
        Assertions.assertEquals(points, standings.get(0).get("total"));
    }

    @Test
    void standingsSortByTotalDescending_andCsvContainsData() {
        ScoringService scoring = new ScoringService();     // use the real service
        CompetitionService svc = new CompetitionService(scoring);

        svc.addCompetitor("Bob");
        svc.addCompetitor("Eve");

        svc.score("Bob", "100m", 11.0);    // Bob scores
        svc.score("Bob", "400m", 50.0);
        svc.score("Eve", "100m", 12.0);    // Eve scores

        List<Map<String, Object>> standings = svc.standings();
        Assertions.assertEquals(2, standings.size());
        Assertions.assertTrue((int)standings.get(0).get("total") >= (int)standings.get(1).get("total"),
                "Standings should be sorted by total points descending");

        String csv = svc.exportCsv();
        Assertions.assertTrue(csv.startsWith("Name,"), "CSV should start with header");
        Assertions.assertTrue(csv.contains("Bob"), "CSV should contain Bob");
        Assertions.assertTrue(csv.contains("Eve"), "CSV should contain Eve");
    }
}
