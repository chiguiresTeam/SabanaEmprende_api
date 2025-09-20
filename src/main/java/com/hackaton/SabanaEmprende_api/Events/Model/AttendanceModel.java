package com.hackaton.SabanaEmprende_api.Events.Model;

import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "atendance_events")
@Getter @Setter
public class AttendanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private PeopleModel people;

    @ManyToOne
    @JoinColumn(name="event_id")
    private EventsModel events;
}