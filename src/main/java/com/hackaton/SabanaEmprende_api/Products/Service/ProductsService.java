package com.hackaton.SabanaEmprende_api.Products.Service;

import com.hackaton.SabanaEmprende_api.Common.Service.R2StorageService;
import com.hackaton.SabanaEmprende_api.Companies.Models.CompaniesModel;
import com.hackaton.SabanaEmprende_api.Companies.Service.CompaniesService;
import com.hackaton.SabanaEmprende_api.Products.Model.ProductsModel;
import com.hackaton.SabanaEmprende_api.Products.Repository.ProductsRepository;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsDto;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CompaniesService companiesService;
    private final R2StorageService r2StorageService;

    private List<String> uploadImagesProducts(List<MultipartFile> files) {
        return files.stream()
                .map(file -> {
                    try {
                        String baseName = file.getOriginalFilename()
                                .trim()
                                .replaceAll("\\s+", "_");
                        String newName = UUID.randomUUID() + "-" + baseName;
                        return r2StorageService.upload(file, newName);
                    } catch (IOException e) {
                        throw new RuntimeException(
                                "Error subiendo archivo " + file.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());
    }


    public ProductsResDto createProduct(@Valid ProductsDto dto) {
        //subir imagenes de productos
        CompaniesModel companiesModel = companiesService.getCompanies(dto.getCompanyId());
        List<String> imagesUrl =  uploadImagesProducts(dto.getImages());
        ProductsModel productsModel = new ProductsModel().fromDto(dto);
        productsModel.setImages(imagesUrl);
        productsModel.setCompanies(companiesModel);
        return productsRepository.save(productsModel).toDto();
    }

    public Page<ProductsResDto> getAllProducts(Pageable pageable) {
        return productsRepository.findAll(pageable).map(ProductsModel::toDto);
    }

    public ProductsResDto updateProduct(@Valid ProductsDto dto, UUID id) {
        ProductsModel productsModel = productsRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Product not found") );
        productsModel.fromDto(dto);
        return productsRepository.save(productsModel).toDto();
    }
}
