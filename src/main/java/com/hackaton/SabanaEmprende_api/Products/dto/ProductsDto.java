package com.hackaton.SabanaEmprende_api.Products.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductsDto {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private BigDecimal price;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Long stock;

    @NotEmpty(message = "Debe proporcionar al menos una imagen")
    private List<@NotNull(message = "Cada archivo de imagen es obligatorio") MultipartFile> images;

    @NotNull(message = "Debe indicar la empresa propietaria")
    private UUID companyId;
}
