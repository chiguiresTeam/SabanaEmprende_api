package com.hackaton.SabanaEmprende_api.Formalization;

import jakarta.persistence.*;

@Entity
@Table(name = "formalization_level")
public class FormalizationLevelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
}
