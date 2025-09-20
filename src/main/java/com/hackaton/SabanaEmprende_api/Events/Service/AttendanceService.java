package com.hackaton.SabanaEmprende_api.Events.Service;

import com.hackaton.SabanaEmprende_api.Events.Model.AttendanceModel;
import com.hackaton.SabanaEmprende_api.Events.Model.EventsModel;
import com.hackaton.SabanaEmprende_api.Events.Repository.AttendanceRepository;
import com.hackaton.SabanaEmprende_api.Events.dto.AttendanceDto;
import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import com.hackaton.SabanaEmprende_api.People.Service.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final PeopleService peopleService;
    private final EventsService eventsService;
    public AttendanceModel markAttendance(@Valid AttendanceDto dto){
        AttendanceModel attendanceModel = new AttendanceModel();
        PeopleModel people = peopleService.getPeopleById(dto.getPersonId());
        EventsModel event = eventsService.getEventById(dto.getEventId());
        attendanceModel.setPeople(people);
        attendanceModel.setEvents(event);
        return attendanceRepository.save(attendanceModel);
    }
}
