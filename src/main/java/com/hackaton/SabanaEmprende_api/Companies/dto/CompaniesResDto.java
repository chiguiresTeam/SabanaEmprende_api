package com.hackaton.SabanaEmprende_api.Companies.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class CompaniesResDto {
    private UUID id;
    private String name, description, address, phone, sector, formalizacion, rut;
    private BigDecimal latitude, longitude;
    private Date registrationDate;
}
