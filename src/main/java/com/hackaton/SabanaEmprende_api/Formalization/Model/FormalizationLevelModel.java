package com.hackaton.SabanaEmprende_api.Formalization.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "formalization_level")
@Getter @Setter
public class FormalizationLevelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
}