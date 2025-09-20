package com.hackaton.SabanaEmprende_api.Companies.Controller;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesFormalizationProcess;
import com.hackaton.SabanaEmprende_api.Companies.Service.CompaniesFormalizationService;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesFormalizationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/formalization")
@RequiredArgsConstructor
public class CompaniesControllerFormalization {
    private final CompaniesFormalizationService companiesFormalizationService;

    @PostMapping
    public ResponseEntity<CompaniesFormalizationProcess> formalizationProcess(@RequestBody CompaniesFormalizationDto dto) {
        CompaniesFormalizationProcess companiesFormalizationProcess = companiesFormalizationService.markStatus(dto);
        return new ResponseEntity<>(companiesFormalizationProcess, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompaniesFormalizationProcess>> getFormalizationProcess() {
        List<CompaniesFormalizationProcess> response = companiesFormalizationService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
