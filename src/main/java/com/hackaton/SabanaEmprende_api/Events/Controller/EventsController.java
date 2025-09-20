package com.hackaton.SabanaEmprende_api.Events.Controller;

import com.hackaton.SabanaEmprende_api.Events.Model.EventsModel;
import com.hackaton.SabanaEmprende_api.Events.Service.EventsService;
import com.hackaton.SabanaEmprende_api.Events.dto.EventsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {
    private final EventsService eventsService;

    @PostMapping
    public ResponseEntity<EventsModel> addEvent(@RequestBody EventsDto eventsDto) {
        EventsModel eventsModel = eventsService.createEvent(eventsDto);
        return ResponseEntity.created(URI.create("/events"+eventsDto.getId().toString())).body(eventsModel);
    }

    @GetMapping
    public ResponseEntity<Page<EventsModel>> getAllEvents(Pageable pageable) {
        return ResponseEntity.ok().body(eventsService.getEvents(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventsModel> updateEvent(@RequestBody EventsDto eventsDto, @PathVariable UUID id) {
        EventsModel eventsModel = eventsService.updateEvent(eventsDto, id);
        return ResponseEntity.ok().body(eventsModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable UUID id) {
        eventsService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
