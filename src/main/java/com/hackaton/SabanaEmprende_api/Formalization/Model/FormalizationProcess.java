package com.hackaton.SabanaEmprende_api.Formalization.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "formalization_process")
@Getter @Setter
public class FormalizationProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;

    private String detailsRenovation;

    private String ministery;
    private String ministeryLink;

    @ManyToOne
    @JoinColumn(name = "categories_formalization_id")
    private CategoriesFormalization categoriesFormalization;
}
