package com.hackaton.SabanaEmprende_api.Events.Repository;

import com.hackaton.SabanaEmprende_api.Events.Model.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventsRepository extends JpaRepository<EventsModel, UUID> {
}
