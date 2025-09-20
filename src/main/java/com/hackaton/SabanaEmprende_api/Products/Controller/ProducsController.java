package com.hackaton.SabanaEmprende_api.Products.Controller;

import com.hackaton.SabanaEmprende_api.Products.Service.ProductsService;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsDto;
import com.hackaton.SabanaEmprende_api.Products.dto.ProductsResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProducsController {
    private final ProductsService productsService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProductsResDto> addProduct(@ModelAttribute ProductsDto dto) {
        ProductsResDto productsResDtos = productsService.createProduct(dto);
        return new ResponseEntity<>(productsResDtos, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProductsResDto>> getProducts(Pageable pageable) {
        Page<ProductsResDto> productsResDtos = productsService.getAllProducts(pageable);
        return new ResponseEntity<>(productsResDtos, HttpStatus.OK);
    }



}
