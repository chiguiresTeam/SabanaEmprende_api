package com.hackaton.SabanaEmprende_api.People.Service;

import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import com.hackaton.SabanaEmprende_api.People.Repository.PeopleRepository;
import com.hackaton.SabanaEmprende_api.People.dto.PeopleDto;
import com.hackaton.SabanaEmprende_api.People.dto.UpdatePeopleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    public PeopleModel createPeople(@Valid PeopleDto dto){
        PeopleModel people = new PeopleModel().fromDto(dto);
        return peopleRepository.save(people);
    }

    public PeopleModel updatePeople(@Valid UpdatePeopleDto dto, Long cc){
        PeopleModel people = peopleRepository.findById(cc).orElseThrow(
                () -> new RuntimeException("People not found")
        );
        people.fromDto(dto);
        return peopleRepository.save(people);
    }

    public void deletePeople(@Valid PeopleDto dto){
        PeopleModel people = peopleRepository.findById(dto.getCc()).orElseThrow();
        peopleRepository.delete(people);
    }

    public Page<PeopleModel> getAllPeople(Pageable pageable){
        return peopleRepository.findAll(pageable);
    }

    public PeopleModel getPeopleById(Long cc){
        return peopleRepository.findById(cc).orElseThrow(
                () -> new RuntimeException("People not found")
        );
    }
}
