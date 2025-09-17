package com.hackaton.SabanaEmprende_api.Companies.dto;

import com.hackaton.SabanaEmprende_api.Formalization.FormalizationLevelModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CompaniesDto {

    private String name, description, address, phone;
    private BigDecimal latitude, longitude;

    private Integer sectorId;

    private FormalizationLevelModel formalizationLevel;
    private Date registrationDate;
}
