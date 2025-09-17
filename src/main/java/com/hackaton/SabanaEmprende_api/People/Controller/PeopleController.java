package com.hackaton.SabanaEmprende_api.People.Controller;

import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import com.hackaton.SabanaEmprende_api.People.Service.PeopleService;
import com.hackaton.SabanaEmprende_api.People.dto.PeopleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PeopleService peopleService;
    @PostMapping
    public ResponseEntity<PeopleModel> addPeople(@RequestBody PeopleDto dto) {
        PeopleModel people = peopleService.createPeople(dto);
        return ResponseEntity.created(URI.create("/people/"+people.getCc())).body(people);
    }

    @GetMapping
    public ResponseEntity<Page<PeopleModel>> getPeople(Pageable pageable) {
        Page<PeopleModel> peopleModel = peopleService.getAllPeople(pageable);
        return ResponseEntity.ok(peopleModel);
    }

    @PutMapping
    public ResponseEntity<PeopleModel> updatePeople(@RequestBody PeopleDto dto) {
        return ResponseEntity.ok(peopleService.updatePeople(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePeople(@RequestBody PeopleDto dto) {
        peopleService.deletePeople(dto);
        return ResponseEntity.noContent().build();
    }
}