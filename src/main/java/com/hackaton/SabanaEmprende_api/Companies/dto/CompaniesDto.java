package com.hackaton.SabanaEmprende_api.Companies.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CompaniesDto {

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 200, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9\\-\\+]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos")
    private String phone;

    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin(value = "-90.0", message = "La latitud mínima es -90.0")
    @DecimalMax(value = "90.0", message = "La latitud máxima es 90.0")
    private BigDecimal latitude;

    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin(value = "-180.0", message = "La longitud mínima es -180.0")
    @DecimalMax(value = "180.0", message = "La longitud máxima es 180.0")
    private BigDecimal longitude;

    @NotNull(message = "El sector es obligatorio")
    private Integer sectorId;

    @NotNull(message = "Indicar la formalización es obligatorio")
    private Integer formalizationId;

    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    private Date registrationDate;

    // Opcional: solo validar si existe
    @Size(min = 8, max = 20, message = "El RUT debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^[0-9]+(-[0-9])?$", message = "El RUT debe ser numérico, con dígito de verificación opcional")
    private String rut;
}
