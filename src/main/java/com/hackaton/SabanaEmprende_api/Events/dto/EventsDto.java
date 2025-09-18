package com.hackaton.SabanaEmprende_api.Events.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class EventsDto {

    private UUID id; // opcional, útil en respuestas pero no en creación

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotBlank(message = "La ubicación no puede estar vacía")
    private String location;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String category;

    @NotNull(message = "El ID del autor es obligatorio")
    private UUID authorId;

    @FutureOrPresent(message = "La fecha debe ser presente o futura")
    private LocalDateTime datePublished;
}
