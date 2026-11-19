package com.relationship.jpa.lab3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab3")
public class Lab3Controller {
    public record CreatePerson(String name, String passportNumber) { }
    public record PersonDto(Long id, String name, Long passportId, String passportNumber) { }
    public record PassportDto(Long id, String passportNumber, String ownerName) { }
    private final Lab3Service service;
    Lab3Controller(Lab3Service service) {
        this.service = service;
    }
    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@RequestBody CreatePerson request) {
        return service.create(request);
    }
    @GetMapping("/people/{id}")
    public PersonDto person(@PathVariable Long id) {
        return service.person(id);
    }
    @GetMapping("/passports/{id}")
    public PassportDto passport(@PathVariable Long id) {
        return service.passport(id);
    }
    @DeleteMapping("/people/{id}/passport")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revoke(@PathVariable Long id) {
        service.revokePassport(id);
    }
}