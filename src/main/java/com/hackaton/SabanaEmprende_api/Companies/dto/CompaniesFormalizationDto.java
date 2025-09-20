package com.hackaton.SabanaEmprende_api.Companies.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CompaniesFormalizationDto {
    private Integer id;
    private Boolean isDone;
    private UUID companyId;

    private Long formalizationProcessId;
    private String formalizationTitle;
    private String formalizationDescription;
}

