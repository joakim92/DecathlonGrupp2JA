package com.example.decathlon.api;

import com.example.decathlon.core.CompetitionService;
import com.example.decathlon.dto.ScoreReq;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompetitionService comp;

    @Test
    void testAddCompetitor() throws Exception {
        mvc.perform(post("/com/example/decathlon/api/competitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Amin\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testScoreEndpoint() throws Exception {
        Mockito.when(comp.score("Amin", "100m", 10.50)).thenReturn(861);

        mvc.perform(post("/com/example/decathlon/api/score")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Amin\",\"event\":\"100m\",\"raw\":\"10.50\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points").value(861));
    }

    @Test
    void testStandingsEndpoint() throws Exception {
        Mockito.when(comp.standings()).thenReturn(List.of(
                Map.of("name", "Amin", "total", 861)
        ));

        mvc.perform(get("/com/example/decathlon/api/standings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Amin"))
                .andExpect(jsonPath("$[0].total").value(861));
    }

    @Test
    void testExportCsvEndpoint() throws Exception {
        Mockito.when(comp.exportCsv()).thenReturn("name,total\nAmin,861");

        mvc.perform(get("/com/example/decathlon/api/export.csv"))
                .andExpect(status().isOk())
                .andExpect(content().string("name,total\nAmin,861"));
    }
    @Test
    void testAddCompetitorEmptyName() throws Exception {
        mvc.perform(post("/com/example/decathlon/api/competitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest()); // ibland 400, ibland 201 beroende på random
    }
    @Test
    void testAddCompetitorTooMany() throws Exception {
        List<Map<String, Object>> fakeList = new java.util.ArrayList<>();
        for (int i = 0; i < 40; i++) {
            fakeList.add(Map.of("name", "Person" + i, "total", 800 + i));
        }
        Mockito.when(comp.standings()).thenReturn(fakeList);

        mvc.perform(post("/com/example/decathlon/api/competitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Extra\"}"))
                .andExpect(status().isTooManyRequests()); // ibland 429, ibland 201 beroende på random
    }
    @Test
    void testAddCompetitorEmptyNameFlaky() throws Exception {
        MvcResult result = mvc.perform(post("/com/example/decathlon/api/competitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertTrue(status == 400 || status == 201, "Expected status 400 or 201, but got: " + status);
    }

}