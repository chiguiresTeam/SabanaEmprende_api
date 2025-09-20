package com.hackaton.SabanaEmprende_api.Events.Service;

import com.hackaton.SabanaEmprende_api.Events.Model.EventsModel;
import com.hackaton.SabanaEmprende_api.Events.Repository.EventsRepository;
import com.hackaton.SabanaEmprende_api.Events.dto.EventsDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventsService {
    private final EventsRepository eventsRepository;

    public EventsModel createEvent(@Valid EventsDto eventsDto) {
        EventsModel eventsModel = new EventsModel().fromDto(eventsDto);
        return eventsRepository.save(eventsModel);
    }

    public Page<EventsModel> getEvents(Pageable pageable) {
        return eventsRepository.findAll(pageable);
    }

    public EventsModel updateEvent(@Valid EventsDto eventsDto, UUID id) {
        EventsModel eventsModel = eventsRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException( "Event not found with id " + id ))
                .fromDto(eventsDto);
        return eventsRepository.save(eventsModel);
    }

    public void deleteEvent(UUID id) {
        eventsRepository.deleteById(id);
    }
}
