package com.hackaton.SabanaEmprende_api.Products.Repository;

import com.hackaton.SabanaEmprende_api.Products.Model.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<ProductsModel, UUID> {
}
