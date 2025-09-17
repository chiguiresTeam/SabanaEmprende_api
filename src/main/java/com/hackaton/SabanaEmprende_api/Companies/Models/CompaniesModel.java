package com.hackaton.SabanaEmprende_api.Companies.Models;

import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import com.hackaton.SabanaEmprende_api.Formalization.FormalizationLevelModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class CompaniesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name, description, address, phone;
    private BigDecimal latitude, longitude;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private CompaniesSector sector;

    @ManyToOne
    @JoinColumn(name = "formalization_level_id")
    private FormalizationLevelModel formalizationLevel;
    private Date registrationDate;

    public CompaniesModel fromDto(CompaniesDto dto){
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.registrationDate = dto.getRegistrationDate();
        return this;
    }

}
