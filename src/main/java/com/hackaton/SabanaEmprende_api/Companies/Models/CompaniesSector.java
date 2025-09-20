package com.hackaton.SabanaEmprende_api.Companies.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies_sector")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompaniesSector {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CompaniesModel> companiesModel;
}
