package com.hackaton.SabanaEmprende_api.Auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Datos para registro de usuario")
public class SingUpDto {

    @NotNull(message = "falta indicar la persona")
    @Schema(description = "ID de la persona relacionada", example = "123")
    private Long peopleId;

    @NotBlank(message = "Email no proporcionado")
    @Email
    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com")
    private String email;

    @NotEmpty(message = "Contraseña vacia")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+\\-={}:;\"',.<>/?]{8,}$",
            message = "La contraseña debe tener mínimo 8 caracteres, con al menos una letra y un número"
    )
    @Schema(description = "Contraseña segura", example = "Password123!")
    private String password;

    @NotNull(message = "Rol no provisto")
    @Schema(description = "ID del rol del usuario", example = "2")
    private Integer rolUserId;
}