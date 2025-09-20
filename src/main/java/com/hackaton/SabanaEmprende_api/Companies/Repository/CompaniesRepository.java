package com.hackaton.SabanaEmprende_api.Companies.Repository;

import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompaniesRepository extends JpaRepository<CompaniesModel, UUID> {
    Optional<CompaniesModel> findByRut(String rut);
}
