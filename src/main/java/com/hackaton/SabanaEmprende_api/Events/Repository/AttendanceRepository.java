package com.hackaton.SabanaEmprende_api.Events.Repository;

import com.hackaton.SabanaEmprende_api.Events.Model.AttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<AttendanceModel, UUID> {
}
