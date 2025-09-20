package com.hackaton.SabanaEmprende_api.Products.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsDto;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsResDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    private Long stock;
    @Column(columnDefinition = "text[]")
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "companies_id")
    @JsonBackReference
    private CompaniesModel companies;

    public ProductsModel fromDto(ProductsDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
        return this;
    }

    public ProductsResDto toDto() {
        ProductsResDto dto = new ProductsResDto();
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setStock(this.stock);
        dto.setImages(this.images);
        dto.setCompany(companies.toDto());
        return dto;
    }
}
