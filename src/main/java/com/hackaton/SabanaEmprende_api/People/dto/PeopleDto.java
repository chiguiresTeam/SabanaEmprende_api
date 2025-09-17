package com.hackaton.SabanaEmprende_api.People.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleDto {
    @NotNull(message = "cc no puede ser nulo")
    @Positive(message = "cc debe ser un número positivo")
    @Min(value = 100000, message = "cc debe tener al menos 6 dígitos")
    @Max(value = 999999999999L, message = "cc debe tener como máximo 12 dígitos")
    private Long cc;

    @NotBlank(message = "firstName es obligatorio")
    @Size(min = 2, max = 100, message = "firstName debe tener entre 2 y 100 caracteres")
    private String firstName;

    @NotBlank(message = "lastName es obligatorio")
    @Size(min = 2, max = 100, message = "lastName debe tener entre 2 y 100 caracteres")
    private String lastName;

    @NotBlank(message = "phone es obligatorio")
    @Pattern(regexp = "^\\d{7,10}$",
            message = "phone debe tener entre 7 y 10 dígitos, solo números")
    private String phone;

    @NotNull(message = "typePeopleId es obligatorio")
    @Positive(message = "typePeopleId debe ser un número positivo")
    private Long typePeopleId;
}
