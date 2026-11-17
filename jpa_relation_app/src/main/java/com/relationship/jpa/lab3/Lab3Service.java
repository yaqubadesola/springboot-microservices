package com.relationship.jpa.lab3;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
class Lab3Service {
    private final PersonRepository people;
    private final PassportRepository passports;
    Lab3Service(PersonRepository people, PassportRepository passports) {
        this.people = people;
        this.passports = passports;
    }
    @Transactional
    public Lab3Controller.PersonDto create(Lab3Controller.CreatePerson request) {
        Person person = new Person(request.name());
        person.setPassport(new Passport(request.passportNumber()));
        return toDto(people.save(person));
    }
    @Transactional(readOnly = true)
    public Lab3Controller.PersonDto person(Long id) {
        return toDto(findPerson(id));
    }
    @Transactional(readOnly = true)
    public Lab3Controller.PassportDto passport(Long id) {
        Passport p = passports.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Passport " + id + " not found"));
        String owner = p.getPerson() == null ? null : p.getPerson().getName();
        return new Lab3Controller.PassportDto(p.getId(), p.getPassportNumber(), owner);
    }
    /** Cutting the link makes the passport an orphan; orphanRemoval deletes its row. */
    @Transactional
    public void revokePassport(Long personId) {
        findPerson(personId).setPassport(null);
    }
    private Person findPerson(Long id) {
        return people.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Person " + id + " not found"));
    }

    private Lab3Controller.PersonDto toDto(Person p) {
        Passport pp = p.getPassport();
        return new Lab3Controller.PersonDto(p.getId(), p.getName(),
                pp ==  null ? null : pp.getId(),
                pp ==  null ? null : pp.getPassportNumber());
    }
}

