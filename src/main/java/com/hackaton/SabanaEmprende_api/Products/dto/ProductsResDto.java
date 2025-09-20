package com.hackaton.SabanaEmprende_api.Products.dto;

import com.hackaton.SabanaEmprende_api.Companies.dto.CompaniesResDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductsResDto {
    private String name, description;
    private List<String> images;
    private BigDecimal price;
    private Long stock;
    private CompaniesResDto company;
}