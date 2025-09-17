package com.hackaton.SabanaEmprende_api.Companies.Service;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesSector;
import com.hackaton.SabanaEmprende_api.Companies.Repository.CompaniesRepository;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompaniesService {
    private final CompaniesRepository companiesRepository;
    private final CompaniesSectorService companiesSectorService;
    public CompaniesModel createCompanies(@Valid CompaniesDto dto){
        CompaniesModel companiesModel = new CompaniesModel().fromDto(dto);
        CompaniesSector sector = companiesSectorService.getSectorById(dto.getSectorId());
        companiesModel.setSector(sector);
        return companiesRepository.save(companiesModel);
    }

    public Page<CompaniesModel> getAllCompanies(Pageable pageable){
        return companiesRepository.findAll(pageable);
    }

    public CompaniesModel updateCompanies(@Valid CompaniesDto dto, UUID id){
        CompaniesModel companiesModel = companiesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Companies not found")
        );
        companiesModel.fromDto(dto);
        return companiesRepository.save(companiesModel);
    }

    public void deleteCompanies(UUID id){
        CompaniesModel companiesModel = companiesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Companies not found")
        );
        companiesRepository.delete(companiesModel);
    }
}
