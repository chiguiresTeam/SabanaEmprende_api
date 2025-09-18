package com.hackaton.SabanaEmprende_api.Events.Model;

import com.hackaton.SabanaEmprende_api.Events.dto.EventsDto;
import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter @Setter
public class EventsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private String location;
    private String category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private PeopleModel people;

    public LocalDateTime datePublished;

    public EventsModel fromDto(EventsDto eventsDto) {
        this.title = eventsDto.getTitle();
        this.description = eventsDto.getDescription();
        this.location = eventsDto.getLocation();
        this.category = eventsDto.getCategory();
        this.datePublished = eventsDto.getDatePublished();
        return this;
    }
}
