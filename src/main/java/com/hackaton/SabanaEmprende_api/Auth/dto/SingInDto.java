package com.hackaton.SabanaEmprende_api.Auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Datos para iniciar sesión")
public class SingInDto {

    @NotBlank(message = "Email no proporcionado")
    @Email
    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com")
    private String email;

    @NotEmpty(message = "No se proporciono ninguna contraseña")
    @Schema(description = "Contraseña del usuario", example = "Password123!")
    private String password;
}