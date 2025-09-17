package com.hackaton.SabanaEmprende_api.Companies.Service;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesSector;
import com.hackaton.SabanaEmprende_api.Companies.Repository.CompaniesSectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompaniesSectorService {
    private final CompaniesSectorRepository companiesSectorRepository;

    public CompaniesSector getSectorById(Integer id) {
        return companiesSectorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Sector not found")
        );
    }
}
