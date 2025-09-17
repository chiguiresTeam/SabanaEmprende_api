package com.hackaton.SabanaEmprende_api.Auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Respuesta de inicio de sesión")
public class SignInResDTO {

    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com")
    private String email;

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String fullName;

    @Schema(description = "ID de la persona", example = "123")
    private Long personId;

    @Schema(description = "Rol del usuario", example = "ADMIN")
    private String role;
}