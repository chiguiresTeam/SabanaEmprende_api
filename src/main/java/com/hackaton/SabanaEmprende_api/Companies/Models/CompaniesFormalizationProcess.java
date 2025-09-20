package com.hackaton.SabanaEmprende_api.Companies.Models;

import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationProcess;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="companies_formalization")
@Getter
@Setter
public class CompaniesFormalizationProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "companies_id")
    private CompaniesModel companiesModel;

    @ManyToOne
    @JoinColumn(name = "formalization_process_id")
    private FormalizationProcess formalizationProcess;
}