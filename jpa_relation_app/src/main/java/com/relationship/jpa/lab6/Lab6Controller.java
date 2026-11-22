package com.relationship.jpa.lab6;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lab6")
public class Lab6Controller {
    public record FetchReport(String strategy, int authors, int books, long sqlStatements) { }
    private final Lab6Service service;
    Lab6Controller(Lab6Service service) {
        this.service = service;
    }
    @PostMapping("/seed")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Long> seed() {
        return Map.of("totalAuthors", service.seed());
    }
    @GetMapping("/lazy-error")
    public Map<String, String> lazyError() {
        return Map.of("result", service.lazyError());
    }
    @GetMapping("/n-plus-one")
    public FetchReport nPlusOne() {
        return service.nPlusOne();
    }
    @GetMapping("/join-fetch")
    public FetchReport joinFetch() {
        return service.joinFetch();
    }
    @GetMapping("/entity-graph")
    public FetchReport entityGraph() {
        return service.entityGraph();
    }
}
