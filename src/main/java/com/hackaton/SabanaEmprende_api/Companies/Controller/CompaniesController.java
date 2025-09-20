package com.hackaton.SabanaEmprende_api.Companies.Controller;

import com.hackaton.SabanaEmprende_api.Companies.Service.CompaniesService;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesResDto;
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
    public ResponseEntity<CompaniesResDto> createCompanies(@RequestBody CompaniesDto dto) {
        CompaniesResDto companiesRes = companiesService.createCompanies(dto);
        return ResponseEntity.created(URI.create("/companies/" + companiesRes.getId())).body(companiesRes);
    }

    @GetMapping
    public ResponseEntity<Page<CompaniesResDto>> getCompanies(Pageable pageable){
        Page<CompaniesResDto> companies = companiesService.getAllCompanies(pageable);
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompaniesResDto> updateCompanies(@RequestBody CompaniesDto dto, @PathVariable("id") UUID id) {
        CompaniesResDto companiesRes = companiesService.updateCompanies(dto, id);
        return ResponseEntity.ok(companiesRes);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompanies(@RequestParam UUID id) {
        companiesService.deleteCompanies(id);
        return ResponseEntity.noContent().build();
    }
}
