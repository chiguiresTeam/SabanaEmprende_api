package com.hackaton.SabanaEmprende_api.Events.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class AttendanceDto {
    private Long personId;
    private UUID eventId;
}
