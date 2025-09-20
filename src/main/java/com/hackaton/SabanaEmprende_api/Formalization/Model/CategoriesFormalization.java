package com.hackaton.SabanaEmprende_api.Formalization.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories_formalization")
@Getter @Setter
public class CategoriesFormalization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String name;
}
