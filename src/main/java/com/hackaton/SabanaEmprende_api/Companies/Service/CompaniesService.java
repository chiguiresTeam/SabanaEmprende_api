package com.hackaton.SabanaEmprende_api.Companies.Service;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesSector;
import com.hackaton.SabanaEmprende_api.Companies.Repository.CompaniesRepository;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesResDto;
import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationLevelModel;
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
    private final FormalizationLevelService formalizationLevelService;

    public CompaniesResDto createCompanies(@Valid CompaniesDto dto){
        if(companiesRepository.findByRut(dto.getRut()).isPresent()){
            throw new RuntimeException("Companies with Rut " + dto.getRut() + " already exists");
        }
        CompaniesModel companiesModel = new CompaniesModel().fromDto(dto);
        CompaniesSector sector = companiesSectorService.getSectorById(dto.getSectorId());
        FormalizationLevelModel formalizationLevel = formalizationLevelService.getFormalizationLevel(dto.getFormalizationId());
        companiesModel.setSector(sector);
        companiesModel.setFormalizationLevel(formalizationLevel);
        return companiesRepository.save(companiesModel).toDto();
    }

    public Page<CompaniesResDto> getAllCompanies(Pageable pageable){
        return companiesRepository.findAll(pageable).map(CompaniesModel::toDto);
    }

    public CompaniesResDto updateCompanies(@Valid CompaniesDto dto, UUID id){
        CompaniesModel companiesModel = companiesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Companies not found")
        );
        companiesModel.fromDto(dto);
        return companiesRepository.save(companiesModel).toDto();
    }

    public void deleteCompanies(UUID id){
        CompaniesModel companiesModel = companiesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Companies not found")
        );
        companiesRepository.delete(companiesModel);
    }

    public CompaniesModel getCompanies(UUID id){
        return companiesRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Companies not found")
        );
    }
}
