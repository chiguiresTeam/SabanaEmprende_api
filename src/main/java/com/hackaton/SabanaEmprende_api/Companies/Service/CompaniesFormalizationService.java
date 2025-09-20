package com.hackaton.SabanaEmprende_api.Companies.Service;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesFormalizationProcess;
import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import com.hackaton.SabanaEmprende_api.Companies.Repository.CompaniesFormalizationProcessRepository;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesFormalizationDto;
import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationProcess;
import com.hackaton.SabanaEmprende_api.Formalization.Service.FormalizationProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompaniesFormalizationService {
    private final CompaniesFormalizationProcessRepository formalizationProcessRepository;
    private final CompaniesService companiesService;
    private final FormalizationProcessService formalizationProcessService;

    public CompaniesFormalizationProcess markStatus(@Valid CompaniesFormalizationDto dto){
        CompaniesFormalizationProcess process = new CompaniesFormalizationProcess();
        FormalizationProcess formalizationProcess = formalizationProcessService.getProcessById(dto.getFormalizationProcessId());
        CompaniesModel company = companiesService.getCompanies(dto.getCompanyId());
        process.setFormalizationProcess(formalizationProcess);
        process.setCompaniesModel(company);
        process.setIsDone(dto.getIsDone());
        return formalizationProcessRepository.save(process);
    }

    public List<CompaniesFormalizationProcess> getAll(){
        return formalizationProcessRepository.findAll();
    }
}
