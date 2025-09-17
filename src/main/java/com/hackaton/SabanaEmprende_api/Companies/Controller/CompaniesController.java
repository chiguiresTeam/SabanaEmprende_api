package com.hackaton.SabanaEmprende_api.Companies.Controller;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import com.hackaton.SabanaEmprende_api.Companies.Service.CompaniesService;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompaniesController {
    private final CompaniesService companiesService;
    @PostMapping
    public ResponseEntity<CompaniesModel> createCompanies(@RequestBody CompaniesDto dto) {
        CompaniesModel companiesModel = companiesService.createCompanies(dto);
        return ResponseEntity.created(URI.create("/companies/" + companiesModel.getId())).body(companiesModel);
    }

    @GetMapping
    public ResponseEntity<Page<CompaniesModel>> getCompanies(Pageable pageable){
        Page<CompaniesModel> companies = companiesService.getAllCompanies(pageable);
        return ResponseEntity.ok(companies);
    }

    @PutMapping
    public ResponseEntity<CompaniesModel> updateCompanies(@RequestBody CompaniesDto dto, @RequestParam UUID id) {
        CompaniesModel companiesModel = companiesService.updateCompanies(dto, id);
        return ResponseEntity.ok(companiesModel);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompanies(@RequestParam UUID id) {
        companiesService.deleteCompanies(id);
        return ResponseEntity.noContent().build();
    }
}
