package com.hackaton.SabanaEmprende_api.Companies.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesDto;
import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesResDto;
import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationLevelModel;
import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import com.hackaton.SabanaEmprende_api.Products.Model.ProductsModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class CompaniesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name, description, address, phone,rut;
    private BigDecimal latitude, longitude;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    @JsonBackReference
    private CompaniesSector sector;

    @ManyToOne
    @JoinColumn(name = "formalization_level_id")
    private FormalizationLevelModel formalizationLevel;
    private Date registrationDate;

    @OneToMany(mappedBy = "companies", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProductsModel> productsModelList;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PeopleModel people;

    public CompaniesModel fromDto(CompaniesDto dto){
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.registrationDate = dto.getRegistrationDate();
        this.rut = dto.getRut();
        return this;
    }

    public CompaniesResDto toDto(){
        CompaniesResDto dto = new CompaniesResDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setAddress(address);
        dto.setPhone(phone);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setRegistrationDate(registrationDate);
        dto.setRut(rut);
        dto.setFormalizacion(formalizationLevel.getName());
        dto.setSector(sector.getName());
        return dto;
    }

}
